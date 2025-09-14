package com.CodeCluster.ExecutionService.KafkaListner;

import com.CodeCluster.ExecutionService.DTO.SubmitRequestDTO;
import com.CodeCluster.ExecutionService.service.SubmissionsService;
import com.CodeCluster.ExecutionService.service.TestCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerCodeExecutionJob {

    private final ObjectMapper objectMapper;
    private final TestCode testCode;
    private final SubmissionsService submissionsService;
    private final Logger log = LoggerFactory.getLogger(ListenerCodeExecutionJob.class);
    ///constructor
    @Autowired
    public ListenerCodeExecutionJob(ObjectMapper objectMapper, TestCode testCode, SubmissionsService submissionsService) {
        this.objectMapper = objectMapper;
        this.testCode = testCode;
        this.submissionsService = submissionsService;
    }

    /// consumes job
    @KafkaListener(topics = "code_execution_job",groupId = "executor-service-group")
    public void consumeJob(String message){
        try{
            SubmitRequestDTO job = objectMapper.readValue(message,SubmitRequestDTO.class);

///         now request sent to TestCode which is responsible for
///         creating container executing code and deleting container
            String output = testCode.test(job);
            boolean isSaved = submissionsService.saveResponse(output, job);
            if(!isSaved)
                log.error("Someting went wrong while saving Job : {} from User : {} ", job.getJobId(), job.getUsername());

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

}
