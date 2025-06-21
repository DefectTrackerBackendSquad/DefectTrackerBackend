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

    private String releaseTestCaseId;

    private Date testDate;
    private Time testTime;

    private String testCaseStatus;

    @OneToOne
//            (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "test_case_id")
    private TestCase testCase;

    @ManyToOne
//            (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id")
    private Releases releases;

}
