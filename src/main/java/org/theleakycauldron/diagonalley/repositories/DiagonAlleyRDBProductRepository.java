package org.theleakycauldron.diagonalley.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.theleakycauldron.diagonalley.daos.entities.Product;

import java.util.Optional;
import java.util.UUID;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@Repository
public interface DiagonAlleyRDBProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Optional<Product> findByUuid(UUID uuid);
}
