package com.jaafar.poc.config;

import com.jaafar.poc.exception.TestException;
import com.jaafar.poc.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 8/27/2019 9:54 AM
 */
@Slf4j
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(TestException.class)
    @ResponseBody
    public ResponseEntity<String> handlePermissionExceptions(HttpServletRequest req, Exception ex) throws IOException {
//        String message = ex.getMessage() == null ? "You do not have permission to perform the action. Please contact your system administrator." : ex.getMessage();
//        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        throw new IOException("adf");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleExceptions(HttpServletRequest req, Exception ex) {
        System.out.println("major exception handler catching");
        String message = "Unexpected error occur";
        log.error("", ex);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    public User handleResponseStatusException(HttpServletRequest req, ResponseStatusException ex) {
        return new User();
    }

}
