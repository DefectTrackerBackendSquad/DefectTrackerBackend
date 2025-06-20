package com.defect.defectTracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNo;
    private Date joinDate;
    private boolean userStatus;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="designation_id")
    private Designation designation;
}
