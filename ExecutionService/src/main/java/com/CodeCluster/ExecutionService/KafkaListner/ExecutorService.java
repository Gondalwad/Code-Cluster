package com.CodeCluster.ExecutionService.KafkaListner;

import com.CodeCluster.ExecutionService.DTO.SubmitRequestDTO;
import com.CodeCluster.ExecutionService.DockerService.ContainerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ExecutorService {

    private final ObjectMapper objectMapper;
    private final ContainerService containerService;
    //constructor
    public ExecutorService(ObjectMapper objectMapper, ContainerService cs) {
        this.objectMapper = objectMapper;
        this.containerService = cs;
    }

    // consumes job
    @KafkaListener(topics = "code_execution_job",groupId = "executor-service-group")
    public void consumeJob(String message){
        try{
            SubmitRequestDTO job = objectMapper.readValue(message,SubmitRequestDTO.class);

            System.out.println("hello");
            containerService.createContainer(job);
            // now request sent to docker Service which is responsible for creating container executing code and deleting container

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
