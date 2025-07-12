package dao;

import configuration.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.entidades.Persona;
import model.entidades.Usuario;
import model.funcionalidad.ListaAulas;

public class SecretariaImp implements SecretariaDao {

    private Connection conn;
    private int idUsuario;
    private String username;
    private String password;
    private String rol;
    private String estado;
    private Persona persona;
    private int idSecretaria;
    private int vdt = 0;
    private int ad = 0;
    private int adt = 0;

    public SecretariaImp() {
        conn = Conexion.estableceConexion();
    }
    Usuario usuario = new Usuario(idUsuario, username, password, rol, estado, persona);

    @Override
    public int obtenerIdSecretariaporPersona(int idPersona) {
        String consulta = "SELECT id_persona FROM usuario WHERE username = ? AND password = ?;";
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPassword());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idSecretaria = rs.getInt("id_persona");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el id Secretaria" + e.getMessage());
        }
        return idSecretaria;
    }

    @Override
    public ArrayList<ListaAulas> obtenerListaAulas() {
        ArrayList<ListaAulas> lista = new ArrayList<>();

        String consulta = "SELECT a.id_aula, a.nombre, n.nombre AS nivel_funcional, d.nombre AS diagnostico, "
                + "a.vacantes_totales, a.vacantes_disponibles, pr.dni "
                + "FROM aula a "
                + "JOIN nivel_funcional n ON a.id_nivel_funcional = n.id_nivel "
                + "JOIN diagnostico d ON a.id_diagnostico_referente = d.id_diagnostico "
                + "JOIN persona pr ON a.id_docente = pr.id_persona";

        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ListaAulas aula = new ListaAulas(
                        rs.getInt("id_aula"),
                        rs.getString("nombre"),
                        rs.getString("nivel_funcional"),
                        rs.getString("diagnostico"),
                        rs.getInt("vacantes_totales"),
                        rs.getInt("vacantes_disponibles"),
                        rs.getString("dni")
                );
                adt = adt + 1;
                vdt = vdt + rs.getInt("vacantes_disponibles");
                if (rs.getInt("vacantes_disponibles") > 0) {
                    ad = ad + 1;
                }

                lista.add(aula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void obtenerDatosSecretaria() {
        String consulta = "SELECT p.nombres, p.apellidos, p.dni, p.celular, p.correo, p.direccion, p.fecha_nacimiento, p.genero FROM persona p JOIN usuario u ON p.id_persona = u.id_persona WHERE u.username = ? AND u.password = ?;";
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPassword());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nombres = rs.getString("nombres");
                persona.setNombres(nombres);
                String apellidos = rs.getString("apellidos");
                persona.setApellidos(apellidos);
                String dni = rs.getString("dni");
                persona.setDni(dni);
                String celular = rs.getString("celular");
                persona.setCelular(celular);
                String correo = rs.getString("correo");
                persona.setCorreo(correo);
                String direccion = rs.getString("direccion");
                persona.setDireccion(direccion);
                Date fecha_nacimiento = rs.getDate("fecha_nacimiento");
                persona.setFechaNacimiento(fecha_nacimiento);
                String genero = rs.getString("genero");
                persona.setGenero(genero);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos de la secretaria" + e.getMessage());
        }
    }

    public int numeroEstudiantes() {
        String consulta = "SELECT * FROM estudiante";
        int i = 0;
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos" + e.getMessage());
        }
        return i;
    }

    public int numeroMatriculas() {
        String consulta = "SELECT * FROM matricula WHERE DATE(fecha_matricula) = CURRENT_DATE();";
        int i = 0;
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos" + e.getMessage());
        }
        return i;
    }

    public int obtenerAulasDisponibles() {
        String consulta = "SELECT a.id_aula, a.nombre, n.nombre AS nivel_funcional, d.nombre AS diagnostico, "
                + "a.vacantes_totales, a.vacantes_disponibles, pr.dni "
                + "FROM aula a "
                + "JOIN nivel_funcional n ON a.id_nivel_funcional = n.id_nivel "
                + "JOIN diagnostico d ON a.id_diagnostico_referente = d.id_diagnostico "
                + "JOIN persona pr ON a.id_docente = pr.id_persona";

        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                rs.getInt("vacantes_totales");

                if (rs.getInt("vacantes_disponibles") > 0) {
                    ad = ad + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ad;
    }

    public int obtenerAulasDisponiblesTotales() {
        String consulta = "SELECT a.id_aula, a.nombre, n.nombre AS nivel_funcional, d.nombre AS diagnostico, "
                + "a.vacantes_totales, a.vacantes_disponibles, pr.dni "
                + "FROM aula a "
                + "JOIN nivel_funcional n ON a.id_nivel_funcional = n.id_nivel "
                + "JOIN diagnostico d ON a.id_diagnostico_referente = d.id_diagnostico "
                + "JOIN persona pr ON a.id_docente = pr.id_persona";

        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                rs.getInt("vacantes_totales");
                adt = adt + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adt;
    }

    public int obtenerVacantesDisponibles() {
        String consulta = "SELECT a.id_aula, a.nombre, n.nombre AS nivel_funcional, d.nombre AS diagnostico, "
                + "a.vacantes_totales, a.vacantes_disponibles, pr.dni "
                + "FROM aula a "
                + "JOIN nivel_funcional n ON a.id_nivel_funcional = n.id_nivel "
                + "JOIN diagnostico d ON a.id_diagnostico_referente = d.id_diagnostico "
                + "JOIN persona pr ON a.id_docente = pr.id_persona";

        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                vdt = vdt + rs.getInt("vacantes_disponibles");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vdt;
    }

}
