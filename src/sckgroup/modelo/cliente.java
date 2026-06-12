package sckgroup.modelo;

/**
 * Clase que representa un cliente del sistema SCK-GROUP
 * @author Cindy Johana Ramirez
 * @version 1.0
 */
public class Cliente {

    private String numeroIdentificacion;
    private String nombreCompleto;
    private String telefono;
    private String ciudad;
    private double montoCredito;
    private int numeroDias;
    private String estadoCredito;
    private String nombreRuta;

    public Cliente() {
    }

    public Cliente(String numeroIdentificacion, String nombreCompleto, String telefono,
                    String ciudad, double montoCredito, int numeroDias,
                    String estadoCredito, String nombreRuta) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.montoCredito = montoCredito;
        this.numeroDias = numeroDias;
        this.estadoCredito = estadoCredito;
        this.nombreRuta = nombreRuta;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getMontoCredito() {
        return montoCredito;
    }

    public void setMontoCredito(double montoCredito) {
        this.montoCredito = montoCredito;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public String getEstadoCredito() {
        return estadoCredito;
    }

    public void setEstadoCredito(String estadoCredito) {
        this.estadoCredito = estadoCredito;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }
}
