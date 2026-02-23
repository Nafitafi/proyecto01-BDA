
package org.itson.banco.presentacion;

import org.itson.banco.dtos.ClienteDTO;
import org.itson.banco.dtos.CuentaDTO;

/**
 *
 * @author oliro
 */
public class ControladorRetirSinCuenta {
    
    // Cambiar los controladores :b
    private ControladorTransferencia controladorTransferencia;
    private ClienteDTO clienteSeleccionado;
    private CuentaDTO cuentaOrigen;
    
    public ControladorRetirSinCuenta(ControladorTransferencia controladorTransferencia, ClienteDTO cliente, CuentaDTO cuenta) {
        this.controladorTransferencia = controladorTransferencia;
        this.clienteSeleccionado = cliente;
        this.cuentaOrigen = cuenta;
    }
    
    public void abrirGenerarRetiroSinCuenta() {
        GenerarRetiroSinCuentaFORM pantalla = new GenerarRetiroSinCuentaFORM(controladorTransferencia, clienteSeleccionado, cuentaOrigen);
        pantalla.setVisible(true);
    }
    
}
