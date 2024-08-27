package com.pagination;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagination")
@RequiredArgsConstructor
public class PaginationController {
    private final AddressService service;

    @GetMapping
    public Page<Address> getAll() {
        return service.getPageAll(PageRequest.of(2, 5));
    }

    @GetMapping("/pageimpl")
    public Page<Address> getAllPageIMpl() {
        return service.getPageImpl(PageRequest.of(2, 5));
    }
}
