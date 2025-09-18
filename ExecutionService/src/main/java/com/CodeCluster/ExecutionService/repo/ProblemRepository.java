package com.CodeCluster.ExecutionService.repo;

import com.CodeCluster.ExecutionService.model.ProblemsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProblemRepository extends JpaRepository<ProblemsTable, UUID> {

    Optional<ProblemsTable> findByProblemName(String problemId);
}
