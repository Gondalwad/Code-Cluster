package com.CodeCluster.ExecutionService.repo;

import com.CodeCluster.ExecutionService.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubmissionsRepository extends JpaRepository<Submission, UUID> {

}
