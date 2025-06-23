package com.defect.defectTracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "releases")
public class Releases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "release_id")
    private String releaseId;

    @Column(name = "release_name")
    private String releaseName;

    @Column(name = "release_type")
    private String releaseType;

    @Column(name = "release_date")  // This should be your only date field
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
