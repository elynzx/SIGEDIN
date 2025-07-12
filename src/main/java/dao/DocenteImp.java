package dao;

import com.google.common.base.Preconditions;
import configuration.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.NivelFuncional;
import model.entidades.Aula;

public class DocenteImp implements DocenteDao {

    private static DocenteImp instanciaDocente;
    private Connection conn;

    public DocenteImp() {
        conn = Conexion.estableceConexion();
    }

    public static DocenteImp obtenerInstancia() {
        if (instanciaDocente == null) {
            instanciaDocente = new DocenteImp();
        }
        return instanciaDocente;
    }

    
    @Override
    public Aula obtenerDatosAula(int idDocente) {
        Preconditions.checkArgument(idDocente > 0, "El ID del docente debe ser mayor a 0");

        String sql = "SELECT a.id_aula, a.nombre, nf.id_nivel, nf.nombre AS nivel_funcional, "
                + "d.id_diagnostico, d.nombre AS diagnostico, a.vacantes_totales, a.vacantes_disponibles "
                + "FROM aula a "
                + "JOIN nivel_funcional nf ON a.id_nivel_funcional = nf.id_nivel "
                + "JOIN diagnostico d ON a.id_diagnostico_referente = d.id_diagnostico "
                + "WHERE a.id_docente = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idDocente);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                NivelFuncional nivel = new NivelFuncional(
                        rs.getInt("id_nivel"),
                        rs.getString("nivel_funcional"));
                Diagnostico diagnostico = new Diagnostico(
                        rs.getInt("id_diagnostico"),
                        rs.getString("diagnostico"));

                return new Aula(rs.getInt("id_aula"),
                        rs.getString("nombre"),
                        nivel, diagnostico,
                        rs.getInt("vacantes_totales"),
                        rs.getInt("vacantes_disponibles"),
                        null);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del aula: " + e.getMessage());
        }
        return null;
    }

    
    @Override
    public int obtenerIdDocenteporPersona(int idPersona) {
        String sql = "SELECT id_docente FROM docente WHERE id_persona = ?";
        int idDocente = -1;

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idPersona);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idDocente = rs.getInt("id_docente");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener idDocente: " + e.getMessage());
        }
        return idDocente;
    }

    
}
