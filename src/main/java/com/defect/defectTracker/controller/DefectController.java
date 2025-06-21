package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.service.DefectService;
import com.defect.defectTracker.utils.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/defect")
@RequiredArgsConstructor
public class DefectController {

//    private final DefectService defectService;
//
//    @PostMapping
//    public ResponseEntity<StandardResponse> createDefect(
//            @RequestPart DefectDto defectDto,
//            @RequestPart(required = false) MultipartFile attachment) {
//
//        try {
//            DefectDto createdDefect = defectService.createDefect(defectDto, attachment);
//            return new ResponseEntity<>(
//                    new StandardResponse(
//                            HttpStatus.CREATED.value(),
//                            "Defect created successfully",
//                            createdDefect),
//                    HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(
//                    new StandardResponse(
//                            HttpStatus.BAD_REQUEST.value(),
//                            "Failed to create defect: " + e.getMessage(),
//                            null),
//                    HttpStatus.BAD_REQUEST);
//        }
//    }

    // Additional endpoints can be added below
}