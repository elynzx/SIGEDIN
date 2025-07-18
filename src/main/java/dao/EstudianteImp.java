/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.common.collect.ImmutableList;
import configuration.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entidades.Apoderado;
import model.funcionalidad.catalogo.Diagnostico;
import model.entidades.Estudiante;
import model.funcionalidad.catalogo.NivelFuncional;
import org.jfree.data.category.DefaultCategoryDataset;

public class EstudianteImp implements EstudianteDao {

    private static EstudianteImp instanciaEstudiante;

    private Connection conn;

    public EstudianteImp() {
        conn = Conexion.estableceConexion();
    }

    public static EstudianteImp obtenerInstancia() {
        if (instanciaEstudiante == null) {
            instanciaEstudiante = new EstudianteImp();
        }
        return instanciaEstudiante;
    }

    @Override
    public Estudiante obtenerEstudiantePorId(int idEstudiante) {
        Estudiante estudiante = null;
        String sql = "SELECT e.id_estudiante, p.nombres, p.apellidos, p.fecha_nacimiento, "
                + "e.tipo_alergia, e.medicamentos, e.observaciones, "
                + "d.id_diagnostico, d.nombre AS diagnostico "
                + "FROM estudiante e "
                + "JOIN persona p ON e.id_persona = p.id_persona "
                + "LEFT JOIN estudiante_diagnostico ed ON e.id_estudiante = ed.id_estudiante "
                + "LEFT JOIN diagnostico d ON ed.id_diagnostico = d.id_diagnostico "
                + "WHERE e.id_estudiante = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEstudiante);
            ResultSet rs = pst.executeQuery();

            List<Diagnostico> diagnosticos = new ArrayList<>();

            while (rs.next()) {
                if (estudiante == null) {
                    estudiante = new Estudiante(
                            rs.getInt("id_estudiante"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getDate("fecha_nacimiento"),
                            rs.getString("tipo_alergia"),
                            rs.getString("medicamentos"),
                            rs.getString("observaciones"),
                            new ArrayList<>()
                    );
                }
                Diagnostico diagnostico = new Diagnostico(rs.getInt("id_diagnostico"), rs.getString("diagnostico"));
                diagnosticos.add(diagnostico);
            }

            if (estudiante != null) {
                estudiante.setDiagnosticos(diagnosticos);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener datos del estudiante: " + e.getMessage());
        }

        return estudiante;
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
    public DefaultCategoryDataset generarGraficoMatriculaPorDiagnostico() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sql = "SELECT d.nombre, COUNT(*) AS total FROM estudiante_diagnostico ed JOIN diagnostico d ON ed.id_diagnostico = d.id_diagnostico JOIN matricula m ON ed.id_estudiante = m.id_estudiante WHERE m.estado = 'activo' GROUP BY d.nombre";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // CORREGIR ESTA LÍNEA:
                dataset.addValue(rs.getInt("total"), rs.getString("nombre"), ""); // ← diagnóstico como serie
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    @Override
    public Estudiante obtenerEstudiantePorDNI(String dni) {
        String sql = "SELECT e.*, p.* FROM estudiante e "
                + "JOIN persona p ON e.id_persona = p.id_persona "
                + "WHERE p.dni = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Estudiante estudiante = new Estudiante();
                    estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                    estudiante.setId(rs.getInt("id_persona"));
                    estudiante.setNombres(rs.getString("nombres"));
                    estudiante.setApellidos(rs.getString("apellidos"));
                    estudiante.setDni(rs.getString("dni"));
                    estudiante.setCorreo(rs.getString("correo"));
                    estudiante.setDireccion(rs.getString("direccion"));
                    estudiante.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    estudiante.setGenero(rs.getString("genero"));
                    estudiante.setAlergias(rs.getBoolean("alergias"));
                    estudiante.setTipoAlergia(rs.getString("tipo_alergia"));
                    estudiante.setTomaMedicamentos(rs.getBoolean("toma_medicamentos"));
                    estudiante.setMedicamentos(rs.getString("medicamentos"));
                    estudiante.setObservaciones(rs.getString("observaciones"));

                    NivelFuncional nivel = new NivelFuncional();
                    nivel.setId(rs.getInt("id_nivel_funcional"));
                    estudiante.setNivelFuncional(nivel);

                    int idApoderado = rs.getInt("id_apoderado");

                    Apoderado apoderado = ApoderadoImp.obtenerInstancia().obtenerApoderadoPorId(idApoderado);
                    estudiante.setApoderado(apoderado);

                    estudiante.setDiagnosticos(obtenerDiagnosticosPorEstudiante(estudiante.getIdEstudiante()));

                    return estudiante;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Diagnostico> obtenerDiagnosticosPorEstudiante(int idEstudiante) {
        List<Diagnostico> lista = new ArrayList<>();
        String sql = "SELECT d.id_diagnostico, d.nombre FROM estudiante_diagnostico ed "
                + "JOIN diagnostico d ON ed.id_diagnostico = d.id_diagnostico "
                + "WHERE ed.id_estudiante = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEstudiante);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Diagnostico diag = new Diagnostico();
                    diag.setId(rs.getInt("id_diagnostico"));
                    diag.setNombre(rs.getString("nombre"));
                    lista.add(diag);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void registrarEstudiante(Estudiante estudiante) {
        String sqlPersona = "INSERT INTO persona (nombres, apellidos, dni, correo, direccion, fecha_nacimiento, genero) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlEstudiante = "INSERT INTO estudiante (id_persona, alergias, tipo_alergia, toma_medicamentos, medicamentos, observaciones, id_nivel_funcional, id_apoderado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, estudiante.getNombres());
                ps.setString(2, estudiante.getApellidos());
                ps.setString(3, estudiante.getDni());
                ps.setString(4, estudiante.getCorreo());
                ps.setString(5, estudiante.getDireccion());
                ps.setDate(6, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
                ps.setString(7, estudiante.getGenero());
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int idPersona = rs.getInt(1);

                    try (PreparedStatement ps2 = conn.prepareStatement(sqlEstudiante)) {
                        ps2.setInt(1, idPersona);
                        ps2.setBoolean(2, estudiante.isAlergias());
                        ps2.setString(3, estudiante.getTipoAlergia());
                        ps2.setBoolean(4, estudiante.isTomaMedicamentos());
                        ps2.setString(5, estudiante.getMedicamentos());
                        ps2.setString(6, estudiante.getObservaciones());
                        ps2.setInt(7, estudiante.getNivelFuncional().getId());
                        ps2.setInt(8, estudiante.getApoderado().getIdApoderado()); // ✅ usar el ID correcto
                        ps2.executeUpdate();
                    }
                }
            }

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actualizarEstudiante(Estudiante estudiante) {
        String sqlPersona = "UPDATE persona SET nombres = ?, apellidos = ?, dni = ?, correo = ?, direccion = ?, fecha_nacimiento = ?, genero = ? WHERE id_persona = ?";
        String sqlEstudiante = "UPDATE estudiante SET alergias = ?, tipo_alergia = ?, toma_medicamentos = ?, medicamentos = ?, observaciones = ?, id_nivel_funcional = ?, id_apoderado = ? WHERE id_estudiante = ?";

        try (PreparedStatement ps1 = conn.prepareStatement(sqlPersona); PreparedStatement ps2 = conn.prepareStatement(sqlEstudiante)) {

            ps1.setString(1, estudiante.getNombres());
            ps1.setString(2, estudiante.getApellidos());
            ps1.setString(3, estudiante.getDni());
            ps1.setString(4, estudiante.getCorreo());
            ps1.setString(5, estudiante.getDireccion());
            ps1.setDate(6, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            ps1.setString(7, estudiante.getGenero());
            ps1.setInt(8, estudiante.getId());
            ps1.executeUpdate();

            ps2.setBoolean(1, estudiante.isAlergias());
            ps2.setString(2, estudiante.getTipoAlergia());
            ps2.setBoolean(3, estudiante.isTomaMedicamentos());
            ps2.setString(4, estudiante.getMedicamentos());
            ps2.setString(5, estudiante.getObservaciones());
            ps2.setInt(6, estudiante.getNivelFuncional().getId());
            ps2.setInt(7, estudiante.getApoderado().getIdApoderado());
            ps2.setInt(8, estudiante.getIdEstudiante());
            ps2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int obtenerIdPorDNI(String dni) {
        String sql = "SELECT e.id_estudiante FROM estudiante e "
                + "JOIN persona p ON e.id_persona = p.id_persona "
                + "WHERE p.dni = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_estudiante");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
