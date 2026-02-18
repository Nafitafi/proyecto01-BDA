/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.entidades;

import java.time.LocalDateTime;

/**
 * Clase Transferencia POJO
 * @author emyla
 */
public class Transferencia {
    
    private int idTransferencia;
    private double monto;
    private LocalDateTime fechaTransferencia;
    private int cuentaOrigen;
    private int cuentaDestino;

    public Transferencia() {
    }

    public Transferencia(int idTransferencia, double monto, LocalDateTime fechaTransferencia, int cuentaOrigen, int cuentaDestino) {
        this.idTransferencia = idTransferencia;
        this.monto = monto;
        this.fechaTransferencia = fechaTransferencia;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public int getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaTransferencia() {
        return fechaTransferencia;
    }

    public void setFechaTransferencia(LocalDateTime fechaTransferencia) {
        this.fechaTransferencia = fechaTransferencia;
    }

    public int getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(int cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public int getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(int cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
    
    
    
    
}
