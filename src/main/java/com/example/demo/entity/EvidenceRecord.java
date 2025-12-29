// package com.example.demo.entity;

// import java.time.LocalDateTime;

// public class EvidenceRecord {

//     private Long id;
//     private IntegrityCase integrityCase;
//     private String evidenceType;
//     private String content;
//     private String submittedBy;
//     private LocalDateTime submittedAt = LocalDateTime.now();

//     public EvidenceRecord() {}

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public IntegrityCase getIntegrityCase() { return integrityCase; }
//     public void setIntegrityCase(IntegrityCase integrityCase) { this.integrityCase = integrityCase; }

//     public String getEvidenceType() { return evidenceType; }
//     public void setEvidenceType(String evidenceType) { this.evidenceType = evidenceType; }

//     public String getContent() { return content; }
//     public void setContent(String content) { this.content = content; }

//     public String getSubmittedBy() { return submittedBy; }
//     public void setSubmittedBy(String submittedBy) { this.submittedBy = submittedBy; }

//     public LocalDateTime getSubmittedAt() { return submittedAt; }
//     public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
// }
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evidence_records")
public class EvidenceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType;

    private String uploadedBy;

    private LocalDateTime uploadedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "integrity_case_id")
    private IntegrityCase integrityCase;

    public EvidenceRecord() {}

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public String getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(String uploadedBy) { this.uploadedBy = uploadedBy; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public IntegrityCase getIntegrityCase() { return integrityCase; }
    public void setIntegrityCase(IntegrityCase integrityCase) {
        this.integrityCase = integrityCase;
    }
}
