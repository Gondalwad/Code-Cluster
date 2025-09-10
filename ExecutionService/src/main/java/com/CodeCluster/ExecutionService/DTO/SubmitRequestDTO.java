package com.CodeCluster.ExecutionService.DTO;

public class SubmitRequestDTO {

    private String username;

    private String programmingLanguage;

    private String codeSolution;

    private String problemId;


    private String jobId;

    public SubmitRequestDTO(String username, String programmingLanguage, String codeSolution, String problemId, String jobId) {
        this.username = username;
        this.programmingLanguage = programmingLanguage;
        this.codeSolution = codeSolution;
        this.problemId = problemId;
        this.jobId = jobId;
    }

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

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "SubmitRequestDTO{" +
                "username='" + username + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                ", codeSolution='" + codeSolution + '\'' +
                ", problemId='" + problemId + '\'' +
                ", jobId=" + jobId +
                '}';
    }
}
