/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase ConexionBD.
 * Se en encarga de realizar la conexion con la base de datos.
 * @author Nahomi Figueroa, Emily Lara y  Oliver Robles
 */
public class ConexionBD {
    private static final String CADENACONEXION = "jdbc:mysql://localhost:3306/banco";
    private static final String USER = "root";
    private static final String CONTRA = "maNENE6166!";

    /**
     * Método estatico para crear una conexión.
     * @return Objeto Connection para realizar la conexión como tal.
     * @throws SQLException en caso de algún fallo
     */
    public static Connection crearConexion() throws SQLException{
        Connection conexion = DriverManager.getConnection(ConexionBD.CADENACONEXION, ConexionBD.USER, ConexionBD.CONTRA);
        return conexion;
    }
}
