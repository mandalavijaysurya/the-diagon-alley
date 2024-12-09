package org.theleakycauldron.diagonalley.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.theleakycauldron.diagonalley.daos.entities.ProductCategory;

import java.util.Optional;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@Repository
public interface DiagonAlleyRDBCategoryRepository extends JpaRepository<ProductCategory, Long> {

    Optional<ProductCategory> findByName(String name);
}
