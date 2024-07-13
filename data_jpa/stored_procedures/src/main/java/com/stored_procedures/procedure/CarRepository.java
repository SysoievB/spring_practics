package com.stored_procedures.procedure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Procedure
    int GET_TOTAL_CARS_BY_MODEL(String model);

    @Procedure("GET_TOTAL_CARS_BY_MODEL")
    int getTotalCarsByModel(String model);

    @Procedure(procedureName = "GET_TOTAL_CARS_BY_MODEL")
    int getTotalCarsByModelProcedureName(String model);

    @Procedure(value = "GET_TOTAL_CARS_BY_MODEL")
    int getTotalCarsByModelValue(String model);
}

