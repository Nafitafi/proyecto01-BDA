
package org.itson.banco.negocio;

import java.security.SecureRandom;
import org.itson.banco.dtos.RetiroDTO;
import org.itson.banco.persistencia.PersistenciaException;
import org.itson.banco.persistencia.RetiroDAO;

/**
 *
 * @author oliro
 */
public class RetiroBO implements IRetiroBO {

    private RetiroDAO retiroDAO;
    
    public RetiroBO(){
        retiroDAO = new RetiroDAO();
    }
    
    @Override
    public RetiroDTO generarRetiroSinCuenta(RetiroDTO retiroDTO) throws NegocioException {
        
        
        String contrasena = String.valueOf(generarContrasena());
        retiroDTO.setContasena(contrasena);
        
        try {
            retiroDAO.generarRetiroSinCuenta(retiroDTO);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible generar el retiro sin cuenta", null);
        }
        
        return retiroDTO;
    }
    
    private int generarContrasena() {
        SecureRandom random = new SecureRandom();
        int min = 10_000_000;
        int max = 99_999_999;
        return random.nextInt((max - min) + 1) + min;
    }
    
}
