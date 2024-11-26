package org.theleakycauldron.diagonalley.services.implementations;

import org.springframework.stereotype.Service;
import org.theleakycauldron.diagonalley.repositories.DiagonAlleyRepository;
import org.theleakycauldron.diagonalley.services.DiagonAlleyService;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@Service
public class DiagonAlleyServiceImpl implements DiagonAlleyService {

    private final DiagonAlleyRepository diagonAlleyRepository;

    public DiagonAlleyServiceImpl(DiagonAlleyRepository diagonAlleyRepository) {
        this.diagonAlleyRepository = diagonAlleyRepository;
    }
}
