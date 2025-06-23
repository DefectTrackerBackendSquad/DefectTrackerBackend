package com.defect.defectTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefectStatusDTO {
    private String status;
    private String statusCode;
    private String message;
    private String defectStatus; // include the input field
}
