/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import org.itson.banco.dtos.ClienteDTO;
import org.itson.banco.dtos.DireccionDTO;
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
     * Método registrarNuevoCliente. Se encarga de registrar en la base de datos
     * al cliente que desee registrarse
     * @param nuevoCliente
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public Cliente registrarNuevoCliente(ClienteDTO nuevoCliente) throws PersistenciaException{
        try{
            String codigoSQL = """              
               INSERT INTO clientes(nombres, apellido_paterno, apellido_materno, fecha_nacimiento, correo, contrasena, id_direccion)
               VALUES (?, ?, ?, ?, ?, ?, ?)                        
            """;
            
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(
                codigoSQL,
                PreparedStatement.RETURN_GENERATED_KEYS
            );
            java.sql.Date fechaFormateada = new java.sql.Date(
                nuevoCliente.getFechaNacimiento().getTimeInMillis()
            );
            
            comando.setString(1, nuevoCliente.getNombres());
            comando.setString(2, nuevoCliente.getApellidoPaterno());
            comando.setString(3, nuevoCliente.getApellidoMaterno());
            comando.setDate(4, fechaFormateada);
            comando.setString(5, nuevoCliente.getCorreo());
            comando.setString(6, nuevoCliente.getConstrasena());
            comando.setInt(7, nuevoCliente.getIdDireccion());
            
            comando.executeUpdate(); 


            ResultSet rs = comando.getGeneratedKeys();
            int idGenerado = 0;
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }

            LOGGER.fine("Se registro la cuenta con éxito");
            
            comando.close();
            conexion.close();
            
            return new Cliente(
               idGenerado,
               nuevoCliente.getNombres(),
               nuevoCliente.getApellidoPaterno(),
               nuevoCliente.getApellidoMaterno(),
               nuevoCliente.getFechaNacimiento(),
               null,
               nuevoCliente.getCorreo(),
               nuevoCliente.getConstrasena(),
               nuevoCliente.getIdDireccion()            
            );
            
            
        } catch(SQLException e){
            throw new PersistenciaException("Error al registrar al cliente", e);
        }
    }

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
        String comandoSQL = "SELECT id_cliente, nombres, apellido_paterno, apellido_materno, correo, contrasena, id_direccion FROM clientes WHERE correo = ?";

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
                cliente.setDireccion(resultados.getInt("id_direccion"));

                return cliente;
            } else {
                return null; 
            }
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fué posible consultar al cliente: ", ex);
        }
    }
    
    @Override
    public ClienteDTO consultarDatosClienteDTO(String correo) throws PersistenciaException {

        String sql = """
            SELECT 
                c.id_cliente,
                c.nombres,
                c.apellido_paterno,
                c.apellido_materno,
                c.fecha_nacimiento,
                c.correo,
                c.contrasena,
                c.id_direccion,
                d.calle,
                d.numero,
                d.colonia,
                d.codigo_postal,
                d.ciudad
            FROM clientes c
            LEFT JOIN direcciones_clientes d ON c.id_direccion = d.id_direccion
            WHERE LOWER(c.correo) = LOWER(?)
        """;

        try (
            Connection conn = ConexionBD.crearConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, correo);
            System.out.println("Buscando cliente con correo: " + correo); // Debug

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ClienteDTO clienteDTO = new ClienteDTO();

                clienteDTO.setId(rs.getInt("id_cliente"));
                clienteDTO.setNombres(rs.getString("nombres"));
                clienteDTO.setApellidoPaterno(rs.getString("apellido_paterno"));
                clienteDTO.setApellidoMaterno(rs.getString("apellido_materno"));

                java.sql.Date fechaSQL = rs.getDate("fecha_nacimiento");
                if (fechaSQL != null) {
                    GregorianCalendar fecha = new GregorianCalendar();
                    fecha.setTime(fechaSQL);
                    clienteDTO.setFechaNacimiento(fecha);
                }

                clienteDTO.setCorreo(rs.getString("correo"));
                clienteDTO.setIdDireccion(rs.getInt("id_direccion"));

                // Crear y asignar la dirección
                if (rs.getInt("id_direccion") > 0) {
                    DireccionDTO direccion = new DireccionDTO(
                        rs.getString("calle"),
                        rs.getString("numero"),
                        rs.getString("colonia"),
                        rs.getString("codigo_postal"),
                        rs.getString("ciudad")
                    );
                    clienteDTO.setDireccion(direccion);
                }

                System.out.println("Cliente encontrado: " + clienteDTO.getNombres()); // Debug

                return clienteDTO;
            }

            System.out.println("No se encontró cliente con correo: " + correo); // Debug
            return null;

        } catch (SQLException e) {
            e.printStackTrace(); // Debug
            throw new PersistenciaException(
                "Error al consultar datos completos del cliente", e
            );
        }
    }
    
    @Override
    public ClienteDTO consultarPorId(int idCliente) throws PersistenciaException {

        String sql = """
            SELECT 
                c.id_cliente,
                c.nombres,
                c.apellido_paterno,
                c.apellido_materno,
                c.fecha_nacimiento,
                c.correo,
                c.contrasena,
                c.id_direccion,
                d.calle,
                d.numero,
                d.colonia,
                d.codigo_postal,
                d.ciudad
            FROM clientes c
            LEFT JOIN direcciones_clientes d ON c.id_direccion = d.id_direccion
            WHERE c.id_cliente = ?
        """;

        try (
            Connection conn = ConexionBD.crearConexion();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, idCliente);
            System.out.println("Buscando cliente con ID: " + idCliente); // Debug

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ClienteDTO clienteDTO = new ClienteDTO();

                clienteDTO.setId(rs.getInt("id_cliente"));
                clienteDTO.setNombres(rs.getString("nombres"));
                clienteDTO.setApellidoPaterno(rs.getString("apellido_paterno"));
                clienteDTO.setApellidoMaterno(rs.getString("apellido_materno"));

                java.sql.Date fechaSQL = rs.getDate("fecha_nacimiento");
                if (fechaSQL != null) {
                    GregorianCalendar fecha = new GregorianCalendar();
                    fecha.setTime(fechaSQL);
                    clienteDTO.setFechaNacimiento(fecha);
                }

                clienteDTO.setCorreo(rs.getString("correo"));
                clienteDTO.setIdDireccion(rs.getInt("id_direccion"));

                // Crear y asignar la dirección si existe
                if (rs.getInt("id_direccion") > 0) {
                    DireccionDTO direccion = new DireccionDTO(
                        rs.getString("calle"),
                        rs.getString("numero"),
                        rs.getString("colonia"),
                        rs.getString("codigo_postal"),
                        rs.getString("ciudad")
                    );
                    clienteDTO.setDireccion(direccion);
                }

                System.out.println("Cliente encontrado: " + clienteDTO.getNombres()); // Debug
                return clienteDTO;
            }

            System.out.println("No se encontró cliente con ID: " + idCliente); // Debug
            return null;

        } catch (SQLException e) {
            e.printStackTrace(); // Debug
            throw new PersistenciaException(
                "Error al consultar cliente por ID: " + idCliente, e
            );
        }
    }
}
