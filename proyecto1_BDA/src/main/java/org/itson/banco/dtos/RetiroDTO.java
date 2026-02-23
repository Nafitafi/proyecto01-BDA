
package org.itson.banco.dtos;

import java.util.GregorianCalendar;

/**
 *
 * @author oliro
 */
public class RetiroDTO {


    private int folioRetiro;
    private double monto;
    private String cuentaOrigen;
    private String contasena;
    private String estado;
    private GregorianCalendar fecha_retiro;

    public RetiroDTO(int folioRetiro, double monto, String cuentaOrigen, String contasena, String estado, GregorianCalendar fecha_retiro) {
        this.folioRetiro = folioRetiro;
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.contasena = contasena;
        this.estado = estado;
        this.fecha_retiro = fecha_retiro;
    }

    public RetiroDTO(double monto, String cuentaOrigen, String contasena) {
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.contasena = contasena;
    }

    public RetiroDTO(int folioRetiro, double monto) {
        this.folioRetiro = folioRetiro;
        this.monto = monto;
    }
    
    
    
    public int getFolioRetiro() {
        return folioRetiro;
    }

    public void setFolioRetiro(int folioRetiro) {
        this.folioRetiro = folioRetiro;
    }

    public double getMonto() {
        return monto;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public String getContasena() {
        return contasena;
    }

    public void setContasena(String contasena) {
        this.contasena = contasena;
    }

    public String getEstado() {
        return estado;
    }

    public GregorianCalendar getFecha_retiro() {
        return fecha_retiro;
    }

    
}
