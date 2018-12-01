package com.company.app;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@JsonSerialize(using = PropertyBAG.JsonSerializeAdapter.class)
@JsonDeserialize(using = PropertyBAG.JsonDeserializeAdapter.class)
public final class PropertyBAG {

  // Returns null if the attribute doesn't exist
  // Returns empty Optional if attribute exists but has no value
  // Returns Optional with value if attribute exists and has a value
  public static Optional<Object> getFieldOrProperty(Object obj, String field) {

    Optional<Object> value = Optional.empty();

    if (!value.isPresent()) {

      try {

        value = Optional.ofNullable(obj.getClass().getField(field).get(obj));
      } catch (NoSuchFieldException e) {

        // The field is not defined - this is NOT an error, it is simply an open/undefined field
        // This makes NO sense to log or throw an exception.
      } catch (IllegalAccessException e) {

        throw new IllegalArgumentException("Inaccessible field : " + field, e);
      }
    }

    return value;
  }

  //region BAG internal data
  private static class Value {

    Object propertyValue;
  }

  private static class Description {

    Class<?> propertyType;
    Object defaultValue;
  }

  private Value value;
  private Description description;
  //endregion

  // Constructor is private - only creation is via the "ofXYZ" static methods.
  private PropertyBAG() {
  }

  //region BAG type methods
  public boolean isValue() {

    return value != null;
  }

  public boolean isDescription() {

    return description != null;
  }
  //endregion

  @Override
  public String toString() {

    if (isValue()) {

      return String.format("value [%s]", value.propertyValue);
    } else if (isDescription()) {

      StringBuilder sb = new StringBuilder();

      if (description.propertyType != null) {

        sb.append(String.format(", type [%s]", description.propertyType));
      }

      if (description.defaultValue != null) {

        sb.append(String.format(", default [%s]", description.defaultValue));
      }

      sb.delete(0, 2);

      return sb.toString();
    }

    return "unknown";
  }

  //region Get methods
  public Object getValue() {

    return value.propertyValue;
  }

  public Class<?> getDescriptionType() {

    return description.propertyType;
  }

  public Object getDescriptionDefaultValue() {

    return description.defaultValue;
  }
  //endregion

  //region Static builders
  public static PropertyBAG ofValue(Object value) {

    if (value == null) {

      throw new IllegalArgumentException("value cannot be null");
    }

    PropertyBAG object = new PropertyBAG();

    object.value = new Value();
    object.value.propertyValue = value;

    return object;
  }

  public static PropertyBAG ofDescription(Class<?> type, Object defaultValue) {

    if (type == null && defaultValue == null) {

      throw new IllegalArgumentException(
          "Invalid description. Both type and default value cannot be null");
    }

    if (type != null && defaultValue != null && !type.isInstance(defaultValue)) {

      throw new IllegalArgumentException("defaultValue does not match type");
    }

    PropertyBAG object = new PropertyBAG();

    object.description = new Description();
    object.description.propertyType = type;
    object.description.defaultValue = defaultValue;

    return object;
  }

  public static PropertyBAG ofDescriptionType(Class<?> type) {

    return ofDescription(type, null);
  }

  public static PropertyBAG ofDescriptionDefaultValue(Object defaultValue) {

    return ofDescription(null, defaultValue);
  }
  //endregion

  //region JSON Serialization/Deserialization
  private static final String JAVA_PREFIX = String.class.getName().replace("String", "");
  private static final String ARRAY_SUFFIX = "[]";

  public static class JsonDeserializeAdapter extends JsonDeserializer<PropertyBAG> {

    private static <T> Stream<T> iterStream(Iterator<T> iter) {

      return StreamSupport.stream(
          Spliterators.spliteratorUnknownSize(
              iter,
              Spliterator.ORDERED),
          false);
    }

