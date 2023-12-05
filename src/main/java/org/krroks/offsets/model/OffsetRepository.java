package org.krroks.offsets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OffsetRepository {

    private String repoOwner;
    private String repoName;
    private String repoOffsetsPath;
    private String repoFileName = "";
    private OffsetsMap offsetsMap;
    private String versionMap = "";

    public String toString() {
        return "OffsetRepository(repoOwner=" + this.getRepoOwner() + ", repoName=" + this.getRepoName() + ", repoOffsetsPath=" + this.getRepoOffsetsPath() + ", repoFileName=" + this.getRepoFileName() + ", offsetsMap=" + this.getOffsetsMap().toString() + ")";
    }
}
