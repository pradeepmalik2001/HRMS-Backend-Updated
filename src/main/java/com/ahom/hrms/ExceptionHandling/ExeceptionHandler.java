package com.ahom.hrms.ExceptionHandling;

import com.ahom.hrms.exception.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
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

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExeceptionHandler extends ResponseEntityExceptionHandler {
//        @ResponseStatus(HttpStatus.BAD_REQUEST)
//        @ExceptionHandler(MethodArgumentNotValidException.class)
//        public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException e){
//            Map<String, String> errorMap = new HashMap<>();
//            e.getBindingResult().getFieldErrors().forEach(error ->{
//                errorMap.put(error.getField(), error.getDefaultMessage());
//            });
//
//            return errorMap;
//        }



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

        @Override
        protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                      HttpStatus status, WebRequest request) {
            String error = "Malformed JSON request";
            return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
        }

        private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
            return new ResponseEntity<>(apiError, apiError.getStatus());
        }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));
    }
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return buildResponseEntity(new ApiError(BAD_REQUEST, error, ex));
    }
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            javax.validation.ConstraintViolationException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getConstraintViolations());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Error writing JSON output";
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
}
        //other exception handlers below







