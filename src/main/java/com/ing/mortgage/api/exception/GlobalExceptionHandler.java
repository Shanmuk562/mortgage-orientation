package com.ing.mortgage.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MortgageOrientationException> onNotFound(NotFoundException ex) {
        log.error("Rate not found : {}, Error: {}", ex, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MortgageOrientationException("NOT_FOUND", ex.getMessage(), traceId()));
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<MortgageOrientationException> onValidationFail(CustomValidationException ex) {
        log.error("Validation failed : {}, Error: {}", ex, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MortgageOrientationException("VALIDATION_FAILED", ex.getMessage(), traceId()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MortgageOrientationException> onRequestValidation(MethodArgumentNotValidException ex) {
        log.error("Request validation at: {}, Error: {}", ex, ex.getMessage());
        var msg = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .findFirst().orElse("Invalid request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MortgageOrientationException("BAD_REQUEST", msg, traceId()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MortgageOrientationException> onAny(Exception ex) {
        log.error("Exception occurred at: {}, Error: {}", ex, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MortgageOrientationException("INTERNAL_ERROR", "Something went wrong",
                        traceId()));
    }

    private String traceId() {
        return java.util.UUID.randomUUID().toString();
    }
}
