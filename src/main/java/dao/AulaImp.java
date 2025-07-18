/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import configuration.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.entidades.Aula;
import model.entidades.Docente;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.NivelFuncional;

/**
 *
 * @author rpasc
 */
public class AulaImp implements AulaDao {

    private static AulaImp instancia;
    private Connection conn;

    public AulaImp() {
        conn = Conexion.estableceConexion();
    }

    public static AulaImp obtenerInstancia() {
        if (instancia == null) {
            instancia = new AulaImp();
        }
        return instancia;
    }

    @Override
    public int contarVacantesDisponibles() {
        String sql = "SELECT SUM(vacantes_disponibles) FROM aula";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int contarAulasConVacantes() {
        String sql = "SELECT COUNT(*) FROM aula WHERE vacantes_disponibles > 0";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Aula> obtenerTodasLasAulas() {
        List<Aula> lista = new ArrayList<>();
        String sql = "SELECT a.*, "
                + "nf.nombre AS nivel_nombre, "
                + "d.nombre AS diagnostico_nombre, "
                + "p.nombres AS nombres_docente, "
                + "p.apellidos AS apellidos_docente "
                + "FROM aula a "
                + "JOIN nivel_funcional nf ON a.id_nivel_funcional = nf.id_nivel "
                + "JOIN diagnostico d ON a.id_diagnostico_referente = d.id_diagnostico "
                + "JOIN docente doc ON a.id_docente = doc.id_docente "
                + "JOIN persona p ON doc.id_persona = p.id_persona";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Aula aula = new Aula();
                aula.setId(rs.getInt("id_aula"));
                aula.setNombre(rs.getString("nombre"));
                aula.setVacantesTotales(rs.getInt("vacantes_totales"));
                aula.setVacantesDisponibles(rs.getInt("vacantes_disponibles"));

                NivelFuncional nivel = new NivelFuncional();
                nivel.setId(rs.getInt("id_nivel_funcional"));
                nivel.setNombre(rs.getString("nivel_nombre"));
                aula.setNivelFuncional(nivel);

                Diagnostico diagnostico = new Diagnostico();
                diagnostico.setId(rs.getInt("id_diagnostico_referente"));
                diagnostico.setNombre(rs.getString("diagnostico_nombre"));
                aula.setDiagnosticoReferente(diagnostico);

                Docente docente = new Docente();
                docente.setIdDocente(rs.getInt("id_docente"));
                docente.setNombres(rs.getString("nombres_docente"));
                docente.setApellidos(rs.getString("apellidos_docente"));
                aula.setDocente(docente);

                lista.add(aula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Aula> filtrarAulasPorNivelYDiagnostico(int idNivelFuncional, List<Integer> idsDiagnosticos) {

        List<Aula> aulas = new ArrayList<>();

        if (idsDiagnosticos.isEmpty()) {
            return aulas;
        }

        String sql = "SELECT a.*, p.nombres, p.apellidos "
                + "FROM aula a "
                + "JOIN docente d ON a.id_docente = d.id_docente "
                + "JOIN persona p ON d.id_persona = p.id_persona "
                + "WHERE a.id_nivel_funcional = ? "
                + "AND a.id_diagnostico_referente IN ("
                + idsDiagnosticos.stream().map(id -> "?").collect(Collectors.joining(", "))
                + ") "
                + "AND a.vacantes_disponibles > 0";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNivelFuncional);
            for (int i = 0; i < idsDiagnosticos.size(); i++) {
                ps.setInt(i + 2, idsDiagnosticos.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Docente docente = new Docente();
                    docente.setNombres(rs.getString("nombres"));
                    docente.setApellidos(rs.getString("apellidos"));

                    Aula aula = new Aula();
                    aula.setId(rs.getInt("id_aula"));
                    aula.setNombre(rs.getString("nombre"));
                    aula.setVacantesTotales(rs.getInt("vacantes_totales"));
                    aula.setVacantesDisponibles(rs.getInt("vacantes_disponibles"));
                    aula.setDocente(docente); // 💡 ahora sí tiene el nombre
                    aulas.add(aula);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aulas;

    }



    public List<Aula> obtenerAulasPorNivelSinFiltrar(int idNivelFuncional) {
        List<Aula> aulas = new ArrayList<>();

        String sql = "SELECT a.*, p.nombres, p.apellidos "
                + "FROM aula a "
                + "JOIN docente d ON a.id_docente = d.id_docente "
                + "JOIN persona p ON d.id_persona = p.id_persona "
                + "WHERE a.id_nivel_funcional = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNivelFuncional);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Docente docente = new Docente();
                docente.setNombres(rs.getString("nombres"));
                docente.setApellidos(rs.getString("apellidos"));

                Aula aula = new Aula();
                aula.setId(rs.getInt("id_aula"));
                aula.setNombre(rs.getString("nombre"));
                aula.setVacantesTotales(rs.getInt("vacantes_totales"));
                aula.setVacantesDisponibles(rs.getInt("vacantes_disponibles"));
                aula.setDocente(docente);

                aulas.add(aula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aulas;
    }
}
