package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Integer yearLevel;

    private Boolean repeatOffender = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "studentProfile")
    private List<IntegrityCase> integrityCases = new ArrayList<>();

    // getters & setters
    public Long getId() { return id; }
    public String getStudentId() { return studentId; }
    public Boolean getRepeatOffender() { return repeatOffender; }
    public Integer getYearLevel() { return yearLevel; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<IntegrityCase> getIntegrityCases() { return integrityCases; }

    public void setId(Long id) { this.id = id; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setRepeatOffender(Boolean repeatOffender) { this.repeatOffender = repeatOffender; }
    public void setYearLevel(Integer yearLevel) { this.yearLevel = yearLevel; }
}
