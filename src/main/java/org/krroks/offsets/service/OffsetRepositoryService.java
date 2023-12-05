package org.krroks.offsets.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.krroks.offsets.model.OffsetRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OffsetRepositoryService {

    private final ObjectMapper objectMapper;

    public OffsetRepositoryService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    public List<OffsetRepository> getOffsetRepositories() {

        File repositoriesFolder = new File("repositories");
        if (!repositoriesFolder.exists()) {
            repositoriesFolder.mkdir();
        }

        List<OffsetRepository> offsetRepositoriesList = new ArrayList<>();

        if (repositoriesFolder.list().length == 0) {
            copyRepositoriesFromResources();
        }

        File[] repositoryFiles = repositoriesFolder.listFiles();
        if (repositoryFiles != null) {
            for (File repositoryFile : repositoryFiles) {
                if (!repositoryFile.isDirectory() && repositoryFile.getName().endsWith(".json")) {
                    try {
                        OffsetRepository offsetRepository = objectMapper.readValue(repositoryFile, OffsetRepository.class);
                        offsetRepositoriesList.add(offsetRepository);
                        log.debug("OffsetRepository: " + offsetRepository.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return offsetRepositoriesList;
    }

    private void copyRepositoriesFromResources() {
        // Specify the path to the resources/repositories directory
        String resourcesRepoPath = "repositories"; // Assuming it's already in the resources folder

        // Get the resources/repositories as a Spring Resource
        Resource resource = new ClassPathResource(resourcesRepoPath);

        try {
            // Copy the contents of resources/repositories to the repositories folder
            org.apache.commons.io.FileUtils.copyDirectory(resource.getFile(), new File("repositories"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
