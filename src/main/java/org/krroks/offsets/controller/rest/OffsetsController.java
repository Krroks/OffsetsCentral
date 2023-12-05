package org.krroks.offsets.controller.rest;

import org.krroks.offsets.model.OffsetRepository;
import org.krroks.offsets.model.Offsets;
import org.krroks.offsets.service.GitHubService;
import org.krroks.offsets.service.OffsetRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OffsetsController {

    @Autowired
    private GitHubService gitHubService;

    @Autowired
    private OffsetRepositoryService offsetRepositoryService;

    @GetMapping("/offsets")
    @ResponseBody()
    public ResponseEntity<Offsets> offsets() {
        try {
            List<Offsets> offsetsList = new ArrayList<>();
            for (OffsetRepository offsetRepository : offsetRepositoryService.getOffsetRepositories()) {
                offsetsList.add(gitHubService.getLastOffsets(offsetRepository));
            }
            offsetsList.sort((o1, o2) -> o1.getVersion().compareTo(o2.getVersion()));
            Offsets offsets = new Offsets();
            for (Offsets offsets1 : offsetsList) {
                offsets.putAll(offsets1);
            }

            return ResponseEntity.ok(offsets);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
