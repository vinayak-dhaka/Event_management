package com.example.Event.Management.repository;

import com.example.Event.Management.model.Attendance;
import com.example.Event.Management.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    long countByRegistrationStudentStudentIdAndPresent(Long studentId, boolean b);

    Optional<Attendance> findByRegistration(Registration registration);
}
