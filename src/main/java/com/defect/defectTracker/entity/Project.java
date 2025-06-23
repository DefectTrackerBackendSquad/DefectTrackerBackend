// Project.java
package com.defect.defectTracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectId;
    private String projectName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long userId;
}
