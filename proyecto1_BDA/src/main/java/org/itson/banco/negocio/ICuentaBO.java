/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.banco.negocio;

import java.util.List;
import org.itson.banco.dtos.CuentaDTO;
import org.itson.banco.entidades.Cuenta;

/**
 * Interfaz CuentaBO.
 * 
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public interface ICuentaBO {

    
    public abstract Cuenta crearNuevaCuenta(CuentaDTO nuevaCuenta, int idClienteLogueado) throws NegocioException;
    
    List<CuentaDTO> obtenerCuentas(int idCliente) throws NegocioException;
    
}
