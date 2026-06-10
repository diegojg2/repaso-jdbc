package org.example.DAO;

import org.example.beans.Pelicula;
import org.example.beans.Soc;

import java.util.ArrayList;

public abstract class AbstractDAO<T> implements DAO<T> {
    protected MotorSQL motorSQL;
    public AbstractDAO(MotorSQL motorSQL) {
        this.motorSQL = motorSQL;
    }

    /*
     * CONSULTAS AVANZADAS
     */

     public abstract ArrayList<T> findIncidenteBySoc(int id);

     /*
     public abstract ArrayList<T> findByDirector(String director);
     public abstract Pelicula findDetallePeliculaByPelicula(int idPelicula);
     */

}
