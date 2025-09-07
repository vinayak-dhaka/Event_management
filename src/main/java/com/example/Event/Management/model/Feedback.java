package com.example.Event.Management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @OneToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    private Integer rating; // 1-5

    private String comment;
}