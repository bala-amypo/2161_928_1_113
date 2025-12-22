package com.example.demo.controller;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cases")
public class IntegrityCaseController {

    private final IntegrityCaseService service;

    public IntegrityCaseController(IntegrityCaseService service) {
        this.service = service;
    }

    @PostMapping
    public IntegrityCase create(@RequestBody IntegrityCase c) {
        return service.createCase(c);
    }

    @PutMapping("/{id}/status")
    public IntegrityCase updateStatus(@PathVariable Long id,
                                      @RequestParam String status) {
        return service.updateCaseStatus(id, status);
    }

    @GetMapping("/student/{id}")
    public List<IntegrityCase> byStudent(@PathVariable Long id) {
        return service.getCasesByStudent(id);
    }
}
