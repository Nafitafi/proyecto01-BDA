/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;
import org.itson.banco.entidades.Operacion;

/**
 * Clase OperacionDAO. Se encarga de recibir los datos de la base de datos.
 *
 * @author Nahomi Figueroa
 */
public class OperacionDAO implements IOperacionDAO {

    private static final Logger LOGGER = Logger.getLogger(OperacionDAO.class.getName());

    /**
     * Constructor por omisión.
     */
    public OperacionDAO() {

    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Operacion> consultarHistorial(int idCliente, String tipoOperacion, Date fechaInicio, Date fechaFin) throws PersistenciaException {

        List<Operacion> historial = new ArrayList<>();
        StringBuilder comandoSQL = new StringBuilder("""
            SELECT o.tipo_operacion, 
                   o.folio_operacion, 
                   o.fecha_operacion,
                   COALESCE(t.monto, r.monto, 0.00) AS monto
            FROM operaciones o
            INNER JOIN cuentas c ON o.numero_cuenta = c.numero_cuenta
            LEFT JOIN transferencias t ON o.folio_operacion = t.folio_operacion
            LEFT JOIN retiros_sin_cuenta r ON o.folio_operacion = r.folio_operacion
            WHERE c.id_cliente = ?
        """);

        if (tipoOperacion != null && !tipoOperacion.equals("Todas")) {
            comandoSQL.append(" AND o.tipo_operacion = ?");
        }
        if (fechaInicio != null && fechaFin != null) {
            comandoSQL.append(" AND o.fecha_operacion BETWEEN ? AND ?");
        }
        comandoSQL.append(" ORDER BY o.fecha_operacion DESC");

        try {
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(comandoSQL.toString());
            int paramIndex = 1;
            comando.setInt(paramIndex++, idCliente);

            if (tipoOperacion != null && !tipoOperacion.equals("Todas")) {
                comando.setString(paramIndex++, tipoOperacion);
            }
            if (fechaInicio != null && fechaFin != null) {
                comando.setTimestamp(paramIndex++, new java.sql.Timestamp(fechaInicio.getTime()));
                comando.setTimestamp(paramIndex++, new java.sql.Timestamp(fechaFin.getTime()));
            }

            ResultSet resultados = comando.executeQuery();

            while (resultados.next()) {
                Operacion entidadOperacion = new Operacion();

                entidadOperacion.setTipoMovimiento(resultados.getString("tipo_operacion"));
                entidadOperacion.setFolio(resultados.getString("folio_operacion"));
                entidadOperacion.setMonto(resultados.getDouble("monto"));

                // Conversión a greggorian calendar.
                java.sql.Timestamp fechaBD = resultados.getTimestamp("fecha_operacion");
                if (fechaBD != null) {
                    GregorianCalendar fechaConvertida = new GregorianCalendar();
                    fechaConvertida.setTimeInMillis(fechaBD.getTime());
                    entidadOperacion.setFechaHora(fechaConvertida);
                }

                historial.add(entidadOperacion);
            }

            // Se cierran conexiones
            comando.close();
            conexion.close();

        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al consultar el historial de operaciones: " + ex.getMessage(), ex);
        }

        return historial;
    }
}
