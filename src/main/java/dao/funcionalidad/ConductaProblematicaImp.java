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
import model.funcionalidad.ConductaProblematica;
import model.funcionalidad.catalogo.TipoConducta;

/**
 *
 * @author rpasc
 */
public class ConductaProblematicaImp implements ConductaProblematicaDao {

    private static ConductaProblematicaImp instancia;

    private Connection conn;

    public ConductaProblematicaImp() {
        conn = Conexion.estableceConexion();
    }

    public static ConductaProblematicaImp obtenerInstancia() {
        if (instancia == null) {
            instancia = new ConductaProblematicaImp();
        }
        return instancia;
    }

    // Guardar
    @Override
    public boolean guardarConductaProblematica(int idEstudiante, int idConducta, int idFuncionComportamiento, int gravedad, String descripcion) {

        String sql = "INSERT INTO conducta_problematica (id_estudiante, fecha, id_tipo_conducta, id_funcion_comportamiento, descripcion, gravedad) VALUES (?, NOW(), ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEstudiante);
            pst.setInt(2, idConducta);
            pst.setInt(3, idFuncionComportamiento);
            pst.setString(4, descripcion);
            pst.setInt(5, gravedad);

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar conducta problemática:  " + e.getMessage());
            return false;
        }

    }

    // Listar por estudiante
    @Override
    public List<ConductaProblematica> obtenerConductasPorEstudiante(int idEstudiante) {
        List<ConductaProblematica> listaConductas = new ArrayList<>();
        String sql = "SELECT c.id_conducta, c.fecha, t.nombre AS tipo_conducta, c.gravedad, c.descripcion "
                + "FROM conducta_problematica c "
                + "JOIN tipo_conducta t ON c.id_tipo_conducta = t.id_tipo_conducta "
                + "WHERE c.id_estudiante = ? "
                + "ORDER BY c.fecha DESC";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEstudiante);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                listaConductas.add(new ConductaProblematica(
                        rs.getInt("id_conducta"),
                        rs.getDate("fecha"),
                        new TipoConducta(rs.getString("tipo_conducta")),
                        rs.getString("descripcion"),
                        rs.getInt("gravedad")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener conductas del estudiante: " + e.getMessage());
        }

        return listaConductas;
    }

}
