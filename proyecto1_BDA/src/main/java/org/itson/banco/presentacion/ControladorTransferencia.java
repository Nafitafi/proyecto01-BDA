
package org.itson.banco.presentacion;

import org.itson.banco.dtos.ClienteDTO;
import org.itson.banco.dtos.CuentaDTO;
import org.itson.banco.dtos.TransferenciaDTO;
import org.itson.banco.negocio.CuentaBO;
import org.itson.banco.negocio.ICuentaBO;
import org.itson.banco.persistencia.CuentaDAO;
import org.itson.banco.persistencia.ICuentaDAO;

/**
 *
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public class ControladorTransferencia {

    private ClienteDTO clienteLogueado;
    private CuentaDTO cuentaOrigen;
    private CuentaDTO cuentaNueva;

    public ControladorTransferencia(){
        
    }
    
    public ControladorTransferencia(ClienteDTO cliente){
        this.clienteLogueado = cliente;
    }
    
    public void iniciar() {
        // Pasamos el controlador al Login para que pueda llamarnos
        InicioFORM inicio = new InicioFORM(this);
        inicio.setVisible(true);
    }

    public void abrirLogin(){
        LoginFORM login = new LoginFORM(this);
        login.setVisible(true);
    }
    public void loginExitoso(ClienteDTO cliente) {
        this.clienteLogueado = cliente;
        CuentaDTO cuentaDTO = new CuentaDTO();
        ICuentaDAO cuentaDAO = new CuentaDAO(cuentaDTO);
        ICuentaBO cuentaBO = new CuentaBO (cuentaDAO);
        
        // Abrimos la pantalla de Cuentas pasándole el controlador y el cliente
        CuentasFORM cuentas = new CuentasFORM(this, clienteLogueado, cuentaBO);
        cuentas.setVisible(true);
    }
    
    public void abrirCrearNuevaCuenta(ControladorTransferencia controlador, ClienteDTO cliente){
        this.clienteLogueado = cliente;
        CuentaDTO cuentaDTO = new CuentaDTO();
        ICuentaDAO cuentaDAO = new CuentaDAO(cuentaDTO);
        ICuentaBO cuentaBO = new CuentaBO(cuentaDAO);
        // Esta fue la mafufada q me rifé, si ayuda familia
        
        NuevaCuentaFORM nuevaCuenta = new NuevaCuentaFORM(this, cliente, cuentaBO);
        nuevaCuenta.setVisible(true);
    }

    public void abrirCreacionCuentaExitosa(ClienteDTO clienteLogueado, CuentaDTO cuentaNueva){
        this.clienteLogueado = clienteLogueado;
        this.cuentaNueva = cuentaNueva;
        CreacionCuentaExitosaFORM exito = new CreacionCuentaExitosaFORM(this, clienteLogueado, cuentaNueva);
        exito.setVisible(true);
    }
    // Método para ir a transferencias (emy agarrate de aqui) (ok nafi gracias)
    public void irATransferencias(CuentaDTO cuentaSeleccionada) {
        this.cuentaOrigen = cuentaSeleccionada;
        TransferenciaFORM trans = new TransferenciaFORM(this, clienteLogueado, cuentaOrigen);
        trans.setVisible(true);
    }
    
    public void abrirConfirmarTransferencia(TransferenciaDTO transferenciaDTO){
        ConfirmarTransferenciaFORM confirmar = new ConfirmarTransferenciaFORM(this, clienteLogueado, transferenciaDTO, cuentaOrigen);
        confirmar.setVisible(true);
    }
    
    public void abrirTransferenciaExitosa(){
        TransferenciaExitosa transferenciaExitosa = new TransferenciaExitosa(this, clienteLogueado);
        transferenciaExitosa.setVisible(true);
    }

    
}
