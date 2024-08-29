package com.dto_projection;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface AddressRepository extends Repository<Address, Long> {
    List<AddressView> getAddressByState(String state);

    AddressDTO findByState(String state);

    //Interface-Based Projections
    interface AddressView {
        String getZipCode();
    }

    //Class-Based Projections
    record AddressDTO(String state, String city){}
}
