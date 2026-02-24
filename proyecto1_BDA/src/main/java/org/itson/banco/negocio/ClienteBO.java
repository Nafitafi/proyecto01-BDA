/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.negocio;

import java.util.GregorianCalendar;
import org.itson.banco.dtos.ClienteDTO;
import org.itson.banco.dtos.DireccionDTO;
import org.itson.banco.entidades.Cliente;
import org.itson.banco.entidades.Direccion;
import org.itson.banco.persistencia.ClienteDAO;
import org.itson.banco.persistencia.DireccionesDAO;
import org.itson.banco.persistencia.IClienteDAO;
import org.itson.banco.persistencia.IDireccionesDAO;
import org.itson.banco.persistencia.PersistenciaException;

/**
 * Clase ClienteBO.
 * Objeto de negocio cliente, se encarga de la lógica de validación 
 * para los clientes al iniciar sesión.
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public class ClienteBO implements IClienteBO {

    private final IClienteDAO clienteDAO;
    private final IDireccionesDAO direccionesDAO;
    private DireccionesBO direccionesBO;

    /**
     * Constructor de cliente BO.
     * 
     * @param clienteDAO ClienteDAO para el acceso del Cliente en cuestión.
     */
    public ClienteBO(IClienteDAO clienteDAO) {
        this.clienteDAO = new ClienteDAO();
        this.direccionesDAO = new DireccionesDAO();
        this.direccionesBO = new DireccionesBO(direccionesDAO);
    }
    

    /**
     * @inheritDoc
     */
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
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error en el sistema. Intente más tarde.", ex);
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
        clienteDTO.setCorreo(clienteEntidad.getCorreo());
        clienteDTO.setIdDireccion(clienteEntidad.getIdDireccion());

        return clienteDTO;
    }
    
    public DireccionDTO obtenerDireccionCliente(int idCliente) throws NegocioException {
        try {
            // Primero obtener el cliente para saber su id_direccion
            // Necesitarías un método para obtener cliente por ID
            ClienteDTO cliente = clienteDAO.consultarPorId(idCliente);

            if (cliente == null || cliente.getIdDireccion() <= 0) {
                return null;
            }

            return direccionesBO.obtenerDireccion(cliente.getIdDireccion());

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener dirección del cliente", e);
        }
    }
   @Override
    public ClienteDTO consultarDatosCliente(String correo) throws NegocioException {
        try {
            ClienteDTO clienteDTO = clienteDAO.consultarDatosClienteDTO(correo);

            if (clienteDTO == null) {
                throw new NegocioException("Cliente no encontrado", null);
            }
            
            // Obtener la dirección usando el id_direccion
            if (clienteDTO.getIdDireccion() > 0) {
                DireccionDTO direccionDTO = direccionesBO.obtenerDireccion(clienteDTO.getIdDireccion());
                clienteDTO.setDireccion(direccionDTO); // Necesitarás agregar este setter en ClienteDTO
            }

            return clienteDTO;

        } catch (PersistenciaException e) {
            e.printStackTrace(); // Debug
            throw new NegocioException(
                "No fue posible consultar los datos del cliente", e
            );
        }
    }
    
    @Override
    public Cliente registrarCliente(
            ClienteDTO clienteDTO,
            DireccionDTO direccionDTO
    ) throws NegocioException {

        try {
           
            Direccion direccion = direccionesDAO.registrarDireccion(direccionDTO);
            System.out.println("ID DIRECCION = " + direccion.getIdDireccion());
            
            clienteDTO.setIdDireccion(direccion.getIdDireccion());

            
            return clienteDAO.registrarNuevoCliente(clienteDTO);

        } catch (PersistenciaException e) {
            throw new NegocioException(
                "No fue posible registrar el cliente", e
            );
        }
    }

}
