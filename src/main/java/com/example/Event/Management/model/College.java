package com.example.Event.Management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "college", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collegeId;

    @Column(nullable = false)
    private String name;
}
