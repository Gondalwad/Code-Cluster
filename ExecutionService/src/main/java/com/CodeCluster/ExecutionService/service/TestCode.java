package com.CodeCluster.ExecutionService.service;

import com.CodeCluster.ExecutionService.DTO.SubmitRequestDTO;
import com.CodeCluster.ExecutionService.service.DockerService.factory.Container;
import com.CodeCluster.ExecutionService.service.DockerService.factory.ContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/// Class is responsible to test code by creating containers
@Service
public class TestCode {

    private final ContainerFactory containerFactory;

    @Autowired
    public TestCode(ContainerFactory containerFactory) {
        this.containerFactory = containerFactory;
    }

    /// method executing actual task of the class
    public String test(SubmitRequestDTO dto){
        /// Gets container for programming language
        Container container = containerFactory.buildContainer(dto.getProgrammingLanguage());

        /// saves returned string whether it is error or output
        String output = container.executeProgram(dto.getProblemId(), dto.getJobId(), dto.getCodeSolution());


        return output;
    }



}
