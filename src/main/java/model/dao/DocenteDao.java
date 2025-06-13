package model.dao;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import configuration.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.NivelFuncional;
import model.entidades.Aula;
import model.entidades.Estudiante;

public class DocenteDao implements IDocenteDao {

    private static DocenteDao instanciaDocente;
    private Connection conn;

    public DocenteDao() {
        conn = Conexion.estableceConexion();
    }

    public static DocenteDao obtenerInstancia() {
        if (instanciaDocente == null) {
            instanciaDocente = new DocenteDao();
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

    @Override
    public List<Estudiante> obtenerListaEstudiantes(int idDocente) {
        List<Estudiante> listaEstudiantes = new ArrayList<>();
        String sql = "SELECT e.id_estudiante, p.nombres, p.apellidos "
                + "FROM estudiante e "
                + "JOIN persona p ON e.id_persona = p.id_persona "
                + "JOIN matricula m ON e.id_estudiante = m.id_estudiante "
                + "JOIN aula a ON m.id_aula = a.id_aula "
                + "WHERE a.id_docente = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idDocente);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                listaEstudiantes.add(new Estudiante(
                        rs.getInt("id_estudiante"), 
                        rs.getString("nombres"), 
                        rs.getString("apellidos")));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de estudiantes: " + e.getMessage());
        }

        return ImmutableList.copyOf(listaEstudiantes);
    }

    @Override
    public List<String> obtenerHistorialIncidentes(int idDocente) {

        List<String> historialTopIncidentes = new ArrayList<>();

        String sql = "(SELECT s.fecha, p.nombres, p.apellidos, s.descripcion_conducta, 'Seguimiento' AS tipo "
                + "FROM seguimiento_conductual s "
                + "JOIN estudiante e ON s.id_estudiante = e.id_estudiante "
                + "JOIN persona p ON e.id_persona = p.id_persona "
                + "JOIN matricula m ON e.id_estudiante = m.id_estudiante "
                + "JOIN aula a ON m.id_aula = a.id_aula "
                + "WHERE a.id_docente = ? "
                + "ORDER BY s.fecha DESC LIMIT 5) "
                + "UNION "
                + "(SELECT c.fecha, p.nombres, p.apellidos, c.descripcion, 'Conducta Problemática' AS tipo "
                + "FROM conducta_problematica c "
                + "JOIN estudiante e ON c.id_estudiante = e.id_estudiante "
                + "JOIN persona p ON e.id_persona = p.id_persona "
                + "JOIN matricula m ON e.id_estudiante = m.id_estudiante "
                + "JOIN aula a ON m.id_aula = a.id_aula "
                + "WHERE a.id_docente = ? "
                + "ORDER BY c.fecha DESC LIMIT 5)";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idDocente);
            pst.setInt(2, idDocente);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                historialTopIncidentes.add(rs.getString("fecha") + " - "
                        + rs.getString("nombres") + " "
                        + rs.getString("apellidos") + ": "
                        + rs.getString("descripcion_conducta") + " ("
                        + rs.getString("tipo") + ")");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener historial de seguimiento: " + e.getMessage());
        }

        return historialTopIncidentes;

    }

    @Override
    public List<String> obtenerTopEstudiantesIncidentes(int idDocente) {

        List<String> estudiantes = new ArrayList<>();
        String sql = "SELECT p.nombres, p.apellidos, COUNT(c.id_conducta) AS cantidad "
                + "FROM conducta_problematica c "
                + "JOIN estudiante e ON c.id_estudiante = e.id_estudiante "
                + "JOIN persona p ON e.id_persona = p.id_persona "
                + "JOIN matricula m ON e.id_estudiante = m.id_estudiante "
                + "JOIN aula a ON m.id_aula = a.id_aula "
                + "WHERE a.id_docente = ? AND c.fecha >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH) "
                + "GROUP BY e.id_estudiante, p.nombres, p.apellidos "
                + "ORDER BY cantidad DESC LIMIT 5";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idDocente);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                estudiantes.add(rs.getString("nombres") + " "
                        + rs.getString("apellidos") + " - "
                        + rs.getInt("cantidad") + " incidentes");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener estudiantes con más incidentes: " + e.getMessage());
        }

        return estudiantes;
    }
}
