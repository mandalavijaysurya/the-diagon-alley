package org.theleakycauldron.diagonalley.commons;

import org.theleakycauldron.diagonalley.dtos.DiagonAlleyKafkaRequestDTO;
import org.theleakycauldron.diagonalley.entities.Outbox;
import org.theleakycauldron.diagonalley.entities.Product;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public class DiagonAlleyUtils {
    public static DiagonAlleyKafkaRequestDTO convertProductToKafkaRequestDTO(Product product){
        return DiagonAlleyKafkaRequestDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .amount(product.getPrice().getAmount())
                .discount(product.getPrice().getDiscount())
                .imageURL(product.getImageURL())
                .productCategory(product.getProductCategory().getName())
                .manufacturer(product.getManufacturer().getName())
                .tags(product.getTags())
                .rating(product.getRating())
                .build();
    }

}
