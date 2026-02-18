/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.negocio;

import org.itson.banco.dtos.ClienteDTO;
import org.itson.banco.entidades.Cliente;
import org.itson.banco.persistencia.IClienteDAO;
import org.itson.banco.persistencia.PersistenciaException;

/**
 *
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public class ClienteBO implements IClienteBO {

    private final IClienteDAO clienteDAO;

    public ClienteBO(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public ClienteDTO login(String correo, String contrasena) throws NegocioException {
        if (correo == null || correo.trim().isEmpty()) {
            throw new NegocioException("El correo no puede estar vacío.", null);
        }
        if (contrasena == null || contrasena.trim().isEmpty()) {
            throw new NegocioException("La contraseña es obligatoria.", null);
        }
        if (!correo.contains("@") || !correo.contains(".")) {
            throw new NegocioException("El formato del correo es inválido.", null);
        }
        Cliente clienteEntidad = null;
        try {
            clienteEntidad = clienteDAO.buscarPorCorreo(correo);
        } catch (PersistenciaException e) {
            //TODO
            throw new NegocioException("Error en el sistema. Intente más tarde.", e);
        }

        if (clienteEntidad == null) {
            throw new NegocioException("Usuario no encontrado.", null);
        }

        if (!clienteEntidad.getContrasena().equals(contrasena)) {
            throw new NegocioException("Contraseña incorrecta.", null);
        }

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(clienteEntidad.getId());
        clienteDTO.setNombres(clienteEntidad.getNombres());
        clienteDTO.setApellidoPaterno(clienteEntidad.getApellidoPaterno());
        clienteDTO.setApellidoMaterno(clienteEntidad.getApellidoMaterno());

        return clienteDTO;
    }

}
