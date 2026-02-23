
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

    private final RetiroDAO retiroDAO;
    
    private int folioSeleccionado;
    
    public RetiroBO(){
        retiroDAO = new RetiroDAO();
    }
    
    @Override
    public RetiroDTO generarRetiroSinCuenta(RetiroDTO retiroDTO) throws NegocioException {
        
        
        String contrasena = String.valueOf(generarContrasena());
        retiroDTO.setContasena(contrasena);
        
        try {
            int folio = retiroDAO.generarRetiroSinCuenta(retiroDTO);
            retiroDTO.setFolioRetiro(folio);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible generar el retiro sin cuenta", null);
        }
        
        return retiroDTO;
    }
    
    @Override
    public void confirmarRetiroSinCuenta(int folio, String contrasena) throws NegocioException {
        
        String cc = "";
        
        try {
            cc = retiroDAO.validarRetiroPendiente(folio);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Folio o contraseña invalidos", null);
        }
        
        if (!contrasena.equals(cc)) {
            throw new NegocioException("Folio o contraseña invalidos", null);
        } 
        
        folioSeleccionado = folio;
        
    }
    
    @Override
    public void realizarRetiroSinCuenta() throws NegocioException {
        
        try {
            retiroDAO.realizarRetiroSinCuenta(folioSeleccionado);
        } catch (PersistenciaException ex){
            throw new NegocioException("No ha sido posible realizar el retiro sin cuenta", null);
        }
        
    }
    
    @Override
    public RetiroDTO consultarRetiro() throws NegocioException {
        
        try {
            return retiroDAO.consultarRetiro(folioSeleccionado);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible encontrar el registro de retiro", null);
        }
        
    }
    
    @Override
    public int generarContrasena() {
        SecureRandom random = new SecureRandom();
        int min = 10_000_000;
        int max = 99_999_999;
        return random.nextInt((max - min) + 1) + min;
    }
    
}
