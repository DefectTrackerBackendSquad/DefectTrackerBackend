package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.service.DefectService;
import com.defect.defectTracker.utils.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/defect")
@RequiredArgsConstructor
public class DefectController {

    private final DefectService defectService;

    @GetMapping("/testcase/{testcaseId}")
    public ResponseEntity<ApiResponse> getDefectByTestcaseId(@PathVariable String testcaseId) {
        DefectDto dto = defectService.getDefectByTestcaseId(testcaseId);
        return ResponseEntity.ok(new ApiResponse(20000, "Retrieved Successfully", dto));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class ApiResponse {
        private int statusCode;
        private String message;
        private Object data;
    }
}