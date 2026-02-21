/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;
import org.itson.banco.dtos.CuentaDTO;
import org.itson.banco.entidades.Cuenta;

/**
 * Clase CuentaDAO. Clase de acceso de datos que hace la conexión con la base de
 * datos.
 *
 * @author Nahomi Figueroa, Emily Lara y Oliver Robles
 */
public class CuentaDAO implements ICuentaDAO {

    private static final Logger LOGGER = Logger.getLogger(CuentaDAO.class.getName());
    
    /**
     * Método crearNuevaCuenta. Este método es el que se comunica directamente con la base de datos
     * enviando los datos para realizar el registro de la nueva cuenta (Una vez que ya se validaron los datos, claro)
     * @param nuevaCuenta
     * @param idClienteLogueado
     * @param numeroCuenta
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public Cuenta crearNuevaCuenta( // Recibe: 
            CuentaDTO nuevaCuenta, // La nueva cuenta
            int idClienteLogueado, // El id del cliente que hizo el proceso (para guardarlo automaticamente)
            String numeroCuenta // El numero de la cuenta que se generó automaticamente)
    )throws PersistenciaException{
        try{
            
            String codigoSQL = """
                INSERT INTO cuentas(numero_cuenta, fecha_apertura, saldo, estado, id_cliente)
                VALUES (?, ?, ?, ?, ?);   
             """; // Se crea el comando SQL 

             Connection conexion = ConexionBD.crearConexion(); // Se establece la conexión 
             PreparedStatement comando = conexion.prepareStatement(codigoSQL); // Preparamos nuestro comando para que se ejecute

             SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd"); // Creamos un formateador para la fecha y no
             // agarrarnos a guamazos mas adelante

             /**
              * Aquí se toma la fecha y con ayuda del formateador, le damos la forma que queremos q tome (en este caso: año-mes-dia) 
              */
             String fechaFormateada = formateador.format(nuevaCuenta.getFechaApertura().getTime()); 

             /** Aquí sustituimos los signos de interrogación del comando SQL con los datos de la cuenta nos ofreció (y los datos 
              * que nosotros tomamos automaticamente (como sus datos y la fecha)
              */
             comando.setString(1, numeroCuenta);
             comando.setString(2, fechaFormateada);
             comando.setDouble(3, nuevaCuenta.getSaldoCuenta());
             comando.setString(4, nuevaCuenta.getEstado());
             comando.setInt(5, idClienteLogueado);

             boolean resultado = comando.execute(); // checamos q todo esté en orden

             LOGGER.fine("Cuenta Registrada Con Éxito"); //Mensaje de éxito

             /**
              * Cerramos conexión y comandos
              */
             comando.close();
             conexion.close();
             
             // Enviamos OBJETO POJO con los datos que obtuvimos yipiii
             return new Cuenta(
                 nuevaCuenta.getNumeroCuenta(),
                 nuevaCuenta.getFechaApertura(),
                 nuevaCuenta.getSaldoCuenta(),
                 nuevaCuenta.getEstado(),
                 idClienteLogueado
             );


            } catch(SQLException e){
                LOGGER.severe(e.getMessage());
                throw new PersistenciaException("Error al crear nueva cuenta", e);
            }
    }

    @Override
    public List<Cuenta> consultarCuentasPorCliente(int idCliente) throws PersistenciaException {
        List<Cuenta> listaCuentas = new ArrayList<>();

        try {
            Connection conexion = ConexionBD.crearConexion();
            String comandoSQL = "SELECT numero_cuenta, saldo, estado, fecha_apertura FROM cuentas WHERE id_cliente = ?";

            PreparedStatement comando = conexion.prepareStatement(comandoSQL);            
            comando.setInt(1, idCliente);
            ResultSet resultados = comando.executeQuery();

//            SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
            
            while (resultados.next()) {
                Cuenta cuenta = new Cuenta();

                cuenta.setNumeroCuenta(resultados.getString("numero_cuenta"));
                cuenta.setSaldo(resultados.getDouble("saldo")); 
                cuenta.setEstado(resultados.getString("estado"));
                
                if(resultados.getDate("fecha_apertura")!=null){
                    GregorianCalendar fechaApertura = new GregorianCalendar();
                    fechaApertura.setTimeInMillis(resultados.getDate("fecha_apertura").getTime());
                    
                    // Ahora lo formateamos (Nafi esto t puede ayudar)
//                    String fechaAperturaFormateada = formateador.format(fechaApertura.getTime());
                
                    cuenta.setFechaApertura(fechaApertura);  
                }
                
                cuenta.setIdCliente(idCliente);
                listaCuentas.add(cuenta);
            }
            
            comando.close();
            conexion.close();
            
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible consultar las cuentas del cliente: ", ex);
        }

        return listaCuentas; 
    }

    /**
     * Metodo de consulta que trae la cuenta que empata con el numero de cuenta necesario.
     * @param numeroCuenta Numero de la cuenta que desea
     * @return La cuenta solicitada o null si no existen en la base
     * @throws PersistenciaException 
     */
    @Override
    public Cuenta consultarCuentaPorNumero(String numeroCuenta) throws PersistenciaException {
                
        try{
            Connection conexion = ConexionBD.crearConexion();
                        
            String comandoSQL = """
                SELECT numero_cuenta, saldo, estado, fecha_apertura, id_cliente
                FROM cuentas WHERE numero_cuenta = ?
            """;
        
            PreparedStatement comando = conexion.prepareStatement(comandoSQL);
            Cuenta cuenta = null;
            comando.setString(1, numeroCuenta);
            ResultSet resultados = comando.executeQuery();
            
//            SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
            
            if (resultados.next()) {
                
                cuenta = new Cuenta();
                
                cuenta.setNumeroCuenta(resultados.getString("numero_cuenta"));
                cuenta.setSaldo(resultados.getDouble("saldo"));
                cuenta.setEstado(resultados.getString("estado"));
                
                if(resultados.getTime("fecha_apertura")!=null){
                    GregorianCalendar fechaApertura = new GregorianCalendar();
                    fechaApertura.setTimeInMillis(resultados.getDate("fecha_apertura").getTime());
                    
                    //String fechaAperturaFormateada = formateador.format(fechaApertura.getTime());
                    
                    cuenta.setFechaApertura(fechaApertura);
                }
                                
                cuenta.setIdCliente(resultados.getInt("id_cliente"));
                return cuenta;
                
            }
            
            comando.close();
            conexion.close();
            
            return cuenta;
            
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al consultar cuenta.", ex);
        }
        
    }
    
    /**
     * Método validarNumeroCuenta. Este es el método que nos ayuda a validar (ademas de BO y la validación de la Base de Datos)
     * que el número de cuenta no esté repetido.
     * @param numeroCuenta
     * @return
     * @throws PersistenciaException 
     */
    public boolean validarNumeroCuenta(String numeroCuenta) throws PersistenciaException{
        try{
            String codigoSQL= """
                SELECT 1 FROM cuentas 
                WHERE numero_cuenta = ?
                LIMIT 1;
            """;
            
            /**
             * Básicamente se realiza una consulta rápida a la base de datos en la que se le pide buscar el número que obtuvimos
             * Esta busqueda nos arroja "falso" si el número no existe (Lo que queremos)
             * y arroja "verdadero" si el número ya está (which la probabilidad es baja... pero nunca cero...)
             */
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            
            comando.setString(1, numeroCuenta);
            ResultSet resultado = comando.executeQuery();
            return resultado.next();        
            
        } catch(SQLException e){
            throw new PersistenciaException("Error al verificar el número de cuenta", e);
        }
    }
    
}
