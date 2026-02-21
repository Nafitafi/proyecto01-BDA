/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import org.itson.banco.dtos.TransferenciaDTO;

/**
 *
 * @author emyla
 */
public interface ITransferenciaDAO {
    
    public abstract int realizarTransferencia(TransferenciaDTO nuevaTransferencia) throws PersistenciaException;
    
}
