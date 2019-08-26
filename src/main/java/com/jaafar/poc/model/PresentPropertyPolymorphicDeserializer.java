package com.kingland.kroger.vcp.proposal.common.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class PresentPropertyPolymorphicDeserializer<T> extends StdDeserializer<T> {

    private final Map<String, Class<?>> propertyNameToType;

    public PresentPropertyPolymorphicDeserializer(Class<T> clazz) {
        super(clazz);
        this.propertyNameToType = Arrays.stream(clazz.getAnnotation(JsonSubTypes.class).value())
                .collect(Collectors.toMap(JsonSubTypes.Type::name, JsonSubTypes.Type::value));
    }

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
        ObjectNode object = objectMapper.readTree(jsonParser);
        for (String propertyName : propertyNameToType.keySet()) {
            if (object.has(propertyName)) {
                return deserialize(objectMapper, propertyName, object);
            }
        }
        return deserialize(objectMapper, "", object);
    }

    @SuppressWarnings("unchecked")
    private T deserialize(ObjectMapper objectMapper,
                          String propertyName,
                          ObjectNode object) throws IOException {
        return (T) objectMapper.treeToValue(object, propertyNameToType.get(propertyName));
    }
}
