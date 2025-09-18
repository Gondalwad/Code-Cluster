package com.CodeCluster.ProblemsService.dto;

import java.util.UUID;

public class ProblemSetDTO {
    private String problemId;
    private int problemNo;
    private String problemName;
    private String problemDescription;
    private String difficulty;

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public int getProblemNo() {
        return problemNo;
    }

    public void setProblemNo(int problemNo) {
        this.problemNo = problemNo;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
