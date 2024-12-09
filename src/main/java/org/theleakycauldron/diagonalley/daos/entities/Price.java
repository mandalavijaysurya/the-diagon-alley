package org.theleakycauldron.diagonalley.daos.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Price extends BaseModel{
    private double amount;
    private double discount;
}
