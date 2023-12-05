package org.krroks.offsets.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile;
import org.krroks.offsets.model.OffsetRepository;
import org.krroks.offsets.model.Offsets;
import org.krroks.offsets.model.OffsetsMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
@Slf4j
public class OffsetReaderService {

    @Autowired
    private ObjectMapper objectMapper;
    public Offsets getOffsets(String json, OffsetRepository offsetRepository, String version) throws JsonProcessingException {
        OffsetsMap offsetsMap = offsetRepository.getOffsetsMap();
        Map<String, Object> jsonData = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        Offsets offsets = new Offsets();

        log.debug(jsonData.toString());

        for (Map.Entry<String, String> entry : offsetsMap.entrySet()) {
            String jsonKey = entry.getKey();
            String offsetField = entry.getValue();

            if (offsetField != null && offsetField.contains("/")){
                String[] offsetFields = offsetField.split("/");
                log.debug("Object: " + offsetFields[0] + " Key: " + offsetFields[1]);
                Map<String, Object> object = (Map<String, Object>) jsonData.get(offsetFields[0]);
                if (object != null) {
                    Object offsetValue = object.get(offsetFields[1]);
                    log.debug("1Key: " + offsetField + " Value: " + offsetValue);
                    if (offsetValue != null)
                        offsets.add(jsonKey, parseHex(offsetValue.toString()));
                }
            }else if (offsetField != null && jsonData.containsKey(offsetField)) {
                String offsetValue = (String) jsonData.get(offsetField);
                log.debug("1Key: " + offsetField + " Value: " + offsetValue);
                if (offsetValue != null)
                    offsets.add(jsonKey, parseHex(offsetValue));
            }else{
                log.debug("2Key: " + offsetField + " Value: " + "null");
            }


        }
        offsets.setVersion(version);
        return offsets;
    }

    public static int parseHex(String hex){
        if (hex.toLowerCase().startsWith("0x"))
            hex = hex.substring(2);
        return Integer.parseInt(hex, 16);
    }

    public Offsets getOffsetsFromIni(String content, OffsetRepository offsetRepository) {
        Offsets offsets = new Offsets();
        String scriptVersion = "";
        try {
            InputStream is = new ByteArrayInputStream(content.getBytes());
            Ini ini = new Ini(is);

            if (offsetRepository.getVersionMap().contains("/")){
                String[] versionFields = offsetRepository.getVersionMap().split("/");
                scriptVersion = ini.get(versionFields[0], versionFields[1]);
                offsets.setVersion(scriptVersion);

                OffsetsMap offsetsMap = offsetRepository.getOffsetsMap();
                for (Map.Entry<String, String> entry : offsetsMap.entrySet()) {
                    String jsonKey = entry.getKey();
                    String offsetField = entry.getValue();

                    if (offsetField != null && offsetField.contains("/")) {
                        String[] offsetFields = offsetField.split("/");
                        log.debug("Object: " + offsetFields[0] + " Key: " + offsetFields[1]);
                        Profile.Section offsetsSection = ini.get(offsetFields[0]);
                        if (offsetsSection != null) {
                            String offsetValue = offsetsSection.get(offsetFields[1]);
                            log.debug("1Key: " + offsetField + " Value: " + offsetValue);
                            if (offsetValue != null)
                                offsets.add(jsonKey, parseHex(offsetValue));
                        }
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return offsets;
    }
}
