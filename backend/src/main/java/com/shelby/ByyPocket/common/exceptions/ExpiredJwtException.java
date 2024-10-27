package com.shelby.ByyPocket.common.exceptions;

/**
 * Custom exception class used for when there is no record for the given filter parameters
 */
public class ExpiredJwtException extends RuntimeException {

    public ExpiredJwtException() {
        super();
    }

    public ExpiredJwtException(String message) {
        super(message);
    }

    public ExpiredJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
