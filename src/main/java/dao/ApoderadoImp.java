package dao;

import configuration.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        Apoderado apoderado = null;
        String sql = "SELECT * FROM apoderado a JOIN persona p ON a.id_persona = p.id_persona WHERE p.dni = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                apoderado = new Apoderado();
                apoderado.setId(rs.getInt("id_apoderado"));
                apoderado.setDni(rs.getString("dni"));
                apoderado.setNombres(rs.getString("nombres"));
                apoderado.setApellidos(rs.getString("apellidos"));
                apoderado.setCorreo(rs.getString("correo"));
                apoderado.setDireccion(rs.getString("direccion"));
                apoderado.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                apoderado.setCelular(rs.getString("celular"));
                apoderado.setParentesco(rs.getString("parentesco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apoderado;
    }

    @Override
    public void registrarApoderado(Apoderado apoderado) {
        String sqlPersona = "INSERT INTO persona (nombres, apellidos, dni, celular, correo, direccion, fecha_nacimiento, genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlApoderado = "INSERT INTO apoderado (id_persona, parentesco) VALUES (?, ?)";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, apoderado.getNombres());
                ps.setString(2, apoderado.getApellidos());
                ps.setString(3, apoderado.getDni());
                ps.setString(4, apoderado.getCelular());
                ps.setString(5, apoderado.getCorreo());
                ps.setString(6, apoderado.getDireccion());
                ps.setDate(7, new java.sql.Date(apoderado.getFechaNacimiento().getTime()));
                ps.setString(8, apoderado.getGenero());
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int idPersona = rs.getInt(1);

                    try (PreparedStatement ps2 = conn.prepareStatement(sqlApoderado, Statement.RETURN_GENERATED_KEYS)) {
                        ps2.setInt(1, idPersona);
                        ps2.setString(2, apoderado.getParentesco());
                        ps2.executeUpdate();

                        ResultSet rs2 = ps2.getGeneratedKeys();
                        if (rs2.next()) {
                            int idApoderado = rs2.getInt(1);
                            apoderado.setIdApoderado(idApoderado); // ✅ guardar el ID
                        }
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

    @Override
    public void actualizarApoderado(Apoderado apoderado) {
        String sqlPersona = "UPDATE persona SET nombres = ?, apellidos = ?, dni = ?, celular = ?, correo = ?, direccion = ?, fecha_nacimiento = ?, genero = ? WHERE id_persona = ?";
        String sqlApoderado = "UPDATE apoderado SET parentesco = ? WHERE id_apoderado = ?";

        try (PreparedStatement ps1 = conn.prepareStatement(sqlPersona); PreparedStatement ps2 = conn.prepareStatement(sqlApoderado)) {

            ps1.setString(1, apoderado.getNombres());
            ps1.setString(2, apoderado.getApellidos());
            ps1.setString(3, apoderado.getDni());
            ps1.setString(4, apoderado.getCelular());
            ps1.setString(5, apoderado.getCorreo());
            ps1.setString(6, apoderado.getDireccion());
            ps1.setDate(7, new java.sql.Date(apoderado.getFechaNacimiento().getTime()));
            ps1.setString(8, apoderado.getGenero());
            ps1.setInt(9, apoderado.getId());
            ps1.executeUpdate();

            ps2.setString(1, apoderado.getParentesco());
            ps2.setInt(2, apoderado.getIdApoderado());
            ps2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
