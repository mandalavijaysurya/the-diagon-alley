package org.theleakycauldron.diagonalley.dtos;

import lombok.*;
import org.theleakycauldron.diagonalley.daos.entities.Outbox;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OutboxEventDTO {
    private Outbox outbox;
    private boolean isUpdated;
}
