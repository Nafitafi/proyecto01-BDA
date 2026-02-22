/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.banco.negocio;

import java.util.Date;
import java.util.List;
import org.itson.banco.dtos.OperacionDTO;

/**
 * Interfaz IOperacionBO.
 * Establece el contrato de los métodos que implican cualquier validación de negocio.
 * 
 * @author Nahomi Figueroa
 */
public interface IOperacionBO {
    /**
     * Método obtenerHistorialValidado.
     * Se encarga de validar y purificar los parametros de búsqueda de las operaciones
     * realizadas por el ID del cliente loggeado.
     * 
     * @param idCliente
     * @param tipo
     * @param inicio
     * @param fin
     * @return Lista de DTOS operacio+on para el transporte de datos
     * @throws NegocioException En caso de algún error de validación
     */
    public List<OperacionDTO> obtenerHistorialValidado(int idCliente, String tipo, Date inicio, Date fin) throws NegocioException;
}
