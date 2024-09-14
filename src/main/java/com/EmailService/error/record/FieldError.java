package com.EmailService.error.record;

public record FieldError(
        String entityName,
        String fieldName,
        String message,
        String code
) {
}
