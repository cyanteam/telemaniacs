package org.cyanteam.telemaniacs.rest.controllers;

import org.cyanteam.telemaniacs.rest.exceptions.ErrorMessage;
import org.cyanteam.telemaniacs.rest.exceptions.ResourceNotFoundException;
import org.cyanteam.telemaniacs.rest.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.context.request.WebRequest;

//@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    protected ResponseEntity<ErrorMessage> handle(RuntimeException e, WebRequest request) {
        ErrorMessage msg = new ErrorMessage(e.getClass().getSimpleName(), e.getMessage());
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (e instanceof ResourceNotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(msg, httpStatus);
    }
}
