package it.unibz.infosec.examproject.product.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionProductHandlerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleIllegalArgument(IllegalArgumentException iae) {
        Map<String, String> error = new HashMap<>();
        error.put("error", iae.getMessage());

        return error;
    }
}