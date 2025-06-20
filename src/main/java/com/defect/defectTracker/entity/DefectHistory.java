package com.defect.defectTracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
@Entity
public class DefectHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date defectDate;
    private Time defectTime;

    private String previousStatus;

    private String assignedTo;

    private String assignedBy;

    private Long releaseId;

    private String defectStatus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "defect_id")
    private Defect defect;


}
