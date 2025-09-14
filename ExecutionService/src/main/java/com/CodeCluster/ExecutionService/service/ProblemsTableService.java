package com.CodeCluster.ExecutionService.service;

import com.CodeCluster.ExecutionService.model.ProblemsTable;
import com.CodeCluster.ExecutionService.repo.ProblemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProblemsTableService {

    private final ProblemRepository problemRepository;

    public ProblemsTableService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public ProblemsTable getProblem(String problemId) {
        Optional<ProblemsTable> problem = problemRepository.findById(UUID.fromString(problemId));
        return problem.orElseThrow(() -> new RuntimeException("Problem not found: " + problemId));
    }
}
