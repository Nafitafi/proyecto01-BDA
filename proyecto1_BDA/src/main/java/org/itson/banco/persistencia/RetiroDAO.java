
package org.itson.banco.persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.itson.banco.dtos.RetiroDTO;

/**
 *
 * @author oliro
 */
public class RetiroDAO implements IRetiroDAO {

    private static final Logger LOGGER = Logger.getLogger(RetiroDAO.class.getName());
    
    @Override
    public int generarRetiroSinCuenta(RetiroDTO retiroDTO) throws PersistenciaException {
        
        String comandoSQL = """
            CALL registrar_retiro_sin_cuenta(?, ?, ?, ?);
                            """;
        
        int folioObtenido = 0;
        
        try (Connection conn = ConexionBD.crearConexion(); CallableStatement comando = conn.prepareCall(comandoSQL);) {
            
            comando.setString(1, retiroDTO.getCuentaOrigen());
            comando.setString(2, retiroDTO.getContasena());
            comando.setDouble(3, retiroDTO.getMonto());
            comando.setInt(4, folioObtenido);
            comando.execute();
            
            folioObtenido = comando.getInt(4);
            
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible generar el retiro sin cuenta", ex);
        } 
        
        LOGGER.fine("Se genero el retiro sin cuenta correctamente.");
        return folioObtenido;
        
    }
    
    @Override
    public String validarRetiroPendiente(int folioRetiro) throws PersistenciaException {
        
        String comandoSQL = """
            SELECT contrasena_retiro FROM retiros_sin_cuenta WHERE estado_retiro = 'pendiente' AND folio_operacion = ?;
                            """;
        
        try (Connection conn = ConexionBD.crearConexion(); CallableStatement comando = conn.prepareCall(comandoSQL);) {
            
            comando.setInt(1, folioRetiro);
            ResultSet resultado = comando.executeQuery();
            
            if (resultado.next()) {
                String contrasena = resultado.getString("contrasena_retiro");
                return contrasena;
            } else {
                throw new PersistenciaException("No se ha encontrado ninfun retiro pendiente con ese folio.", null);
            }
            
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible encontrar la operacion de retiro sin cuenta.", ex);
        } 
        
    }
    
    @Override
    public boolean realizarRetiroSinCuenta(int folioRetiro) throws PersistenciaException {
        
        String comandoSQL = """
            CALL realizar_retiro_sin_cuenta(?, ?);
                            """;
        
        int folioObtenido = 0;
        
        try (Connection conn = ConexionBD.crearConexion(); CallableStatement comando = conn.prepareCall(comandoSQL);) {
            
            comando.setInt(1, folioRetiro);
            comando.setInt(2, folioObtenido);
            comando.execute();
            
        }catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible encontrar la operacion de retiro sin cuenta.", ex);
        } 
        
        LOGGER.fine("El retiro fue exitoso.");
        return true;
        
    }
    
}
