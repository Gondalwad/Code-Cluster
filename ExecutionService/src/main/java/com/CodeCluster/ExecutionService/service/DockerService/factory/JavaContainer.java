package com.CodeCluster.ExecutionService.service.DockerService.factory;

import com.CodeCluster.ExecutionService.model.ProblemsTable;
import com.CodeCluster.ExecutionService.service.DockerService.JavaDockerRunner;
import com.CodeCluster.ExecutionService.service.ProblemsTableService;
import com.CodeCluster.ExecutionService.service.TempFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class JavaContainer implements Container {
    private final String fileExtension = ".java";
    private final ProblemsTableService problemsTableService;
    private final TempFileService tempFileService;
    private final JavaDockerRunner javaDockerRunner;

    @Autowired
    public JavaContainer(ProblemsTableService problemsTableService,
                         TempFileService tempFileService,
                         JavaDockerRunner javaDockerRunner) {
        this.problemsTableService = problemsTableService;
        this.tempFileService = tempFileService;
        this.javaDockerRunner = javaDockerRunner;
    }

    @Override
    public String executeProgram(String problemId, String jobId, String code) {
        ProblemsTable problem = problemsTableService.getProblem(problemId);
        File folder = tempFileService.prepareTempFolder(jobId, code, problem, fileExtension);

        try {
                    /// run java Tests using DockerRunner
            return javaDockerRunner.runJavaTests(folder, problem.getTestCaseFileName());
        } finally {
            tempFileService.cleanupTempFolder(jobId);
        }
    }
}
