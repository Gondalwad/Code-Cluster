package com.CodeCluster.ExecutionService.DockerService;

import com.CodeCluster.ExecutionService.DTO.SubmitRequestDTO;
import com.CodeCluster.ExecutionService.DockerService.factory.JavaContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class ContainerService {

    @Autowired
    JavaContainer jc;

    public String createContainer(SubmitRequestDTO dto){

        String result = jc.executeProgram(dto.getProblemId(),dto.getJobId(),dto.getCodeSolution());

        System.out.println(result);
        return "";
    }



}
