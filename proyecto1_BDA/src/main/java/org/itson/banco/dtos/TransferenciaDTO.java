/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.dtos;

/**
 * Clase Transferencia DTO
 * @author emyla
 */
public class TransferenciaDTO {
    
    private int cuentaOrigen;
    private int cuentaDestino;
    private double monto;

    public TransferenciaDTO(int cuentaOrigen, int cuentaDestino, double monto) {
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
    }

    public int getCuentaOrigen() {
        return cuentaOrigen;
    }

    public int getCuentaDestino() {
        return cuentaDestino;
    }

    public double getMonto() {
        return monto;
    }
    
    
}
