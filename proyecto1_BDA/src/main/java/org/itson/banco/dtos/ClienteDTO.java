/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.dtos;

/**
 * Clase ClienteDTO. Clase encargada de transferir los datos de un cliente entre
 * capas.
 *
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public class ClienteDTO {

    private int id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public ClienteDTO() {
    }

    /**
     * Constructor de la clase ClienteDTO
     *
     * @param id
     * @param nombre
     * @param apellidoPaterno
     */
    public ClienteDTO(int id, String nombres, String apellidoPaterno, String apellidoMaterno) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombre) {
        this.nombres = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String toString(){
        return nombres + " " + " " + apellidoPaterno + " " + apellidoMaterno;
    }
    
}
