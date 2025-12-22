package com.example.demo.controller;

import com.example.demo.entity.RepeatOffenderRecord;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/repeat-offenders")
public class RepeatOffenderRecordController {

    @PostMapping("/refresh/{studentId}")
    public RepeatOffenderRecord refresh(@PathVariable Long studentId) {
        return new RepeatOffenderRecord();
    }

    @GetMapping("/student/{studentId}")
    public RepeatOffenderRecord getByStudent(@PathVariable Long studentId) {
        return new RepeatOffenderRecord();
    }

    @GetMapping
    public List<RepeatOffenderRecord> getAll() {
        return new ArrayList<>();
    }
}
