package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PenaltyAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private IntegrityCase integrityCase;

    private String penaltyType;
    private String details;
    private String issuedBy;
    private LocalDateTime issuedAt = LocalDateTime.now();

    public IntegrityCase getIntegrityCase() { return integrityCase; }
    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIntegrityCase(IntegrityCase c) { this.integrityCase = c; }
}
