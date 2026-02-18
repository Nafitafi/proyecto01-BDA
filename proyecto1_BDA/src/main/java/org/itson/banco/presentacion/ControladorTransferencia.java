/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.presentacion;

import org.itson.banco.dtos.ClienteDTO;

/**
 *
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public class ControladorTransferencia {

    private ClienteDTO clienteLogueado;

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

    // Método para ir a transferencias (emy agarrate de aqui)
    public void irATransferencias() {
        // TransferenciaFORM trans = new TransferenciaFORM(this, clienteLogueado);
        // trans.setVisible(true);
    }
}
