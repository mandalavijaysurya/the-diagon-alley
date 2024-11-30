package org.theleakycauldron.diagonalley.services;

import org.theleakycauldron.diagonalley.dtos.DiagonAlleyCreateCategoryRequestDTO;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyCreateCategoryResponseDTO;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyCreateProductRequestDTO;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyCreateProductResponseDTO;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

public interface DiagonAlleyService {
    DiagonAlleyCreateProductResponseDTO addProduct(DiagonAlleyCreateProductRequestDTO requestDTO);
    DiagonAlleyCreateCategoryResponseDTO addCategory(DiagonAlleyCreateCategoryRequestDTO requestDTO);
}
