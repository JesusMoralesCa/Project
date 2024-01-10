package com.jmorales.springbootreact.Serialice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class BlobBase64Serializer extends JsonSerializer<Blob> {

    @Override
    public void serialize(Blob blob, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (blob != null) {
            byte[] bytes = new byte[0];
            try {
                bytes = blob.getBytes(1, (int) blob.length());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String base64Encoded = Base64.encodeBase64String(bytes);
            jsonGenerator.writeString(base64Encoded);
        }
    }
}