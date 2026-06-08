package org.example.beans;

public class Incidente {
    private int id;
    private String codigoIncidente;
    private String tipoIncidente;
    private String fechaDeteccion;
    private String estado;
    private String autorExamen;
    private Soc soc;

    public Incidente() {
    }
    public Incidente(int id, String codigoIncidente, String tipoIncidente, String fechaDeteccion, String estado, String autorExamen, Soc soc) {
        this.id = id;
        this.codigoIncidente = codigoIncidente;
        this.tipoIncidente = tipoIncidente;
        this.fechaDeteccion = fechaDeteccion;
        this.estado = estado;
        this.autorExamen = autorExamen;
        this.soc = soc;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCodigoIncidente() {
        return codigoIncidente;
    }
    public void setCodigoIncidente(String codigoIncidente) {
        this.codigoIncidente = codigoIncidente;
    }
    public String getTipoIncidente() {
        return tipoIncidente;
    }
    public void setTipoIncidente(String tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }
    public String getFechaDeteccion() {
        return fechaDeteccion;
    }
    public void setFechaDeteccion(String fechaDeteccion) {
        this.fechaDeteccion = fechaDeteccion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getAutorExamen() {
        return autorExamen;
    }
    public void setAutorExamen(String autorExamen) {
        this.autorExamen = autorExamen;
    }
    public Soc getSoc() {
        return soc;
    }
    public void setSoc(Soc soc) {
        this.soc = soc;
    }

    @Override
    public String toString() {
        return "Incidentes{" +
                "id=" + id +
                ", codigoIncidente='" + codigoIncidente + '\'' +
                ", tipoIncidente='" + tipoIncidente + '\'' +
                ", fechaDeteccion='" + fechaDeteccion + '\'' +
                ", estado='" + estado + '\'' +
                ", autorExamen='" + autorExamen + '\'' +
                ", soc=" + soc +
                '}';
    }
}
