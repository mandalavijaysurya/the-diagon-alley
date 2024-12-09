package org.theleakycauldron.diagonalley.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.theleakycauldron.diagonalley.daos.documents.Product;

import java.util.List;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

public interface DiagonAlleyElasticProductRepository extends ElasticsearchRepository<Product, String> {
    List<Product> findProductByTagsEquals(List<String> keyword);
}
