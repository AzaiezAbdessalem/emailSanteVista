package com.EmailService.error.exception;



import com.EmailService.error.record.ExceptionWithErrorResponse;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * MailNotSendException is the exception that is thrown when the mail is not send.
 * This exception is used to send an error response to the client.
 *
 * @author Fethi Benseddik
 */

@Getter
@ToString
public class MailNotSendException extends RuntimeException implements ExceptionWithErrorResponse {

    private final String message;
    private final String code;
    private final HttpStatus httpStatus;
    private final int status;


    public MailNotSendException(String message) {
        this.message = message;
        this.code = "mail.not.send";
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.status = httpStatus.value();

    }
}
