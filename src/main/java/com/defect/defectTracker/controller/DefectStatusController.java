package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.DefectStatusDto;
import com.defect.defectTracker.service.DefectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/defect-status")
public class DefectStatusController {

    @Autowired
    private DefectStatusService service;


    @PutMapping("/{id}")
    public DefectStatusDto updateDefectStatus(@PathVariable Long id, @RequestBody DefectStatusDto dto) {
        return service.updateDefectStatus(id, dto);
    }
}
