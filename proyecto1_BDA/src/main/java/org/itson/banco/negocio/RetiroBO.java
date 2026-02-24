
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
    
    /**
     * Metodo encargado de generar y registrar un retiro sin cuenta junto a su contrasena correspondiente
     * @param numeroCuenta
     * @param monto
     * @return
     * @throws NegocioException 
     */
    @Override
    public RetiroDTO generarRetiroSinCuenta(String numeroCuenta, double monto) throws NegocioException {
        
        if (monto < 10) {
            throw new NegocioException("El monto a retirar debe ser mayor a 10$", null);
        }
        
        if (monto > 10000) {
            throw new NegocioException("El monto a retirar no puede ser mayor a 10000", null);
        }
        
        String contrasena = String.valueOf(generarContrasena());
        
        RetiroDTO retiroDTO = new RetiroDTO(monto, numeroCuenta, contrasena);
        
        try {
            int folio = retiroDAO.generarRetiroSinCuenta(retiroDTO);
            retiroDTO.setFolioRetiro(folio);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible generar el retiro sin cuenta", null);
        }
        
        return retiroDTO;
    }
    
    /**
     * Metodo encargado de confirmar que el folio y contrasena sean validos y pertenezcan a un retiro sin cuenta
     * @param folio
     * @param contrasena
     * @throws NegocioException 
     */
    @Override
    public void confirmarRetiroSinCuenta(int folio, String contrasena) throws NegocioException {
        
        if (folio < 10000000 || folio > 99999999) {
            throw new NegocioException("El folio debe ser un número positivo de 8 dígitos.", null);
        }

        // Validación de Contraseña (Solo números y 8 dígitos)
        if (contrasena == null || !contrasena.matches("\\d{8}")) {
            throw new NegocioException("La contraseña debe ser numérica y de 8 dígitos.", null);
        }
        
        try {
            
            String cc = retiroDAO.validarRetiroPendiente(folio);
            if (!contrasena.equals(cc)) {
                throw new NegocioException("Folio o contraseña invalido.", null);
            } 
            
            folioSeleccionado = folio;
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("Folio o contraseña invalido.", null);
        }
        
    }
    
    /**
     * Metodo encargado de realizar un retiroSinCuenta marcado como pendiente, liberando el monto y cambiando
     * el estado del retiro
     * @throws NegocioException 
     */
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
