/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.dtos;

import java.util.GregorianCalendar;

/**
 * Clase OperacionDTO. Se encarga de transportar datos de las operaciones entre
 * capas.
 *
 * @author Nahomi Figueroa
 */
public class OperacionDTO {

    private String tipoMovimiento;
    private String folio; // Usamos String porque el folio de operación es INT, pero el de cuenta es VARCHAR(12)
    private double monto;
    private GregorianCalendar fechaHora;
    
    /**
     * Constructor por omisión
     */
    public OperacionDTO(){
        
    }
    
    /**
     * Constructor con todos sus parametros.
     * @param tipoMovimiento
     * @param folio
     * @param monto
     * @param fechaHora 
     */
    public OperacionDTO(String tipoMovimiento, String folio, double monto, GregorianCalendar fechaHora) {
        this.tipoMovimiento = tipoMovimiento;
        this.folio = folio;
        this.monto = monto;
        this.fechaHora = fechaHora;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public GregorianCalendar getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(GregorianCalendar fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    
}


