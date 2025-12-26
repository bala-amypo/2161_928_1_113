package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RepeatOffenderRecordService;
import com.example.demo.util.RepeatOffenderCalculator;

import java.util.List;

public class RepeatOffenderRecordServiceImpl implements RepeatOffenderRecordService {

    private final StudentProfileRepository studentRepo;
    private final IntegrityCaseRepository caseRepo;
    private final RepeatOffenderRecordRepository repeatRepo;
    private final RepeatOffenderCalculator calculator;

    public RepeatOffenderRecordServiceImpl(
            StudentProfileRepository studentRepo,
            IntegrityCaseRepository caseRepo,
            RepeatOffenderRecordRepository repeatRepo,
            RepeatOffenderCalculator calculator
    ) {
        this.studentRepo = studentRepo;
        this.caseRepo = caseRepo;
        this.repeatRepo = repeatRepo;
        this.calculator = calculator;
    }

    @Override
    public RepeatOffenderRecord recalculateForStudent(Long studentId) {
        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        List<IntegrityCase> cases = caseRepo.findByStudentProfile(student);
        RepeatOffenderRecord record =
                calculator.computeRepeatOffenderRecord(student, cases);

        repeatRepo.save(record);
        student.setRepeatOffender(record.getTotalCases() >= 2);
        studentRepo.save(student);

        return record;
    }
}
