package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "repeat_offender_records")
public class RepeatOffenderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile studentProfile;

    private Integer totalCases;
    private String flagSeverity;

    public RepeatOffenderRecord() {}

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
