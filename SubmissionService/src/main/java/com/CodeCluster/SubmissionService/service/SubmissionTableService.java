package com.CodeCluster.SubmissionService.service;

import com.CodeCluster.SubmissionService.model.Submission;
import com.CodeCluster.SubmissionService.repo.SubmissionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SubmissionTableService {
    private final SubmissionsRepository submissionsRepository;

    @Autowired
    public SubmissionTableService(SubmissionsRepository submissionsRepository){
        this.submissionsRepository = submissionsRepository;
    }

    /// return optional of Submission if not found empty else Submission
    public Optional<Submission> getSubmission(String jobId){
        return submissionsRepository.findById(UUID.fromString(jobId));
    }
}
