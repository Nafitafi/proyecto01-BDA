/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.dtos;

import java.util.GregorianCalendar;

/**
 * Clase CuentaDTO.
 * Se encarga de transportar los datos de una cuenta entre capas.
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public class CuentaDTO {
    private String numeroCuenta;
    private double saldoCuenta;
    private String estado;
    private GregorianCalendar fechaApertura;

    /**
     * Constructor de la clase CuentaDTO
     * @param numeroCuenta
     * @param saldoCuenta
     * @param estado 
     */
    public CuentaDTO(String numeroCuenta, double saldoCuenta, String estado, GregorianCalendar fechaApertura) {
        this.numeroCuenta = numeroCuenta;
        this.saldoCuenta = saldoCuenta;
        this.estado = estado;
        this.fechaApertura = fechaApertura;
    }

    public CuentaDTO() {
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(double saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public GregorianCalendar getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(GregorianCalendar fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    @Override
    public String toString() {
        return numeroCuenta;
    }
    
    
}
