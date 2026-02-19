/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.banco.persistencia;

import java.util.List;
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
    
    public CuentaDAOTest() {
    }

    @Test
    public void testSomeMethod() {
    }
    /**
     * Prueba testConsultarCuentasClienteExistente().
     * Asegura que el DAO recupere una lista de ENTIDADES Cuenta.
     */
    @Test
    public void testConsultarCuentasClienteExistente() {
        
        ConexionBD conexion = new ConexionBD();
        ICuentaDAO cuentaDAO = new CuentaDAO(conexion);
        int idClienteValido = 1; 
        List<Cuenta> cuentas = assertDoesNotThrow(() -> {
            return cuentaDAO.consultarCuentasPorCliente(idClienteValido);
        });

        assertNotNull(cuentas);
        assertFalse(cuentas.isEmpty());
        
        // Verificar que el objeto dentro sea una Entidad
        assertTrue(cuentas.get(0) instanceof Cuenta);
    }

    /**
     * Prueba testConsultarCuentasClienteInexistente().
     */
    @Test
    void testConsultarCuentasClienteInexistente() {
        
        ConexionBD conexion = new ConexionBD();
        ICuentaDAO cuentaDAO = new CuentaDAO(conexion);
        int idFantasma = 99999;

        List<Cuenta> cuentas = assertDoesNotThrow(() -> {
            return cuentaDAO.consultarCuentasPorCliente(idFantasma);
        });

        assertNotNull(cuentas);
        assertTrue(cuentas.isEmpty());
    }
    
    /**
     * Prueba testConsultarCuentaPorNumeroExistente().
     * Comprobar que el metodo regrese la cuenta pertinente
     */
    @Test
    void testConsultarCuentaPorNumeroExistente() {
        
        ConexionBD conexion = new ConexionBD();
        ICuentaDAO cuentaDAO = new CuentaDAO(conexion);
        int numeroCuentaValido = 2;
        
        Cuenta cuenta = assertDoesNotThrow(() -> {
            return cuentaDAO.consultarCuentaPorNumero(numeroCuentaValido);
        });
        
        assertNotNull(cuenta);
        assertTrue(cuenta instanceof Cuenta);
        assertTrue(cuenta.getNumeroCuenta() == numeroCuentaValido);
        
    }
    
    /***
     * Prueba testConsultarCuentaPorNumeroInexistente()
     * Comprobar que si no se encuentra la cuenta regrese una Cuenta nula
     */
    @Test
    void testConsultarCuentaPorNumeroInexistente() {
        
        ConexionBD conexion = new ConexionBD();
        ICuentaDAO cuentaDAO = new CuentaDAO(conexion);
        int NumeroCuentaInexistente = 50;
        
        Cuenta cuenta = assertDoesNotThrow(() -> {
            return cuentaDAO.consultarCuentaPorNumero(NumeroCuentaInexistente);
        });
        
        assertNull(cuenta);
        
    }
    
}
