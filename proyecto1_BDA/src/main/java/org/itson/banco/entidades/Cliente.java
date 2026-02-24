/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.entidades;

import java.util.GregorianCalendar;

/**
 * Clase cliente.
 * Clase entidad Cliente, encargada de crear objetos molde.
 * @author Nahomi Figueroa
 */
public class Cliente {
   private int id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private GregorianCalendar fechaNacimiento;
    private Integer edad;
    private String correo;
    private String contrasena; // SOLO PARA LA BD
    private int idDireccion;

    /**
     * Constructor por omisión.
     */
    public Cliente() {
    }

    public Cliente(int id, String nombres, String apellidoPaterno, String apellidoMaterno, GregorianCalendar fechaNacimiento, Integer edad, String correo, String contrasena, int idDireccion) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.correo = correo;
        this.contrasena = contrasena;
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

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
    public void setDireccion(int idDireccion){
        this.idDireccion = idDireccion;
    }
    
   
}
