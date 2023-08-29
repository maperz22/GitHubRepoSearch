package com.maperz.githubreposearch.exceptions.aop;

import com.maperz.githubreposearch.exceptions.dto.ErrorResponse;
import com.maperz.githubreposearch.exceptions.GithubUserNotFoundException;
import org.springframework.http.*;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponse bodyOfResponse = ErrorResponse.builder()
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .message("Media " + request.getHeader("Accept") + " not acceptable. Please use: application/json")
                .build();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, bodyOfResponse,
                headers, HttpStatus.NOT_ACCEPTABLE, request);
    }


    @ExceptionHandler(GithubUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleException(GithubUserNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        ErrorResponse.builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .message(e.getMessage())
                                .build()
                );
    }



}
