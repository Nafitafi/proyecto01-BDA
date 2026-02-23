/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.banco.negocio;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.itson.banco.dtos.OperacionDTO;
import org.itson.banco.persistencia.IOperacionDAO;
import org.itson.banco.persistencia.OperacionDAO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nafbr
 */
public class OperacionBOTest {
    
    public OperacionBOTest() {
    }

    @Test
    public void testSomeMethod() {
    }
    
    /**
     * Prueba unitaria que asegura que si funcione la validación del ID
     */
    @Test
    public void testObtenerHistorialIdInvalido() {
        IOperacionDAO operacionDAO = new OperacionDAO();
        IOperacionBO operacionBO = new OperacionBO(operacionDAO);
        
        NegocioException excepcion = assertThrows(NegocioException.class, () -> {
            operacionBO.obtenerHistorialValidado(-1, "Todas", null, null);
        });
        
        assertEquals("ID de cliente inválido.", excepcion.getMessage());
    }

    /**
     * Prueba unitaria que asegura que al ingrear fechas invalidas no pasa nada raro
     */
    @Test
    public void testObtenerHistorialFechasInvalidas() {
        IOperacionDAO operacionDAO = new OperacionDAO();
        IOperacionBO operacionBO = new OperacionBO(operacionDAO);
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date inicio = cal.getTime(); 
        
        cal.add(Calendar.DAY_OF_MONTH, -2);
        Date fin = cal.getTime(); 
        
        NegocioException excepcion = assertThrows(NegocioException.class, () -> {
            operacionBO.obtenerHistorialValidado(1, "Todas", inicio, fin);
        });
        
        assertEquals("La fecha inicio no puede ser mayor a la fecha fin.", excepcion.getMessage());
    }

    /**
     * Prueba unitaria del happy path
     */
    @Test
    public void testObtenerHistorialExitoso() {
        IOperacionDAO operacionDAO = new OperacionDAO();
        IOperacionBO operacionBO = new OperacionBO(operacionDAO);
        
        try {
            List<OperacionDTO> resultado = operacionBO.obtenerHistorialValidado(1, "Todas", null, null);
            
            assertNotNull(resultado, "La lista de DTOs no debe ser nula");
            
        } catch (NegocioException ex) {
            fail("No debió lanzar excepción con datos correctos: " + ex.getMessage());
        }
    }
}
