package org.theleakycauldron.diagonalley.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public class Outbox extends BaseModel {
    @OneToOne(fetch = FetchType.EAGER)
    private Product product;
    private boolean isPersisted;
}
