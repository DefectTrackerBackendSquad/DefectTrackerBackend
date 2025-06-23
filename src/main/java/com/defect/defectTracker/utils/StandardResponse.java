package com.defect.defectTracker.utils;

import com.defect.defectTracker.dto.DefectDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponse {
    private String status;
    private String message;
    private Object data;
    private int statusCode;

//    public StandardResponse(int value, String defectCreatedSuccessfully, DefectDto createdDefect) {
//    }
}
