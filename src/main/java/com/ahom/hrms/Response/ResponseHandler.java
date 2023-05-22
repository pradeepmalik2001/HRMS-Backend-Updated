package com.ahom.hrms.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler
{
    public static ResponseEntity<Object> responseBuilder(
            String message, HttpStatus httpStatus, Object responseObject
    )
    {
        Map<String, Object> response = new HashMap<>();
        response.put("Message", message);
        response.put("Status", httpStatus.value());
        response.put("Data", responseObject);

        return new ResponseEntity<>(response, httpStatus);
    }
}
