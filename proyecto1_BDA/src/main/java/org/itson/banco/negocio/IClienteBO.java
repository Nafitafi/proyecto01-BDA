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
    
    ClienteDTO login(String correo, String contrasena) throws NegocioException;
    
}
