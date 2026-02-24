/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.itson.banco.dtos.DireccionDTO;
import org.itson.banco.entidades.Direccion;

/**
 * Clase DireccionesDAO. 
 * @author Emily Lara
 */
public class DireccionesDAO implements IDireccionesDAO {
    
    private static final Logger LOGGER = Logger.getLogger(DireccionesDAO.class.getName());
    private ConexionBD conexion;
    
    
    public DireccionesDAO(ConexionBD conexion){
        this.conexion = conexion;
    }
    
    public DireccionesDAO(){
    }
    
    @Override
    public Direccion registrarDireccion(DireccionDTO direccion)throws PersistenciaException{
        try{
            String codigoSQL = """
                 INSERT INTO direcciones_clientes(calle, numero, colonia, codigo_postal, ciudad)
                 VALUES (?, ?, ?, ?, ?);   
             """; // Se crea el comando SQL 

            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(
                codigoSQL,
                PreparedStatement.RETURN_GENERATED_KEYS
            );

            comando.setString(1, direccion.getCalle());
            comando.setString(2, direccion.getNumero());
            comando.setString(3, direccion.getColonia());
            comando.setString(4, direccion.getCodigoPostal());
            comando.setString(5, direccion.getCiudad());

            comando.executeUpdate(); // 👈 INSERT correcto


            var rs = comando.getGeneratedKeys();
            int idGenerado = 0;
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }


            LOGGER.fine("Se registró la dirección correctamente");

            comando.close();
            conexion.close();

            return new Direccion(
                    idGenerado,
                    direccion.getCalle(),
                    direccion.getNumero(),
                    direccion.getColonia(),
                    direccion.getCodigoPostal(),
                    direccion.getCiudad()
            );
           
        } catch(SQLException e){
            LOGGER.severe(e.getMessage());
            throw new PersistenciaException("No se pudo registrar la direccion", e);
        }
    }
    
    @Override
    public Direccion consultarPorId(int idDireccion)
            throws PersistenciaException {
        try {
            String sql = """
                SELECT id_direccion, calle, numero, colonia,
                       codigo_postal, ciudad
                FROM direcciones_clientes
                WHERE id_direccion = ?;
            """;

            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql);
            comando.setInt(1, idDireccion);

            ResultSet rs = comando.executeQuery();

            Direccion direccion = null;
            if (rs.next()) {
                direccion = new Direccion(
                    rs.getInt("id_direccion"),
                    rs.getString("calle"),
                    rs.getString("numero"),
                    rs.getString("colonia"),
                    rs.getString("codigo_postal"),
                    rs.getString("ciudad")
                );
            }

            comando.close();
            conexion.close();
            return direccion;

        } catch (SQLException e) {
            throw new PersistenciaException(
                "Error al consultar la dirección", e
            );
        }
    }
    
}
