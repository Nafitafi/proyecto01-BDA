
package org.itson.banco.persistencia;

import org.itson.banco.dtos.RetiroDTO;

/**
 *
 * @author oliro
 */
public interface IRetiroDAO {
    
    public abstract int generarRetiroSinCuenta(RetiroDTO retiroDTO) throws PersistenciaException;
    
    public abstract String validarRetiroPendiente(int folioRetiro) throws PersistenciaException;
    
    public abstract boolean realizarRetiroSinCuenta(int folioRetiro) throws PersistenciaException;
    
}
