package com.example.demo.service.impl;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.service.RepeatOffenderRecordService;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RepeatOffenderRecordServiceImpl implements RepeatOffenderRecordService {

    private final StudentProfileRepository studentRepo;
    private final IntegrityCaseRepository caseRepo;
    private final RepeatOffenderRecordRepository recordRepo;

    public RepeatOffenderRecordServiceImpl(StudentProfileRepository studentRepo,
                                           IntegrityCaseRepository caseRepo,
                                           RepeatOffenderRecordRepository recordRepo) {
        this.studentRepo = studentRepo;
        this.caseRepo = caseRepo;
        this.recordRepo = recordRepo;
    }

    @Override
    public RepeatOffenderRecord recalculateForStudent(Long studentId) {

        StudentProfile profile = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        List<IntegrityCase> cases =
                caseRepo.findByStudentProfile_Id(studentId);

        int total = cases.size();
        String severity =
                total >= 4 ? "HIGH" :
                total >= 2 ? "MEDIUM" : "LOW";

        RepeatOffenderRecord record =
                recordRepo.findByStudentProfile(profile)
                        .orElse(new RepeatOffenderRecord());

        record.setStudentProfile(profile);
        record.setTotalCases(total);
        record.setFlagSeverity(severity);

        profile.setRepeatOffender(total >= 2);
        studentRepo.save(profile);

        return recordRepo.save(record);
    }
}
