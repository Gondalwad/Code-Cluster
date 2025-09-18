package com.CodeCluster.ProblemsService.controller;

import com.CodeCluster.ProblemsService.dto.ResultResponseDTO;
import com.CodeCluster.ProblemsService.dto.SubmitRequestDTO;
import com.CodeCluster.ProblemsService.dto.SubmitResponseDTO;
import com.CodeCluster.ProblemsService.service.SolutionSubmissionService;
import com.CodeCluster.ProblemsService.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class SubmissionController {

    private final SolutionSubmissionService submissionService;
    private final ResultService resultService;
    public SubmissionController(SolutionSubmissionService submissionService, ResultService resultService) {
        this.submissionService = submissionService;
        this.resultService = resultService;
    }

    @PostMapping("/submit-solution")
    public ResponseEntity<SubmitResponseDTO> submitSolution(@RequestBody SubmitRequestDTO submitRequest){
        /// upload event to kafka for execution using submission service which return SubmitResponseDTO to
        /// send to respond user immediately
        SubmitResponseDTO submitResponse = submissionService.uploadEvent(UUID.randomUUID(), submitRequest);
        return ResponseEntity.status(201).body(submitResponse);

    }

    @GetMapping("/result")
    public ResponseEntity<ResultResponseDTO> getResult(@RequestParam(name = "jobId", required = true) String jobId){
        Optional<ResultResponseDTO> resultResponseDTO = resultService.fetchResult(jobId);

        /// response to user
        return resultResponseDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


//    public ResponseEntity<TestCaseResultDTO> submit
}
