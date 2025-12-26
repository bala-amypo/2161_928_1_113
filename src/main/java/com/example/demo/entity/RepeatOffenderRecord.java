package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "repeat_offender_records")
public class RepeatOffenderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile studentProfile;

    private Integer totalCases;
    private LocalDate firstIncidentDate;
    private String flagSeverity;

    // getters & setters
    public StudentProfile getStudentProfile() { return studentProfile; }
    public Integer getTotalCases() { return totalCases; }
    public String getFlagSeverity() { return flagSeverity; }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public void setTotalCases(Integer totalCases) {
        this.totalCases = totalCases;
    }

    public void setFlagSeverity(String flagSeverity) {
        this.flagSeverity = flagSeverity;
    }
}
