/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.banco.negocio;

import org.itson.banco.dtos.ClienteDTO;
import org.itson.banco.persistencia.ClienteDAO;
import org.itson.banco.persistencia.IClienteDAO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nafbr
 */
public class ClienteBOTest {

    private final IClienteDAO clienteDAO = new ClienteDAO();
    private final IClienteBO clienteBO = new ClienteBO(clienteDAO);

    /**
     * Prueba el happy path, un cliente que sí existe pone su contraseña
     * correcta.
     */
    @Test
    public void testIniciarSesionCredencialesValidas() {
        String correoReal = "emily@gmail.com";
        String contrasenaReal = "123456"; 

        ClienteDTO clienteLogueado = assertDoesNotThrow(() -> {
            return clienteBO.login(correoReal, contrasenaReal);
        });

        assertNotNull(clienteLogueado);
    }

    /**
     * El cliente existe, pero se equivocó de contraseña.
     * El BO debe bloquear el paso lanzando una NegocioException.
     */
    @Test
    public void testIniciarSesionContrasenaIncorrecta() {
        String correoReal = "emily@gmail.com";
        String contrasenaFalsa = "contrasenaEquivocada123";

        assertThrows(NegocioException.class, () -> {
            clienteBO.login(correoReal, contrasenaFalsa);
        });
    }

    /**
     * Alguien intenta entrar con un correo que no está
     * registrado. El BO debe lanzar una NegocioException diciendo que el
     * usuario no existe.
     */
    @Test
    public void testIniciarSesionCorreoInexistente() {
        String correoFalso = "fantasma@banco.com";
        String contrasenaCualquiera = "123456";

        assertThrows(NegocioException.class, () -> {
            clienteBO.login(correoFalso, contrasenaCualquiera);
        });
    }

    /**
     * EL usuario no escribió nadota. El BO debería rechazarlo antes de siquiera ir a la Base de
     * Datos.
     */
    @Test
    public void testIniciarSesionCamposVacios() {
        String correoVacio = "";
        String contrasenaVacia = "";

        assertThrows(NegocioException.class, () -> {
            clienteBO.login(correoVacio, contrasenaVacia);
        });
    }
}
