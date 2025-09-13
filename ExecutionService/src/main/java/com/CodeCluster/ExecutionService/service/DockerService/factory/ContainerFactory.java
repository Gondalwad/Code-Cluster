package com.CodeCluster.ExecutionService.service.DockerService.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContainerFactory {
    private final JavaContainer javaContainer;

    @Autowired
    public ContainerFactory(JavaContainer javaContainer) {
        this.javaContainer = javaContainer;
    }

    /// generate containers
    public Container buildContainer(String programmingLanguage) {

        if(programmingLanguage.equalsIgnoreCase("java"))
            return this.javaContainer;

        throw new IllegalArgumentException("No such programming Language");
    }
}
