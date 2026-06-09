package org.example.DAO;
import org.example.beans.Incidente;
import org.example.beans.Soc;

import java.util.ArrayList;

public class IncidenteDAOImpl extends AbstractDAO<Incidente>{
    private static final String SQL_INSERT =
            "INSERT INTO INCIDENTES (CODIGO_INCIDENTE, TIPO_INCIDENTE, FECHA_DETECCION, ESTADO, AUTOR_EXAMEN, FK_SOC_ID) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE INCIDENTES SET CODIGO_INCIDENTE = ?, TIPO_INCIDENTE = ?, FECHA_DETECCION = ?, ESTADO = ?,  AUTOR_EXAMEN = ?, FK_SOC_ID = ? WHERE ID = ?";

    public IncidenteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    @Override
    public void add(Incidente incidente) {
        try{
            // 1º CONECTAR
            motorSQL.connect();

            // 2º PREPARAR
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, incidente.getCodigoIncidente());
            motorSQL.getPs().setString(2, incidente.getTipoIncidente());
            motorSQL.getPs().setString(3, incidente.getFechaDeteccion());
            motorSQL.getPs().setString(4, incidente.getEstado());
            motorSQL.getPs().setString(5, incidente.getAutorExamen());
            motorSQL.getPs().setInt(6, incidente.getSoc().getId());

            // 3º EJECUTAR UPDATE
            int rows = motorSQL.executeUpdate();
            System.out.println("INSERTADOS: " + rows);
        }catch (Exception e){
            System.out.println("Error: " + e);
        }finally {
            motorSQL.close();
        }
    }

    public static void main (String[] args){
        //INSTANCI INCIDENTE
        IncidenteDAOImpl incidenteDAOImpl = new IncidenteDAOImpl(MotorFactory.create(MotorFactory.POSTGRE));

        //TEST 1: AÑADIR
        Incidente incidente = new Incidente();
        incidente.setCodigoIncidente("");
        incidente.setTipoIncidente("");
        incidente.setFechaDeteccion("");
        incidente.setEstado("");
        incidente.setAutorExamen("DIEGO JUSTO");
        incidente.setSoc(new Soc(1));
        incidenteDAOImpl.add(incidente);

        //TEST 1: UPDATE
        Incidente incidente2 = new Incidente();
        incidente2.setCodigoIncidente("A");
        incidente2.setTipoIncidente("B");
        incidente2.setFechaDeteccion("C");
        incidente2.setEstado("D");
        incidente2.setAutorExamen("DIEGO JUSTO");
        incidente2.setSoc(new Soc(1));
        incidenteDAOImpl.update(7, incidente2);
    }

    @Override
    public void update(int id, Incidente incidente) {
            try{
                // 1º CONECTAR
                motorSQL.connect();

                // 2º PREPARAR
                motorSQL.prepare(SQL_UPDATE);
                motorSQL.getPs().setString(1, incidente.getCodigoIncidente());
                motorSQL.getPs().setString(2, incidente.getTipoIncidente());
                motorSQL.getPs().setString(3, incidente.getFechaDeteccion());
                motorSQL.getPs().setString(4, incidente.getEstado());
                motorSQL.getPs().setString(5, incidente.getAutorExamen());
                motorSQL.getPs().setInt(6, incidente.getSoc().getId());
                motorSQL.getPs().setInt(7, id);

                // 3º EJECUTAR UPDATE
                int rows = motorSQL.executeUpdate();
                System.out.println("ACTUALIZADOS: " + rows);
            }catch (Exception e){
                System.out.println("Error: " + e);
            }finally {
                motorSQL.close();
            }
    }


    @Override
    public void delete(int id) {

    }

    @Override
    public Incidente find(int id) {
        return null;
    }

    @Override
    public ArrayList<Incidente> findAll() {
        return null;
    }
}
