package com.EmailService.error;



import com.EmailService.error.exception.MailNotSendException;
import com.EmailService.error.record.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;


/**
 * ExceptionsHandler is the class that handle all the exception from the application for witch we want to send an error response to the client.
 *
 * @author Fethi Benseddik
 */
@ControllerAdvice
@Slf4j
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MailNotSendException.class)
    protected ResponseEntity<ErrorResponse> handleMailNotSendException(MailNotSendException ex) {
        log.error("Mail not send: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                ex.getCode(),
                ex.getHttpStatus().getReasonPhrase(),
                ex.getStatus(),
                Instant.now());
        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }

}
