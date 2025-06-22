package com.defect.defectTracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ReleasesDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReleaseResponse {
        private String releaseId;
        private String releaseName;
        private String projectId;
        private String releaseDate;
        private String releaseType;
    }

    @Data
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ApiResponse {
        private String status;
        private String statusCode;
        private String message;
        private Object result;
    }
}
