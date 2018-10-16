package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public enum GlobalMapper {
  INSTANCE;
  private static final Logger LOG = LoggerFactory.getLogger(GlobalMapper.class);
  private ObjectMapper mapper;
  private ObjectMapper prettyMapper;

  GlobalMapper() {
    mapper = basicMapper().enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT);
    prettyMapper = basicMapper().enable(SerializationFeature.INDENT_OUTPUT);
  }

  public <T> T clone(T value, Class<T> type) {
    T result = null;
    try {
      String jsonObj = mapper.writeValueAsString(value);
      result = mapper.readValue(jsonObj, type);
    } catch (IOException e) {
      LOG.error(e.getMessage(), e);
    }
    return result;
  }

  public ObjectMapper mapper() {
    return mapper;
  }

  public ObjectMapper getPrettyMapper() {
    return prettyMapper;
  }

  private ObjectMapper basicMapper() {
    return new ObjectMapper()
        .setSerializationInclusion(NON_NULL)
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }
}
