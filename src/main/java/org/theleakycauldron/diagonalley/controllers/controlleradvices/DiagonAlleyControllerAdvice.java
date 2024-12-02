package org.theleakycauldron.diagonalley.controllers.controlleradvices;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.theleakycauldron.diagonalley.exceptions.*;

import java.util.Optional;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@ControllerAdvice
public class DiagonAlleyControllerAdvice {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse>  categoryNotFound(CategoryNotFoundException ex, String message){
        return ResponseEntity.status(404).body(ErrorResponse.create(ex, HttpStatusCode.valueOf(404), message));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> categoryAlreadyExists(CategoryAlreadyExistsException ex, String message){
        return ResponseEntity.of(Optional.of(ErrorResponse.create(ex, HttpStatusCode.valueOf(409), message)));
    }

    @ExceptionHandler(OutboxNotExistsException.class)
    public ResponseEntity<ErrorResponse> outboxNotFound(OutboxNotExistsException ex, String message){
        return ResponseEntity.status(404).body(ErrorResponse.create(ex, HttpStatusCode.valueOf(404), message));
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> productAlreadyExists(ProductAlreadyExistsException ex, String message){
        return ResponseEntity.of(Optional.of(ErrorResponse.create(ex, HttpStatusCode.valueOf(409), message)));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse>  productNotFoundExists(ProductNotFoundException ex, String message) {
        return ResponseEntity.status(404).body(ErrorResponse.create(ex, HttpStatusCode.valueOf(404), message));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeException(RuntimeException ex, String message){
        return ResponseEntity.status(500).body(ErrorResponse.create(ex, HttpStatusCode.valueOf(500), message));
    }
}
