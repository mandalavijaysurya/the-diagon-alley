package org.theleakycauldron.diagonalley.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.theleakycauldron.diagonalley.dtos.*;
import org.theleakycauldron.diagonalley.services.DiagonAlleyService;

import java.net.URISyntaxException;
import java.time.LocalDateTime;

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
    public ResponseEntity<DiagonAlleyCreateProductResponseDTO> addProduct(@RequestBody @Valid DiagonAlleyCreateProductRequestDTO requestDTO) throws URISyntaxException {
        DiagonAlleyCreateProductResponseDTO responseDTO = diagonAlleyService.addProduct(requestDTO);
        responseDTO.setStatusCode(201);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @PostMapping("/category")
    public ResponseEntity<DiagonAlleyResponseDTO> addCategory(@RequestBody @Valid DiagonAlleyCreateCategoryRequestDTO requestDTO){
        DiagonAlleyResponseDTO responseDTO = diagonAlleyService.addCategory(requestDTO);
        responseDTO.setStatusCode(201);
        return ResponseEntity.status(201).body(responseDTO);

    }

    @PutMapping("/product")
    public ResponseEntity<DiagonAlleyResponseDTO> modifyCategory(@RequestBody @Valid DiagonAlleyUpdateProductRequestDTO requestDTO){
        DiagonAlleyUpdateProductResponseDTO responseDTO = diagonAlleyService.updateProduct(requestDTO);
        responseDTO.setStatusCode(200);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @GetMapping("/product")
    public ResponseEntity<DiagonAlleyGetProductsResponseDTO> getProductByName(@RequestParam String query){
        DiagonAlleyGetProductsResponseDTO responseDTO = diagonAlleyService.getProductByKeywords(query);
        return ResponseEntity.status(200).body(responseDTO);
    }
}
