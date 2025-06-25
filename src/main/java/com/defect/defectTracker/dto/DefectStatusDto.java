package com.defect.defectTracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefectStatusDto {
    private Long id;

    @NotBlank(message = "defectStatus cannot be empty")
    private String defectStatusName;

}
