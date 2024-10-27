package com.shelby.ByyPocket.common.exceptions;


import static com.shelby.ByyPocket.common.Constants.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;


/**
 * Global exception handler class for handling all the exceptions
 */
@Slf4j(topic = "GlobalExceptionHandler")
@RestControllerAdvice
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {

    /**
     * This parameter is used to print StackTrace while debugging. Its value is false by default.
     */
    @Value("${exception.trace:false}")
    private boolean printStackTrace;

    /**
     * Handles custom ElementAlreadyExistsException
     *
     * @param ex
     * @param request
     * @return ResponseEntity<Object> with detailed information related to the error
     */
    @ExceptionHandler(ElementAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleElementAlreadyExistsException(ElementAlreadyExistsException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.CONFLICT, request);
    }

    /**
     * Handles custom NoSuchElementFoundException
     *
     * @param ex
     * @param request
     * @return ResponseEntity<Object> with detailed information related to the error
     */
    @ExceptionHandler(NoSuchElementFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleElementAlreadyExistsException(NoSuchElementFoundException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handles custom ExpiredJwtException
     *
     * @param ex
     * @param request
     * @return ResponseEntity<Object> with detailed information related to the error
     */
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    /**
     * Build error message by the given exception, status and request
     *
     * @param ex
     * @param statusCode
     * @param request
     * @return ResponseEntity<Object> with detailed information related to the error
     */
    private ResponseEntity<Object> buildErrorResponse(Exception ex,
                                                      HttpStatusCode statusCode,
                                                      WebRequest request) {
        return buildErrorResponse(ex, ex.getMessage(), statusCode, request);
    }


    /**
     * Build error message by the given exception, message, status and request
     *
     * @param ex
     * @param message
     * @param statusCode
     * @param request
     * @return ResponseEntity<Object> with detailed information related to the error
     */
    private ResponseEntity<Object> buildErrorResponse(Exception ex,
                                                      String message,
                                                      HttpStatusCode statusCode,
                                                      WebRequest request) {
        final ErrorResponse errorResponse = new ErrorResponse(statusCode.value(), message);
        if (printStackTrace && isTraceOn(request)) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(ex));
        }
        return ResponseEntity.status(statusCode).body(errorResponse);
    }

    /**
     * Checks the tracing parameter sent by request
     *
     * @param request
     * @return the tracing status based on the request
     */
    private boolean isTraceOn(WebRequest request) {
        String[] value = request.getParameterValues(TRACE);
        return Objects.nonNull(value)
                && value.length > 0
                && value[0].contentEquals("true");
    }

    /**
     * Handles internal exceptions
     *
     * @param ex
     * @param body
     * @param headers
     * @param statusCode
     * @param request
     * @return ResponseEntity<Object> with detailed information related to the error
     */
    @Override
    public ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request) {

        return buildErrorResponse(ex, statusCode, request);
    }
}