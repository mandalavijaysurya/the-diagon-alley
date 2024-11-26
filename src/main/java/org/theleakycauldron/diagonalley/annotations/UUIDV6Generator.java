package org.theleakycauldron.diagonalley.annotations;

import org.hibernate.annotations.IdGeneratorType;
import org.theleakycauldron.diagonalley.generators.SortedUUIDGenerator;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
@IdGeneratorType(SortedUUIDGenerator.class)
public @interface UUIDV6Generator {

}
