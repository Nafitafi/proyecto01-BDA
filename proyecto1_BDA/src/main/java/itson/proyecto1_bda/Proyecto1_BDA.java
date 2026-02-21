/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package itson.proyecto1_bda;

import org.itson.banco.negocio.TransferenciaBO;
import org.itson.banco.persistencia.ITransferenciaDAO;
import org.itson.banco.persistencia.TransferenciaDAO;
import org.itson.banco.presentacion.ControladorTransferencia;

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
        ControladorTransferencia casoTransferencia = new ControladorTransferencia();
        casoTransferencia.iniciar();
    }
}
