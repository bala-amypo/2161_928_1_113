package com.example.demo.controller;

import com.example.demo.entity.PenaltyAction;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/penalties")
public class PenaltyActionController {

    @PostMapping
    public PenaltyAction add(@RequestBody PenaltyAction penalty) {
        return penalty;
    }

    @GetMapping("/case/{caseId}")
    public List<PenaltyAction> getByCase(@PathVariable Long caseId) {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public PenaltyAction getById(@PathVariable Long id) {
        return new PenaltyAction();
    }

    @GetMapping
    public List<PenaltyAction> getAll() {
        return new ArrayList<>();
    }
}
