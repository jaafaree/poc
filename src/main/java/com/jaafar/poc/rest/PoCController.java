package com.jaafar.poc.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaafar.poc.exception.TestException;
import com.jaafar.poc.model.BaseResponse;
import com.jaafar.poc.model.CollectionResponse;
import com.jaafar.poc.model.OptionRequest;
import com.jaafar.poc.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
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

    @GetMapping("/testruntimeexception")
    public void testruntimeexception() {
        throw new SubTestException();
    }

    class SubTestException extends TestException {
        public SubTestException() {
            super();
        }
    }

    @GetMapping("/testStaticField")
    public User testStaticField() {
        return new User("jaafar", 1L);
    }

    @GetMapping("/testResponseStatusException")
    public String testResponseStatusException() {
        throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/testProtectSensitiveData")
    public User testProtectSensitiveData() {
        User user = new User("test", 1L);
        user.setAnswerOne("1");
        user.setAnswerTwo("2");
        user.setPassword("123");
        user.setAn("adf");
        return user.protectSensitiveData("en");
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return new User();
    }

    @GetMapping("/vendors")
    public List<User> getUsers(@RequestParam Long... ids) {
        return Collections.singletonList(new User());
    }

    @GetMapping("/vendors/{id}")
    public User getUser2(@PathVariable String id) {
        return new User();
    }

    @PostMapping("/vendors")
    public BaseResponse searchOption(@RequestBody OptionRequest request) {
        final List<Map<String, String>> repo = new ArrayList<>();
        for (long i = 0; i < 100; i++) {
            Map<String, String> option = new HashMap<>();
            option.put("id", i+ "");
            option.put("text", "option " + i);
            repo.add(option);
        }
        final List<Map<String, String>> result;
        if (request.getIds() != null) {
            result = request.getIds().stream()
                    .map(id -> repo.stream().filter(i -> id .equals(i.get("id"))).findAny().orElse(null))
                    .collect(Collectors.toList());
        } else if (request.getSearchInput() != null) {
            result = repo.stream()
                    .filter(i -> i.get("text").contains(request.getSearchInput()))
                    .collect(Collectors.toList());
        } else {
            result = repo;
        }
        long offset = request.getPager().getPer() * (request.getPager().getCur() - 1);
        long limit = request.getPager().getPer();
        return new CollectionResponse()
                .total((long) result.size())
                .data(
                        result.stream()
                                .skip(offset)
                                .limit(limit)
                                .collect(Collectors.toList())
                );
    }

}
