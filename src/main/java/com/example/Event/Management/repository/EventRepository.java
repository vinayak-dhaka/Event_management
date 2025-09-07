package com.example.Event.Management.repository;

import com.example.Event.Management.model.College;
import com.example.Event.Management.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByNameAndCollege(String techFest, College college);
}
