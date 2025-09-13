package com.CodeCluster.ExecutionService.service.DockerService;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Service
public class JavaDockerRunner {

    public String runJavaTests(File folder,String testCaseFileName) {
        String containerWorkDir = "/app";
        String hostVolumePath = folder.getAbsolutePath();

        String[] command = {
                "docker", "run", "--rm",
                "-v", hostVolumePath + ":/app",
                "-w", containerWorkDir,
                "openjdk:17",
                "sh", "-c",
                "javac " + testCaseFileName + ".java" +
                        " && java " + testCaseFileName
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
            throw new RuntimeException("Docker execution failed: " + e.getMessage(), e);
        }
    }
}
