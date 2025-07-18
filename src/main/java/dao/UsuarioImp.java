package dao;

import configuration.Conexion;
import configuration.UsuarioConectado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.entidades.Usuario;
import utilities.SeguridadUtil;

public class UsuarioImp implements UsuarioDao {

    private static UsuarioImp instanciaEstudiante;

    private Connection conn;

    public UsuarioImp() {
        conn = Conexion.estableceConexion();
    }

    public static UsuarioImp obtenerInstancia() {
        if (instanciaEstudiante == null) {
            instanciaEstudiante = new UsuarioImp();
        }
        return instanciaEstudiante;
    }

    @Override
    public UsuarioConectado validarLogin(String username, String password) {
        String sql = "SELECT u.id_usuario, u.username, u.rol, p.id_persona, p.nombres, p.apellidos, u.password "
                + "FROM usuario u "
                + "JOIN persona p ON u.id_persona = p.id_persona "
                + "WHERE u.username = ? AND u.estado = 'activo'";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String passwordHash = rs.getString("password");
                if (SeguridadUtil.verificar(password, passwordHash)) {
                    return new UsuarioConectado(
                            rs.getInt("id_usuario"),
                            rs.getString("username"),
                            rs.getString("rol"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getInt("id_persona")
                    );
                } else {
                    System.out.println("Contraseña incorrecta.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error en validarLogin: " + e.getMessage());
        }
        return null;
    }

    @Override
    public int obtenerIdPersonaPorRol(String rol, int idPersona) {
        String sql;

        switch (rol.toLowerCase()) {
            case "docente" ->
                sql = "SELECT id_docente FROM docente WHERE id_persona = ?";

            case "secretaria" ->
                sql = "SELECT id_usuario FROM usuario WHERE id_persona = ?";

            case "administrador" ->
                sql = "SELECT id_usuario FROM usuario WHERE id_persona = ?";

            default ->
                throw new IllegalArgumentException("Rol no reconocido" + rol);
        }

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idPersona);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ID por rol: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public boolean actualizarContraseña(int idUsuario, String nuevaContraseña) {
        String sql = "UPDATE usuario SET password = ? WHERE id_usuario = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nuevaContraseña);
            pst.setInt(2, idUsuario);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuario buscarPorId(int idUsuario) {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("rol"),
                        rs.getString("estado"),
                        null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
