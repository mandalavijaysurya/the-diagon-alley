package org.theleakycauldron.diagonalley.services;

import org.theleakycauldron.diagonalley.dtos.*;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

public interface DiagonAlleyService {
    DiagonAlleyCreateProductResponseDTO addProduct(DiagonAlleyCreateProductRequestDTO requestDTO);
    DiagonAlleyCreateCategoryResponseDTO addCategory(DiagonAlleyCreateCategoryRequestDTO requestDTO);
    DiagonAlleyUpdateProductResponseDTO updateProduct(DiagonAlleyUpdateProductRequestDTO requestDTO);
    DiagonAlleyDeleteProductResponseDTO deleteProduct(DiagonAlleyDeleteProductRequestDTO requestDTO);
    DiagonAlleyGetProductsResponseDTO getProductByKeywords(String query);
}
