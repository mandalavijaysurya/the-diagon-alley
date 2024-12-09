package org.theleakycauldron.diagonalley.dtos;

import lombok.*;

import java.util.List;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DiagonAlleyGetProductsResponseDTO {
    private List<DiagonAlleyGetProductResponseDTO> diagonAlleyGetProductResponseDTOList;
}
