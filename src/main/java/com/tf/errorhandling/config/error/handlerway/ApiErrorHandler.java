package com.tf.errorhandling.config.error.handlerway;

import com.tf.errorhandling.exceptions.PacException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
@ConditionalOnProperty(value = "error.attribute.enhancer", havingValue = "false")
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAll(Exception ex, WebRequest request) {
        logger.error("Unhandled Error Occurred", ex);
        ApiError apiError = ApiError.builder()
                .code("100")
                .message("Pac Unhandled Error")
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(PacException.class)
    public ResponseEntity<ApiError> handlePacException(PacException ex, WebRequest request) {
        logger.error("PAC Specific Error Occurred", ex);
        ApiError apiError = ApiError.builder()
                .code("101")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return ResponseEntity.badRequest().body(apiError);
    }
}
