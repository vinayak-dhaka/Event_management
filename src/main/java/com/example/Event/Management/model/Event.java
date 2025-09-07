package com.example.Event.Management.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String name;

    private String type; // Workshop / Hackathon / Fest

    private LocalDate eventDate;

    private Integer maxSeats;

    @ManyToOne
    @JoinColumn(name = "college_id")
    private College college;
}
