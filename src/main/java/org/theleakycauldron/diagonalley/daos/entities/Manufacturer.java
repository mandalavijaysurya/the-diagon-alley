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
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public class Manufacturer extends BaseModel {
    private String name;
}
