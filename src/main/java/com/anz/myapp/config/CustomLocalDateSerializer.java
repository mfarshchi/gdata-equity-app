/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/config/CustomLocalDateSerializer.java.p.vm
 */
package com.anz.myapp.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class CustomLocalDateSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate date, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(date != null ? ISO_LOCAL_DATE.format(date) + "T22:00:00.000Z" : null); // 22: as primeng sent it...
    }
}
