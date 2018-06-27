package com.company.app.secure;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class CustomBeanSerializerModifier extends BeanSerializerModifier {
  @Override
  public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
                                                   BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {

    final Subject subject = SecurityUtils.getSubject();
//    List<String> allowedRoles = Arrays.asList("admin", "c3");
    if (nonNull(subject) &&
        subject.isAuthenticated() &&
        (subject.hasRole("c3") || subject.hasRole("admin"))) {
      return beanProperties;
    } else {
      /* Return the properties that do not contain the Secured annotation */
      return beanProperties
          .stream()
          .filter(property -> property.getAnnotation(Secured.class) == null)
          .collect(Collectors.toList());
    }
  }
}
