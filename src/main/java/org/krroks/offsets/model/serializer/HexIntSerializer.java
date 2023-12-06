package org.krroks.offsets.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class HexIntSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == 0)
            gen.writeNull();
        else
            gen.writeString("0x" + Integer.toHexString(value));
    }
}
