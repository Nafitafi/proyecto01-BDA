
package org.itson.banco.negocio;

import org.itson.banco.dtos.RetiroDTO;

/**
 *
 * @author oliro
 */
public interface IRetiroBO {
    
    public abstract RetiroDTO generarRetiroSinCuenta(RetiroDTO retiroDTO) throws NegocioException;
    
}
