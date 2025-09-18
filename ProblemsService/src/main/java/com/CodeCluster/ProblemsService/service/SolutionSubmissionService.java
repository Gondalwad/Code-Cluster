package com.CodeCluster.ProblemsService.service;

import com.CodeCluster.ProblemsService.dto.SubmitRequestDTO;
import com.CodeCluster.ProblemsService.dto.SubmitResponseDTO;
import com.CodeCluster.ProblemsService.exception.SubmissionException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SolutionSubmissionService {

    private static final Logger log = LoggerFactory.getLogger(SolutionSubmissionService.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final String TOPIC = "code_execution_job";
    private static final String GROUPID = "executor-service-group";
    private final ProblemsTableService problemsTableService;
    public SolutionSubmissionService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper, ProblemsTableService problemsTableService) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.problemsTableService = problemsTableService;
    }

    // produce event to topic = code_execution_job
    public SubmitResponseDTO uploadEvent(UUID jobId, SubmitRequestDTO submitRequest){

        try{
            // sets jobId
            submitRequest.setJobId(jobId);


            submitRequest.setProblemId(
                    problemsTableService
                            .getProblemByName(
                                    submitRequest
                                            .getProblemId())
                            .get()
                            .getProblemId()
                            .toString()
            );


            // Converts object to JSON String
            String payload = objectMapper.writeValueAsString(submitRequest);

            // upload event to topic with payload
            kafkaTemplate.send(TOPIC, payload);
            SubmitResponseDTO submitResponse = new SubmitResponseDTO();
            submitResponse.setJobId(jobId.toString());

            return submitResponse;

        }catch (Exception e){
            log.error(e.getMessage());
            throw new SubmissionException("Submission Failed! Try Again");
        }
    }
}
