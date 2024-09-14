package com.EmailService.error.record;

import java.time.Instant;

/**
 * ErrorResponse is the error response we can send to the client when catching an exception.
 *
 * @param message
 * @param code
 * @param statusMessage
 * @param status
 * @param timestamp
 */
public record ErrorResponse(
        String message,
        String code,
        String statusMessage,
        int status,
        Instant timestamp
) {
}
