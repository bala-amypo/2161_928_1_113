package com.example.demo.controller;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cases")
public class IntegrityCaseController {

    private final IntegrityCaseService service;

    public IntegrityCaseController(IntegrityCaseService service) {
        this.service = service;
    }

    @PostMapping
    public IntegrityCase create(@RequestBody IntegrityCase integrityCase) {
        return service.createCase(integrityCase);
    }

    @PutMapping("/{id}/status")
    public IntegrityCase updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return service.updateCaseStatus(id, status);
    }

    @GetMapping("/student/{studentId}")
    public List<IntegrityCase> getByStudent(@PathVariable Long studentId) {
        return service.getCasesByStudent(studentId);
    }

    @GetMapping("/{id}")
    public Optional<IntegrityCase> getById(@PathVariable Long id) {
        return service.getCaseById(id);
    }
}
