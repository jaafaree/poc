package com.jaafar.poc.config;

import com.jaafar.poc.exception.TestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 8/27/2019 9:54 AM
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(TestException.class)
    @ResponseBody
    public ResponseEntity<String> handlePermissionExceptions(HttpServletRequest req, Exception ex) {
        String message = ex.getMessage() == null ? "You do not have permission to perform the action. Please contact your system administrator." : ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

}
