
package org.itson.banco.persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.SQLException;
import org.itson.banco.dtos.TransferenciaDTO;

/**
 *
 * @author emyla
 */
public class TransferenciaDAO implements ITransferenciaDAO{
    
    private static final Logger LOGGER = Logger.getLogger(TransferenciaDAO.class.getName());
    private final ConexionBD conexion;
    
    public TransferenciaDAO(ConexionBD conexionBD){
        this.conexion = conexionBD;
    }
    
    /**
     * Metodo encargado de realizar una transferencia, usa un stored procedure con 
     * la logica y registro de la transferencia.
     * @param transferenciaDTO
     * @return Regresa true/false dependiendo el exito de la operacion
     * @throws PersistenciaException 
     */
    @Override
    public int realizarTransferencia(TransferenciaDTO transferenciaDTO) throws PersistenciaException {
        
        String sqlTransferencia = """
            CALL realizar_transferencia(?, ?, ?, ?);
                            """;
        
        int folioObtenido = 0;
        
        try (Connection conn = conexion.crearConexion(); CallableStatement comando = conn.prepareCall(sqlTransferencia);){
            
            comando.setString(1, transferenciaDTO.getCuentaOrigen());
            comando.setString(2, transferenciaDTO.getCuentaDestino());
            comando.setDouble(3, transferenciaDTO.getMonto());
            comando.setInt(4, folioObtenido);
            comando.execute();
            
            folioObtenido = comando.getInt(4);
            
            
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible realizar la transferencia", ex);
        } 
        
        LOGGER.fine("Se ha realizado la transferencia correctamente");
        return folioObtenido;
        
    }
            
}
