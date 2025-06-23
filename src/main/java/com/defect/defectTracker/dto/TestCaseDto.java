package com.defect.defectTracker.dto;

import jakarta.transaction.Transactional;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Transactional

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TestCaseDto {
    private Long id;
    private String testCaseId;
    private String description;
    private String steps;


    private Long subModuleId;
    private Long moduleId;
    private Long projectId;
    private Long severityId;
    private Long typeId;
    private Long subModuleId;
    private Long moduleId;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCaseDto {
    private Long id;
    private String description;
    private String subModuleId;
    private Long severityId;
    private String steps;
    private Long typeId;
    private String moduleId;
    private String projectId;
    private String testCaseId;
}
