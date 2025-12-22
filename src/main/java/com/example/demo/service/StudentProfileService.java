package com.example.demo.service;

import com.example.demo.entity.StudentProfile;

import java.util.List;

public interface StudentProfileService {

    StudentProfile save(StudentProfile student);

    List<StudentProfile> getAll();

    StudentProfile updateRepeatStatus(Long id, boolean repeatOffender);
}
