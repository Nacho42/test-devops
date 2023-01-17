package com.opentrends.apibuilder.errors.controllers;

import com.opentrends.apibuilder.errors.ErrorCodes;
import com.opentrends.apibuilder.errors.exceptions.ApiBuilderException;
import com.opentrends.apibuilder.errors.exceptions.BeanAlreadyExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final StringBuilder errorMessageBuilder = new StringBuilder();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorMessageBuilder.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(", ");
        }
        final String errorMessage = errorMessageBuilder.length() > 0 ? errorMessageBuilder.substring(0, errorMessageBuilder.length() - 2) : StringUtils.EMPTY;
        final HashMap<String, String> responseBody = createExceptionResponseBody(new ApiBuilderException(ErrorCodes.BAD_REQUEST_CODE, errorMessage));

        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final HashMap<String, String> responseBody = createExceptionResponseBody(new ApiBuilderException(ErrorCodes.BAD_REQUEST_CODE, ErrorCodes.BAD_REQUEST_MESSAGE));
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = ApiBuilderException.class)
    ResponseEntity<Object> handleException(ApiBuilderException ex, WebRequest request) {
        final HashMap<String, String> responseBody = createExceptionResponseBody(ex);
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    @ExceptionHandler(value = BeanAlreadyExistsException.class)
    ResponseEntity<Object> handleConflictException(ApiBuilderException ex, WebRequest request) {
        final HashMap<String, String> responseBody = createExceptionResponseBody(ex);
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    private HashMap<String, String> createExceptionResponseBody(ApiBuilderException exception) {
        final HashMap<String, String> responseBody = new HashMap<>();
        responseBody.put("errorCode", exception.getErrorCode());
        responseBody.put("errorMessage", exception.getErrorMessage());
        return responseBody;
    }

}