    private static Object convertTreeNode(TreeNode value) {

      if (value.isValueNode()) {

        ValueNode node = (ValueNode) value;

        if (node.isBoolean()) {
          return node.asBoolean();
        }

        if (node.isInt()) {
          return node.asInt();
        }

        return node.asText();
      }

      if (value.isArray()) {

        ArrayNode node = (ArrayNode) value;

        return iterStream(node.elements())
            .map(JsonDeserializeAdapter::convertTreeNode)
            .toArray();
      }

      if (value.isObject()) {

        ObjectNode node = (ObjectNode) value;

        return iterStream(node.fields())
            .collect(Collectors.toMap(
                Entry::getKey,
                x -> convertTreeNode(x.getValue())));
      }

      return null;
    }

    private static Class<?> getType(String typeName) throws IOException {

      try {

        return Class.forName(typeName);
      } catch (ClassNotFoundException e) {

        try {

          return Class.forName(JAVA_PREFIX + typeName);
        } catch (ClassNotFoundException e2) {

          throw new IOException("Unknown type : " + typeName);
        }
      }
    }

    private static PropertyBAG getBAGForValue(Object value) throws IOException {

      if (!(value instanceof Map)) {
        return PropertyBAG.ofValue(value);
      }

      Map resultMap = (Map) value;
      Object typeValue = resultMap.get("type");
      Object defaultValue = resultMap.get("default");
      Class<?> type = null;

      if (typeValue != null && typeValue instanceof String) {

        type = getTypeFromName((String) typeValue);
      }

      return
          (type != null || defaultValue != null)
              ? PropertyBAG.ofDescription(type, defaultValue)
              : PropertyBAG.ofValue(value);
    }

    private static Class<?> getTypeFromName(String typeValue) throws IOException {
      String typeName = typeValue;
      boolean isArray = typeName.endsWith(ARRAY_SUFFIX);

      if (isArray) {

        typeName = typeName.substring(0, typeName.length() - ARRAY_SUFFIX.length());
      }

      Class<?> type = getType(typeName);

      if (isArray) {

        type = Array.newInstance(type, 0).getClass();
      }
      return type;
    }

    @Override
    public PropertyBAG deserialize(
        final JsonParser jp, final DeserializationContext ctxt)
        throws IOException {

      TreeNode value = jp.readValueAsTree();
      Object result = convertTreeNode(value);

      if (result == null) {
        throw new IOException("Failed to parse JSON value");
      }

      return getBAGForValue(result);
    }
  }

  public static class JsonSerializeAdapter extends JsonSerializer<PropertyBAG> {

    @Override
    public void serialize(
        final PropertyBAG value, final JsonGenerator jgen,
        final SerializerProvider provider) throws
        IOException {
      if (value.value != null) {

        serializeValue(jgen, value.value.propertyValue);
      } else {

        jgen.writeStartObject();

        if (value.description.propertyType != null) {

          jgen.writeStringField("type", getNameForType(value.description.propertyType));
        }

        if (value.description.defaultValue != null) {

          jgen.writeFieldName("default");
          serializeValue(jgen, value.description.defaultValue);
        }

        jgen.writeEndObject();
      }
    }

    private String getNameForType(Class<?> type) {

      String suffix = "";

      if (type.isArray()) {

        suffix = ARRAY_SUFFIX;
        type = type.getComponentType();
      }

      String typeName = type.getName();

      if (typeName.startsWith(JAVA_PREFIX)) {

        typeName = typeName.substring(JAVA_PREFIX.length());
      }

      return typeName + suffix;
    }

    private static void serializeValue(final JsonGenerator jgen, Object value) throws IOException {
      if (value instanceof Object[]) {

        jgen.writeStartArray();

        Object[] arr = (Object[]) value;
        for (Object entry : arr) {
          serializeValue(jgen, entry);
        }

        jgen.writeEndArray();
      } else if (value instanceof Map) {

        jgen.writeStartObject();

        Map<String,Object> map = (Map) value;
        for (Map.Entry<String,Object> entry : map.entrySet()) {

          jgen.writeFieldName(entry.getKey());
          serializeValue(jgen, entry.getValue());
        }

        jgen.writeEndObject();
      } else {

        jgen.writeObject(value);
      }
    }
  }
  //endregion
}
