/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.negocio;

/**
 * Clase NegocioException. Excepción personalizada que toma lugar en la capa de
 * Negocio.
 *
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public class NegocioException extends Exception {

    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }

}
