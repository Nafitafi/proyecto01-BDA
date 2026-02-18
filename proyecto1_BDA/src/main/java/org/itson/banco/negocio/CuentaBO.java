/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.negocio;

import java.util.ArrayList;
import java.util.List;
import org.itson.banco.dtos.CuentaDTO;
import org.itson.banco.entidades.Cuenta;
import org.itson.banco.persistencia.ICuentaDAO;
import org.itson.banco.persistencia.PersistenciaException;

/**
 *
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public class CuentaBO implements ICuentaBO {

    private final ICuentaDAO cuentaDAO;

    public CuentaBO(ICuentaDAO cuentaDAO) {
        this.cuentaDAO = cuentaDAO;
    }

    @Override
    public List<CuentaDTO> obtenerCuentas(int idCliente) throws NegocioException {
        // Validación lógica
        if (idCliente <= 0) {
            throw new NegocioException("ID inválido", null);
        }

        try {
            List<Cuenta> entidades = cuentaDAO.consultarCuentasPorCliente(idCliente);

            List<CuentaDTO> dtos = new ArrayList<>();

            for (Cuenta entidad : entidades) {
                CuentaDTO dto = new CuentaDTO();

                dto.setNumeroCuenta(entidad.getNumeroCuenta());
                dto.setSaldoCuenta(entidad.getSaldo());
                dto.setEstado(entidad.getEstado());
                dto.setFechaApertura(entidad.getFechaApertura());

                dtos.add(dto);
            }

            return dtos;

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error en el sistema al obtener cuentas.", ex);
        }
    }
}
