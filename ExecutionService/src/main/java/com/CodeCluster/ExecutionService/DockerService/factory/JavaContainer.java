package com.CodeCluster.ExecutionService.DockerService.factory;

import com.CodeCluster.ExecutionService.model.ProblemsTable;
import com.CodeCluster.ExecutionService.repo.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.UUID;

@Component
public class JavaContainer implements Container {

    private final ProblemRepository pr;
    private String jobId;
    private String codeFileName;
    private String code;
    private String testCaseFileName;
    private File folder;

    @Autowired
    public JavaContainer(ProblemRepository pr) {
        this.pr = pr;
    }

    @Override
    public String executeProgram(String problemId, String jobId, String code) {
        this.code = code;
        this.jobId = jobId;

        folder = new File("temp/" + jobId);
        if (!folder.exists() && !folder.mkdirs()) {
            return "Something went wrong while creating directory";
        }

        if (!getTestCaseFile(problemId)) {
            return "Something went wrong while fetching test case file";
        }

        String containerWorkDir = "/app";
        String hostVolumePath = folder.getAbsolutePath();

        String[] command = {
                "docker", "run", "--rm",
                "-v", hostVolumePath + ":/app",
                "-w", containerWorkDir,
                "openjdk:17",
                "sh", "-c",
                "javac " + codeFileName + " " + testCaseFileName +
                        " && java " + testCaseFileName.replace(".java", "")
        };

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                return "Execution failed with exit code " + exitCode + "\n" + output;
            }

            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Execution failed due to exception: " + e.getMessage();
        }
    }

    private boolean getTestCaseFile(String problemId) {
        Optional<ProblemsTable> problem = pr.findById(UUID.fromString(problemId));
        if (problem.isEmpty()) {
            return false;
        }

        this.codeFileName = problem.get().getCodeFileName();
        this.testCaseFileName = problem.get().getTestCaseFileName();
        String testCaseCode = problem.get().getJavaTestCaseFile();

        Path directoryPath = Paths.get("temp", jobId);
        Path testFilePath = directoryPath.resolve(testCaseFileName);
        Path codeFilePath = directoryPath.resolve(codeFileName);

        try {
            if (Files.notExists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            Files.write(testFilePath, testCaseCode.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(codeFilePath, this.code.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
