package org.theleakycauldron.diagonalley.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.theleakycauldron.diagonalley.services.DiagonAlleyService;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@RestController
public class DiagonAlleyController {

    private final DiagonAlleyService diagonAlleyService;

    public DiagonAlleyController(DiagonAlleyService diagonAlleyService) {
        this.diagonAlleyService = diagonAlleyService;
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(String product){
        return ResponseEntity.ok("Product added successfully");
    }
}
