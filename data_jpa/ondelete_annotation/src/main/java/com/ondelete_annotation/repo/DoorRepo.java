package com.ondelete_annotation.repo;

import com.ondelete_annotation.entity.Door;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoorRepo extends JpaRepository<Door, Long> {
}
