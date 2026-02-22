/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.itson.banco.dtos.OperacionDTO;
import org.itson.banco.entidades.Operacion;
import org.itson.banco.persistencia.IOperacionDAO;
import org.itson.banco.persistencia.PersistenciaException;

/**
 * Clase OperacionBO. Se encarga de validar la información de la consulta de
 * operaciones.
 *
 * @author Nahomi Figueroa
 */
public class OperacionBO implements IOperacionBO {

    private final IOperacionDAO operacionDAO;

    public OperacionBO(IOperacionDAO operacionDAO) {
        this.operacionDAO = operacionDAO;
    }

    @Override
    public List<OperacionDTO> obtenerHistorialValidado(int idCliente, String tipo, Date inicio, Date fin) throws NegocioException {
        if (idCliente <= 0) {
            throw new NegocioException("ID de cliente inválido.", null);
        }
        if (inicio != null && fin != null && inicio.after(fin)) {
            throw new NegocioException("La fecha inicio no puede ser mayor a la fecha fin.", null);
        }

        try {
            List<Operacion> entidades = operacionDAO.consultarHistorial(idCliente, tipo, inicio, fin);
            List<OperacionDTO> dtos = new ArrayList<>();

            for (Operacion entidad : entidades) {
                OperacionDTO dto = new OperacionDTO();

                dto.setTipoMovimiento(entidad.getTipoMovimiento());
                dto.setFolio(entidad.getFolio());
                dto.setMonto(entidad.getMonto());
                dto.setFechaHora(entidad.getFechaHora());

                dtos.add(dto);
            }

            return dtos;

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error en el sistema al obtener el historial.", ex);
        }
    }
}

