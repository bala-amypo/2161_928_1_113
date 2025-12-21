package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;
    private String name;
    private String email;
    private String program;

    @Column(nullable = false)
    private Integer yearLevel;

    private Boolean repeatOffender = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "studentProfile", cascade = CascadeType.ALL)
    private List<IntegrityCase> integrityCases = new ArrayList<>();

    public StudentProfile() {}

    public Long getId() {
        return id;
    }

    public Boolean getRepeatOffender() {
        return repeatOffender;
    }

    public void setRepeatOffender(Boolean repeatOffender) {
        this.repeatOffender = repeatOffender;
    }
}
