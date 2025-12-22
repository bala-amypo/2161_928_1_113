package com.example.demo.service.impl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository repository;

    public StudentProfileServiceImpl(StudentProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentProfile save(StudentProfile student) {
        return repository.save(student);
    }

    @Override
    public List<StudentProfile> getAll() {
        return repository.findAll();
    }

    @Override
    public StudentProfile updateRepeatStatus(Long id, boolean repeatOffender) {
        StudentProfile student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setRepeatOffender(repeatOffender);
        return repository.save(student);
    }
}
