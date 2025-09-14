package com.CodeCluster.ExecutionService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "Submissions")
public class Submission {
    @Id
    private UUID jobId;
    private String status;
    @Lob
    private byte[] testCaseOutput;
    private String username;
    private UUID problemId;

    public UUID getJobId() {
        return jobId;
    }

    public void setJobId(UUID jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getTestCaseOutput() {
        return testCaseOutput;
    }

    public void setTestCaseOutput(String testCaseOutput) {
        this.testCaseOutput = testCaseOutput.getBytes();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getProblemId() {
        return problemId;
    }

    public void setProblemId(UUID problemId) {
        this.problemId = problemId;
    }
}
