/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.presentacion;

import org.itson.banco.dtos.ClienteDTO;
import org.itson.banco.dtos.CuentaDTO;
import org.itson.banco.dtos.TransferenciaDTO;
import org.itson.banco.negocio.ITransferenciaBO;
import org.itson.banco.negocio.TransferenciaBO;
import org.itson.banco.persistencia.ITransferenciaDAO;
import org.itson.banco.persistencia.TransferenciaDAO;

/**
 *
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public class ControladorTransferencia {

    private ClienteDTO clienteLogueado;
    private CuentaDTO cuentaOrigen;

    public ControladorTransferencia(){
        
    }
    
    public ControladorTransferencia(ClienteDTO cliente){
        this.clienteLogueado = cliente;
    }
    
    public void iniciar() {
        // Pasamos el controlador al Login para que pueda llamarnos
        LoginFORM login = new LoginFORM(this);
        login.setVisible(true);
    }

    public void loginExitoso(ClienteDTO cliente) {
        this.clienteLogueado = cliente;
        
        // Abrimos la pantalla de Cuentas pasándole el controlador y el cliente
        CuentasFORM cuentas = new CuentasFORM(this, clienteLogueado);
        cuentas.setVisible(true);
    }

    // Método para ir a transferencias (emy agarrate de aqui) (ok nafi gracias)
    public void irATransferencias(CuentaDTO cuentaSeleccionada) {
        this.cuentaOrigen = cuentaSeleccionada;
        TransferenciaFORM trans = new TransferenciaFORM(this, clienteLogueado, cuentaOrigen);
        trans.setVisible(true);
    }
    
    public String realizarTransferencia(TransferenciaDTO dto) throws Exception {

        ITransferenciaDAO transferenciaDAO = new TransferenciaDAO();
        TransferenciaBO transferenciaBO = new TransferenciaBO(transferenciaDAO);
        
        return transferenciaBO.realizarTransferencia(dto);
    }
    
    public void abrirConfirmarTransferencia(){
        ConfirmarTransferenciaFORM confirmar = new ConfirmarTransferenciaFORM(this, clienteLogueado);
        confirmar.setVisible(true);
    }
}
