package com.hari.repository;

import com.hari.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission,Long> {
    List<Submission> findByTaskId(Long taskId);
}
