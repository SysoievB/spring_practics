package com.ondelete_annotation.repo;

import com.ondelete_annotation.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepo extends JpaRepository<House, Long> {
}
