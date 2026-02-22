/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.presentacion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.itson.banco.dtos.OperacionDTO;
import org.itson.banco.negocio.IOperacionBO;

/**
 * Clase ControladorHistorial.
 * Es la clase orquestadora del caso de uso individual "Consultar operaciones", se encarga de que
 * la presentación sea simplemente presentación.
 * @author Nahomi Figueroa
 */
public class ControladorHistorial {

    private final IOperacionBO operacionBO;
    private List<OperacionDTO> listaOperaciones;
    private final int idClienteLogueado;

    public ControladorHistorial(IOperacionBO operacionBO, int idClienteLogueado) {
        this.operacionBO = operacionBO;
        this.idClienteLogueado = idClienteLogueado;
    }

    public void buscarOperaciones(String tipo, Date inicio, Date fin) throws Exception {
        this.listaOperaciones = operacionBO.obtenerHistorialValidado(this.idClienteLogueado, tipo, inicio, fin);
    }

    public DefaultTableModel armarModeloTabla() {
        String[] columnas = {"Tipo de movimiento", "Folio", "Monto", "Fecha"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        if (this.listaOperaciones != null) {
            for (OperacionDTO op : this.listaOperaciones) {
                String fechaString = op.getFechaHora() != null ? formatoFecha.format(op.getFechaHora().getTime()) : "N/A";
                String montoString = op.getTipoMovimiento().equals("alta de cuenta") ? "N/A" : String.format("$%.2f", op.getMonto());
                Object[] fila = {
                    op.getTipoMovimiento().toUpperCase(), 
                    op.getFolio(),
                    montoString,
                    fechaString
                };
                modelo.addRow(fila);
            }
        }
        return modelo;
    }
}
