package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.DefectHistoryDto;
import com.defect.defectTracker.service.DefectHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/defect-history")
@CrossOrigin
public class DefectHistoryController {

    @Autowired
    private DefectHistoryService defectHistoryService;

    @GetMapping("/by-defect/{defectId}")
    public List<DefectHistoryDto> getDefectHistoriesByDefectId(@PathVariable Long defectId) {
        return defectHistoryService.getDefectHistoriesByDefectId(defectId);
    }
}
