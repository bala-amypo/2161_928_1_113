package com.example.demo.repository;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IntegrityCaseRepository extends JpaRepository<IntegrityCase, Long> {

    List<IntegrityCase> findByStudentProfile(StudentProfile studentProfile);

    List<IntegrityCase> findByStudentProfile_Id(Long studentId);

    @Query("SELECT c FROM IntegrityCase c WHERE c.studentProfile.studentId = :studentIdentifier")
    List<IntegrityCase> findByStudentIdentifier(@Param("studentIdentifier") String studentIdentifier);

    @Query("SELECT c FROM IntegrityCase c WHERE c.status = :status AND c.incidentDate >= :since")
    List<IntegrityCase> findRecentCasesByStatus(
            @Param("status") String status,
            @Param("since") LocalDate since
    );

    List<IntegrityCase> findByIncidentDateBetween(LocalDate start, LocalDate end);

    List<IntegrityCase> findByStatus(String status);
}
