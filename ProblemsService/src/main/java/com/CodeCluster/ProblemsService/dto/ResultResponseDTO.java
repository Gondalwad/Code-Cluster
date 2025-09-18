package com.CodeCluster.ProblemsService.dto;

import java.util.HashMap;
import java.util.UUID;

public class ResultResponseDTO {
    private UUID jobId;
    private HashMap<String, String> testCase1;
    private HashMap<String, String> testCase2;
    private HashMap<String, String> testCase3;
    private String status;
    private int numberOfTestCases;
    private int numberOfTestCasesPassed;
    private String errorMessage;
    private String executionTime;
    public UUID getJobId() {
        return jobId;
    }

    public void setJobId(UUID jobId) {
        this.jobId = jobId;
    }

    public HashMap<String, String> getTestCase1() {
        return testCase1;
    }

    public void setTestCase1(HashMap<String, String> testCase1) {
        this.testCase1 = testCase1;
    }

    public HashMap<String, String> getTestCase2() {
        return testCase2;
    }

    public void setTestCase2(HashMap<String, String> testCase2) {
        this.testCase2 = testCase2;
    }

    public HashMap<String, String> getTestCase3() {
        return testCase3;
    }

    public void setTestCase3(HashMap<String, String> testCase3) {
        this.testCase3 = testCase3;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberOfTestCases() {
        return numberOfTestCases;
    }

    public void setNumberOfTestCases(int numberOfTestCases) {
        this.numberOfTestCases = numberOfTestCases;
    }

    public int getNumberOfTestCasesPassed() {
        return numberOfTestCasesPassed;
    }

    public void setNumberOfTestCasesPassed(int numberOfTestCasesPassed) {
        this.numberOfTestCasesPassed = numberOfTestCasesPassed;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }
}
