package org.theleakycauldron.diagonalley.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.theleakycauldron.diagonalley.entities.Outbox;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@Repository
public interface DiagonAlleyRDBOutboxRepository extends JpaRepository<Outbox, Long> {

}
