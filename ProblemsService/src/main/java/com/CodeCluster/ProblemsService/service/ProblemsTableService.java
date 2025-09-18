package com.CodeCluster.ProblemsService.service;

import com.CodeCluster.ProblemsService.model.ProblemsTable;
import com.CodeCluster.ProblemsService.repo.ProblemsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProblemsTableService {
    private final ProblemsRepository problemsRepository;

    public ProblemsTableService(ProblemsRepository problemsRepository) {
        this.problemsRepository = problemsRepository;
    }

    /// get only one problem id
    public Optional<ProblemsTable> getProblemByName(String problemName){
        return problemsRepository.findByProblemName(problemName);
    }

    /// get list of 20 problems
    public List<ProblemsTable> getProblems(int from){
        Pageable pageable = PageRequest.of(from,from+20);
        /// gets problems int 'from' to int 'to'
        Page<ProblemsTable> problemsPage = problemsRepository.findAll(pageable);

        return problemsPage.getContent();


    }
}
