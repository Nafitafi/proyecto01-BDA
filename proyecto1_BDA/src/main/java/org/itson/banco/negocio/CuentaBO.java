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
    
    /** 
     * Este método se encargara de validar lo necesario para poder crear una nueva cuenta
     * @param nuevaCuenta
     * @param idClienteLogueado
     * @return
     * @throws NegocioException 
     */
    @Override
    public Cuenta crearNuevaCuenta(
            CuentaDTO nuevaCuenta, 
            int idClienteLogueado
    ) throws NegocioException{
        try{
            String numCuenta;
            /** En este bloque, le pedimos al flujo que CONTINUE creando numeros aleatoriamente
            *   mientras no se esté duplicado (cosa que es dificil que suceda pero no imposible)
            *   Cabe recalcar que esta válidación se apoya de otra validación de CuentaDAO
            **/
            do{
                numCuenta = generarNumeroCuenta();
            } while(cuentaDAO.validarNumeroCuenta(numCuenta)); 
            
            nuevaCuenta.setNumeroCuenta(numCuenta); // Una vez validado, se setea el numero de cuenta aleatorio
            
            /**
             * Se envia un objeto Cuenta DAO acompañado de los datos para la creación de la cuenta
             * Ojo que se envía el id del cliente que inicio sesión directamente, de esta forma se evita suplantación de identidad
             * Tambien se envia el numero de cuenta q se acaba de generar.
             * 
            **/ 
            return cuentaDAO.crearNuevaCuenta(
                    nuevaCuenta, 
                    idClienteLogueado, 
                    numCuenta
            );

        } catch (PersistenciaException e){
            throw new NegocioException("Error al crear nueva cuenta", e);
        }
       
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
    
    
    /**
     * Método privado para generar el número de cuenta aleatorio
     * Solo es utilizado en esta clase :3
     * @return 
     */
    private String generarNumeroCuenta() {
        int parte1 = (int) (Math.random() * 900) + 100;   
        int parte2 = (int) (Math.random() * 900) + 100;   
        int parte3 = (int) (Math.random() * 9000) + 1000; 

        return parte1 + "-" + parte2 + "-" + parte3;
    }
}
