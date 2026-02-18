/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

/**
 * Clase PersistenciaException.
 * Excepción personalizada que toma lugar en la capa de persistencia.
 * 
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public class PersistenciaException extends Exception{
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
