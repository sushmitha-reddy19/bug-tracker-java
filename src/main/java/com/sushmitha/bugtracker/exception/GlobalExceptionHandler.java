package com.sushmitha.bugtracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiExceptions.NotFound.class) ResponseEntity<?> notFound(ApiExceptions.NotFound ex) { return response(HttpStatus.NOT_FOUND, ex.getMessage()); }
    @ExceptionHandler(ApiExceptions.Conflict.class) ResponseEntity<?> conflict(ApiExceptions.Conflict ex) { return response(HttpStatus.CONFLICT, ex.getMessage()); }
    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class}) ResponseEntity<?> badRequest(Exception ex) { return response(HttpStatus.BAD_REQUEST, "Invalid request"); }
    private ResponseEntity<?> response(HttpStatus status, String message) { return ResponseEntity.status(status).body(Map.of("timestamp", Instant.now(), "status", status.value(), "error", message)); }
}
