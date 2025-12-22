package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")   // 🔥 FIXES SWAGGER CORS
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @PutMapping("/{id}/repeat")
    public Student updateRepeat(
            @PathVariable Long id,
            @RequestParam boolean repeatOffender
    ) {
        return studentService.updateRepeatStatus(id, repeatOffender);
    }
}
