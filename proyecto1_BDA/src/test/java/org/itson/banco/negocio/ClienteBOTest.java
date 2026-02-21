/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.banco.negocio;

import java.util.List;
import org.itson.banco.dtos.CuentaDTO;
import org.itson.banco.negocio.CuentaBO;
import org.itson.banco.negocio.ICuentaBO;
import org.itson.banco.negocio.NegocioException;
import org.itson.banco.persistencia.ConexionBD;
import org.itson.banco.persistencia.CuentaDAO;
import org.itson.banco.persistencia.ICuentaDAO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nafbr
 */
public class ClienteBOTest {

    public ClienteBOTest() {
    }

    @Test
    public void testSomeMethod() {
    }

    @Test
    public void testObtenerCuentasClienteExistente() {
        
        CuentaDTO dto = new CuentaDTO();
        ICuentaDAO cuentaDAO = new CuentaDAO(dto);

        ICuentaBO cuentaBO = new CuentaBO(cuentaDAO);

        int idClienteEmily = 1;

        List<CuentaDTO> cuentas = assertDoesNotThrow(() -> {
            return cuentaBO.obtenerCuentas(idClienteEmily);
        });

        assertNotNull(cuentas);
        assertFalse(cuentas.isEmpty());

        System.out.println("Cuentas recuperadas por BO: " + cuentas.size());
    }

    /**
     * Prueba testObtenerCuentasClienteInexistente(). Asegura que el BO retorne
     * una lista vacía (y no null) si el cliente no existe.
     */
    @Test
    void testObtenerCuentasClienteInexistente() {
        CuentaDTO dto = new CuentaDTO();
        ICuentaDAO cuentaDAO = new CuentaDAO(dto);
        ICuentaBO cuentaBO = new CuentaBO(cuentaDAO);

        int idFantasma = 999;

        List<CuentaDTO> cuentas = assertDoesNotThrow(() -> {
            return cuentaBO.obtenerCuentas(idFantasma);
        });

        assertNotNull(cuentas);
        assertTrue(cuentas.isEmpty());
    }

    /**
     * Prueba testObtenerCuentasIdInvalido().
     * El BO debe impedir que busquemos IDs negativos o
     * cero.
     */
    @Test
    void testObtenerCuentasIdInvalido() {
        CuentaDTO dto = new CuentaDTO();
        ICuentaDAO cuentaDAO = new CuentaDAO(dto);
        ICuentaBO cuentaBO = new CuentaBO(cuentaDAO);

        int idInvalido = -5; // Un ID negativo no tiene sentido je

        assertThrows(NegocioException.class, () -> {
            cuentaBO.obtenerCuentas(idInvalido);
        });
    }
}
