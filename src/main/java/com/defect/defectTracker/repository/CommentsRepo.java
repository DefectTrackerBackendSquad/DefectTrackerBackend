package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentsRepo extends JpaRepository<Comments, Long> {
}
