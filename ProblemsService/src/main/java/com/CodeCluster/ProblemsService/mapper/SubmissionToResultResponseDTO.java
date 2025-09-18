package com.CodeCluster.ProblemsService.mapper;

import com.CodeCluster.ProblemsService.dto.ResultResponseDTO;
import com.CodeCluster.ProblemsService.model.Submission;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

public class SubmissionToResultResponseDTO {

    public static ResultResponseDTO buildDTO(Submission submission){
        ResultResponseDTO resultResponseDTO = new ResultResponseDTO();
        String[] testCaseResult = new String(submission.getTestCaseOutput(), StandardCharsets.UTF_8).split("\n");

        resultResponseDTO.setJobId(submission.getJobId());
        /// returns Result response dto with message is status is ERROR
        if(submission.getStatus().equalsIgnoreCase("Error")){
            /// sets status to error
            resultResponseDTO.setStatus(submission.getStatus());
            /// gets output in array from bytearray by splitting from newLine
            String errorMessage = Arrays.toString(Arrays.copyOfRange(testCaseResult, 0, 3)); /// get first three lines as error
            resultResponseDTO.setErrorMessage(errorMessage); /// sets error message to response dto
            return resultResponseDTO;

        }
        /// sets total no of test cases
        resultResponseDTO.setNumberOfTestCases(testCaseResult.length-1);
        /// gets test cases passed by counting no of string ending with literal "Passed"
        resultResponseDTO.setNumberOfTestCasesPassed((int)
                Arrays.stream(testCaseResult)
                        .filter(s -> s.endsWith("passed"))
                        .count()
        );

        /// sets num of testcases execution time in ms
        resultResponseDTO.setExecutionTime(String.valueOf(submission.getExecutionTime())+"ms");

        /// setes status if not error
        resultResponseDTO.setStatus(submission.getStatus());

        /// prepare test cases
        for(int i = 0; i<=2; i++){
            HashMap<String, String> testCase = new HashMap<>();
            testCase.put("Test Input", testCaseResult[i].split(":")[0].trim());
            testCase.put("Expected Output", testCaseResult[i].split(":")[1].trim());
            testCase.put("Your Output", testCaseResult[i].split(":")[2].trim());
            /// sets value to test cases in dto
            if(i==0)
                resultResponseDTO.setTestCase1(testCase);
            else if(i==1)
                resultResponseDTO.setTestCase2(testCase);
            else
                resultResponseDTO.setTestCase3(testCase);
        }




        return resultResponseDTO;

    }
}
