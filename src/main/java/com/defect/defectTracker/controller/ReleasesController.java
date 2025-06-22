package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.ReleasesDto;
import com.defect.defectTracker.dto.ReleasesDto.ReleaseResponse;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import com.defect.defectTracker.service.ReleasesService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/release")
@RequiredArgsConstructor
public class ReleasesController {

    @Autowired
    private ReleasesService releasesService;

    @GetMapping("/{releaseId}")
    public ResponseEntity<ReleasesDto.ApiResponse> getReleaseByReleaseId(@PathVariable String releaseId) {
        try {
            ReleaseResponse response = releasesService.getReleaseByReleaseId(releaseId);

            ReleasesDto.ApiResponse successResponse = new ReleasesDto.ApiResponse(
                    "success",
                    "2000",
                    "Retrieved successfully",
                    response
            );

            return ResponseEntity.ok(successResponse);

        } catch (ResourceNotFoundException e) {
            ReleasesDto.ApiResponse failResponse = new ReleasesDto.ApiResponse(
                    "failure",
                    "4000",
                    "Id not found: " + e.getMessage(),
                    null
            );

            return ResponseEntity.ok(failResponse); // or .badRequest() if you want 400
        } catch (Exception e) {
            ReleasesDto.ApiResponse errorResponse = new ReleasesDto.ApiResponse(
                    "failure",
                    "5000",
                    "Something went wrong: " + e.getMessage(),
                    null
            );

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

}
