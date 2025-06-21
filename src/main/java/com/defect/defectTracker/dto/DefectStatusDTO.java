package com.defect.defectTracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefectStatusDTO {
    private String defectStatus; // input field (e.g., "Open", "Closed")

    // These are for response purposes
    private String status;       // e.g., "success" or "error"
    private String statusCode;   // e.g., "2001", "4000"
    private String message;      // human-readable message
}
