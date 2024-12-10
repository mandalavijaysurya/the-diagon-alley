package org.theleakycauldron.diagonalley.commons;

import org.theleakycauldron.diagonalley.dtos.DiagonAlleyGetProductResponseDTO;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyGetProductsResponseDTO;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyKafkaRequestDTO;
import org.theleakycauldron.diagonalley.daos.entities.Product;

import java.util.List;
import java.util.stream.Collectors;

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

    public static DiagonAlleyGetProductsResponseDTO convertProductToGetProductsResponseDTOs(List<org.theleakycauldron.diagonalley.daos.documents.Product> products) {
        List<DiagonAlleyGetProductResponseDTO> dtos = products.stream().map(
                product -> DiagonAlleyGetProductResponseDTO.builder()
                        .name(product.getProductName())
                        .description(product.getProductDescription())
                        .amount(product.getProductPrice())
                        .discount(product.getDiscount())
                        .imageURL(product.getImageUrl())
                        .productCategory(product.getProductCategory())
                        .manufacturer(product.getManufacturerName())
                        .tags(product.getTags())
                        .rating(product.getRating())
                        .build()
        ).collect(Collectors.toList());

        return DiagonAlleyGetProductsResponseDTO.builder()
                .diagonAlleyGetProductResponseDTOList(dtos)
                .build();
    }
}
