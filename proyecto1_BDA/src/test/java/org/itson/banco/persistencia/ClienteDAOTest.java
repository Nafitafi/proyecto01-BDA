/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.banco.persistencia;

import org.itson.banco.entidades.Cliente;
import org.itson.banco.persistencia.ClienteDAO;
import org.itson.banco.persistencia.ConexionBD;
import org.itson.banco.persistencia.IClienteDAO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nafbr
 */
public class ClienteDAOTest {

    public ClienteDAOTest() {
    }

    @Test
    public void testSomeMethod() {
    }

    /**
     * Prueba buscarPorCorreoExistente. Verifica que si el correo existe,
     * retorne una ENTIDAD Cliente completa.
     */
    @Test
    public void testBuscarPorCorreoExistente() {
        ConexionBD conexion = new ConexionBD();
        IClienteDAO clienteDAO = new ClienteDAO(conexion);
        
        String correoReal = "emily@gmail.com"; 

      
        Cliente resultado = assertDoesNotThrow(() -> {
            return clienteDAO.buscarPorCorreo(correoReal);
        });

        assertNotNull(resultado);
        
        assertNotNull(resultado.getContrasena());
    }

    /**
     * Prueba buscarPorCorreoInexistente. Verifica que retorne NULL si el correo
     * no está en la base de datos.
     */
    @Test
    public void testBuscarPorCorreoInexistente() {

        ConexionBD conexion = new ConexionBD();
        IClienteDAO clienteDAO = new ClienteDAO(conexion);
        String correoFalso = "fantasma@banco.com";

        Cliente resultado = assertDoesNotThrow(() -> {
            return clienteDAO.buscarPorCorreo(correoFalso);
        });

        assertNull(resultado);
    }
}
