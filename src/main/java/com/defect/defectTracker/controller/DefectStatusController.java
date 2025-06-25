package com.defect.defectTracker.controller;

import com.defect.defectTracker.service.DefectStatusService;
import com.defect.defectTracker.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.defect.defectTracker.dto.DefectStatusDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/defect-status")
public class DefectStatusController {

    @Autowired
    private DefectStatusService defectStatusService;

    @PutMapping("/{id}")
    public DefectStatusDto updateDefectStatus(@PathVariable Long id, @RequestBody DefectStatusDto dto) {
        return defectStatusService.updateDefectStatus(id, dto);
    }

    @DeleteMapping("/defectStatus/{defectStatusId}")
    public ResponseEntity<StandardResponse> deleteDefectStatus(@PathVariable Long defectStatusId) {
        StandardResponse response = defectStatusService.deleteDefectStatus(defectStatusId);
        if ("success".equalsIgnoreCase(response.getStatus())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getDefectStatuses(){
        List<DefectStatusDto> defectStatusDtos = defectStatusService.getDefectStatuses();
        return(ResponseEntity.ok(new StandardResponse("Success","Retrieved successfully", defectStatusDtos,2000)));
    }
}
