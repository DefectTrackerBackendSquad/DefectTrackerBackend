package com.defect.defectTracker.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.List;
import jakarta.persistence.*;

@Data
@Entity
public class Severity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ✅ Internal primary key for database

    //Column(name = "severity_id", unique = true, nullable = false)
   // private String severityId; // ✅ Business ID like "SEV-001"

    private String severityName; // e.g., "Critical", "High", etc.

   // @OneToMany(mappedBy = "severity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<TestCase> testCases; // Optional reverse mapping
}