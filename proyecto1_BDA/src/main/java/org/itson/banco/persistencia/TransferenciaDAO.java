/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.itson.banco.dtos.TransferenciaDTO;

/**
 *
 * @author emyla
 */
public class TransferenciaDAO implements ITransferenciaDAO{
    
    private static final Logger LOGGER = Logger.getLogger(TransferenciaDAO.class.getName());
    
    @Override
    public String realizarTransferencia(
            TransferenciaDTO nuevaTransferencia
    ) throws PersistenciaException{
        try{
            // Código SQL del Sorted Procedure
            String comandoSQL = "{CALL sp_realizar_transferencia(?, ?, ?)}";
            
            // Probamos la conexión
            Connection conexion = ConexionBD.crearConexion();
            CallableStatement comando = conexion.prepareCall(comandoSQL);
            
            comando.setInt(1, nuevaTransferencia.getCuentaOrigen());
            comando.setInt(2, nuevaTransferencia.getCuentaDestino());
            comando.setDouble(3, nuevaTransferencia.getMonto());
            
            ResultSet resultado = comando.executeQuery();
            
            String mensaje = "";
            
            if(resultado.next()){
                mensaje = resultado.getString("mensaje");
            }
            
            comando.close();
            conexion.close();
            
            LOGGER.fine("Transferencia realizada con exito");
            
            return mensaje;
        } catch (SQLException e){
            LOGGER.severe(e.getMessage());
            throw new PersistenciaException("No fue posible realizar la transferencia", e);
        }              
    }
        
}
