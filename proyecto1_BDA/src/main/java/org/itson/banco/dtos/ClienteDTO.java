/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.dtos;

import java.util.GregorianCalendar;

/**
 * Clase ClienteDTO.
 * Clase encargada de transferir los datos de un cliente entre
 * capas.
 *
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public class ClienteDTO {

    private Integer id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private GregorianCalendar fechaNacimiento;
    private Integer edad;
    private String correo;
    private String constrasena;
    private Integer idDireccion;

    /**
     * Constructor por defecto de la clase ClienteDTO
     */
    public ClienteDTO() {
    }

    /**
     * Constructor de la clase ClienteDTO
     * 
     * @param id
     * @param nombres
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @param fechaNacimiento
     * @param edad
     * @param correo
     * @param constraseña
     * @param idDireccion 
     */
    public ClienteDTO(Integer id, String nombres, String apellidoPaterno, String apellidoMaterno, GregorianCalendar fechaNacimiento, Integer edad, String correo, String constrasena, Integer idDireccion) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.correo = correo;
        this.constrasena = constrasena;
        this.idDireccion = idDireccion;
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

    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getConstrasena() {
        return constrasena;
    }

    public void setConstrasena(String constrasena) {
        this.constrasena = constrasena;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String toString(){
        return nombres + " " + " " + apellidoPaterno + " " + apellidoMaterno;
    }
    
}
