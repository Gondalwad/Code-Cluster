package com.CodeCluster.ExecutionService.KafkaListner;

import com.CodeCluster.ExecutionService.DTO.SubmitRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ExecutorService {

    private final ObjectMapper objectMapper;

    //constructor
    public ExecutorService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // consumes job
    @KafkaListener(topics = "code_execution_job",groupId = "executor-service-group")
    public void consumeJob(String message){
        try{
            SubmitRequestDTO job = objectMapper.readValue(message,SubmitRequestDTO.class);

            System.out.println(job.toString());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
