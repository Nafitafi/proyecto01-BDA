/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.dtos;

/**
 * Clase CuentaDTO.
 * Se encarga de transportar los datos de una cuenta entre capas.
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public class CuentaDTO {
    private int numeroCuenta;
    private double saldoCuenta;
    private String estado;
    private String fechaApertura;

    /**
     * Constructor de la clase CuentaDTO
     * @param numeroCuenta
     * @param saldoCuenta
     * @param estado 
     */
    public CuentaDTO(int numeroCuenta, double saldoCuenta, String estado, String fechaApertura) {
        this.numeroCuenta = numeroCuenta;
        this.saldoCuenta = saldoCuenta;
        this.estado = estado;
        this.fechaApertura = fechaApertura;
    }

    public CuentaDTO() {
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
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

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
    
}
