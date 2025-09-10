package com.CodeCluster.SubmissionService.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class SubmitRequestDTO {
    @NotBlank(message = "Login to submit answer")
    private String username;
    @NotBlank(message = "select programming language")
    private String programmingLanguage;
    @NotBlank(message = "Need code solution")
    private String codeSolution;
    @NotBlank(message = "Problem id is required")
    private String problemId;

    @NotBlank
    private UUID jobId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getCodeSolution() {
        return codeSolution;
    }

    public void setCodeSolution(String codeSolution) {
        this.codeSolution = codeSolution;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public @NotBlank UUID getJobId() {
        return jobId;
    }

    public void setJobId(@NotBlank UUID jobId) {
        this.jobId = jobId;
    }
}
