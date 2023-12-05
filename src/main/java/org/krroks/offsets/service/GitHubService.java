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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GitHubService {

    @Autowired
    private OffsetReaderService offsetReaderService;

    @Value("${app.github.token}")
    private String githubToken;

    public Offsets getLastOffsets(OffsetRepository offsetRepository) throws IOException {

        GitHub github = null;
        if (githubToken.isEmpty())
            github = new GitHubBuilder().build();
        else
            github = new GitHubBuilder().withOAuthToken(githubToken).build();

        GHRepository repository = github.getRepository(offsetRepository.getRepoOwner() + "/" + offsetRepository.getRepoName());

        List<GHContent> contentMap =  repository.getDirectoryContent(offsetRepository.getRepoOffsetsPath());
        log.info(offsetRepository.toString());

        GHContent previousFile = null;
        GHContent content = null;
        if (offsetRepository.getRepoFileName().isEmpty()) {
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
        log.debug("File Content: " + content.getContent());
        log.info(offsetRepository.getOffsetsMap().toString());

        log.info(content.getName());

        if (content.getName().endsWith(".json"))
            return offsetReaderService.getOffsets(content.getContent(), offsetRepository, getVersionFromFileName(content.getName()));
        else if (content.getName().endsWith(".ini"))
            return offsetReaderService.getOffsetsFromIni(content.getContent(), offsetRepository);

        log.error("File extension not supported");
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
