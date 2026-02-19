/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.banco.persistencia;

import java.util.List;
import org.itson.banco.dtos.CuentaDTO;
import org.itson.banco.entidades.Cuenta;

/**
 * Interfaz ICuentaDAO.
 * Contrato para el acceso de datos para los objetos Cuenta
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public interface ICuentaDAO {
    List<Cuenta> consultarCuentasPorCliente(int idCliente) throws PersistenciaException;
    
    Cuenta consultarCuentaPorNumero(int numeroCuenta) throws PersistenciaException;
    
}
