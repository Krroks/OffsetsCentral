package org.krroks.offsets.service;

import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.krroks.offsets.model.OffsetRepository;
import org.krroks.offsets.model.Offsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class GitHubService {

    @Autowired
    private OffsetReaderService offsetReaderService;

    @Value("${app.github.token}")
    private String githubToken;

    public GitHub getGithub() throws IOException {
        GitHub github = null;
        if (githubToken.isEmpty())
            github = new GitHubBuilder().build();
        else
            github = new GitHubBuilder().withOAuthToken(githubToken).build();
        return github;
    }

    public GHRepository getRepository(String repoOwner, String repoName) throws IOException {
        GitHub github = getGithub();
        return github.getRepository(repoOwner + "/" + repoName);
    }



    public Offsets getLastOffsets(OffsetRepository offsetRepository) throws IOException {

        GHRepository repository = getRepository(offsetRepository.getRepoOwner(), offsetRepository.getRepoName());

        GHContent previousFile = null;
        GHContent content = null;
        if (offsetRepository.getRepoFileName().isEmpty()) {
            List<GHContent> contentMap =  repository.getDirectoryContent(offsetRepository.getRepoOffsetsPath());
            log.info(offsetRepository.toString());

            for (GHContent entry : contentMap) {
                if (previousFile != null) {
                    if (compareVersions(previousFile.getName(), entry.getName()) < 0) {
                        previousFile = entry;
                    }
                }else {
                    previousFile = entry;
                }
            }
            content = repository.getFileContent(offsetRepository.getRepoOffsetsPath() + "/" + previousFile.getName());
        }else {
            content = repository.getFileContent(offsetRepository.getRepoOffsetsPath() + "/" + offsetRepository.getRepoFileName());
        }

        if (content.getName().endsWith(".json"))
            return offsetReaderService.getOffsets(content.getContent(), offsetRepository, getVersionFromFileName(content.getName()));
        else if (content.getName().endsWith(".ini"))
            return offsetReaderService.getOffsetsFromIni(content.getContent(), offsetRepository);

        log.error("File extension not supported");
        return null;
    }

    public Offsets getOffsetsFromVersion(OffsetRepository offsetRepository, String targetVersion) throws IOException {
        GHRepository repository = getRepository(offsetRepository.getRepoOwner(), offsetRepository.getRepoName());

        GHContent previousFile = null;
        GHContent content = null;

        if (offsetRepository.getRepoFileName().isEmpty()) {
            List<GHContent> contentMap =  repository.getDirectoryContent(offsetRepository.getRepoOffsetsPath());
            log.info(offsetRepository.toString());
            List<GHContent> entriesWithTargetVersion = new ArrayList<>();
            for (GHContent entry : contentMap) {
                if (entry.getName().contains(targetVersion)) {
                    entriesWithTargetVersion.add(entry);
                }
            }
            if (entriesWithTargetVersion.isEmpty())
                return null;
            else
            {
                for (GHContent entry : entriesWithTargetVersion) {
                    if (previousFile != null) {
                        if (compareVersions(previousFile.getName(), entry.getName()) < 0) {
                            previousFile = entry;
                        }
                    }else {
                        previousFile = entry;
                    }
                }
                content = repository.getFileContent(offsetRepository.getRepoOffsetsPath() + "/" + previousFile.getName());
            }
            if (content.getName().endsWith(".json"))
                return offsetReaderService.getOffsets(content.getContent(), offsetRepository, getVersionFromFileName(content.getName()));
            else if (content.getName().endsWith(".ini"))
                return offsetReaderService.getOffsetsFromIni(content.getContent(), offsetRepository);
        }else{
            content = repository.getFileContent(offsetRepository.getRepoOffsetsPath() + "/" + offsetRepository.getRepoFileName());

            if (content.getName().endsWith(".ini")){
                return offsetReaderService.getOffsetsFromIni(content.getContent(), offsetRepository, targetVersion);

            }

        }
        return null;
    }

    // Método para comparar versiones
    private static int compareVersions(String fileName1, String fileName2) {
        String version1 = getVersionFromFileName(fileName1);
        String version2 = getVersionFromFileName(fileName2);
        String[] parts1 = version1.split("\\.");
        String[] parts2 = version2.split("\\.");

        for (int i = 0; i < Math.min(parts1.length, parts2.length); i++) {
            int part1 = Integer.parseInt(parts1[i]);
            int part2 = Integer.parseInt(parts2[i]);

            if (part1 != part2) {
                return Integer.compare(part1, part2);
            }
        }

        return Integer.compare(parts1.length, parts2.length);
    }

    private static String getVersionFromFileName(String fileName) {
        int startIndex = fileName.indexOf('-') + 1;
        int endIndex = fileName.lastIndexOf('.');
        return fileName.substring(startIndex, endIndex);
    }
}
