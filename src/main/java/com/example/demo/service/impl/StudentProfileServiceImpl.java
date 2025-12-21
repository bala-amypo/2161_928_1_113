package com.example.demo.service.impl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.service.StudentProfileService;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentRepo;
    private final IntegrityCaseRepository caseRepo;
    private final RepeatOffenderRecordRepository recordRepo;

    public StudentProfileServiceImpl(StudentProfileRepository studentRepo,
                                     IntegrityCaseRepository caseRepo,
                                     RepeatOffenderRecordRepository recordRepo) {
        this.studentRepo = studentRepo;
        this.caseRepo = caseRepo;
        this.recordRepo = recordRepo;
    }

    @Override
    public StudentProfile createStudent(StudentProfile studentProfile) {
        studentProfile.setRepeatOffender(false);
        return studentRepo.save(studentProfile);
    }

    @Override
    public StudentProfile getStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public List<StudentProfile> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public StudentProfile updateRepeatOffenderStatus(Long studentId) {

        StudentProfile profile = getStudentById(studentId);
        List<IntegrityCase> cases =
                caseRepo.findByStudentProfile_Id(studentId);

        int totalCases = cases.size();
        boolean repeat = totalCases >= 2;

        profile.setRepeatOffender(repeat);

        RepeatOffenderRecord record =
                recordRepo.findByStudentProfile(profile)
                        .orElse(new RepeatOffenderRecord());

        record.setStudentProfile(profile);
        record.setTotalCases(totalCases);
        record.setFlagSeverity(
                totalCases >= 4 ? "HIGH" :
                totalCases >= 2 ? "MEDIUM" : "LOW"
        );

        recordRepo.save(record);
        return studentRepo.save(profile);
    }
}
