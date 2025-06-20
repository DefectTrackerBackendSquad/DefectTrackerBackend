package com.defect.defectTracker.repository;


import com.defect.defectTracker.entity.GroupPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPrivilegeRepo extends JpaRepository<GroupPrivilege, Long> {
}
