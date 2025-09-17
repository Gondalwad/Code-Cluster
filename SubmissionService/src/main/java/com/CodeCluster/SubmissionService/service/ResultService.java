package com.CodeCluster.SubmissionService.service;

import com.CodeCluster.SubmissionService.dto.ResultResponseDTO;
import com.CodeCluster.SubmissionService.mapper.SubmissionToResultResponseDTO;
import com.CodeCluster.SubmissionService.model.Submission;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultService {
    private SubmissionTableService submissionTableService;

    public ResultService(SubmissionTableService submissionTableService) {
        this.submissionTableService = submissionTableService;
    }

    /// controller's service to fetch result from DB and process to respond as ResultResponseDTO or Empty
    public Optional<ResultResponseDTO> fetchResult(String jobId) {
        /// get submission with String jobId
        Optional<Submission> submissionResponse = submissionTableService.getSubmission(jobId);

        /// return ResultResponseDTO is submissionResponse != null or empty otherwise
        return submissionResponse.map(SubmissionToResultResponseDTO::buildDTO);

    }
}
