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
    Cliente buscarPorCorreo(String correo) throws PersistenciaException;
}
