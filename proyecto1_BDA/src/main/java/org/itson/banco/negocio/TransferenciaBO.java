/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.negocio;

import org.itson.banco.dtos.TransferenciaDTO;
import org.itson.banco.persistencia.ITransferenciaDAO;
import org.itson.banco.persistencia.PersistenciaException;
import org.itson.banco.persistencia.TransferenciaDAO;

/**
 *
 * @author emyla
 */
public class TransferenciaBO implements ITransferenciaBO {
    
    private ITransferenciaDAO transferenciaDAO;
    
    
    public TransferenciaBO(TransferenciaDAO transfrenciaDAO){
            this.transferenciaDAO = transferenciaDAO;
    }
    
    @Override
    public String realizarTransferencia(
            TransferenciaDTO nuevaTransferencia
    ) throws NegocioException{
        
        if(nuevaTransferencia.getMonto() <= 0){
            throw new NegocioException("El monto debe ser mayor a $0.00", null);
        }
        
        if(nuevaTransferencia.getCuentaOrigen() <= 0){
            throw new NegocioException("La cuenta origen es obligatoria.", null); 
        }
        
        if(nuevaTransferencia.getCuentaDestino() <= 0){
            throw new NegocioException("La cuenta destino es obligatoria.", null);
        }
        
        if(nuevaTransferencia.getCuentaOrigen() == nuevaTransferencia.getCuentaDestino()){
            throw new NegocioException("La cuenta origen y la cuenta destino NO pueden ser la misma", null);
        }
        
        try{
            String resultado = transferenciaDAO.realizarTransferencia(nuevaTransferencia);
            
            if("Saldo insuficiente".equalsIgnoreCase(resultado)){
                throw new NegocioException("No cuenta con el saldo suficiente", null);
            }
            
            if(!"Transferencia realizada con exito".equalsIgnoreCase(resultado)){
                throw new NegocioException("No fue posible realizar la transferencia", null);
            }
            
            return resultado;
            
        } catch(PersistenciaException e){
            throw new NegocioException("Error al realizar la transferencia", e);
        }
        
    }
    
}
