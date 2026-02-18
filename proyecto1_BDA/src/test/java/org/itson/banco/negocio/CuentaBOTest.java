/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.banco.negocio;

import java.util.List;
import org.itson.banco.dtos.CuentaDTO;
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
    
    public CuentaBOTest() {
    }

    @Test
    public void testSomeMethod() {
    }
    /**
     * Prueba testObtenerCuentasValidas().
     * Verifica que el BO devuelva DTOs listos para la pantalla.
     */
    @Test
    public void testObtenerCuentasValidas() {

        // Preparación de dependencias
        ConexionBD conexion = new ConexionBD();
        ICuentaDAO cuentaDAO = new CuentaDAO(conexion);
        ICuentaBO cuentaBO = new CuentaBO(cuentaDAO); 
        
        int idClienteValido = 1;

        List<CuentaDTO> cuentas = assertDoesNotThrow(() -> {
            return cuentaBO.obtenerCuentas(idClienteValido);
        });

        assertNotNull(cuentas);
        assertFalse(cuentas.isEmpty());
        
        // Confirmar que son DTOs
        assertTrue(cuentas.get(0) instanceof CuentaDTO);
        System.out.println("Saldo recuperado en DTO: " + cuentas.get(0).getSaldoCuenta());
    }

    /**
     * Prueba testObtenerCuentasIdInvalido().
     * No permitir IDs negativos o cero.
     */
    @Test
    public void testObtenerCuentasIdInvalido() {

        ConexionBD conexion = new ConexionBD();
        ICuentaDAO cuentaDAO = new CuentaDAO(conexion);
        ICuentaBO cuentaBO = new CuentaBO(cuentaDAO);
        
        int idNegativo = -5;

        // Debe lanzar NegocioException, NO debe llamar a la BD
        assertThrows(NegocioException.class, () -> {
            cuentaBO.obtenerCuentas(idNegativo);
        });
    }
}
