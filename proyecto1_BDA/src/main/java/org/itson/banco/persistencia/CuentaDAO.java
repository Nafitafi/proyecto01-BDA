/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.itson.banco.dtos.CuentaDTO;
import org.itson.banco.entidades.Cuenta;

/**
 * Clase CuentaDAO. Clase de acceso de datos que hace la conexión con la base de
 * datos.
 *
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public class CuentaDAO implements ICuentaDAO {

    private static final Logger LOGGER = Logger.getLogger(CuentaDAO.class.getName());
    private final ConexionBD conexion;

    public CuentaDAO(ConexionBD conexionBD) {
        this.conexion = conexionBD;
    }

    @Override
    public List<Cuenta> consultarCuentasPorCliente(int idCliente) throws PersistenciaException {
        List<Cuenta> listaCuentas = new ArrayList<>();

        String comandoSQL = "SELECT numero_cuenta, saldo, estado, fecha_apertura FROM cuentas WHERE id_cliente = ?";

        try (Connection conn = conexion.crearConexion(); PreparedStatement comando = conn.prepareStatement(comandoSQL)) {

            comando.setInt(1, idCliente);
            ResultSet resultados = comando.executeQuery();

            while (resultados.next()) {
                Cuenta cuenta = new Cuenta();

                cuenta.setNumeroCuenta(resultados.getInt("numero_cuenta"));
                cuenta.setSaldo(resultados.getDouble("saldo")); 
                cuenta.setEstado(resultados.getString("estado"));
                cuenta.setFechaApertura(resultados.getString("fecha_apertura"));
                cuenta.setIdCliente(idCliente);

                listaCuentas.add(cuenta);
            }
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible consultar las cuentas del cliente: ", ex);
        }

        return listaCuentas; 
    }
}
