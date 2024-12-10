package org.theleakycauldron.diagonalley.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.theleakycauldron.diagonalley.daos.entities.Price;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@Repository
public interface DiagonAlleyRDBPriceRepository extends JpaRepository<Price, Long> {
}
