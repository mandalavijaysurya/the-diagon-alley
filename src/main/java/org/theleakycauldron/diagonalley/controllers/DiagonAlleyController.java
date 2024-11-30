package org.theleakycauldron.diagonalley.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyCreateCategoryRequestDTO;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyCreateProductRequestDTO;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyCreateProductResponseDTO;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyResponseDTO;
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
}
