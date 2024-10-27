package com.shelby.ByyPocket.common.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.shelby.ByyPocket.common.Utils.getStatusFromCode;

/**
 * Builds API response in a proper format with timestamp, message and data
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private String timestamp;
    private final String message;
    private final T data;
    private final Integer code;
    private final String status;

    public static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public ApiResponse(Integer code, String message, T data) {
        this.code = code;
        this.timestamp = getCurrentTimestamp();
        this.message = message;
        this.data = data;
        this.status = getStatusFromCode(code);
    }
}