package org.theleakycauldron.diagonalley.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.theleakycauldron.diagonalley.annotations.ValidURL;

import java.util.List;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DiagonAlleyCreateProductRequestDTO {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String manufacturer;
    @NotNull
    private String productCategory;
    @NotNull
    private double price;
    private double discount;

    @NotNull
    @ValidURL
    private String imageUrl;

    private List<String> tags;
    public double rating;
}
