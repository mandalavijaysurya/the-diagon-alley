package org.theleakycauldron.diagonalley.controllers.controlleradvices;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.theleakycauldron.diagonalley.exceptions.CategoryAlreadyExistsException;
import org.theleakycauldron.diagonalley.exceptions.CategoryNotFoundException;

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
        return ResponseEntity.status(404).body(ErrorResponse.create(ex, HttpStatusCode.valueOf(404), message));
    }
}
