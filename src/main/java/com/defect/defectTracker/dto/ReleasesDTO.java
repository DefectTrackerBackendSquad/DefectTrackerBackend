package com.defect.defectTracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReleasesDTO {

    private Long id;
    private String releaseId;
    private String releaseName;
    private Date releaseDate;
    private String releaseType;
    private Long projectId;

    // Static method to convert Entity → DTO
    public static ReleasesDTO fromEntity(com.defect.defectTracker.entity.Releases entity) {
        ReleasesDTO dto = new ReleasesDTO();
        dto.setId(entity.getId());
        dto.setReleaseId(entity.getReleaseId());
        dto.setReleaseName(entity.getReleaseName());
        dto.setReleaseDate(entity.getReleaseDate());
        dto.setReleaseType(entity.getReleaseType());

        if (entity.getProject() != null) {
            dto.setProjectId(entity.getProject().getId());
        }
        return dto;
    }

    // Static method to convert DTO → Response (formatted)
    public static ReleaseResponse toResponse(ReleasesDTO dto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return new ReleaseResponse(
                dto.getReleaseId(),
                dto.getReleaseName(),
                dto.getProjectId(),
                dto.getReleaseDate() != null ? sdf.format((Date) dto.getReleaseDate()) : null,
                dto.getReleaseType()
        );
    }

    // Client-facing formatted response class
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReleaseResponse {
        private String releaseId;
        private String releaseName;
        private Long projectId;
        private String releaseDate;
        private String releaseType;
    }

    // API wrapper class
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApiResponse {
        private String status;
        private String statusCode;
        private String message;
        private Object result;
    }
}
