package com.defect.defectTracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.lang.Module;

@Data
@Entity
public class AllocateModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "module_id", nullable = false)
    private Modules modules;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_module_id", nullable = false)
    private SubModule subModule;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_assign_id", nullable = false)
    private ProjectAllocation projectAllocation;
}
