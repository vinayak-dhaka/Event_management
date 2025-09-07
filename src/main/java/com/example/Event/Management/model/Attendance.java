package com.example.Event.Management.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    @OneToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    private Boolean present;

    private LocalDateTime markedAt = LocalDateTime.now();
}