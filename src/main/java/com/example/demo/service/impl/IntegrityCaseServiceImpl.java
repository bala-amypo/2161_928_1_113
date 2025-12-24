package com.example.demo.service.impl;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.IntegrityCaseService;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IntegrityCaseServiceImpl implements IntegrityCaseService {

    private final IntegrityCaseRepository caseRepo;
    private final StudentProfileRepository studentRepo;

    public IntegrityCaseServiceImpl(IntegrityCaseRepository caseRepo,
                                    StudentProfileRepository studentRepo) {
        this.caseRepo = caseRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public IntegrityCase createCase(IntegrityCase integrityCase) {

        if (integrityCase.getStudentProfile() == null ||
            integrityCase.getStudentProfile().getId() == null) {
            throw new IllegalArgumentException("StudentProfile is required");
        }

        StudentProfile profile = studentRepo.findById(
                integrityCase.getStudentProfile().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        integrityCase.setStudentProfile(profile);
        return caseRepo.save(integrityCase);
    }

    @Override
    public IntegrityCase updateCaseStatus(Long caseId, String newStatus) {

        IntegrityCase icase = caseRepo.findById(caseId)
                .orElseThrow(() -> new ResourceNotFoundException("Case not found"));

        icase.setStatus(newStatus);
        return caseRepo.save(icase);
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
