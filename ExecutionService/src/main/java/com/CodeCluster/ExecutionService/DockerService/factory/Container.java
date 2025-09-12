package com.CodeCluster.ExecutionService.DockerService.factory;

public interface Container {

   public String executeProgram(String problemId, String jobId, String code);
}
