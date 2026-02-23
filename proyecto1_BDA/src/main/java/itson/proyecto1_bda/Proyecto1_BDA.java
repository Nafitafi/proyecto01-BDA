/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package itson.proyecto1_bda;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.itson.banco.dtos.ClienteDTO;
import org.itson.banco.dtos.CuentaDTO;
import org.itson.banco.negocio.TransferenciaBO;
import org.itson.banco.persistencia.ITransferenciaDAO;
import org.itson.banco.persistencia.TransferenciaDAO;
import org.itson.banco.presentacion.ConfirmarRetiroSinCuentaFORM;
import org.itson.banco.presentacion.ControladorTransferencia;
import org.itson.banco.presentacion.CuentaDatosFORM;
import org.itson.banco.presentacion.GenerarRetiroSinCuentaFORM;
import org.itson.banco.presentacion.RetiroSinCuentaFORM;

/**
 * Clase principal del proyecto.
 * Aquí se inicializa todo-
 * @author nafbr
 */
public class Proyecto1_BDA {

    /**
     * Método main.
     * @param args 
     */
    public static void main(String[] args) {
        
        //Datos de prueba
        //ClienteDTO clienteDTO = new ClienteDTO(2, "Oliver", "Robles", "Cota");
        //GregorianCalendar fecha_apertura = new GregorianCalendar(2023, Calendar.FEBRUARY, 15);
        //CuentaDTO cuentaDTO = new CuentaDTO("234-567-8901", 300.50, "activa", fecha_apertura);
        
        ControladorTransferencia controlador = new ControladorTransferencia();
        controlador.iniciar();
        
        //RetiroSinCuentaFORM form1 = new RetiroSinCuentaFORM();
        //form1.setVisible(true);
        
        //ConfirmarRetiroSinCuentaFORM form2 = new ConfirmarRetiroSinCuentaFORM();
        //form2.setVisible(true);
        
        //CuentaDatosFORM form3 = new CuentaDatosFORM(controlador, clienteDTO, cuentaDTO);
        //form3.setVisible(true);
        
        //GenerarRetiroSinCuentaFORM form4 = new GenerarRetiroSinCuentaFORM();
        //form4.setVisible(true);
        
    }
}
