package com.webmvctest_autoconfiguremockmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureMockMvc
class CarControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void getCars_returnsAllCars() throws Exception {
        // Given
        Car car1 = new Car("BMW", "X5", "Black");
        Car car2 = new Car("Audi", "A4", "White");
        repository.save(car1);
        repository.save(car2);

        // When & Then
        mockMvc.perform(get("/car")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].brand", is(car1.getBrand())))
                .andExpect(jsonPath("$[0].model", is(car1.getModel())))
                .andExpect(jsonPath("$[0].color", is(car1.getColor())))
                .andExpect(jsonPath("$[1].brand", is(car2.getBrand())))
                .andExpect(jsonPath("$[1].model", is(car2.getModel())))
                .andExpect(jsonPath("$[1].color", is(car2.getColor())));
    }

    @Test
    void getCars_returns_empty_list_of_cars_when_no_cars_found() throws Exception {
        //when & then
        mockMvc.perform(get("/car")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void findById_returns_car_when_car_found() throws Exception {
        //given
        val car = new Car("BMW", "X10", "Black");
        val carID = repository.save(car).getId();

        //when & then
        mockMvc.perform(get("/car/{id}", carID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is(car.getBrand())))
                .andExpect(jsonPath("$.model", is(car.getModel())))
                .andExpect(jsonPath("$.color", is(car.getColor())));
    }

    @Test
    void findById_returns_exception_when_car_not_found() throws Exception {
        //given
        val id = 100000L;
        val car = new Car("BMW", "X10", "Black");
        val carID = repository.save(car).getId();

        //when & then
        mockMvc.perform(get("/car/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    assertThat(id).isNotEqualTo(carID);
                    assertThat(result)
                            .extracting(MvcResult::getResolvedException)
                            .isInstanceOf(CarService.CarNotFoundException.class);
                });
    }

    @Test
    void save_returns_saved_car() throws Exception {
        //given
        val car = new Car("BMW", "X10", "Black");
        repository.save(car);

        val json = objectMapper.writeValueAsString(car);

        //when & then
        mockMvc.perform(post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brand", is(car.getBrand())))
                .andExpect(jsonPath("$.model", is(car.getModel())))
                .andExpect(jsonPath("$.color", is(car.getColor())));
    }

    @Test
    void delete_returns_void_when_car_found() throws Exception {
        //given
        val car = new Car("BMW", "X10", "Black");
        val id = repository.save(car).getId();
        repository.findById(id);

        //when & then
        mockMvc.perform(delete("/car/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_returns_exception_when_car_not_found() throws Exception {
        //given
        val idForDelete = 1000L;
        val car = new Car("BMW", "X10", "Black");
        val id = repository.save(car).getId();
        repository.findById(idForDelete);

        //when & then
        mockMvc.perform(delete("/car/{id}", idForDelete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    assertThat(id).isNotEqualTo(idForDelete);
                    assertThat(result)
                            .extracting(MvcResult::getResolvedException)
                            .isInstanceOf(CarService.CarNotFoundException.class);
                });
    }

    @Test
    void findByBrandAndModel_returns_car_when_car_found() throws Exception {
        //given
        val brand = "BMW";
        val model = "X10";
        val car = new Car(brand, model, "Black");
        repository.save(car);

        //when & then
        mockMvc.perform(get("/car/ByBrandAndModel")
                        .param("brand", brand)
                        .param("model", model)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is(car.getBrand())))
                .andExpect(jsonPath("$.model", is(car.getModel())))
                .andExpect(jsonPath("$.color", is(car.getColor())));
    }

    @Test
    void findByBrandAndModel_returns_exception_when_car_not_found() throws Exception {
        //given
        val brand = "BMW";
        val model = "X10";

        //when & then
        mockMvc.perform(get("/car/ByBrandAndModel")
                        .param("brand", brand)
                        .param("model", model)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertThat(result)
                                .extracting(MvcResult::getResolvedException)
                                .isInstanceOf(CarService.CarNotFoundException.class)
                );
    }

    @Test
    void update_returns_updated_car_when_fields_present() throws Exception {
        // given
        val car = new Car("BMW", "X10", "Black");
        val savedCar = repository.save(car);
        Long id = savedCar.getId();
        val carForUpdate = new UpdateCarDto("Audi", "Q5", "White");
        val json = objectMapper.writeValueAsString(carForUpdate);

        // when & then
        mockMvc.perform(put("/car/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is(carForUpdate.brand())))
                .andExpect(jsonPath("$.model", is(carForUpdate.model())))
                .andExpect(jsonPath("$.color", is(carForUpdate.color())));
    }

    @Test
    void update_returns_not_found_when_fields_present() throws Exception {
        //given
        val id = 1L;
        val car = new Car("BMW", "X10", "Black");

        val json = objectMapper.writeValueAsString(car);

        //when & then
        mockMvc.perform(put("/car/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertThat(result)
                                .extracting(MvcResult::getResolvedException)
                                .isInstanceOf(CarService.CarNotFoundException.class)
                );
    }

    @Test
    void update_returns_not_found_when_fields_absent() throws Exception {
        //given
        val id = 1L;
        val car = new Car();

        val json = objectMapper.writeValueAsString(car);

        //when & then
        mockMvc.perform(put("/car/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertThat(result)
                                .extracting(MvcResult::getResolvedException)
                                .isInstanceOf(CarService.CarNotFoundException.class)
                );
    }
}