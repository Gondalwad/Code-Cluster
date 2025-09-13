package com.CodeCluster.ExecutionService.service;

import com.CodeCluster.ExecutionService.DTO.SubmitRequestDTO;
import com.CodeCluster.ExecutionService.service.DockerService.factory.Container;
import com.CodeCluster.ExecutionService.service.DockerService.factory.ContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestCode {

    private final ContainerFactory containerFactory;

    @Autowired
    public TestCode(ContainerFactory containerFactory) {
        this.containerFactory = containerFactory;
    }

    public String test(SubmitRequestDTO dto){
        /// Gets container for programming language
        Container container = containerFactory.buildContainer(dto.getProgrammingLanguage());
        /// saves returned string whether it is error or output
        String output = container.executeProgram(dto.getProblemId(), dto.getJobId(), dto.getCodeSolution());

        // Temprority printint output and will be removed later after saving response into DB
        System.out.println(output);

        return output;
    }



}
