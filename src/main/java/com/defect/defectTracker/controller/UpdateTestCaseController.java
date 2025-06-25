//package com.defect.defectTracker.controller;
//
//import com.defect.defectTracker.dto.UpdateTestCaseDto;
//import com.defect.defectTracker.response.ApiResponse;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//
//@RestController
//@RequestMapping("/api/v1/testcase")
//@RequiredArgsConstructor
//public class UpdateTestCaseController {
//
//    private final UpdateTestCaseService updateTestCaseService;
//
//    //  PUT API to update TestCase by testCaseId
//    @PutMapping("/{testCaseId}")
//    public ResponseEntity<ApiResponse> updateTestCase(
//            @PathVariable String testCaseId,
//            @RequestBody UpdateTestCaseDto dto) {
//
//        //  Forward request to service layer
//        return updateTestCaseService.updateTestCase(testCaseId, dto);
//    }
//}