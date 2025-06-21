// Severity.java
package com.defect.defectTracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Severity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String severityName;
    private byte[] severityColor;
}
