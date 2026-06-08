package org.example.beans;

public class InformeIncidente {
    private int id;
    private String malwareDetectado;
    private int nivelSeveridad;
    private String conclusion;
    private String autorExamen;
    private Incidente incidente;

    public InformeIncidente() {
    }
    public InformeIncidente(int id, String malwareDetectado, int nivelSeveridad, String conclusion, String autorExamen, Incidente incidente) {
        this.id = id;
        this.malwareDetectado = malwareDetectado;
        this.nivelSeveridad = nivelSeveridad;
        this.conclusion = conclusion;
        this.autorExamen = autorExamen;
        this.incidente = incidente;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMalwareDetectado() {
        return malwareDetectado;
    }
    public void setMalwareDetectado(String malwareDetectado) {
        this.malwareDetectado = malwareDetectado;
    }
    public int getNivelSeveridad() {
        return nivelSeveridad;
    }
    public void setNivelSeveridad(int nivelSeveridad) {
        this.nivelSeveridad = nivelSeveridad;
    }
    public String getConclusion() {
        return conclusion;
    }
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
    public String getAutorExamen() {
        return autorExamen;
    }
    public void setAutorExamen(String autorExamen) {
        this.autorExamen = autorExamen;
    }
    public Incidente getIncidente() {
        return incidente;
    }
    public void setIncidente(Incidente incidente) {
        this.incidente = incidente;
    }

    @Override
    public String toString() {
        return "InformeIncidente{" +
                "id=" + id +
                ", malwareDetectado='" + malwareDetectado + '\'' +
                ", nivelSeveridad=" + nivelSeveridad +
                ", conclusion='" + conclusion + '\'' +
                ", autorExamen='" + autorExamen + '\'' +
                ", incidente=" + incidente +
                '}';
    }
}
