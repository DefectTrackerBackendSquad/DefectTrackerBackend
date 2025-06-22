package com.defect.defectTracker.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
@Entity
public class ReleaseTestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "release_test_case_id")
    private String releaseTestCaseId; // ✅ Mapped correctly, logical name

    private Date testDate;
    private Time testTime;

    private String testCaseStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "test_case_id") // ✅ FK column to TestCase entity
    private TestCase testCase;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id")
    private Releases releases;
}