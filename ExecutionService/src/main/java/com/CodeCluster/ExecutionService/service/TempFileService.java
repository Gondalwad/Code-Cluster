package com.CodeCluster.ExecutionService.service;

import com.CodeCluster.ExecutionService.model.ProblemsTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Service
public class TempFileService {

    private static final Logger log = LoggerFactory.getLogger(TempFileService.class);

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

    /// method to delete temp folder and files
    public void cleanupTempFolder(String jobId) {
        String absolutePath = new File("temp", jobId).getAbsolutePath();
        File directory = new File(absolutePath);

        if (directory.exists() && directory.isDirectory()) {
            deleteRecursively(directory);
        } else {
            log.error("Directory not found or is not a valid directory: {}", absolutePath);
        }
    }


     /// private method to deletes the specified directory and its contents.
    private void deleteRecursively(File dir) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteRecursively(file);
                } else {
                    if (!file.delete()) {
                        log.warn("Failed to delete file: {}", file.getAbsolutePath());
                    }
                }
            }
        }
        /// warning if deletion failed
        if (!dir.delete()) {
            log.warn("Failed to delete directory: {}", dir.getAbsolutePath());
        }
    }
}
