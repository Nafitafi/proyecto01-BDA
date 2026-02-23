/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.banco.persistencia;

import org.itson.banco.dtos.RetiroDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author oliro
 */
public class IRetiroDAOTest {
    
    public IRetiroDAOTest() {
    }

    @Test
    public void generarRetiroSinCuentaFuncionaOk() {
        // folio, monto, cuentaOrigen, contrasena, estado, retiro
        RetiroDTO retiroDTO = new RetiroDTO(0, 100, "234-567-8901", "12345678", "pendiente", null);
        RetiroDAO retiroDAO = new RetiroDAO();
        
        int folio = assertDoesNotThrow(() -> {
            return retiroDAO.generarRetiroSinCuenta(retiroDTO);
        });
        
        System.out.println(folio);
        
        assertNotNull(folio);
        
    }
    
    @Test
    public void validarRetiroPendienteFuncionaOk() {
        
        RetiroDTO retiroDTO = new RetiroDTO(0, 100, "234-567-8901", "12345678", "pendiente", null);
        RetiroDAO retiroDAO = new RetiroDAO();
        
        int folio = assertDoesNotThrow(() -> {
            return retiroDAO.generarRetiroSinCuenta(retiroDTO);
        });
        
        assertNotNull(folio);
        
        String contrasena = assertDoesNotThrow(() -> {
            return retiroDAO.validarRetiroPendiente(folio);
        });
        
        assertNotNull(contrasena);
        assertEquals("12345678", contrasena);
        
    }
    
    @Test
    public void realizarRetiroSinCuentaFuncionaOk() {
        
        RetiroDTO retiroDTO = new RetiroDTO(0, 100, "234-567-8901", "12345678", "pendiente", null);
        RetiroDAO retiroDAO = new RetiroDAO();
        
        int folio = assertDoesNotThrow(() -> {
            return retiroDAO.generarRetiroSinCuenta(retiroDTO);
        });
        
        assertNotNull(folio);
        
        String contrasena = assertDoesNotThrow(() -> {
            return retiroDAO.validarRetiroPendiente(folio);
        });
        
        assertNotNull(contrasena);
        assertEquals("12345678", contrasena);
        
        boolean resultado = assertDoesNotThrow(() -> {
            return retiroDAO.realizarRetiroSinCuenta(folio);
        });
        
        assertTrue(resultado);
        
    }
    
}
