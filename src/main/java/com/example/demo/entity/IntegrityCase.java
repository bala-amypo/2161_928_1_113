package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "integrity_cases")
public class IntegrityCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private StudentProfile studentProfile;

    private String status = "OPEN";

    private LocalDate incidentDate;
    private LocalDateTime createdAt = LocalDateTime.now();

    public IntegrityCase() {}

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
