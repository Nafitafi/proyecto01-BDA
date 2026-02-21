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

    private final IClienteDAO clienteDAO = new ClienteDAO();

    @Test
    public void testBuscarPorCorreoExistente() {
        String correoReal = "emily@gmail.com";

        Cliente resultado = assertDoesNotThrow(() -> {
            return clienteDAO.buscarPorCorreo(correoReal);
        });

        assertNotNull(resultado);
        assertNotNull(resultado.getContrasena());
    }

    @Test
    public void testBuscarPorCorreoInexistente() {
        String correoFalso = "fantasma@banco.com";

        Cliente resultado = assertDoesNotThrow(() -> {
            return clienteDAO.buscarPorCorreo(correoFalso);
        });

        assertNull(resultado);
    }
}
