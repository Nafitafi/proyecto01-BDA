/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.banco.negocio;

import org.itson.banco.dtos.ClienteDTO;
import org.itson.banco.dtos.CuentaDTO;
import org.itson.banco.entidades.Cuenta;

/**
 * Interfaz IClientesBO. 
 * Se encarga de toda la lógica de validación el la capa de negocio.
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public interface IClienteBO {

    /**
     * Método login.
     * 
     * Ejectuta la acción de login segun su correo y contraseña, valida los campos
     * solicitados.
     * 
     * @param correo Correo del cliente.
     * @param contrasena Contraseña del cliente.
     * @return Cliente DTO para transportarlo entre capas.
     * @throws NegocioException 
     */
    ClienteDTO login(String correo, String contrasena) throws NegocioException;
    
}
