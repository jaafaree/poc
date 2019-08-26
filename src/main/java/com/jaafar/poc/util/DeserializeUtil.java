package com.jaafar.poc.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 7/18/2019 6:13 PM
 */
public class DeserializeUtil {

    public static <T> List<T> deserialize(ObjectMapper objectMapper, String content) {
        try {
            return objectMapper.readValue(content, new TypeReference<ArrayList<T>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
