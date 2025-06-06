package com.example.POS.System.exceptions;

import com.example.POS.System.exceptions.Custom.BadRequestException;
import com.example.POS.System.exceptions.Custom.NotFoundException;
import com.example.POS.System.utils.Utils;
import com.example.POS.System.utils.GlobalResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalResponse<Object>> handleNotFoundException(NotFoundException ex) {
        return Utils.NotFound(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GlobalResponse<Object>> handleBadRequestException(BadRequestException ex) {
        return Utils.BadRequest(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GlobalResponse<Object>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex, WebRequest request) {

        return Utils.BadRequest(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GlobalResponse<Object>> myConstrainViolationException(ConstraintViolationException e) {
        List<String> message = new ArrayList<>();

        e.getConstraintViolations().forEach(constraintViolation -> {
            message.add(constraintViolation.getMessageTemplate());
        });

        return Utils.BadRequest(message.getFirst());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<GlobalResponse<Map<String, String>>> myMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        Map<String, String> response = new HashMap<>();

        response.put(e.getParameterName(), e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GlobalResponse<>("Bad Request", 400, response));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<GlobalResponse<Object>> handleNoResourceFoundException(NoResourceFoundException ex, WebRequest request) {
        return Utils.NotFound("URL not Found!");
    }

    // Handle DataIntegrityViolationException (for example, unique constraint violations)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<GlobalResponse<Object>> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        return Utils.Duplicate(ex.getRootCause().getMessage());
    }

    // Handle validation errors (e.g. @NotNull, @Email, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse<Object>> handleValidationException(MethodArgumentNotValidException ex) {
        AtomicReference<String> errors = new AtomicReference<>("");
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.set(error.getField() + " = " + error.getDefaultMessage());
        });

        return Utils.BadRequest(errors.get());
    }

    // Handle other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponse<Object>> handleGeneralException(Exception ex, WebRequest request) {
        return Utils.ServerError(ex.getMessage());
    }

}
