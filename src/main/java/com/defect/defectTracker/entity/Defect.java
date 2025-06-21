package com.defect.defectTracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.print.attribute.standard.Media;
import java.time.LocalDateTime;

@Data
@Entity
public class Defect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String defectId;

    private String description;
    private int reOpenCount;

    // If you want to persist attachments, use byte[] or String (file path or base64). Otherwise, keep as @Transient.
    @Transient
    private String attachment; // Use String for file path or base64, or change to byte[] if needed

    private String steps;
    private LocalDateTime createdDate;

    // Lombok @Data will generate getter/setter for createdDate

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "release_test_case_id")
    private ReleaseTestCase releaseTestCase;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_by")
    private User assignedBy;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "severity_id")
    private Severity severity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "defect_status_id")
    private DefectStatus defectStatus;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne//(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToOne//(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type defectType;

}
