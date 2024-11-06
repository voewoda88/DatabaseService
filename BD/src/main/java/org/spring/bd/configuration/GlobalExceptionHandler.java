package org.spring.bd.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.spring.bd.exceptions.BadRequestException;
import org.spring.bd.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String ERROR_EXAMPLE = """
    {
    "timestamp": "some timestamp",
    "status": "some status",
    "error": "some error",
    "message": "some message",
    "path": "some path"
    }
    """;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex, HttpServletRequest request) {
        HttpStatus status = getHttpStatus(ex);

        Map<String, Object> response = new HashMap<>();

        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", ex.getMessage());
        response.put("path", request.getRequestURI());

        return new ResponseEntity<>(response, status);
    }

    private HttpStatus getHttpStatus(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof ResourceNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof BadRequestException) {
            status = HttpStatus.BAD_REQUEST;
        }

        return status;
    }
}
