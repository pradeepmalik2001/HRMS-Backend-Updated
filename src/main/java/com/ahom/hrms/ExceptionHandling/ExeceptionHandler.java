package com.ahom.hrms.ExceptionHandling;

import com.ahom.hrms.exception.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExeceptionHandler  {
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
    public final ResponseEntity<EmailResponse>handleCustomException(CustomException customException){
            EmailResponse response=new EmailResponse(
            customException.getMessage(),
        HttpStatus.ALREADY_REPORTED.value()
            );
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(response);
    }

    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<ApiResponse>handleException(Exception e){
        ApiResponse response = new ApiResponse(
                e.getMessage(),
                NOT_FOUND.value()
        );
        return ResponseEntity.status(NOT_FOUND).body(response);

    }
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException ex) {
        String errorMessage = "Invalid value for 'roles' field.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

}
        //other exception handlers below







