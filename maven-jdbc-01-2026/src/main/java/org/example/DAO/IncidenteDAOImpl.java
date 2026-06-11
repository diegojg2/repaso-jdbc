package org.example.DAO;
import org.example.beans.Incidente;
import org.example.beans.Soc;

import java.sql.ResultSet;
import java.util.ArrayList;

public class IncidenteDAOImpl extends AbstractDAO<Incidente>{
    private static final String SQL_INSERT =
            "INSERT INTO INCIDENTES (CODIGO_INCIDENTE, TIPO_INCIDENTE, FECHA_DETECCION, ESTADO, AUTOR_EXAMEN, FK_SOC_ID) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE INCIDENTES SET CODIGO_INCIDENTE = ?, TIPO_INCIDENTE = ?, FECHA_DETECCION = ?, ESTADO = ?,  AUTOR_EXAMEN = ?, FK_SOC_ID = ? WHERE ID = ?";
    private static final String SQL_DELETE =
            "DELETE FROM INCIDENTES WHERE ID = ?";
    private static final String SQL_FIND =
            "SELECT * FROM INCIDENTES WHERE ID = ?";
    private static final String SQL_FINDBYSOC =
            "SELECT I.ID as ID_INCIDENTE, I.CODIGO_INCIDENTE, I.TIPO_INCIDENTE, I.FECHA_DETECCION, I.ESTADO, I.AUTOR_EXAMEN, " +
                    "S.ID as ID_SOC, S.NOMBRE, S.PAIS, S.NIVEL_SEGURIDAD " +
                    "FROM INCIDENTES I INNER JOIN SOCS S " +
                    "ON I.FK_SOC_ID = S.ID " +
                    "WHERE I.FK_SOC_ID = ?";

    public IncidenteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    public static void main (String[] args) {
        //INSTANCIA INCIDENTE
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

        //TEST 2: UPDATE
        Incidente incidente2 = new Incidente();
        incidente2.setCodigoIncidente("A");
        incidente2.setTipoIncidente("B");
        incidente2.setFechaDeteccion("C");
        incidente2.setEstado("D");
        incidente2.setAutorExamen("DIEGO JUSTO");
        incidente2.setSoc(new Soc(1));
        incidenteDAOImpl.update(7, incidente2);

        //TEST 3: DELETE
        incidenteDAOImpl.delete(1);

        //TEST 4: findIncidenteBySoc
        incidenteDAOImpl.findIncidenteBySoc(1);
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
        try{
            // 1º CONECTAR
            motorSQL.connect();

            // 2º PREPARAR
            motorSQL.prepare(SQL_DELETE);
            motorSQL.getPs().setInt(1, id);

            // 3º EJECUTAR UPDATE
            int rows = motorSQL.executeUpdate();
            System.out.println("ELIMINADOS: " + rows);
        }catch (Exception e){
            System.out.println("Error: " + e);
        }finally {
            motorSQL.close();
        }
    }

    @Override
    public Incidente find(int id) {
        Incidente incidente = null;
       try {
           motorSQL.connect();

           motorSQL.prepare(SQL_FIND);
           motorSQL.getPs().setInt(1, id);
           ResultSet rs =
                   motorSQL.executeQuery();
           if(rs.next()){

               incidente =
                       mapIncidente(rs);
           }

       }catch (Exception e){
           System.out.println("Error: " + e);
       }finally {
         motorSQL.close();
       }
       return incidente;
    }

    @Override
    public ArrayList<Incidente> findIncidenteBySoc(int id) {
        ArrayList<Incidente> incidentes = new ArrayList<>();
        try{
            // 1º CONECTAR
            motorSQL.connect();

            // 2º PREPARAR
            motorSQL.prepare(SQL_FINDBYSOC);

            motorSQL.getPs().setInt(1, id);


            // 3º EJECUTAR UPDATE
            ResultSet rs = motorSQL.executeQuery();

            while(rs.next()){
                    Incidente incidente = new Incidente();
                    incidente.setId(rs.getInt("ID_INCIDENTE"));
                    incidente.setCodigoIncidente(rs.getString("CODIGO_INCIDENTE"));
                    incidente.setTipoIncidente(rs.getString("TIPO_INCIDENTE"));
                    incidente.setFechaDeteccion(rs.getString("FECHA_DETECCION"));
                    incidente.setEstado(rs.getString("ESTADO"));
                    incidente.setAutorExamen(rs.getString("AUTOR_EXAMEN"));

                    Soc soc = new Soc();
                    soc.setId(rs.getInt("ID_SOC"));
                    soc.setNombre(rs.getString("NOMBRE"));
                    soc.setPais(rs.getString("PAIS"));
                    soc.setNivelSeguridad(rs.getInt("NIVEL_SEGURIDAD"));

                    incidente.setSoc(soc);

                    incidentes.add(incidente);
            }

        }catch (Exception e){
            System.out.println("Error: " + e);
        }finally {
            motorSQL.close();
        }

        if (incidentes.isEmpty()) {
            System.out.println("No se encontraron incidentes para el SOC con ID " + id);
        } else {
            for (Incidente i : incidentes) {
                System.out.println(i);
            }
        }

        return incidentes;
    }


    @Override
    public ArrayList<Incidente> findAll() {
        return null;
    }

    private Incidente mapIncidente(ResultSet rs) throws Exception {
        Incidente incidente = new Incidente();
        incidente.setId(rs.getInt("ID"));
        incidente.setCodigoIncidente(rs.getString("CODIGO_INCIDENTE"));
        incidente.setTipoIncidente(rs.getString("TIPO_INCIDENTE"));
        incidente.setFechaDeteccion(rs.getString("FECHA_DETECCION"));
        incidente.setEstado(rs.getString("ESTADO"));
        incidente.setAutorExamen(rs.getString("AUTOR_EXAMEN"));

        return incidente;
    }
}
