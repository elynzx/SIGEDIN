/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.funcionalidad;

import configuration.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.funcionalidad.PlanIntervencion;
import model.funcionalidad.ResumenIncidentes;
import model.funcionalidad.catalogo.EstrategiaIntervencion;
import model.funcionalidad.catalogo.FuncionComportamiento;
import model.funcionalidad.catalogo.TipoConducta;

public class PlanIndividualImp implements PlanIndividualDao {

    private static PlanIndividualImp instancia;
    private Connection conn;

    public PlanIndividualImp() {
        conn = Conexion.estableceConexion();
    }

    public static PlanIndividualImp obtenerInstancia() {
        if (instancia == null) {
            instancia = new PlanIndividualImp();
        }
        return instancia;
    }

    @Override
    public List<PlanIntervencion> obtenerHistorialPlanes(int idEstudiante) {
        List<PlanIntervencion> historial = new ArrayList<>();
        String sql = "SELECT p.fecha_inicio, t.nombre AS tipo_conducta, e.nombre AS estrategia "
                + "FROM plan_intervencion p "
                + "JOIN tipo_conducta t ON p.id_tipo_conducta = t.id_tipo_conducta "
                + "JOIN estrategia_intervencion e ON p.id_estrategia = e.id_estrategia "
                + "WHERE p.id_estudiante = ? "
                + "ORDER BY p.fecha_inicio DESC";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEstudiante);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                historial.add(new PlanIntervencion(
                        rs.getDate("fecha_inicio"),
                        new TipoConducta(rs.getString("tipo_conducta")),
                        new EstrategiaIntervencion(rs.getString("estrategia"))
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener historial de planes: " + e.getMessage());
        }

        return historial;
    }

    @Override
    public List<ResumenIncidentes> obtenerResumenIncidentes(int idEstudiante) {
        List<ResumenIncidentes> resumen = new ArrayList<>();
        String sql = "SELECT t.id_tipo_conducta, t.nombre AS tipo_conducta, COUNT(*) AS frecuencia, AVG(c.gravedad) AS gravedad_promedio, "
                + "(SELECT COALESCE(f.nombre, 'Sin función registrada') FROM conducta_problematica c2 "
                + "JOIN funcion_comportamiento f ON c2.id_funcion_comportamiento = f.id_funcion "
                + "WHERE c2.id_tipo_conducta = c.id_tipo_conducta AND c2.id_estudiante = c.id_estudiante "
                + "ORDER BY c2.fecha DESC LIMIT 1) AS ultima_funcion "
                + "FROM conducta_problematica c "
                + "JOIN tipo_conducta t ON c.id_tipo_conducta = t.id_tipo_conducta "
                + "WHERE c.id_estudiante = ? AND c.fecha >= DATE_SUB(CURDATE(), INTERVAL 14 DAY) "
                + "GROUP BY t.id_tipo_conducta";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEstudiante);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                resumen.add(new ResumenIncidentes(
                        new TipoConducta(rs.getInt("id_tipo_conducta"), rs.getString("tipo_conducta")),
                        rs.getInt("frecuencia"),
                        rs.getDouble("gravedad_promedio"),
                        new FuncionComportamiento(0, rs.getString("ultima_funcion"))
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener resumen de incidentes: " + e.getMessage());
        }

        return resumen;
    }

    @Override
    public List<TipoConducta> obtenerTipoConductasEstudiante(int idEstudiante) {
        List<TipoConducta> listaTipoConductas = new ArrayList<>();
        String sql = "SELECT DISTINCT t.id_tipo_conducta, t.nombre "
                + "FROM conducta_problematica c "
                + "JOIN tipo_conducta t ON c.id_tipo_conducta = t.id_tipo_conducta "
                + "WHERE c.id_estudiante = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEstudiante);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                listaTipoConductas.add(new TipoConducta(rs.getInt("id_tipo_conducta"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener tipos de conducta del estudiante: " + e.getMessage());
        }
        return listaTipoConductas;
    }

    @Override
    public FuncionComportamiento obtenerUltimaFuncionComportamiento(int idEstudiante, int idTipoConducta) {
        FuncionComportamiento funcion = new FuncionComportamiento(0, "Sin función registrada");
        String sql = "SELECT f.id_funcion, f.nombre "
                + "FROM conducta_problematica c "
                + "JOIN funcion_comportamiento f ON c.id_funcion_comportamiento = f.id_funcion "
                + "WHERE c.id_estudiante = ? AND c.id_tipo_conducta = ? "
                + "ORDER BY c.fecha DESC LIMIT 1";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEstudiante);
            pst.setInt(2, idTipoConducta);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                funcion = new FuncionComportamiento(rs.getInt("id_funcion"), rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la última función de comportamiento: " + e.getMessage());
        }

        return funcion;
    }

    @Override
    public boolean verificarPlanExistente(int idEstudiante, int idTipoConducta, int idEstrategia) {
        String sql = "SELECT COUNT(*) FROM plan_intervencion "
                + "WHERE id_estudiante = ? AND id_tipo_conducta = ? AND id_estrategia = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEstudiante);
            pst.setInt(2, idTipoConducta);
            pst.setInt(3, idEstrategia);
            ResultSet rs = pst.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar si el plan ya se implementó: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean registrarPlanIntervencion(int idEstudiante, int idTipoConducta, int idFuncionComportamiento,
            String objetivo, int idEstrategia, boolean aplicadoAntes, String observaciones) {
        String sql = "INSERT INTO plan_intervencion (id_estudiante, fecha_inicio, id_tipo_conducta, id_funcion_comportamiento, objetivo, id_estrategia, aplicado_antes, observaciones) "
                + "VALUES (?, NOW(), ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEstudiante);
            pst.setInt(2, idTipoConducta);
            pst.setInt(3, idFuncionComportamiento);
            pst.setString(4, objetivo);
            pst.setInt(5, idEstrategia);
            pst.setBoolean(6, aplicadoAntes);
            pst.setString(7, observaciones);

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar plan de intervención: " + e.getMessage());
            return false;
        }
    }
}
