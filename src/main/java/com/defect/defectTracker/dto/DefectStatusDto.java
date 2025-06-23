package com.defect.defectTracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DefectStatusDto {
    @NotBlank(message = "Data cannot be null or empty")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z ]*$",
            message = "Invalid Data Type")
    private String defectStatusName;
}