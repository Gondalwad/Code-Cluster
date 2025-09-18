package com.CodeCluster.ProblemsService.service;

import com.CodeCluster.ProblemsService.dto.ProblemDTO;
import com.CodeCluster.ProblemsService.dto.ProblemSetDTO;
import com.CodeCluster.ProblemsService.exception.SQLException;
import com.CodeCluster.ProblemsService.mapper.ProblemsToDTO;
import com.CodeCluster.ProblemsService.model.ProblemsTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemsControllerService {

    private static final Logger log = LoggerFactory.getLogger(ProblemsControllerService.class);
    ProblemsTableService problemsTableService;

    public ProblemsControllerService(ProblemsTableService problemsTableService) {
        this.problemsTableService = problemsTableService;
    }

    public Optional<ProblemDTO> getOneProblem(String problemName){
        Optional<ProblemsTable> problem = problemsTableService.getProblemByName(problemName);
        /// return empty if no problem found
        try{
            return problem.map(ProblemsToDTO::toProblemDTO);
        } catch (Exception e) {
            log.error("Error in getting one Problem with id {} : {}", problemName ,e.getMessage());
            throw new SQLException("Error in loading this problem ! Try another");
        }


    }

    public List<ProblemSetDTO> getManyProblems(int from){

        try{
            List<ProblemsTable> problemList = problemsTableService.getProblems(from);
            return ProblemsToDTO.toProblemSetDTO(problemList);
        }catch (Exception ex){
            log.error("Error in getting list of Problems : {}",ex.getMessage());
            throw new SQLException("Something went wrong please try again later !");
        }


    }
}
