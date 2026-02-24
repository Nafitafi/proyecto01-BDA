/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.dtos;

/**
 * Clase DireccionDTO.
 * Se encarga de transportar los datos de una cuenta entre capas.
 * @author Emily Lara
 */
public class DireccionDTO {
    private String calle;
    private String numero;
    private String colonia;
    private String codigoPostal;
    private String ciudad;

    /**
     * Constructor de la clase DireccionDTO
     * @param calle
     * @param numero
     * @param colonia
     * @param codigoPostal
     * @param ciudad
     */

    public DireccionDTO(String calle, String numero, String colonia, String codigoPostal, String ciudad) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
    }
    
    /**
     * Constructor por omisión.
     */
    public DireccionDTO(){
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
}
