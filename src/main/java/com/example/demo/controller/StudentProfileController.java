package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentProfileController {

    private final StudentProfileService studentService;

    public StudentProfileController(StudentProfileService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentProfile save(@RequestBody StudentProfile student) {
        return studentService.save(student);
    }

    @GetMapping
    public List<StudentProfile> getAll() {
        return studentService.getAll();
    }

    @PutMapping("/{id}/repeat")
    public StudentProfile updateRepeat(
            @PathVariable Long id,
            @RequestParam boolean repeatOffender
    ) {
        return studentService.updateRepeatStatus(id, repeatOffender);
    }
}
