package com.andreea.librarium.serialization;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampDeserializer extends JsonDeserializer<Timestamp> {
    private static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String dateValue = jsonParser.getValueAsString();
        try {
            Date date = DATE_TIME_FORMATTER.parse(dateValue);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new IOException("Error parsing date string: " + dateValue, e);
        }
    }
}
