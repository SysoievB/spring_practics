package com.immutable_annotation;

import org.springframework.data.jpa.repository.JpaRepository;

interface AddressRepo extends JpaRepository<Address, Long> {
}
