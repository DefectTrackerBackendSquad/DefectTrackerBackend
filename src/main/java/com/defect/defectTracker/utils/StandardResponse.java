package com.defect.defectTracker.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponse {
    private String status;
    private String message;
    private Object data;
    private int statusCode;

}
