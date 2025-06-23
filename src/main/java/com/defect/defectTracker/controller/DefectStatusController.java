package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.DefectStatusDTO;
import com.defect.defectTracker.service.DefectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/defectStatus")
public class DefectStatusController {

    @Autowired
    private DefectStatusService defectStatusService;

    @PostMapping
    public ResponseEntity<DefectStatusDTO> addDefectStatus(@RequestBody DefectStatusDTO dto) {
        DefectStatusDTO response = defectStatusService.createDefectStatus(dto);

        switch (response.getStatusCode()) {
            case "2001":
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            case "4000":
                return ResponseEntity.badRequest().body(response);
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}


