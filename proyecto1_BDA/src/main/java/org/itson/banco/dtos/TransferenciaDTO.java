/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.dtos;

/**
 * Clase Transferencia DTO.
 * Clase encargada de transportar los datos de una transferencia entre capas.
 * @author Emily Lara
 */
public class TransferenciaDTO {
    
    private String cuentaOrigen;
    private String cuentaDestino;
    private double monto;

    /**
     * Constructor de la clase.
     * @param cuentaOrigen
     * @param cuentaDestino
     * @param monto 
     */
    public TransferenciaDTO(String cuentaOrigen, String cuentaDestino, double monto) {
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public double getMonto() {
        return monto;
    }
    
    
}
