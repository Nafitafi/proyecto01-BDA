
package org.itson.banco.entidades;

import java.util.GregorianCalendar;

/**
 *
 * @author oliro
 */
public class Retiro {

    private int folioRetiro;
    private double monto;
    private String cuentaOrigen;
    private String contasena;
    private String estado;
    private GregorianCalendar fecha_retiro;
    
    
    public Retiro(){
        
    }

    public Retiro(int folioRetiro, double mondo, String cuentaOrigen, String contasena, String estado, GregorianCalendar fecha_retiro) {
        this.folioRetiro = folioRetiro;
        this.monto = mondo;
        this.cuentaOrigen = cuentaOrigen;
        this.contasena = contasena;
        this.estado = estado;
        this.fecha_retiro = fecha_retiro;
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

    public void setMonto(double monto) {
        this.monto = monto;
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

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public GregorianCalendar getFecha_retiro() {
        return fecha_retiro;
    }

    public void setFecha_retiro(GregorianCalendar fecha_retiro) {
        this.fecha_retiro = fecha_retiro;
    }
    
}
