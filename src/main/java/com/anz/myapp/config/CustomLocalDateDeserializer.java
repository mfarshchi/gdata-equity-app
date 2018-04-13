/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/config/CustomLocalDateDeserializer.java.p.vm
 */
package com.anz.myapp.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    public LocalDate deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {

        if (parser.hasTokenId(JsonTokenId.ID_STRING)) {
            String date = parser.getText().trim();

            if (date.isEmpty()) {
                return null;
            }

            return LocalDateTime.ofInstant(Instant.parse(date), ZoneOffset.UTC).toLocalDate();
        }

        return null;
    }
}
