/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.negocio;

import org.itson.banco.dtos.DireccionDTO;
import org.itson.banco.entidades.Direccion;

/**
 *
 * @author Emily Lara
 */
public interface IDireccionesBO {
     
    public abstract Direccion registrarDireccion(DireccionDTO direccion) throws NegocioException;
    
    public DireccionDTO obtenerDireccion(int idDireccion) throws NegocioException;

}
