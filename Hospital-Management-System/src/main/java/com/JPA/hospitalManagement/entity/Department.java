package com.JPA.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 100 )
    private String name;

    @OneToOne
    private Doctor headDoctor;

    @ManyToMany
    @JoinTable(
        name = "dpt_doctors",
        joinColumns = @JoinColumn(name = "dpt_id"),
        inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private Set<Doctor> doctors = new HashSet<>();
}
