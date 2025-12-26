package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.IntegrityCaseService;

import java.util.List;
import java.util.Optional;

public class IntegrityCaseServiceImpl implements IntegrityCaseService {

    private final IntegrityCaseRepository caseRepo;
    private final StudentProfileRepository studentRepo;

    public IntegrityCaseServiceImpl(
            IntegrityCaseRepository caseRepo,
            StudentProfileRepository studentRepo
    ) {
        this.caseRepo = caseRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public IntegrityCase createCase(IntegrityCase integrityCase) {
        if (integrityCase.getStudentProfile() == null) {
            throw new IllegalArgumentException("Student required");
        }

        studentRepo.findById(integrityCase.getStudentProfile().getId())
                .orElseThrow(() -> new IllegalArgumentException("Student missing"));

        integrityCase.setStatus("OPEN");
        return caseRepo.save(integrityCase);
    }

    @Override
    public IntegrityCase updateCaseStatus(Long caseId, String newStatus) {
        IntegrityCase c = caseRepo.findById(caseId)
                .orElseThrow(() -> new IllegalArgumentException("Case not found"));

        c.setStatus(newStatus);
        return caseRepo.save(c);
    }

    @Override
    public List<IntegrityCase> getCasesByStudent(Long studentId) {
        return caseRepo.findByStudentProfile_Id(studentId);
    }

    @Override
    public Optional<IntegrityCase> getCaseById(Long caseId) {
        return caseRepo.findById(caseId);
    }
}
