package org.theleakycauldron.diagonalley.entities;


import jakarta.persistence.Entity;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@Entity
public class Manufacturer extends BaseModel {
    private String name;
    private String phoneNumber;
    private String email;
    private Address address;
}
