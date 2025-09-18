package com.CodeCluster.ProblemsService.repo;

import com.CodeCluster.ProblemsService.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubmissionsRepository extends JpaRepository<Submission, UUID> {

}
