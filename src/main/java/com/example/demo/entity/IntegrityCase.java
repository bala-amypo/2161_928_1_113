package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class IntegrityCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile studentProfile;

    private String courseCode;
    private String instructorName;
    private String description;
    private String status = "OPEN";
    private LocalDate incidentDate;
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "integrityCase")
    private List<PenaltyAction> penalties = new ArrayList<>();

    // getters & setters
    public Long getId() { return id; }
    public String getStatus() { return status; }
    public StudentProfile getStudentProfile() { return studentProfile; }
    public List<PenaltyAction> getPenalties() { return penalties; }

    public void setId(Long id) { this.id = id; }
    public void setStudentProfile(StudentProfile s) { this.studentProfile = s; }
    public void setStatus(String status) { this.status = status; }
}
