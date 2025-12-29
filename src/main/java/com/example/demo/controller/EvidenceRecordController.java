// package com.example.demo.controller;

// import com.example.demo.entity.EvidenceRecord;
// import com.example.demo.service.EvidenceRecordService;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/evidence")
// public class EvidenceRecordController {

//     private final EvidenceRecordService service;

//     public EvidenceRecordController(EvidenceRecordService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public EvidenceRecord submit(@RequestBody EvidenceRecord record) {
//         return service.submitEvidence(record);
//     }
// }
package com.example.demo.controller;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.service.EvidenceRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evidence")
public class EvidenceRecordController {

    private final EvidenceRecordService service;

    public EvidenceRecordController(EvidenceRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EvidenceRecord> submitEvidence(
            @RequestBody EvidenceRecord record) {

        EvidenceRecord saved = service.addEvidence(record);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
