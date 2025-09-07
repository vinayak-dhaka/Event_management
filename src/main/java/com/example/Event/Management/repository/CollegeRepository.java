package com.example.Event.Management.repository;

import com.example.Event.Management.model.College;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollegeRepository extends JpaRepository<College, Long> {
    Optional<College> findByName(String name);
}