
package org.itson.banco.presentacion;

import java.util.ArrayList;
import java.util.List;
import org.itson.banco.dtos.ClienteDTO;
import org.itson.banco.dtos.CuentaDTO;
import org.itson.banco.dtos.TransferenciaDTO;
import org.itson.banco.negocio.ClienteBO;
import org.itson.banco.negocio.CuentaBO;
import org.itson.banco.negocio.IClienteBO;
import org.itson.banco.negocio.ICuentaBO;
import org.itson.banco.persistencia.ClienteDAO;
import org.itson.banco.persistencia.ConexionBD;
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
   
    private final IClienteBO clienteBO;
    private final ICuentaBO cuentaBO;
    private List<CuentaDTO> cuentasCliente;
    
    public ControladorTransferencia() {
        this.clienteBO = new ClienteBO(new ClienteDAO());
        this.cuentaBO = new CuentaBO(new CuentaDAO()); 
    }
    
    public ControladorTransferencia(ClienteDTO cliente){
        this();
        this.clienteLogueado = cliente;
    }
    
    public void iniciar() {
        InicioFORM inicio = new InicioFORM(this);
        inicio.setVisible(true);
    }

    public void abrirLogin(){
        LoginFORM login = new LoginFORM(this, this.clienteBO);
        login.setVisible(true);
    }
    
    public void abrirInicioCliente(ClienteDTO cliente){
        this.clienteLogueado = cliente;
        InicioClienteFORM inicio = new InicioClienteFORM(this, this.clienteLogueado);
        inicio.setVisible(true);
    }
    
    public void loginExitoso(ClienteDTO cliente) {
        this.clienteLogueado = cliente;
        CuentasFORM cuentas = new CuentasFORM(this, this.clienteLogueado, this.cuentaBO);
        cuentas.setVisible(true);
    }
    
    public void cargarCuentasDelCliente(int idCliente, ICuentaBO cuentaBO) throws Exception {
        this.cuentasCliente = cuentaBO.obtenerCuentas(idCliente);
    }
    
    public CuentaDTO obtenerCuentaPorNumero(String numeroCuenta) {
        if (this.cuentasCliente != null) {
            for (CuentaDTO cuenta : this.cuentasCliente) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    return cuenta; // Regresa el objeto completo
                }
            }
        }
        return null;
    }
    
    public List<String> obtenerNumerosDeCuenta() {
        List<String> numeros = new ArrayList<>();
        if (this.cuentasCliente != null) {
            for (CuentaDTO cuenta : this.cuentasCliente) {
                numeros.add(cuenta.getNumeroCuenta());
            }
        }
        return numeros;
    }
    
    public double obtenerSaldo(String numeroCuenta) {
        if (this.cuentasCliente != null) {
            for (CuentaDTO cuenta : this.cuentasCliente) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    return cuenta.getSaldoCuenta();
                }
            }
        }
        return 0.0;
    }
    
    public void abrirCrearNuevaCuenta(ClienteDTO cliente){
        this.clienteLogueado = cliente;
        NuevaCuentaFORM nuevaCuenta = new NuevaCuentaFORM(this, this.clienteLogueado, this.cuentaBO);
        nuevaCuenta.setVisible(true);
    }

    public void abrirCreacionCuentaExitosa(ClienteDTO clienteLogueado, CuentaDTO cuentaNueva){
        this.clienteLogueado = clienteLogueado;
        this.cuentaNueva = cuentaNueva;
        CreacionCuentaExitosaFORM exito = new CreacionCuentaExitosaFORM(this, this.clienteLogueado, this.cuentaNueva);
        exito.setVisible(true);
    }
    
    // Método para ir a transferencias (emy agarrate de aqui) (ok nafi gracias)
    public void irATransferencias(CuentaDTO cuentaSeleccionada) {
        this.cuentaOrigen = cuentaSeleccionada;
        TransferenciaFORM trans = new TransferenciaFORM(this, this.clienteLogueado, this.cuentaOrigen);
        trans.setVisible(true);
    }
    
    public void abrirConfirmarTransferencia(TransferenciaDTO transferenciaDTO){
        ConfirmarTransferenciaFORM confirmar = new ConfirmarTransferenciaFORM(this, clienteLogueado, transferenciaDTO, cuentaOrigen);
        confirmar.setVisible(true);
    }
    
    public void abrirTransferenciaExitosa(int folio){
        TransferenciaExitosa transferenciaExitosa = new TransferenciaExitosa(this, clienteLogueado, folio);
        transferenciaExitosa.setVisible(true);
    }

    
}
