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
import java.util.List;
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
    public boolean registrarPersona(Usuario usuario) {
        
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
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar persona "+ e.getMessage());
            e.printStackTrace();
            return false;
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
    public boolean registrar(Usuario usuario) {
       String consulta="INSERT INTO usuario (username,password,rol,estado,id_persona) VALUES (?,?,?,?,?)";
       try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPassword());
            pst.setString(3, usuario.getRol());
            pst.setString(4, usuario.getEstado());
            pst.setInt(5, usuario.getPersona().getId());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "usuario registrado correctamente");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario "+ e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public List<String[]> listarEstudiantesMatriculados() {
        List<String[]> lista = new ArrayList<>();
        String consulta = "SELECT " +
            "e.id_estudiante, p.apellidos, p.nombres, d.nombre AS diagnostico, " +
            "n.nombre AS nivel_funcional, a.nombre AS aula, " +
            "pd.nombres AS docente_nombres, pd.apellidos AS docente_apellidos, " +
            "p.celular, m.fecha_matricula " +
            "FROM estudiante e " +
            "JOIN persona p ON p.id_persona = e.id_persona " +
            "JOIN matricula m ON m.id_estudiante = e.id_estudiante " +
            "JOIN aula a ON m.id_aula = a.id_aula " +
            "JOIN docente dc ON dc.id_docente = a.id_docente " +
            "JOIN persona pd ON pd.id_persona = dc.id_persona " +
            "JOIN nivel_funcional n ON n.id_nivel = a.id_nivel_funcional " +
            "JOIN diagnostico d ON d.id_diagnostico = a.id_diagnostico_referente";

        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            String[] fila = new String[10];
            fila[0] = rs.getString("id_estudiante");
            fila[1] = rs.getString("apellidos");
            fila[2] = rs.getString("nombres");
            fila[3] = rs.getString("diagnostico");
            fila[4] = rs.getString("nivel_funcional");
            fila[5] = rs.getString("aula");
            fila[6] = rs.getString("docente_nombres") + " " + rs.getString("docente_apellidos");
            fila[7] = rs.getString("celular");
            fila[8] = rs.getString("fecha_matricula");
            lista.add(fila);
        }
        } catch (SQLException e) {
        System.out.println("Error al listar estudiantes: " + e.getMessage());
        }

    return lista;
}

    @Override
    public List<String[]> listarEstudiantesMatriculadosFiltro(String aula, String diagnostico, String docente) {
            List<String[]> lista = new ArrayList<>();
    String consulta = "SELECT " +
            "e.id_estudiante, p.apellidos, p.nombres, d.nombre AS diagnostico, " +
            "n.nombre AS nivel_funcional, a.nombre AS aula, " +
            "pd.nombres AS docente_nombres, pd.apellidos AS docente_apellidos, " +
            "p.celular, m.fecha_matricula " +
            "FROM estudiante e " +
            "JOIN persona p ON p.id_persona = e.id_persona " +
            "JOIN matricula m ON m.id_estudiante = e.id_estudiante " +
            "JOIN aula a ON m.id_aula = a.id_aula " +
            "JOIN docente dc ON dc.id_docente = a.id_docente " +
            "JOIN persona pd ON pd.id_persona = dc.id_persona " +
            "JOIN nivel_funcional n ON n.id_nivel = a.id_nivel_funcional " +
            "JOIN diagnostico d ON d.id_diagnostico = a.id_diagnostico_referente";
    
    if(aula!=null && diagnostico!=null && docente!=null){
        consulta +=" WHERE pd.apellidos='"+docente+"' AND a.nombre='"+aula+"' AND d.nombre='"+diagnostico+"'"; 
    }else{if(aula!=null &&  diagnostico==null && docente==null){
        consulta +=" WHERE a.nombre='"+aula+"'";
    }else{if(aula!=null &&  diagnostico!=null && docente==null){
        consulta +=" WHERE a.nombre='"+aula+"' AND d.nombre='"+diagnostico+"'"; 
    }else{if(aula!=null && diagnostico==null && docente!=null){
        consulta +=" WHERE pd.apellidos='"+docente+"' AND a.nombre='"+aula+"'";
                }else{if(aula==null && diagnostico!=null && docente==null){
                    consulta +=" WHERE d.nombre='"+diagnostico+"'";
                    }else{if(aula==null && diagnostico!=null && docente!=null){
                            consulta +=" WHERE pd.apellidos='"+docente+"' AND d.nombre='"+diagnostico+"'";
                        }else{if(aula==null && diagnostico==null && docente!=null){
                                consulta+=" WHERE pd.apellidos='"+docente+"'";
                            }else{if(aula==null && diagnostico== null && docente== null){
                                    JOptionPane.showMessageDialog(null, " Valores ingresados invalidos");
                                    return null;
                                }
                            }
                        }
                    }
                }
            }  
        }
    }
    
    try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            String[] fila = new String[10];
            fila[0] = rs.getString("id_estudiante");
            fila[1] = rs.getString("apellidos");
            fila[2] = rs.getString("nombres");
            fila[3] = rs.getString("diagnostico");
            fila[4] = rs.getString("nivel_funcional");
            fila[5] = rs.getString("aula");
            fila[6] = rs.getString("docente_nombres") + " " + rs.getString("docente_apellidos");
            fila[7] = rs.getString("celular");
            fila[8] = rs.getString("fecha_matricula");
            lista.add(fila);
        }
    } catch (SQLException e) {
        System.out.println("Error al listar estudiantes: " + e.getMessage());
    }

    return lista;
    }
    
    @Override
    public List<String> listaAulas() {
    List<String> listaAulas = new ArrayList<>();
    String consulta = "SELECT nombre FROM aula";

    try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            String nombre = rs.getString("nombre");
            listaAulas.add(nombre);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener lista aula: " + e.getMessage());
    }

    return listaAulas;
    }
    
    @Override
    public List<String> listaDiagnostico() {
    List<String> listaDiagnostico = new ArrayList<>();
    String consulta = "SELECT nombre FROM diagnostico";

    try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            String nombre = rs.getString("nombre");
            listaDiagnostico.add(nombre);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener lista Diagnostico: " + e.getMessage());
    }

    return listaDiagnostico;
}

    @Override
    public List<String> listaDocentes() {
        List<String> listaDocentes = new ArrayList<>();
        String consulta = "SELECT p.nombres, p.apellidos FROM persona p JOIN docente d ON d.id_persona=p.id_persona";

        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombres");
                String apellido = rs.getString("apellidos");
                listaDocentes.add(apellido);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener lista docentes: " + e.getMessage());
        }

        return listaDocentes;
    }
    
    @Override
    public int obtenerIdMatricula(String id_alumno){
        int id=0;
        String consulta="SELECT m.id_matricula FROM matricula m JOIN estudiante e ON m.id_estudiante=e.id_estudiante WHERE e.id_estudiante="+id_alumno+"";
        
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_matricula");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener lista Diagnostico: " + e.getMessage());
        }
        return id;
    }

    @Override
    public void registrarCambioEstudiante(String dato_final, String idEstudiante,String Dato) {
        String consulta="";
        int id_estudiante = Integer.parseInt(idEstudiante);
        int id_persona=0;
        int id_aula=0;
        switch(dato_final){
            case "nombres": id_persona=obtenerIdPersona(id_estudiante); consulta="UPDATE persona SET nombres = '"+Dato+"' WHERE id_persona="+id_persona+"";break;
            case "apellidos": id_persona=obtenerIdPersona(id_estudiante); consulta="UPDATE persona SET apellidos = '"+Dato+"' WHERE id_persona="+id_persona+"";break;
            case "aula": id_persona=obtenerIdPersona(id_estudiante); id_aula=obtenerIdAula(Dato);consulta="UPDATE matricula SET id_aula = "+id_aula+" WHERE id_estudiante="+id_estudiante+"";break;
            case "celular": id_persona=obtenerIdPersona(id_estudiante); consulta="UPDATE persona SET celular = '"+Dato+"' WHERE id_persona="+id_persona+"";break;
            default:
        }
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Cambio realizado de "+dato_final+" correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al registrar usuario "+ e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public int obtenerIdPersona(int idEstudiante) {
        String consulta="SELECT id_persona FROM estudiante WHERE id_estudiante="+idEstudiante+"";
        int id_persona=0;
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                id_persona = rs.getInt("id_persona");
                System.out.println("id persona: "+id_persona);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener id persona " + e.getMessage());
        }
        return id_persona;
    }
    
    @Override
    public int obtenerIdAula(String Dato){
        int id_aula=0;
        String consulta="SELECT id_aula FROM aula WHERE nombre='"+Dato+"'";
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                id_aula = rs.getInt("id_aula");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener id aula " + e.getMessage());
        }
        return id_aula;
    }
    
    
    
}
