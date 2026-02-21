/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.banco.persistencia;

import java.util.List;
import org.itson.banco.dtos.CuentaDTO;
import org.itson.banco.entidades.Cuenta;
import org.itson.banco.persistencia.ConexionBD;
import org.itson.banco.persistencia.CuentaDAO;
import org.itson.banco.persistencia.ICuentaDAO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nafbr
 */
public class CuentaDAOTest {

    private final ICuentaDAO cuentaDAO = new CuentaDAO();

    @Test
    public void testConsultarCuentasClienteExistente() {
        int idClienteValido = 1;
        List<Cuenta> cuentas = assertDoesNotThrow(() -> {
            return cuentaDAO.consultarCuentasPorCliente(idClienteValido);
        });

        assertNotNull(cuentas);
        assertFalse(cuentas.isEmpty());
        assertTrue(cuentas.get(0) instanceof Cuenta);
    }

    @Test
    void testConsultarCuentasClienteInexistente() {
        int idFantasma = 99999;

        List<Cuenta> cuentas = assertDoesNotThrow(() -> {
            return cuentaDAO.consultarCuentasPorCliente(idFantasma);
        });

        assertNotNull(cuentas);
        assertTrue(cuentas.isEmpty());
    }

    @Test
    void testConsultarCuentaPorNumeroExistente() {
        String numeroCuentaValido = "123-456-7890";

        Cuenta cuenta = assertDoesNotThrow(() -> {
            return cuentaDAO.consultarCuentaPorNumero(numeroCuentaValido);
        });

        assertNotNull(cuenta);
        assertTrue(cuenta instanceof Cuenta);
        assertTrue(cuenta.getNumeroCuenta().equalsIgnoreCase(numeroCuentaValido));
    }

    @Test
    void testConsultarCuentaPorNumeroInexistente() {
        String numeroCuentaInexistente = "1";

        Cuenta cuenta = assertDoesNotThrow(() -> {
            return cuentaDAO.consultarCuentaPorNumero(numeroCuentaInexistente);
        });

        assertNull(cuenta);
    }
}
