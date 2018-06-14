package com.company.app.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public enum GlobalMapper {
  INSTANCE;

  ObjectMapper mapper = new ObjectMapper();
  ObjectMapper prettyMapper = new ObjectMapper();

  GlobalMapper() {
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule
      .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(ISO_LOCAL_DATE_TIME))
      .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(ISO_LOCAL_DATE_TIME));

    mapper.setSerializationInclusion(NON_NULL)
      .registerModule(new ParameterNamesModule())
      .registerModule(new Jdk8Module())
      .registerModule(javaTimeModule)
      .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    prettyMapper.setSerializationInclusion(NON_NULL)
      .registerModule(new ParameterNamesModule())
      .registerModule(new Jdk8Module())
      .registerModule(javaTimeModule)
      .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
      .enable(SerializationFeature.INDENT_OUTPUT);
  }

  public ObjectMapper mapper() {
    return mapper;
  }

  public ObjectMapper prettyMapper() {
    return prettyMapper;
  }
}
