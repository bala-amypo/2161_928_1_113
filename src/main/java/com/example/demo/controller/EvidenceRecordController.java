package com.example.demo.controller;

import com.example.demo.entity.EvidenceRecord;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/evidence")
public class EvidenceRecordController {

    @PostMapping
    public EvidenceRecord submit(@RequestBody EvidenceRecord record) {
        return record;
    }

    @GetMapping("/case/{caseId}")
    public List<EvidenceRecord> getByCase(@PathVariable Long caseId) {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public EvidenceRecord getById(@PathVariable Long id) {
        return new EvidenceRecord();
    }

    @GetMapping
    public List<EvidenceRecord> getAll() {
        return new ArrayList<>();
    }
}
