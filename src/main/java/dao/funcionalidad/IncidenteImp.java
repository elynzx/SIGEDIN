/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.funcionalidad;

import configuration.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IncidenteImp implements IncidenteDao {

    private static IncidenteImp instanciaIncidente;
    private Connection conn;

    public IncidenteImp() {
        conn = Conexion.estableceConexion();
    }

    public static IncidenteImp obtenerInstancia() {
        if (instanciaIncidente == null) {
            instanciaIncidente = new IncidenteImp();
        }
        return instanciaIncidente;
    }

    @Override
    public Map<String, Object> obtenerResumenIncidentes(int idEstudiante) {
        Map<String, Object> resumen = new HashMap<>();

        String sqlConducta = "SELECT COUNT(*) AS total, AVG(gravedad) AS promedio FROM conducta_problematica WHERE id_estudiante = ?";
        String sqlFichaAbc = "SELECT COUNT(*) AS total, AVG(gravedad) AS promedio FROM ficha_abc WHERE id_estudiante = ?";

        try (PreparedStatement pst1 = conn.prepareStatement(sqlConducta); PreparedStatement pst2 = conn.prepareStatement(sqlFichaAbc)) {

            pst1.setInt(1, idEstudiante);
            ResultSet rs1 = pst1.executeQuery();
            if (rs1.next()) {
                resumen.put("conducta_total", rs1.getInt("total"));
                resumen.put("conducta_prom", rs1.getDouble("promedio"));
            }

            pst2.setInt(1, idEstudiante);
            ResultSet rs2 = pst2.executeQuery();
            if (rs2.next()) {
                resumen.put("abc_total", rs2.getInt("total"));
                resumen.put("abc_prom", rs2.getDouble("promedio"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener resumen de incidentes: " + e.getMessage());
        }

        return resumen;
    }

    @Override
    public List<String> obtenerActividadIncidentesRegistrados(int idDocente) {

        List<String> historialTopIncidentes = new ArrayList<>();

        String sql
                = "SELECT * FROM ( "
                + "    (SELECT s.fecha, p.nombres, p.apellidos, s.descripcion_conducta AS descripcion, 'Seguimiento' AS tipo "
                + "     FROM seguimiento_conductual s "
                + "     JOIN estudiante e ON s.id_estudiante = e.id_estudiante "
                + "     JOIN persona p ON e.id_persona = p.id_persona "
                + "     JOIN matricula m ON e.id_estudiante = m.id_estudiante "
                + "     JOIN aula a ON m.id_aula = a.id_aula "
                + "     WHERE a.id_docente = ?) "
                + " "
                + "    UNION ALL "
                + " "
                + "    (SELECT c.fecha, p.nombres, p.apellidos, c.descripcion, 'Conducta Problemática' AS tipo "
                + "     FROM conducta_problematica c "
                + "     JOIN estudiante e ON c.id_estudiante = e.id_estudiante "
                + "     JOIN persona p ON e.id_persona = p.id_persona "
                + "     JOIN matricula m ON e.id_estudiante = m.id_estudiante "
                + "     JOIN aula a ON m.id_aula = a.id_aula "
                + "     WHERE a.id_docente = ?) "
                + ") AS historial "
                + "ORDER BY fecha DESC "
                + "LIMIT 10";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idDocente);
            pst.setInt(2, idDocente);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                historialTopIncidentes.add(rs.getString("fecha") + " - "
                        + rs.getString("nombres") + " "
                        + rs.getString("apellidos") + ": "
                        + rs.getString("descripcion") + " ("
                        + rs.getString("tipo") + ")");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener historial de seguimiento: " + e.getMessage());
        }

        return historialTopIncidentes;

    }

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

    @Override
    public Map<Integer, Integer> obtenerTopEstudiantesConConductasProblematicas(int idDocente) {
        Map<Integer, Integer> mapa = new HashMap<>();
        String sql = "SELECT cp.id_estudiante, COUNT(*) AS total "
                + "FROM conducta_problematica cp "
                + "JOIN matricula m ON cp.id_estudiante = m.id_estudiante "
                + "JOIN aula a ON m.id_aula = a.id_aula "
                + "WHERE a.id_docente = ? "
                + "GROUP BY cp.id_estudiante";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idDocente);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                mapa.put(rs.getInt("id_estudiante"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener conductas problemáticas: " + e.getMessage());
        }

        return mapa;
    }

    @Override
    public Map<Integer, Integer> obtenerTopEstudiantesConFichasABC(int idDocente) {
        Map<Integer, Integer> mapa = new HashMap<>();
        String sql = "SELECT fa.id_estudiante, COUNT(*) AS total "
                + "FROM ficha_abc fa "
                + "JOIN matricula m ON fa.id_estudiante = m.id_estudiante "
                + "JOIN aula a ON m.id_aula = a.id_aula "
                + "WHERE a.id_docente = ? "
                + "GROUP BY fa.id_estudiante";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idDocente);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                mapa.put(rs.getInt("id_estudiante"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener fichas ABC: " + e.getMessage());
        }

        return mapa;
    }

    @Override
    public Map<String, Integer> obtenerFichasABCporAntecedente(int idEstudiante) {
        Map<String, Integer> mapa = new HashMap<>();
        String sql = "SELECT an.descripcion AS antecedente, COUNT(*) AS cantidad "
                + "FROM ficha_abc fa "
                + "JOIN antecedente an ON fa.id_antecedente = an.id_antecedente "
                + "WHERE fa.id_estudiante = ? "
                + "GROUP BY an.descripcion";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEstudiante);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                mapa.put(rs.getString("antecedente"), rs.getInt("cantidad"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener fichas ABC por antecedente: " + e.getMessage());
        }
        return mapa;
    }

    @Override
    public Map<String, Integer> obtenerConductasProblematicasPorMes(int idEstudiante) {
        Map<String, Integer> mapa = new LinkedHashMap<>();
        String sql = "SELECT DATE_FORMAT(fecha, '%M') AS mes, COUNT(*) AS cantidad "
                + "FROM conducta_problematica "
                + "WHERE id_estudiante = ? "
                + "GROUP BY mes "
                + "ORDER BY MONTH(STR_TO_DATE(mes, '%M'))";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEstudiante);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                mapa.put(rs.getString("mes"), rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener conductas problemáticas por mes: " + e.getMessage());
        }

        return mapa;
    }

    @Override
    public Map<String, Integer> obtenerConductasPorTipo(int idEstudiante) {
        Map<String, Integer> mapa = new HashMap<>();
        String sql = "SELECT tc.nombre AS tipo, COUNT(*) AS cantidad "
                + "FROM conducta_problematica cp "
                + "JOIN tipo_conducta tc ON cp.id_tipo_conducta = tc.id_tipo_conducta "
                + "WHERE cp.id_estudiante = ? "
                + "GROUP BY tc.nombre";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEstudiante);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                mapa.put(rs.getString("tipo"), rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener conductas por tipo: " + e.getMessage());
        }

        return mapa;
    }

}
