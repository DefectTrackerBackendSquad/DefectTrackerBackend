// Module.java
package com.defect.defectTracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Modules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String moduleId;
    private String moduleName;
    private Long projectId;
}