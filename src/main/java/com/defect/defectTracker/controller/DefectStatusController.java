package com.example.defectTracker.controller;

import com.example.defectTracker.Dto.DefectStatusRequest;
import com.example.defectTracker.Dto.DefectStatusRequest;
import com.example.defectTracker.Dto.DefectStatusResponse;
import com.example.defectTracker.service.DefectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/defectStatus")
public class DefectStatusController {

    @Autowired
    private DefectStatusService defectStatusService;

    @PostMapping
    public ResponseEntity<DefectStatusResponse> addDefectStatus(@RequestBody DefectStatusRequest defectStatusRequest) {
        DefectStatusResponse response = defectStatusService.createDefectStatus(defectStatusRequest);

        // Map status codes to HTTP statuses
        switch (response.getStatusCode()) {
            case "2001":
                return ResponseEntity.status(201).body(response); // 201 Created
            case "4000":
                return ResponseEntity.badRequest().body(response); // 400 Bad Request
            default:
                return ResponseEntity.internalServerError().body(response); // 500 Internal Server Error
        }
    }
}