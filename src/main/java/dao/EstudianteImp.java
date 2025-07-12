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
import java.util.ArrayList;
import java.util.List;
import model.funcionalidad.catalogo.Diagnostico;
import model.entidades.Estudiante;

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
    
}
