/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    @Override
    public boolean realizarTransferencia(TransferenciaDTO transferenciaDTO) throws PersistenciaException {
        
        String sqlRetiro = """
            UPDATE cuentas
            SET saldo = saldo - ?
            WHERE numero_cuenta = ?
                            """;
        String sqlIngreso = """
            UPDATE cuentas
            SET saldo = saldo + ?
            WHERE numero_cuenta = ?;
                                """;
        
        String insertOp = """
            
                          """;
        
        try (Connection conn = conexion.crearConexion()){
            
            conn.setAutoCommit(false);
            
            try (PreparedStatement comandoRetiro = conn.prepareStatement(sqlRetiro);
                    PreparedStatement comandoIngreso = conn.prepareStatement(sqlIngreso);){
                
                comandoRetiro.setDouble(1, transferenciaDTO.getMonto());
                comandoRetiro.setString(2, transferenciaDTO.getCuentaOrigen());
                comandoRetiro.executeUpdate();
                
                comandoIngreso.setDouble(1, transferenciaDTO.getMonto());
                comandoIngreso.setString(2, transferenciaDTO.getCuentaDestino());
                comandoIngreso.executeUpdate();
                
                conn.commit();
                
            } catch (SQLException ex) {
                conn.rollback();
                LOGGER.severe(ex.getMessage());
                throw new PersistenciaException("No fue posible realizar la transferencia", ex);
            }
            
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible realizar la transferencia", ex);
        } 
        
        
        
        LOGGER.fine("Se ha realizado la transferencia correctamente");
        return true;
        
    }
    
    public String realizarTransferencia2(TransferenciaDTO nuevaTransferencia) throws PersistenciaException{
        try{
            // Código SQL del Sorted Procedure
            String comandoSQL = "{CALL sp_realizar_transferencia(?, ?, ?)}";
            
            // Probamos la conexión
            Connection conn = conexion.crearConexion();
            CallableStatement comando = conn.prepareCall(comandoSQL);
            
            comando.setString(1, nuevaTransferencia.getCuentaOrigen());
            comando.setString(2, nuevaTransferencia.getCuentaDestino());
            comando.setDouble(3, nuevaTransferencia.getMonto());
            
            ResultSet resultado = comando.executeQuery();
            
            String mensaje = "";
            
            if(resultado.next()){
                mensaje = resultado.getString("mensaje");
            }
            
            comando.close();
            conn.close();
            
            LOGGER.fine("Transferencia realizada con exito");
            
            return mensaje;
        } catch (SQLException e){
            LOGGER.severe(e.getMessage());
            throw new PersistenciaException("No fue posible realizar la transferencia", e);
        }              
    }
        
}
