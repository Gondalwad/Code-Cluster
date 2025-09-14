package com.CodeCluster.ExecutionService.KafkaListner;

import com.CodeCluster.ExecutionService.DTO.SubmitRequestDTO;
import com.CodeCluster.ExecutionService.service.TestCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerCodeExecutionJob {

    private final ObjectMapper objectMapper;
    private final TestCode testCode;
    ///constructor
    @Autowired
    public ListenerCodeExecutionJob(ObjectMapper objectMapper, TestCode testCode) {
        this.objectMapper = objectMapper;
        this.testCode = testCode;
    }

    /// consumes job
    @KafkaListener(topics = "code_execution_job",groupId = "executor-service-group")
    public void consumeJob(String message){
        try{
            SubmitRequestDTO job = objectMapper.readValue(message,SubmitRequestDTO.class);

///         now request sent to TestCode which is responsible for
///         creating container executing code and deleting container
            String output = testCode.test(job);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
