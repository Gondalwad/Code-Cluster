package com.CodeCluster.ExecutionService.service;

import com.CodeCluster.ExecutionService.DTO.SubmitRequestDTO;
import com.CodeCluster.ExecutionService.model.Submission;
import com.CodeCluster.ExecutionService.repo.SubmissionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SubmissionsService {

    private static final Logger log = LoggerFactory.getLogger(SubmissionsService.class);
    private SubmissionsRepository submissionsRepository;

    @Autowired
    public SubmissionsService(SubmissionsRepository submissionRepository) {
        this.submissionsRepository = submissionRepository;
    }

    public boolean saveResponse(String testCaseOutput, SubmitRequestDTO submitRequestDTO){

        Submission submission = new Submission();
        submission.setJobId(UUID.fromString(submitRequestDTO.getJobId().trim()));
        submission.setProblemId(UUID.fromString(submitRequestDTO.getProblemId().trim()));
        submission.setUsername(submitRequestDTO.getUsername());
        submission.setTestCaseOutput(testCaseOutput);
        submission.setSubmissionDate(LocalDateTime.now());
        submission.setStatus("Passed");

        try{
            /// sets status as error if testCaseOutput doesn't start with literal "Input"
            /// or if contains literal "failed" or if contains literal "Exception"
            if(testCaseOutput.contains("Exception") || testCaseOutput.startsWith("Execution failed with")){
                submission.setStatus("Error");
                submissionsRepository.save(submission);
                return true;
            }
            /// output to array
            String[] outputArray = testCaseOutput.split("\n");
            /// sets time execution time given in the last line of testCaseOutput
            submission.setExecutionTime((int)Long.parseLong(
                    outputArray[outputArray.length-1]
                            .split(":")[1].trim()
            )/1000000);
            /// set status as failed if any of testcase failed
            for(int i = 0; i < outputArray.length ; i++){
                /// checks each line if ends with literal "failed"

                if(outputArray[i].endsWith("failed") ){
                    submission.setStatus("Failed");
                    submissionsRepository.save(submission);
                    return true;
                }



            }
            /// finally saves submission with status passed if no error or failure found
            submissionsRepository.save(submission);
            return true;

        }catch(Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
}
