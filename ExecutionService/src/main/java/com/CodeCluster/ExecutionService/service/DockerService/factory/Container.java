package com.CodeCluster.ExecutionService.service.DockerService.factory;

public interface Container {

   public String executeProgram(String problemId, String jobId, String code);
}
