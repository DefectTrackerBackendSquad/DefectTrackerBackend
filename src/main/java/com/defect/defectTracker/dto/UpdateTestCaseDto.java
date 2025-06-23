package com.defect.defectTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data                       // lombock-gets, sets, toStng, equals
@NoArgsConstructor          //  no-ags constr
@AllArgsConstructor         //all-args constructor
public class UpdateTestCaseDto {


    private String testCaseId;
    private String testCase;
    private String subModuleId;
    private String description;
    private Long severityId;
    private String steps;
    private Long typeId;
    private String projectId;
}