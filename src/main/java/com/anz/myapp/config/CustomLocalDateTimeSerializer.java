/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/config/CustomLocalDateTimeSerializer.java.p.vm
 */
package com.anz.myapp.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime date, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(date != null ? ISO_LOCAL_DATE_TIME.format(date) + ".000Z" : null);
    }
}
