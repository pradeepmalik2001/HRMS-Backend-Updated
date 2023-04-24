package com.ahom.hrms.ExceptionHandling;

import com.ahom.hrms.exception.CustomException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExecptionHandler {
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException e){
            Map<String, String> errorMap = new HashMap<>();
            e.getBindingResult().getFieldErrors().forEach(error ->{
                errorMap.put(error.getField(), error.getDefaultMessage());
            });

            return errorMap;
        }


    @ExceptionHandler(value =com.ahom.hrms.exception.AuthenticationException.class)
    public final ResponseEntity<String> handelAuthenticationFailException
            (AuthenticationException authenticationFailException)
    {
        return  new ResponseEntity<>
                (authenticationFailException.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = CustomException.class)
    public final ResponseEntity<String>handleCustomException(CustomException customException){
        return new ResponseEntity<>(customException.getMessage(),HttpStatus.ALREADY_REPORTED);
    }

    }

