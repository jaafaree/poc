package com.jaafar.poc.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaafar.poc.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 7/11/2019 9:16 AM
 */
@Slf4j
@RestController
@RequestMapping("test")
public class PoCController {

    @Autowired
    private AbstractApplicationContext applicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @ResponseBody
    @DeleteMapping("testWhetherDeleteMethodCouldReceiveRequestBody")
    public User testWhetherDeleteMethodCouldReceiveRequestBody(@RequestBody User user) {
        log.info(user.toString());
        log.info(applicationContext.getBean("poCController").toString());
        List<User> arr = new ArrayList<>();
        Map<Long, User> collect = arr.stream()
                .collect(Collectors.toMap(User::getId, u -> u));
        return user;
    }

    @PostMapping("testObjectMapper")
    public User testObjectMapper(@RequestBody User user) {
        log.info(user.toString());
        log.info(applicationContext.getBean("poCController").toString());
        List<User> arr = new ArrayList<>();
        Map<Long, User> collect = arr.stream()
                .collect(Collectors.toMap(User::getId, u -> u));
        return user;
    }

    @GetMapping("/csv")
    public ResponseEntity<byte[]> download() throws JsonProcessingException {

        List<User> arr = new ArrayList<>();
        arr.add(new User("test", 1L));
        arr.add(new User("test", 1L));
        arr.add(new User("test", 1L));
        arr.add(new User("test", 1L));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/json; charset=UTF-8"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "demo.json" + "\"")
                .body(objectMapper.writeValueAsBytes(arr));
    }

}
