/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.banco.persistencia;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.itson.banco.entidades.Operacion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nafbr
 */
public class OperacionDAOTest {
    
    public OperacionDAOTest() {
    }

    @Test
    public void testSomeMethod() {
    }
    
    /**
     * Prueba unitaria que asegura que si funcione consultar historial (todas a nivel persistencia)
     */
    @Test
    public void testConsultarHistorialSinFiltros() {
        try {
            IOperacionDAO operacionDAO = new OperacionDAO();
            List<Operacion> resultado = operacionDAO.consultarHistorial(1, "Todas", null, null);
            assertNotNull(resultado);    
        } catch (PersistenciaException ex) {
            fail("Falló la consulta a la base de datos: " + ex.getMessage());
        }
    }
    
    /**
     * Prueba unitaria que asegura que si funcione consultar historial (con filtro de fechas a nivel persistencia)
     */
    @Test
    public void testConsultarHistorialConFiltroFechas() {
        try {
            IOperacionDAO operacionDAO = new OperacionDAO();
            
            Calendar cal = Calendar.getInstance();
            Date fin = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, -5);
            Date inicio = cal.getTime();
            
            List<Operacion> resultado = operacionDAO.consultarHistorial(1, "Todas", inicio, fin);
            
            assertNotNull(resultado);
            
        } catch (PersistenciaException ex) {
            fail("Falló la consulta con filtro de fechas: " + ex.getMessage());
        }
    }
}
