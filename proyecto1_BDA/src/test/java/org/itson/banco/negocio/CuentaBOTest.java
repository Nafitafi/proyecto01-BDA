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
public class CuentaBOTest {

    private final ICuentaDAO cuentaDAO = new CuentaDAO();
    private final ICuentaBO cuentaBO = new CuentaBO(cuentaDAO);

    @Test
    public void testObtenerCuentasValidas() {
        int idClienteValido = 1;

        List<CuentaDTO> cuentas = assertDoesNotThrow(() -> {
            return cuentaBO.obtenerCuentas(idClienteValido);
        });

        assertNotNull(cuentas);
        assertFalse(cuentas.isEmpty());
        assertTrue(cuentas.get(0) instanceof CuentaDTO);
        System.out.println("Saldo recuperado en DTO: " + cuentas.get(0).getSaldoCuenta());
    }

    @Test
    void testObtenerCuentasClienteInexistente() {
        int idFantasma = 999;

        List<CuentaDTO> cuentas = assertDoesNotThrow(() -> {
            return cuentaBO.obtenerCuentas(idFantasma);
        });

        assertNotNull(cuentas);
        assertTrue(cuentas.isEmpty());
    }

    @Test
    void testObtenerCuentasIdInvalido() {
        int idInvalido = -5;

        assertThrows(NegocioException.class, () -> {
            cuentaBO.obtenerCuentas(idInvalido);
        });
    }
}
