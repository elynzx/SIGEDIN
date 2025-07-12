/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.funcionalidad;

import configuration.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.entidades.Estudiante;
import model.funcionalidad.Asistencia;

public class AsistenciaImp implements AsistenciaDao {

    private static AsistenciaImp instancia;
    private Connection conn;

    public AsistenciaImp() {
        conn = Conexion.estableceConexion();
    }

    public static AsistenciaImp obtenerInstancia() {
        if (instancia == null) {
            instancia = new AsistenciaImp();
        }
        return instancia;
    }

    @Override
    public List<Asistencia> obtenerAsistenciasDelDia(int idDocente, Date fecha) {
        List<Asistencia> lista = new ArrayList<>();
        String sql = "SELECT asi.id_asistencia, asi.asistio, asi.fecha, "
                + "e.id_estudiante, p.nombres, p.apellidos "
                + "FROM asistencia asi "
                + "JOIN estudiante e ON asi.id_estudiante = e.id_estudiante "
                + "JOIN persona p ON e.id_persona = p.id_persona "
                + "JOIN matricula m ON m.id_estudiante = e.id_estudiante "
                + "JOIN aula a ON a.id_aula = m.id_aula "
                + "WHERE asi.fecha = ? AND a.id_docente = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, fecha);
            ps.setInt(2, idDocente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidos(rs.getString("apellidos"));

                Asistencia asistencia = new Asistencia(
                        rs.getInt("id_asistencia"),
                        estudiante,
                        rs.getDate("fecha"),
                        rs.getBoolean("asistio")
                );

                lista.add(asistencia);
            }
        } catch (SQLException e) {
        }
        return lista;
    }

    @Override
    public List<Asistencia> obtenerEstudiantesSinAsistencia(int idDocente) {
        List<Asistencia> lista = new ArrayList<>();
        String sql = "SELECT e.id_estudiante, p.nombres, p.apellidos "
                + "FROM estudiante e "
                + "JOIN persona p ON e.id_persona = p.id_persona "
                + "JOIN matricula m ON m.id_estudiante = e.id_estudiante "
                + "JOIN aula a ON a.id_aula = m.id_aula "
                + "WHERE a.id_docente = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idDocente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidos(rs.getString("apellidos"));

                Asistencia asistencia = new Asistencia(
                        0,
                        estudiante,
                        new Date(System.currentTimeMillis()),
                        false
                );

                lista.add(asistencia);
            }
        } catch (SQLException e) {
        }
        return lista;
    }

    @Override
    public void registrarAsistencias(List<Asistencia> lista) {
        String sql = "INSERT INTO asistencia (id_estudiante, fecha, asistio) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Asistencia a : lista) {
                ps.setInt(1, a.getEstudiante().getIdEstudiante());
                ps.setDate(2, a.getFecha());
                ps.setBoolean(3, a.isAsiste());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
        }
    }

    @Override
    public void modificarAsistencias(List<Asistencia> lista) {
        String sql = "UPDATE asistencia SET asistio = ? WHERE id_estudiante = ? AND fecha = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Asistencia a : lista) {
                ps.setBoolean(1, a.isAsiste());
                ps.setInt(2, a.getEstudiante().getIdEstudiante());
                ps.setDate(3, a.getFecha());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
        }
    }

}
