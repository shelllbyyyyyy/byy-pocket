package com.shelby.ByyPocket.common;

import org.springframework.http.HttpStatus;

public class Utils {
    public static String getStatusFromCode(Integer code) {
        return switch (code) {
            case 200 -> HttpStatus.OK.name();
            case 201 -> HttpStatus.CREATED.name();
            case 202 -> HttpStatus.ACCEPTED.name();
            case 204 -> HttpStatus.NO_CONTENT.name();

            case 301 -> HttpStatus.MOVED_PERMANENTLY.name();
            case 302 -> HttpStatus.FOUND.name();
            case 304 -> HttpStatus.NOT_MODIFIED.name();

            case 400 -> HttpStatus.BAD_REQUEST.name();
            case 401 -> HttpStatus.UNAUTHORIZED.name();
            case 403 -> HttpStatus.FORBIDDEN.name();
            case 404 -> HttpStatus.NOT_FOUND.name();
            case 409 -> HttpStatus.CONFLICT.name();

            case 500 -> HttpStatus.INTERNAL_SERVER_ERROR.name();
            case 502 -> HttpStatus.BAD_GATEWAY.name();
            case 503 -> HttpStatus.SERVICE_UNAVAILABLE.name();
            case 504 -> HttpStatus.GATEWAY_TIMEOUT.name();

            default -> "unknown";
        };
    }
}
