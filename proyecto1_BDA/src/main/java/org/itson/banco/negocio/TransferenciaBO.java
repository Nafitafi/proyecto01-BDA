/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.negocio;

import org.itson.banco.dtos.CuentaDTO;
import org.itson.banco.dtos.TransferenciaDTO;
import org.itson.banco.persistencia.ITransferenciaDAO;
import org.itson.banco.persistencia.PersistenciaException;

/**
 *
 * @author emyla
 */
public class TransferenciaBO implements ITransferenciaBO {
    
    private ITransferenciaDAO transferenciaDAO;
    
    
    public TransferenciaBO(ITransferenciaDAO transfrenciaDAO){
            this.transferenciaDAO = transfrenciaDAO;
    }
    
    @Override
    public int realizarTransferencia(TransferenciaDTO transferenciaDTO) throws NegocioException{
        
        try{
            return transferenciaDAO.realizarTransferencia(transferenciaDTO);
        } catch(PersistenciaException e){
            throw new NegocioException("Error al realizar la transferencia", e);
        }
        
    }
    
    public boolean confirmarTransferencia(TransferenciaDTO transferenciaDTO, CuentaDTO cuentaOrigen) throws NegocioException {
        
        if(transferenciaDTO.getMonto() <= 0){
            throw new NegocioException("El monto debe ser mayor a $0.00", null);
        }
        
        if(cuentaOrigen.getSaldoCuenta() < transferenciaDTO.getMonto()) {
            throw new NegocioException("El monto no puede ser mayor a su saldo actual.", null);
        }
        
        if(transferenciaDTO.getCuentaOrigen() == null){
            throw new NegocioException("La cuenta origen es obligatoria.", null); 
        }
        
        if(transferenciaDTO.getCuentaDestino() == null){
            throw new NegocioException("La cuenta destino es obligatoria.", null);
        }
        
        if(transferenciaDTO.getCuentaOrigen().equalsIgnoreCase(transferenciaDTO.getCuentaDestino()) || transferenciaDTO.getCuentaDestino().equalsIgnoreCase(transferenciaDTO.getCuentaOrigen()) ){
            throw new NegocioException("La cuenta origen y la cuenta destino NO pueden ser la misma", null);
        }
        
        // Hacer la comprobacion de saldo suficiente
        
        return true;
    }
    
}
