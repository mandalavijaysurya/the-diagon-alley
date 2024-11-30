package org.theleakycauldron.diagonalley.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Product extends BaseModel{
    private String name;
    private String description;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Price price;
    @Column(length = 1000)
    private String imageURL;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private ProductCategory productCategory;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Manufacturer manufacturer;
    @ElementCollection
    @CollectionTable(name = "product_tags", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "tag")
    private List<String> tags;
    private double rating;
}
