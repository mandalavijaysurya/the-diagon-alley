package org.theleakycauldron.diagonalley.exceptions;

public class ProductAlreadyDeletedException extends RuntimeException{
    
    public ProductAlreadyDeletedException(String message){
        super(message);
    }
}
