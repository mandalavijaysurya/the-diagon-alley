package org.theleakycauldron.diagonalley.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
public class DiagonAlleyDeleteProductRequestDTO extends DiagonAlleyResponseDTO{
    @NotNull
    private UUID uuid;
}
