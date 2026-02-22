
package org.itson.banco.persistencia;

/**
 *
 * @author oliro
 */
public interface IRetiroDAO {
    
    public abstract int realizarRetiroSinCuenta() throws PersistenciaException;
    
}
