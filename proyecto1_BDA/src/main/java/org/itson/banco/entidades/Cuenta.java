/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.entidades;

import java.util.GregorianCalendar;

/**
 * Clase entidad Cuenta. 
 * Representa una cuenta de las varias que puede tener el cliente.
 * @author Nahomi Figueroa
 */
public class Cuenta {

    private String numeroCuenta; // Primary Key
    private GregorianCalendar fechaApertura;
    private double saldo;
    private String estado; 
    private int idCliente; 

    /**
     * COnstructor por omisión.
     */
    public Cuenta() {
    }

    /**
     * Constructor de la clase con sus atributos.
     * @param numeroCuenta
     * @param fechaApertura
     * @param saldo
     * @param estado
     * @param idCliente 
     */
    public Cuenta(String numeroCuenta, GregorianCalendar fechaApertura, double saldo, String estado, int idCliente) {
        this.numeroCuenta = numeroCuenta;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public GregorianCalendar getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(GregorianCalendar fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
