package com.CodeCluster.SubmissionService.service;

import com.CodeCluster.SubmissionService.dto.SubmitRequestDTO;
import com.CodeCluster.SubmissionService.dto.SubmitResponseDTO;
import com.CodeCluster.SubmissionService.exception.SubmissionException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SolutionSubmissionService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final String TOPIC = "code_execution_job";
    private static final String GROUPID = "executor-service-group";
    public SolutionSubmissionService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    // produce event to topic = code_execution_job
    public SubmitResponseDTO uploadEvent(UUID jobId, SubmitRequestDTO submitRequest){

        try{
            // sets jobId
            submitRequest.setJobId(jobId);

            // Converts object to JSON String
            String payload = objectMapper.writeValueAsString(submitRequest);

            // upload event to topic with payload
            kafkaTemplate.send(TOPIC, payload);
            SubmitResponseDTO submitResponse = new SubmitResponseDTO();
            submitResponse.setJobId(jobId.toString());

            return submitResponse;

        }catch (Exception e){
            throw new SubmissionException("Submission Failed! Try Again");
        }
    }
}
