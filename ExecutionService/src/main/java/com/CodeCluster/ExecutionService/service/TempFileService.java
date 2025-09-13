package com.CodeCluster.ExecutionService.service;

import com.CodeCluster.ExecutionService.model.ProblemsTable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Service
public class TempFileService {

    public File prepareTempFolder(String jobId, String code, ProblemsTable problem, String fileExtension) {
        Path directoryPath = Paths.get("temp", jobId);
        File folder = directoryPath.toFile();

        try {
            if (!folder.exists() && !folder.mkdirs()) {
                throw new RuntimeException("Failed to create temp folder for job " + jobId);
            }

            Path codeFilePath = directoryPath.resolve(problem.getCodeFileName() + fileExtension);
            Path testFilePath = directoryPath.resolve(problem.getTestCaseFileName() + fileExtension);

            Files.write(testFilePath, problem.getJavaTestCaseFile().getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            Files.write(codeFilePath, code.getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException("Error writing temp files for job " + jobId, e);
        }

        return folder;
    }

    /// deletes folder created with jobId
    public void cleanupTempFolder(String jobId) {
        Path directoryPath = Paths.get("temp", jobId);
        try {
            if (Files.exists(directoryPath)) {
                Files.walk(directoryPath)
                        .sorted((p1, p2) -> p2.compareTo(p1)) // children first, then parent
                        .forEach(path -> {
                            try {
                                Files.deleteIfExists(path);
                            } catch (IOException e) {
                                throw new RuntimeException("Failed to delete: " + path, e);
                            }
                        });
            }
        } catch (IOException e) {
            throw new RuntimeException("Error cleaning up temp folder for job " + jobId, e);
        }
    }

}
