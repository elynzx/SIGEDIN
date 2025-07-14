/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import configuration.Conexion;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.entidades.Persona;
import model.entidades.Usuario;
import model.funcionalidad.ListaAulas;
import model.funcionalidad.ListaUsuarios;

/**
 *
 * @author HAYDEE
 */
public class AdministradorImp implements AdministradorDao {
    
    private Connection conn;
    private static AdministradorImp instanciaAdministrador;

    public AdministradorImp() {
        conn = Conexion.estableceConexion();
    }
    
    
    
    @Override
    public int obtenerIdAdministradorporPersona(int idPersona) {
        int id=0;
        String consulta ="SELECT id_administrador FROM administrador WHERE id_persona="+idPersona+" ";
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_administrador");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener id de administrador" + e.getMessage());
        }
        return id;
    }

    @Override
    public ArrayList<ListaAulas> obtenerListaAulas() {
        ArrayList<ListaAulas> lista = new ArrayList<>();
        int adt=0;
        int vdt=0;
        int ad=0;
        
        String consulta = "SELECT a.id_aula, a.nombre, n.nombre AS nivel_funcional, d.nombre AS diagnostico, " +
                     "a.vacantes_totales, a.vacantes_disponibles, pr.dni " +
                     "FROM aula a " +
                     "JOIN docente doc ON a.id_docente = doc.id_docente " +
                     "JOIN nivel_funcional n ON a.id_nivel_funcional = n.id_nivel " +
                     "JOIN diagnostico d ON a.id_diagnostico_referente = d.id_diagnostico "+
                     "JOIN persona pr ON doc.id_persona = pr.id_persona ";
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
                adt=adt+1;
                vdt=vdt+rs.getInt("vacantes_disponibles");
                if(rs.getInt("vacantes_disponibles")>0){
                    ad=ad+1;
                }

                lista.add(aula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    @Override
    public ArrayList<ListaUsuarios> obtenerListaUsuarios() {
        ArrayList<ListaUsuarios> lista = new ArrayList<>();

        
        
        
        String consulta = "SELECT u.id_usuario, p.id_persona,p.nombres,p.apellidos,p.dni,p.celular,p.correo,p.direccion,p.fecha_nacimiento,p.genero, u.username, u.rol, u.estado FROM usuario u \n" +
                "JOIN persona p ON p.id_persona=u.id_persona; ";
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Persona persona = new Persona(
                rs.getInt("id_persona"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getString("dni"),
                rs.getString("celular"),
                rs.getString("correo"),
                rs.getString("direccion"),
                rs.getDate("fecha_nacimiento"),
                rs.getString("genero")
                );
                
                
                
                ListaUsuarios usuario = new ListaUsuarios(
                        rs.getInt("id_usuario"),
                        persona,
                        rs.getString("username"),
                        rs.getString("rol"),
                        rs.getString("estado")
                );
                lista.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public static AdministradorImp obtenerInstancia() {
        if (instanciaAdministrador == null) {
            instanciaAdministrador = new AdministradorImp();
        }
        return instanciaAdministrador;
    }
    
    @Override
    public void registrarCambio(String tipo_dato,String dato, int id){
        String consulta="";
        switch (tipo_dato){
            case "Nombres": consulta="UPDATE persona SET nombres = '"+dato+"' WHERE id_persona="+id+"";break;
            case "Apellidos": consulta="UPDATE persona SET apellidos = '"+dato+"' WHERE id_persona="+id+"";break;
            case "Nombre de Usuario": consulta="UPDATE usuario SET username = '"+dato+"' WHERE id_persona="+id+"";break;
            case "Rol": consulta="UPDATE usuario SET rol = '"+dato+"' WHERE id_persona="+id+"";break;
            case "Estado": consulta="UPDATE usuario SET estado = '"+dato+"' WHERE id_persona="+id+"";break;
            default: return;
        }
        
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cambio registrado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cambiar dato");
            e.printStackTrace();
        }
    }

    @Override
    public boolean VerificarDni(String dni) {
        String consulta="SELECT dni FROM persona WHERE dni='"+dni+"'";
        boolean confirmacion=true;
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                confirmacion=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return confirmacion;
    }

    @Override
    public void registrarPersona(Usuario usuario) {
        String consulta="INSERT INTO persona (nombres,apellidos,dni,celular,correo,direccion,fecha_nacimiento,genero) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            pst.setString(1, usuario.getPersona().getNombres());
            pst.setString(2, usuario.getPersona().getApellidos());
            pst.setString(3, usuario.getPersona().getDni());
            pst.setString(4, usuario.getPersona().getCelular());
            pst.setString(5, usuario.getPersona().getCorreo());
            pst.setString(6, usuario.getPersona().getDireccion());
            pst.setDate(7, usuario.getPersona().getFechaNacimiento());
            pst.setString(8, usuario.getPersona().getGenero());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Persona Registrado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al registrar reporte "+ e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
    public int obtenerIdPersona(Usuario usuario) {
        int idPersona=0;
        String consulta="SELECT id_persona FROM persona WHERE dni='"+usuario.getPersona().getDni()+"'";
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                idPersona=rs.getInt("id_persona");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idPersona;
    }
    
    @Override
    public void registrar(Usuario usuario) {
       String consulta="INSERT INTO usuario (username,password,rol,estado,id_persona) VALUES (?,?,?,?,?)";
       try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPassword());
            pst.setString(3, usuario.getRol());
            pst.setString(4, usuario.getEstado());
            pst.setInt(5, usuario.getPersona().getId());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "usuario registrado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al registrar reporte "+ e.getMessage());
            e.printStackTrace();
        }
    }
    
}
