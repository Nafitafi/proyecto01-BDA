/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import org.itson.banco.dtos.TransferenciaDTO;
import org.itson.banco.negocio.NegocioException;
import org.itson.banco.negocio.TransferenciaBO;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * Cuentas de prueba:
 * Cuenta origen 123-456-7890
 * Cuenta destino 234-567-8901
 * @author emyla
 */
public class TransferenciaDAOTest {
    
    public TransferenciaDAOTest(){
    }
    
    /***
     * Test testRealizarTransferenciaFuncionaOK()
     */
    @Test
    void testRealizarTransferenciaFuncionaOK(){
        
        // Cuenta origen 123-456-7890
        // Cuenta destino 234-567-8901
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO("123-456-7890", "234-567-8901", 100);
        TransferenciaBO transferenciaBO = new TransferenciaBO(new TransferenciaDAO(new ConexionBD()));
        
        int folio = Assertions.assertDoesNotThrow(() -> {
            return transferenciaBO.realizarTransferencia(transferenciaDTO);
        });
        
        assertNotNull(folio); 
        
    }

    @Test
    void testRealizarTransferenciaCuentaInvalida() {
        // Cuenta origen 123-456-7890
        // Cuenta destino 000-000-0000 invalida
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO("123-456-7890", "000-000-0000", 100);
        TransferenciaBO transferenciaBO = new TransferenciaBO(new TransferenciaDAO(new ConexionBD()));
        
        Assertions.assertThrows(NegocioException.class, () -> {
            transferenciaBO.realizarTransferencia(transferenciaDTO);
        });
        
    }
    
    @Test
    void testRealizarTransferenciaMontoInvalido() {
        // Cuenta origen 123-456-7890
        // Cuenta destino 234-567-8901 
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO("123-456-7890", "000-000-0000", 10000000);
        TransferenciaBO transferenciaBO = new TransferenciaBO(new TransferenciaDAO(new ConexionBD()));
        
        Assertions.assertThrows(NegocioException.class, () -> {
            transferenciaBO.realizarTransferencia(transferenciaDTO);
        });
        
    }
}
