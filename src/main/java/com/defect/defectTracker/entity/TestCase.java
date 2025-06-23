package com.defect.defectTracker.entity;

import com.defect.defectTracker.entity.Modules;
import jakarta.persistence.*;
import lombok.Data;
import com.defect.defectTracker.entity.Modules;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String testCaseId;
    private String description;
    private String steps;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_module_id")
    private SubModule subModule;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id")
    private Modules module;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "severity_id")
    private Severity severity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    public void setModules(Modules moduleId) {

    }

//    public void setModules(Modules moduleId) {
//    }

//    public Thread getModules() {
//    }

//    public void setModules(Modules moduleId) {
//    }
//
//    public Thread getModules() {
//    }
}




