/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import org.itson.banco.dtos.DireccionDTO;
import org.itson.banco.entidades.Direccion;

/**
 * Clase Interfaz DireccionesDAO.
 * @author Emily Lara
 */
public interface IDireccionesDAO {
    
    public abstract Direccion registrarDireccion(DireccionDTO direccion) throws PersistenciaException;
    
    public Direccion consultarPorId(int idDireccion) throws PersistenciaException;
}
