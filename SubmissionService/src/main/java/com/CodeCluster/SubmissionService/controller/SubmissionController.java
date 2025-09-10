package com.CodeCluster.SubmissionService.controller;

import com.CodeCluster.SubmissionService.dto.SubmitRequestDTO;
import com.CodeCluster.SubmissionService.dto.SubmitResponseDTO;
import com.CodeCluster.SubmissionService.service.SolutionSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/submit")
public class SubmissionController {

    private final SolutionSubmissionService submissionService;

    public SubmissionController(SolutionSubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/solution")
    public ResponseEntity<SubmitResponseDTO> submitSolution(@RequestBody SubmitRequestDTO submitRequest){

        SubmitResponseDTO submitResponse = submissionService.uploadEvent(UUID.randomUUID(), submitRequest);

        return ResponseEntity.status(201).body(submitResponse);

    }
}
