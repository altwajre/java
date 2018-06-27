package com.company.app.secure;

import com.company.app.not.needed.Gatekeeper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;

import java.io.IOException;

public class MaskedSerializer extends StdSerializer<String> implements ContextualSerializer {

  public MaskedSerializer() {
    super(String.class);
    ThreadContext.bind(Gatekeeper.INSTANCE.securityManager());
  }

  @Override
  public void serialize(String value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException {
    jgen.writeString("***");
  }

  @Override
  public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) {
    return shouldMask() ? this : new StringSerializer();
  }

  public boolean shouldMask() {
    final Subject subject = SecurityUtils.getSubject();

    //make an external call to get the roles for this API
    return !(subject.isAuthenticated() & subject.hasRole("c3"));
  }
}
