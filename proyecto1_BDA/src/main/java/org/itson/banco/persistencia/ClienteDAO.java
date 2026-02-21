/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.itson.banco.dtos.ClienteDTO;
import org.itson.banco.entidades.Cliente;

/**
 * Clase ClienteDAO. Clase de acceso de datos que hace la conexión con la base
 * de datos.
 *
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles.
 */
public class ClienteDAO implements IClienteDAO {

    private static final Logger LOGGER = Logger.getLogger(ClienteDAO.class.getName());


    /**
     * Método bucarPorCredenciales. Se encarga de buscar que en la base de datos
     * se encuentren las credenciales de inicio de sesión.
     *
     * @param correo
     * @param contrasena
     * @return
     * @throws PersistenciaException
     */
    @Override
    public Cliente buscarPorCorreo(String correo) throws PersistenciaException {
        String comandoSQL = "SELECT id_cliente, nombres, apellido_paterno, apellido_materno, correo, contrasena FROM clientes WHERE correo = ?";

        try (Connection conn = ConexionBD.crearConexion(); 
             PreparedStatement comando = conn.prepareStatement(comandoSQL)) {

            comando.setString(1, correo); 
            ResultSet resultados = comando.executeQuery();

            if (resultados.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultados.getInt("id_cliente"));
                cliente.setNombres(resultados.getString("nombres"));
                cliente.setApellidoPaterno(resultados.getString("apellido_paterno"));
                cliente.setApellidoMaterno(resultados.getString("apellido_materno"));
                cliente.setCorreo(resultados.getString("correo"));
                cliente.setContrasena(resultados.getString("contrasena"));

                return cliente;
            } else {
                return null; 
            }
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fué posible consultar al cliente: ", ex);
        }
    }
}
