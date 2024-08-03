package com.pagination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepo extends PagingAndSortingRepository<Address, Long> {

    Page<Address> findAll(Pageable pageable);

    Page<Address> findByCityContaining(String city, Pageable pageable);

    @Query(value = "SELECT * FROM pagination.address WHERE city = :city LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Address> findAddressByCityWithPagination(@Param("city") String city, @Param("limit") int limit, @Param("offset") int offset);

    @Query(value = "SELECT * FROM pagination.address WHERE city = :city",
            countQuery = "SELECT count(*) FROM pagination.address WHERE city = :city",
            nativeQuery = true)
    Page<Address> findByCityWithPagination(@Param("city") String city, Pageable pageable);
}
