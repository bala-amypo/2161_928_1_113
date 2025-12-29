// package com.example.demo.entity;

// import java.time.LocalDateTime;

// public class PenaltyAction {

//     private Long id;
//     private IntegrityCase integrityCase;
//     private String penaltyType;
//     private String details;
//     private String issuedBy;
//     private LocalDateTime issuedAt = LocalDateTime.now();

//     public PenaltyAction() {}

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public IntegrityCase getIntegrityCase() { return integrityCase; }
//     public void setIntegrityCase(IntegrityCase integrityCase) { this.integrityCase = integrityCase; }

//     public String getPenaltyType() { return penaltyType; }
//     public void setPenaltyType(String penaltyType) { this.penaltyType = penaltyType; }

//     public String getDetails() { return details; }
//     public void setDetails(String details) { this.details = details; }

//     public String getIssuedBy() { return issuedBy; }
//     public void setIssuedBy(String issuedBy) { this.issuedBy = issuedBy; }

//     public LocalDateTime getIssuedAt() { return issuedAt; }
//     public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }
// }

package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "penalty_actions")
public class PenaltyAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "integrity_case_id")
    private IntegrityCase integrityCase;

    private String penaltyType;
    private String details;
    private String issuedBy;

    private LocalDateTime issuedAt = LocalDateTime.now();

    public PenaltyAction() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public IntegrityCase getIntegrityCase() { return integrityCase; }
    public void setIntegrityCase(IntegrityCase integrityCase) {
        this.integrityCase = integrityCase;
    }

    public String getPenaltyType() { return penaltyType; }
    public void setPenaltyType(String penaltyType) { this.penaltyType = penaltyType; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public String getIssuedBy() { return issuedBy; }
    public void setIssuedBy(String issuedBy) { this.issuedBy = issuedBy; }

    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }
}
