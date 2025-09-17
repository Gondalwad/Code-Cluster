package com.CodeCluster.SubmissionService.repo;

import com.CodeCluster.SubmissionService.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubmissionsRepository extends JpaRepository<Submission, UUID> {

}
