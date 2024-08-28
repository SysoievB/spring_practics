package com.pagination;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepo repo;

    public Page<Address> getPageAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Page<Address> getPageImpl(@Nullable Pageable pageable) {
        val list = repo.findAll(pageable).stream().toList();
        return new PageImpl<>(
                list,
                Optional.ofNullable(pageable).orElse(Pageable.unpaged()),
                list.size()
        );
    }

    public Page<Address> getPageImpl2(@Nullable Pageable pageable) {
        // Fetch all data
        List<Address> list = repo.findAll(Pageable.unpaged()).getContent();

        // Apply pagination manually
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Address> pagedList;

        if (list.size() < startItem) {
            pagedList = List.of(); // Empty list if startItem is out of bounds
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            pagedList = list.subList(startItem, toIndex);
        }

        // Return the manually created Page object
        return new PageImpl<>(pagedList, pageable, list.size());
    }

    public Page<Address> getPageImpl1(@Nullable Pageable pageable) {
        // Fetch all records as a list
        val list = repo.findAll(Pageable.unpaged()).getContent();

        // Handle the case where pageable is null
        if (pageable == null) {
            pageable = Pageable.unpaged();
        }

        // If pageable is unpaged, return the full list in a PageImpl
        if (pageable.isUnpaged()) {
            return new PageImpl<>(list);
        }

        // Manually apply pagination
        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.min((start + pageable.getPageSize()), list.size());
        List<Address> pagedList = list.subList(start, end);

        // Create a new PageImpl object with the paged list and total size
        return new PageImpl<>(pagedList, pageable, list.size());
    }

    public Page<Address> searchAddressesByCity(String city, Pageable pageable) {
        return repo.findByCityContaining(city, pageable);
    }

    public List<Address> findAddressByCityWithPagination(String city, int limit, int offset) {
        return repo.findAddressByCityWithPagination(city, limit, offset);
    }

    public Page<Address> findByCityWithPagination(String city, Pageable pageable) {
        return repo.findByCityWithPagination(city, pageable);
    }
}
