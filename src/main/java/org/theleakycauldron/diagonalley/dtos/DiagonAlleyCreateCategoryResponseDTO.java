package org.theleakycauldron.diagonalley.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@SuperBuilder
@AllArgsConstructor
@Getter
@Setter
public class DiagonAlleyCreateCategoryResponseDTO extends DiagonAlleyResponseDTO {
    private String response;
}
