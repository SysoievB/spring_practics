package com.pagination;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepo repo;

    public Page<Address> getPageAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Page<Address> searchAddressesByCity(String city, Pageable pageable) {
        return repo.findByCityContaining(city, pageable);
    }

    public List<Address> findAddressByCityWithPagination(int limit, int offset) {
        return repo.findAddressByCityWithPagination(limit, offset);
    }

    public Page<Address> findByCityWithPagination(String city, Pageable pageable) {
        return repo.findByCityWithPagination(city, pageable);
    }
}
