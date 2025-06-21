//package com.defect.defectTracker.dto;
//
//import lombok.Data;
//import java.time.LocalDateTime;
//import java.util.List;
//
//
//public class DefectDto {
//
//    // Main Defect DTO
//    @Data
//    public static class DefectRequest {
//        private String dfId; // DF00001 format
//        private String title;
//        private String description;
//        private String tcId; // TC00001 format
//        private Long severityId; // 1,2,3...
//        private Long defectStatusId; // 1,2,3...
//        private Long priorityId; // 1,2,3...
//        private Long typeId; // 1,2,3...
//        private Integer reOpenCount;
//        private byte[] attachment;
//        private String usAssignedToId; // US0001 format
//        private String steps;
//        private String retId; // RET0001 format
//        private String prId; // PR0001 format
//        private String usAssignedById; // US0001 format
////        private List<CommentRequest> comments;
//    }
//
//
////    public static class DefectResponse {
////        private String dfId;
////        private String title;
////        private String description;
////        private TestCaseResponse testCase;
////        private SeverityResponse severity;
////        private DefectStatusResponse defectStatus;
////        private PriorityResponse priority;
////        private TypeResponse type;
////        private Integer reOpenCount;
////        private String attachmentPath;
////        private UserResponse assignedTo;
////        private String steps;
////        private ReleaseTestcaseResponse releaseTestcase;
////        private ProjectResponse project;
////        private UserResponse assignedBy;
////        private LocalDateTime createdAt;
////        private LocalDateTime updatedAt;
////        private List<CommentResponse> comments;
////        private List<DefectHistoryResponse> defectHistories;
////    }
////
////    // Related DTOs
////    public static class CommentRequest {
////        private String usId; // US0001 format
////        private String comment;
////        private byte[] attachment;
////    }
////
////
////    public static class CommentResponse {
////        private Long id; // 1,2,3...
////        private UserResponse user;
////        private String comment;
////        private String attachmentPath;
////        private LocalDateTime createdAt;
////    }
////
////
////    public static class DefectHistoryResponse {
////        private Long id; // 1,2,3...
////        private DefectStatusResponse defectStatus;
////        private UserResponse assignedBy;
////        private LocalDateTime assignmentDate;
////        private String previousStatus;
////    }
////
////
////    public static class TestCaseResponse {
////        private String tcId; // TC00001 format
////        private String description;
////        private SubmoduleResponse submodule;
////        private SeverityResponse severity;
////        private String steps;
////        private TypeResponse type;
////        private ModuleResponse module;
////        private ProjectResponse project;
////    }
////
////
////    public static class SeverityResponse {
////        private Long id; // 1,2,3...
////        private String name;
////        private String color;
////    }
////
////
////    public static class DefectStatusResponse {
////        private Long id; // 1,2,3...
////        private String name;
////    }
////
////
////    public static class PriorityResponse {
////        private Long id; // 1,2,3...
////        private String name;
////        private String color;
////    }
////
////
////    public static class TypeResponse {
////        private Long id; // 1,2,3...
////        private String name;
////    }
////
////
////    public static class ReleaseTestcaseResponse {
////        private String retId; // RET0001 format
//////        private ReleaseResponse release;
////        private TestCaseResponse testCase;
////        private LocalDateTime testDate;
////        private String testTime;
////        private UserResponse owner;
////        private String status;
////    }
////
////
////    public static class SubmoduleResponse {
////        private String smId; // SM0001 format
////        private String name;
////        private ModuleResponse module;
////    }
////
////
////    public static class ModuleResponse {
////        private String moId; // MO0001 format
////        private String name;
////        private ProjectResponse project;
////    }
////
////
////    public static class ProjectResponse {
////        private String prId; // PR0001 format
////        private String name;
////        private UserResponse projectManager;
////        private String duration;
////    }
////
////
////    public static class UserResponse {
////        private String usId; // US0001 format
////        private String username;
////        private String email;
////        private String firstName;
////        private String lastName;
////        private String gender;
////        private String phone;
////        private LocalDateTime joinDate;
////        private DesignationResponse designation;
////        private String status;
////    }
////
////
////    public static class DesignationResponse {
////        private Long id; // 1,2,3...
////        private String name;
////    }
////
////    // Filter DTO
////
////    public static class DefectFilterRequest {
////        private String prId; // PR0001 format
////        private String reId; // RE0001 format
////        private String moId; // MO0001 format
////        private String smId; // SM0001 format
////        private Long severityId; // 1,2,3...
////        private Long priorityId; // 1,2,3...
////        private Long typeId; // 1,2,3...
////        private Long defectStatusId; // 1,2,3...
////        private String usAssignedToId; // US0001 format
////        private LocalDateTime startDate;
////        private LocalDateTime endDate;
////        private String searchQuery;
////    }
//}

package com.defect.defectTracker.dto;

import com.defect.defectTracker.entity.Defect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefectDto {

    private String dfId;                // DF00001 format
    private String title;
    private String description;
    private String tcId;               // TC00001 format
    private Long severityId;           // 1,2,3...
    private Long defectStatusId;       // 1,2,3...
    private Long priorityId;           // 1,2,3...
    private Long typeId;               // 1,2,3...
    private Integer reOpenCount;
    private byte[] attachment;         // optional file attachment
    private String usAssignedToId;     // US0001 format
    private String steps;
    private String retId;              // RET0001 format
    private String prId;               // PR0001 format
    private String usAssignedById;     // US0001 format

    public DefectDto(Defect savedDefect) {
    }

    // If you want to add comments in future:
    // private List<CommentRequest> comments;
}
