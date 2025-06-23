package com.defect.defectTracker.dto;

import lombok.Data;

@Data
public class ReleaseTestCaseDto {
    private String releaseTestCaseId;
    // other fields, getters, setters
    public String getReleaseTestCaseId() {
        return releaseTestCaseId;
    }
    public void setReleaseTestCaseId(String releaseTestCaseId) {
        this.releaseTestCaseId = releaseTestCaseId;
    }
}