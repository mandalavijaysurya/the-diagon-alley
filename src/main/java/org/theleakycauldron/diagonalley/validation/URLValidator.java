package org.theleakycauldron.diagonalley.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.theleakycauldron.diagonalley.annotations.ValidURL;

import java.net.URI;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public class URLValidator implements ConstraintValidator<ValidURL, String> {

    @Override
    public boolean isValid(String url, ConstraintValidatorContext constraintValidatorContext) {
        if(url == null) {
            return true;
        }
        try {
            new URI(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
