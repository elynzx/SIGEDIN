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
import java.util.List;
import model.entidades.Apoderado;
import model.entidades.Aula;
import model.entidades.Docente;
import model.entidades.Estudiante;
import model.funcionalidad.Matricula;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.NivelFuncional;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author rpasc
 */
public class MatriculaImp implements MatriculaDao {

    private static MatriculaImp instancia;
    private Connection conn;

    public MatriculaImp() {
        conn = Conexion.estableceConexion();
    }

    public static MatriculaImp obtenerInstancia() {
        if (instancia == null) {
            instancia = new MatriculaImp();
        }
        return instancia;
    }

    @Override
    public int contarMatriculasActivas() {
        String sql = "SELECT COUNT(*) FROM matricula WHERE estado = 'activo'";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int contarMatriculasPorFechaActual() {
        String sql = "SELECT COUNT(*) FROM matricula WHERE fecha_matricula = CURDATE() AND estado = 'activo'";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public DefaultCategoryDataset generarGraficoMatriculaPorAula() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sql = "SELECT a.nombre, COUNT(*) AS total FROM matricula m JOIN aula a ON m.id_aula = a.id_aula WHERE m.estado = 'activo' GROUP BY a.nombre";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // CORREGIR ESTA LÍNEA:
                dataset.addValue(rs.getInt("total"), rs.getString("nombre"), ""); // ← aula como serie
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    @Override
    public void registrarMatricula(Matricula matricula) {
        String sql = "INSERT INTO matricula (id_estudiante, id_aula, fecha_matricula, estado) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, matricula.getEstudiante().getIdEstudiante());
            ps.setInt(2, matricula.getAula().getId());
            ps.setDate(3, new java.sql.Date(matricula.getFechaMatricula().getTime()));
            ps.setString(4, matricula.getEstado());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cambiarEstadoMatricula(int idMatricula, String nuevoEstado) {
        String sql = "UPDATE matricula SET estado_actual = ? WHERE id_matricula = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idMatricula);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Matricula obtenerMatriculaPorEstudiante(int idEstudiante) {
        String sql = "SELECT m.*, a.*, nf.*, d.*, p.nombres AS docente_nombres, p.apellidos AS docente_apellidos "
                + "FROM matricula m "
                + "JOIN aula a ON m.id_aula = a.id_aula "
                + "JOIN nivel_funcional nf ON a.id_nivel_funcional = nf.id_nivel "
                + "JOIN diagnostico d ON a.id_diagnostico_referente = d.id_diagnostico "
                + "JOIN docente doc ON a.id_docente = doc.id_docente "
                + "JOIN persona p ON doc.id_persona = p.id_persona "
                + "WHERE m.id_estudiante = ? "
                + "ORDER BY m.id_matricula DESC LIMIT 1"; // ✅ solo la más reciente

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEstudiante);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Matricula matricula = new Matricula();
                    matricula.setId(rs.getInt("id_matricula"));
                    matricula.setFechaMatricula(rs.getDate("fecha_matricula"));
                    matricula.setEstado(rs.getString("estado"));

                    Aula aula = new Aula();
                    aula.setId(rs.getInt("id_aula"));
                    aula.setNombre(rs.getString("nombre"));
                    aula.setVacantesTotales(rs.getInt("vacantes_totales"));
                    aula.setVacantesDisponibles(rs.getInt("vacantes_disponibles"));

                    NivelFuncional nivel = new NivelFuncional();
                    nivel.setId(rs.getInt("id_nivel_funcional"));
                    nivel.setNombre(rs.getString("nf.nombre"));
                    aula.setNivelFuncional(nivel);

                    Diagnostico diagnostico = new Diagnostico();
                    diagnostico.setId(rs.getInt("id_diagnostico"));
                    diagnostico.setNombre(rs.getString("d.nombre"));
                    aula.setDiagnosticoReferente(diagnostico);

                    Docente docente = new Docente();
                    docente.setNombres(rs.getString("docente_nombres"));
                    docente.setApellidos(rs.getString("docente_apellidos"));
                    aula.setDocente(docente);

                    matricula.setAula(aula);
                    return matricula;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizarMatricula(Matricula matricula) {
        String sql = "UPDATE matricula SET id_aula = ?, fecha_matricula = ?, estado = ? WHERE id_matricula = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, matricula.getAula().getId());
            ps.setDate(2, new java.sql.Date(matricula.getFechaMatricula().getTime()));
            ps.setString(3, matricula.getEstado());
            ps.setInt(4, matricula.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
