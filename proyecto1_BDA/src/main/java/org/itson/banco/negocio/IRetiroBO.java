
package org.itson.banco.negocio;

import org.itson.banco.dtos.RetiroDTO;

/**
 *
 * @author oliro
 */
public interface IRetiroBO {
    
    public abstract RetiroDTO generarRetiroSinCuenta(String numeroCuenta, double monto) throws NegocioException;
    
    public abstract void confirmarRetiroSinCuenta(int folio, String contrasena) throws NegocioException;
    
    public abstract void realizarRetiroSinCuenta() throws NegocioException;
    
    public abstract RetiroDTO consultarRetiro() throws NegocioException;
    
    public abstract int generarContrasena();
    
}
