package org.spring.bd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException(String message, Object... args) {
        super(String.format(message, args));
    }
}
