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
import model.funcionalidad.FichaAbc;
import model.funcionalidad.catalogo.Antecedente;

public class FichaAbcImp implements FichaAbcDao {

    private static FichaAbcImp instancia;
    private Connection conn;

    public FichaAbcImp() {
        conn = Conexion.estableceConexion();
    }

    public static FichaAbcImp obtenerInstancia() {
        if (instancia == null) {
            instancia = new FichaAbcImp();
        }
        return instancia;
    }

    //Guardar
    @Override
    public boolean guardarFichaAbc(int idEstudiante, int idAntecedente, String comportamiento, String consecuencia, int gravedad) {

        String sql = "INSERT INTO ficha_abc (id_estudiante, fecha, id_antecedente, comportamiento, consecuencia, gravedad) VALUES (?, NOW(), ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEstudiante);
            pst.setInt(2, idAntecedente);
            pst.setString(3, comportamiento);
            pst.setString(4, consecuencia);
            pst.setInt(5, gravedad);

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar Ficha ABC:  " + e.getMessage());
            return false;
        }

    }

    // Listar por estudiante
    @Override
    public List<FichaAbc> obtenerFichasPorEstudiante(int idEstudiante) {
        List<FichaAbc> listaFichas = new ArrayList<>();
        String sql = "SELECT f.fecha, a.descripcion AS antecedente, f.comportamiento, f.gravedad "
                + "FROM ficha_abc f "
                + "JOIN antecedente a ON f.id_antecedente = a.id_antecedente "
                + "WHERE f.id_estudiante = ? "
                + "ORDER BY f.fecha DESC";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEstudiante);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                listaFichas.add(new FichaAbc(
                        rs.getDate("fecha"),
                        new Antecedente(rs.getString("antecedente")),
                        rs.getString("comportamiento"),
                        rs.getInt("gravedad")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener fichas ABC del estudiante: " + e.getMessage());
        }

        return listaFichas;
    }

}
