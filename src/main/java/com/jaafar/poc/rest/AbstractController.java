package com.jaafar.poc.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaafar.poc.util.DeserializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 7/18/2019 5:36 PM
 */
public abstract class AbstractController<T> {

    @Autowired
    private ObjectMapper objectMapper;

    @ResponseBody
    @PostMapping("testGenerics")
    public List<T> getInvalidRecords(@RequestBody List<T> arr) throws IOException {
        String content =
                "    {\n" +
                "        \"name\": \"1\",\n" +
                "        \"id\": 2\n" +
                "    }\n";

        List<T> arr2 = this.objectMapper.readValue(content, new TypeReference<T>(){});

        List<Object> deserialize = DeserializeUtil.deserialize(this.objectMapper, "[\n" +
                "    {\n" +
                "        \"name\": \"1\",\n" +
                "        \"id\": 2\n" +
                "    }\n" +
                "]");

        List<T> arr3 = this.deserialize(content);

        System.out.println(arr);
        return arr;
    }

    abstract protected List<T> deserialize(String content);

}
