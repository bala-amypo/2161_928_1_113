package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyActionService;

public class PenaltyActionServiceImpl implements PenaltyActionService {

    private final PenaltyActionRepository penaltyRepo;
    private final IntegrityCaseRepository caseRepo;

    public PenaltyActionServiceImpl(
            PenaltyActionRepository penaltyRepo,
            IntegrityCaseRepository caseRepo
    ) {
        this.penaltyRepo = penaltyRepo;
        this.caseRepo = caseRepo;
    }

    @Override
    public PenaltyAction addPenalty(PenaltyAction action) {
        IntegrityCase c = caseRepo.findById(action.getIntegrityCase().getId())
                .orElseThrow(() -> new IllegalArgumentException("Case not found"));

        c.setStatus("UNDER_REVIEW");
        caseRepo.save(c);

        return penaltyRepo.save(action);
    }
}
