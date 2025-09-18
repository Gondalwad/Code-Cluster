package com.CodeCluster.ProblemsService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.util.UUID;

@Entity
public class ProblemsTable {
    @Id
    private UUID problemId;
    private String problemName;
    private String problemDescription;
    private String testCaseFileName;
    private String codeFileName;
    private int problemNo;
    private String difficultyLevel;
    @Lob
    private String javaTestCaseFile;
    @Lob
    private String javaSnippet;


    public UUID getProblemId() {
        return problemId;
    }

    public void setProblemId(UUID problemId) {
        this.problemId = problemId;
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

    public String getJavaTestCaseFile() {
        return javaTestCaseFile;
    }

    public void setJavaTestCaseFile(String javaTestCaseFile) {
        this.javaTestCaseFile = javaTestCaseFile;
    }

    public String getTestCaseFileName() {
        return testCaseFileName;
    }

    public void setTestCaseFileName(String testCaseFileName) {
        this.testCaseFileName = testCaseFileName;
    }

    public String getJavaSnippet() {
        return javaSnippet;
    }

    public void setJavaSnippet(String javaSnippet) {
        this.javaSnippet = javaSnippet;
    }

    public String getCodeFileName() {
        return codeFileName;
    }

    public void setCodeFileName(String codeFileName) {
        this.codeFileName = codeFileName;
    }

    public int getProblemNo() {return problemNo;}

    public void setProblemNo(int problemNo) { this.problemNo = problemNo;}

    public String getDifficultyLevel() {return difficultyLevel;}

    public void setDifficultyLevel(String difficultyLevel) {this.difficultyLevel = difficultyLevel;}
}
