package com.ahom.hrms.ExceptionHandling;

import com.ahom.hrms.exception.ApiResponse;
import com.ahom.hrms.exception.CustomException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExeceptionHandler {
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

    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<ApiResponse>handleException(Exception e){
        ApiResponse response = new ApiResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ErrorObject handleJsonErrors(HttpMessageNotReadableException exception){
//
//            return new ErrorObject(exception.getMessage(), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException ex) {
        String errorMessage = "Invalid value for 'roles' field.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
    }

