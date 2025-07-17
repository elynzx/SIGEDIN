package dao;

import configuration.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.entidades.Apoderado;

public class ApoderadoImp implements ApoderadoDao {

    private static ApoderadoImp instancia;
    private Connection conn;

    public ApoderadoImp() {
        conn = Conexion.estableceConexion();
    }

    public static ApoderadoImp obtenerInstancia() {
        if (instancia == null) {
            instancia = new ApoderadoImp();
        }
        return instancia;
    }

    @Override
    public Apoderado obtenerApoderadoPorDNI(String dni) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean registrarApoderado(Apoderado apoderado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Apoderado obtenerApoderadoPorId(int idApoderado) {
        String sql = "SELECT a.*, p.* FROM apoderado a "
                + "JOIN persona p ON a.id_persona = p.id_persona "
                + "WHERE a.id_apoderado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idApoderado);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Apoderado apoderado = new Apoderado();
                    apoderado.setIdApoderado(rs.getInt("id_apoderado"));
                    apoderado.setId(rs.getInt("id_persona"));
                    apoderado.setNombres(rs.getString("nombres"));
                    apoderado.setApellidos(rs.getString("apellidos"));
                    apoderado.setDni(rs.getString("dni"));
                    apoderado.setCelular(rs.getString("celular"));
                    apoderado.setCorreo(rs.getString("correo"));
                    apoderado.setDireccion(rs.getString("direccion"));
                    apoderado.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    apoderado.setGenero(rs.getString("genero"));
                    apoderado.setParentesco(rs.getString("parentesco"));
                    return apoderado;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
