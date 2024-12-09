package org.theleakycauldron.diagonalley.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiagonAlleyKafkaRequestDTO {
    private String name;
    private String description;
    private double amount;
    private double discount;
    private String imageURL;
    private String productCategory;
    private String manufacturer;
    private List<String> tags;
    private double rating;
}
