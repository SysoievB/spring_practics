package com.webmvctest_autoconfiguremockmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * By setting the controllers parameter to CarController.class in the example above,
 * we're telling Spring Boot to restrict the application context created for this test to the
 * given controller bean and some framework beans needed for Spring Web MVC. All other beans
 * we might need have to be included separately or mocked away with @MockBean.
 * <p>
 * If we leave away the controllers parameter, Spring Boot will include all controllers in
 * the application context. Thus, we need to include or mock away all beans any controller
 * depends on.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CarController.class)
class CarControllerIsolatedTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarService carService;

    @Test
    void getCars_returns_empty_list_of_cars_when_no_cars_found() throws Exception {
        //given
        given(carService.findAll()).willReturn(Collections.emptyList());

        //when & then
        mockMvc.perform(get("/car")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getCars_returns_list_of_cars_when_cars_found() throws Exception {
        //given
        val car1 = new Car("BMW", "X10", "Black");
        val car2 = new Car("Mersedes Benz", "300", "Red");
        given(carService.findAll()).willReturn(List.of(car1, car2));

        //when & then
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
    void findById_returns_car_when_car_found() throws Exception {
        //given
        val id = 1000L;
        val car = new Car(id, "BMW", "X10", "Black");
        given(carService.findById(anyLong())).willReturn(car);

        //when & then
        mockMvc.perform(get("/car/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is(car.getBrand())))
                .andExpect(jsonPath("$.model", is(car.getModel())))
                .andExpect(jsonPath("$.color", is(car.getColor())));
    }

    @Test
    void findById_returns_exception_when_car_not_found() throws Exception {
        //given
        val id = 1L;
        given(carService.findById(anyLong())).willThrow(new CarService.CarNotFoundException(""));

        //when & then
        mockMvc.perform(get("/car/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertThat(result)
                                .extracting(MvcResult::getResolvedException)
                                .isInstanceOf(CarService.CarNotFoundException.class)
                );
    }

    @Test
    void save_returns_saved_car() throws Exception {
        //given
        val id = 1L;
        val car = new Car(id, "BMW", "X10", "Black");
        given(carService.save(any())).willReturn(car);

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
        val id = 1000L;
        doNothing().when(carService).delete(anyLong());

        //when & then
        mockMvc.perform(delete("/car/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_returns_exception_when_car_not_found() throws Exception {
        //given
        val id = 1000L;
       doThrow(new CarService.CarNotFoundException(""))
               .when(carService).delete(anyLong());

        //when & then
        mockMvc.perform(delete("/car/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertThat(result)
                                .extracting(MvcResult::getResolvedException)
                                .isInstanceOf(CarService.CarNotFoundException.class)
                );
    }

    @Test
    void findByBrandAndModel_returns_car_when_car_found() throws Exception {
        //given
        val brand = "BMW";
        val model = "X10";
        val car = new Car(brand, model, "Black");
        given(carService.findByBrandAndModel(anyString(), anyString())).willReturn(car);

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
        given(carService.findByBrandAndModel(anyString(), anyString()))
                .willThrow(new CarService.CarNotFoundException(""));

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
        //given
        val id = 1L;
        val car = new Car("BMW", "X10", "Black");
        given(carService.update(any(), notNull(), notNull(), notNull())).willReturn(car);

        val json = objectMapper.writeValueAsString(car);

        //when & then
        mockMvc.perform(put("/car/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is(car.getBrand())))
                .andExpect(jsonPath("$.model", is(car.getModel())))
                .andExpect(jsonPath("$.color", is(car.getColor())));
    }

    @Test
    void update_returns_not_found_when_fields_present() throws Exception {
        //given
        val id = 1L;
        val car = new Car("BMW", "X10", "Black");
        given(carService.update(any(), notNull(), notNull(), notNull())).willThrow(new CarService.CarNotFoundException(""));

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
        given(carService.update(any(), isNull(), isNull(), isNull())).willThrow(new CarService.CarNotFoundException(""));

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