package com.defect.defectTracker.controller;
import com.defect.defectTracker.dto.DefectStatusDTO;
import com.defect.defectTracker.service.DefectStatusService;
import com.defect.defectTracker.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/v1")
public class DefectStatusController {

    @Autowired
    private DefectStatusService defectStatusService;

    @DeleteMapping("/defectStatus/{defectStatusId}")
    public ResponseEntity<StandardResponse> deleteDefectStatus(@PathVariable Long defectStatusId) {
        StandardResponse response = defectStatusService.deleteDefectStatus(defectStatusId);
        if ("success".equalsIgnoreCase(response.getStatus())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }



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



