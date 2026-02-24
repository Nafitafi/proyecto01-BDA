/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.negocio;

import org.itson.banco.dtos.DireccionDTO;
import org.itson.banco.entidades.Direccion;
import org.itson.banco.persistencia.IDireccionesDAO;
import org.itson.banco.persistencia.PersistenciaException;

/**
 *
 * @author emyla
 */
public class DireccionesBO implements IDireccionesBO {
    
    private IDireccionesDAO direccionesDAO;
    
    public DireccionesBO(IDireccionesDAO direccionesDAO){
        this.direccionesDAO = direccionesDAO;
    }
    
    @Override
    public Direccion registrarDireccion(DireccionDTO direccion) throws NegocioException{
        
        if(direccion.getCalle() == null){
            throw new NegocioException("Favor de agregar nombre de la calle", null);
        }

        if(direccion.getNumero() == null){
            throw new NegocioException("Favor de agregar numero de su domicilio", null);
        }

        if(direccion.getColonia() == null){
            throw new NegocioException("Favor de agregar colonia", null);
        }

        if(direccion.getNumero() == null){
            throw new NegocioException("Favor de agregar codigo postal de su domicilio", null);
        }

        if(direccion.getNumero() == null){
            throw new NegocioException("Favor de agregar ciudad", null);
        }

        try{
            Direccion nuevaDireccion = direccionesDAO.registrarDireccion(direccion);
            return nuevaDireccion;
        } catch(PersistenciaException e){
            throw new NegocioException("Error al registrar la direccion", e);
        }
    }
    
    @Override
    public DireccionDTO obtenerDireccion(int idDireccion)
            throws NegocioException {

        if (idDireccion <= 0) {
            throw new NegocioException("ID de dirección inválido", null);
        }

        try {
            Direccion entidad = direccionesDAO.consultarPorId(idDireccion);

            if (entidad == null) {
                throw new NegocioException(
                    "Dirección no encontrada", null
                );
            }

            return new DireccionDTO(
                entidad.getCalle(),
                entidad.getNumero(),
                entidad.getColonia(),
                entidad.getCodigoPostal(),
                entidad.getCiudad()
            );

        } catch (PersistenciaException e) {
            throw new NegocioException(
                "Error al obtener dirección", e
            );
        }
    }
}
