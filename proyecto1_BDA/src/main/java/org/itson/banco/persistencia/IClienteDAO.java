/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.banco.persistencia;

import org.itson.banco.entidades.Cliente;

/**
 * Interfaz IClienteDAO.
 * Contrato para el acceso de datos para los objetos Cliente
 * @author Nahomi Figueroa, Emily lara y Oliver Robles
 */
public interface IClienteDAO {
    /**
     * Método buscarPorCorreo().
     * Busca los datos del cliente mediante su correo
     * 
     * @param correo
     * @return el objeto cliente encontrado
     * @throws PersistenciaException en caso de algún fallo
     */
    Cliente buscarPorCorreo(String correo) throws PersistenciaException;
}
