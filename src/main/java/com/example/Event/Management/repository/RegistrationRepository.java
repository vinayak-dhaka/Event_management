package com.example.Event.Management.repository;

import com.example.Event.Management.model.Event;
import com.example.Event.Management.model.Registration;
import com.example.Event.Management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    long countByEventEventId(Long eventId);

    Optional<Registration> findByStudentAndEvent(Student student, Event event);
}
