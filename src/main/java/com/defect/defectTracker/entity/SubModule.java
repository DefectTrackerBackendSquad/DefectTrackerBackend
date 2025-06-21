// SubModule.java
package com.defect.defectTracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SubModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subModuleId;
    private String subModuleName;
    private Long moduleId;
}
