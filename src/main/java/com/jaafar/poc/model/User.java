package com.jaafar.poc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 7/11/2019 9:19 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseModel{

    public User(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private String name;
    private Long id;
    private String password;
    private Long challengeOne;
    private String answerOne;
    private Long challengeTwo;
    private String answerTwo;

    public User protectSensitiveData(String... fields) {
        final Object defaultClearValue = null;
        final List<String> defaultFields = Arrays.asList("password", "answerOne", "answerTwo");
        List<String> cols = Arrays.stream(fields)
                .collect(Collectors.toList());
        cols.addAll(defaultFields);
        cols.stream()
                .filter(Objects::nonNull)
                .distinct()
                .forEach(col -> {
                        Class<?> clazz = this.getClass();
                        while (clazz != null) {
                            try {
                                try {
                                    this.getClass()
                                            .getMethod(
                                                    "set" + col.substring(0, 1).toUpperCase() + col.substring(1),
                                                    clazz.getDeclaredField(col).getType()
                                            )
                                            .invoke(this, defaultClearValue);
                                    clazz = null;
                                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ignore) {
                                }
                            } catch (NoSuchFieldException ignore) {
                                clazz = clazz.getSuperclass();
                            }
                        }
                });
        return this;
    }
}
