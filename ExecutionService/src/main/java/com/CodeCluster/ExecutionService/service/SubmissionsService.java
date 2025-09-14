package com.CodeCluster.ExecutionService.service;

import com.CodeCluster.ExecutionService.DTO.SubmitRequestDTO;
import com.CodeCluster.ExecutionService.model.Submission;
import com.CodeCluster.ExecutionService.repo.SubmissionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class SubmissionsService {

    private static final Logger log = LoggerFactory.getLogger(SubmissionsService.class);
    private SubmissionsRepository submissionsRepository;

    @Autowired
    public SubmissionsService(SubmissionsRepository submissionRepository) {
        this.submissionsRepository = submissionRepository;
    }

    public boolean saveResponse(String testCaseOutput, SubmitRequestDTO submitRequestDTO){

        Submission submission = new Submission();
        submission.setJobId(UUID.fromString(submitRequestDTO.getJobId()));
        submission.setProblemId(UUID.fromString(submitRequestDTO.getProblemId()));
        submission.setUsername(submitRequestDTO.getUsername());
        submission.setTestCaseOutput(testCaseOutput);
        submission.setStatus("Success");

        /// sets status as failed if testCaseOutput doesn't start with literal "Input"
        /// or if contains literal "failed" or if contains literal "Exception"
        if(!testCaseOutput.startsWith("Input") || testCaseOutput.contains("Exception") || testCaseOutput.contains("failed") ){
            submission.setStatus("Failed");
        }

        try{
            submissionsRepository.save(submission);
        }catch(Exception e){
            log.error(e.getMessage());
            return false;
        }

        return true;
    }
}
