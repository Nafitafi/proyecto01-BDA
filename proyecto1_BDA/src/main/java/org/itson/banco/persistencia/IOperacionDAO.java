/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.banco.persistencia;

import java.util.List;
import org.itson.banco.entidades.Operacion;

/**
 * Interfaz IOperacionDAO.
 * Contratoq que establece que la DAO
 * se encarga de tener el acceso con la base de datos.
 * 
 * @author Nahomi Figueroa
 */
public interface IOperacionDAO {
    /**
     * Método consultarHistorial.
     * Se encarga de consultar el listado de operaciones realizadas dentro de los parametros establecidos
     * 
     * @param idCliente
     * @param tipoOperacion
     * @param fechaInicio
     * @param fechaFin
     * @return Lista de DTOS de operaciones
     * @throws PersistenciaException en c aso de errores ocurridos dentro de esta clase.
     */
    public List<Operacion> consultarHistorial(int idCliente, String tipoOperacion, java.util.Date fechaInicio, java.util.Date fechaFin) throws PersistenciaException;
}
