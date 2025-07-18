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
import java.sql.Timestamp;
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
            System.out.println("Error al obtener id matricula: " + e.getMessage());
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
    
    @Override
    public int obtenerId_Tipo_Matricula(String tipo_reporte){
        int idTipoReporte=0;
        String consulta="SELECT id_tipo_reporte FROM tipo_reporte WHERE nombre='"+tipo_reporte+"'";
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idTipoReporte = rs.getInt("id_tipo_reporte");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener id del tipo_reporte " + e.getMessage());
        }
        
        return idTipoReporte;
    }
    
    @Override
    public List<String[]> obtenerListaMatriculasPorAula(String filtro,boolean aulas, boolean diagnostico, boolean docente){
        List<String[]> lista = new ArrayList<>();
        String consulta="SELECT\n" +
            "   e.id_estudiante, p.apellidos, p.nombres, d.nombre AS diagnostico, \n" +
            "   n.nombre AS nivel_funcional, a.nombre AS aula, \n" +
            "   pd.nombres AS docente_nombres, pd.apellidos AS docente_apellidos, \n" +
            "   p.celular, m.fecha_matricula \n" +
            "FROM estudiante e \n" +
            "JOIN persona p ON p.id_persona = e.id_persona \n" +
            "JOIN matricula m ON m.id_estudiante = e.id_estudiante \n" +
            "JOIN aula a ON m.id_aula = a.id_aula \n" +
            "JOIN docente dc ON dc.id_docente = a.id_docente \n" +
            "JOIN persona pd ON pd.id_persona = dc.id_persona \n" +
            "JOIN nivel_funcional n ON n.id_nivel = a.id_nivel_funcional \n" +
            "JOIN diagnostico d ON d.id_diagnostico = a.id_diagnostico_referente ";
                
        if(aulas==true && diagnostico==false && docente==false){
            consulta +=" WHERE a.nombre='"+filtro+"'";
            System.out.println("error 1");
        }else{
            if(diagnostico==true && aulas==false && docente==false){
                consulta +=" WHERE d.nombre='"+filtro+"'";
                System.out.println("error 2");
            }else{
                if(diagnostico==false && aulas==false && docente==true && filtro!=""){
                    consulta +=" WHERE dc.id_docente="+filtro+"";
                    System.out.println("error 3");
                }else{
                    
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
    public void registrarReporte(int idTipoReporte,String criterio_filtro,int idEstudiante, int id,int idEmpleado, Timestamp timestamp){
        int idReporte=0;
        String estudianteString=null;
        String idAula=null;
        if(idEstudiante==0){
            estudianteString="NULL";
        }else{
            estudianteString=Integer.toString(idEstudiante);
        }
        if(id==0){
            idAula="NULL";
        }else{
            idAula=Integer.toString(id);
        }
        
        
        
        String consulta="INSERT INTO reporte (id_tipo_reporte, criterio_filtro, id_estudiante, id_aula, fecha_generacion, generado_por) VALUES (?,?,"+estudianteString+","+idAula+",?,?);";
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            pst.setInt(1, idTipoReporte);
            pst.setString(2, criterio_filtro);
            pst.setTimestamp(3, timestamp);
            pst.setInt(4, idEmpleado);
            pst.executeUpdate();
            System.out.println("reporte Registrado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al registrar reporte "+ e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    @Override
    public int obtenerIdReporte(Timestamp timestamp, int idEmpleado){
        int idReporte=0;
        System.out.println("id empleado "+idEmpleado+" fecha: "+timestamp);
         String consulta="SELECT id_reporte FROM reporte WHERE fecha_generacion= '"+timestamp+"' AND generado_por="+idEmpleado+"";
        
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idReporte = rs.getInt("id_reporte");
            }
            System.out.println("id obtenido correctamente "+idReporte);
            
        } catch (SQLException e) {
            System.out.println("Error al obtener id del reporte " + e.getMessage());
        }
        return idReporte;
    }
    
    @Override
    public int obtenerAula(String aula){
        int idAula=0;
        String consulta="SELECT id_aula FROM aula WHERE nombre='"+aula+"'";
        
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idAula = rs.getInt("id_aula");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener id del aula " + e.getMessage());
        }
         
        return idAula;
     }

    @Override
    public String obtenerIdDocente(String filtro) {
        String consulta="SELECT d.id_docente FROM docente d JOIN persona p ON p.id_persona=d.id_persona WHERE p.apellidos='"+filtro+"'";
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                filtro = rs.getString("id_docente");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener id del docente " + e.getMessage());
        }
        return filtro;
    }
    
    @Override
    public String verContraseña(String contraseña, Object Id, int idAdministrador) {
    String contra = "";

    // Validar que Id no sea null y convertirlo correctamente
    if (Id == null || !(Id instanceof Number)) {
        JOptionPane.showMessageDialog(null, "ID inválido");
        return contra;
    }

    int idUsuario = ((Number) Id).intValue();

    String consulta = "SELECT password FROM usuario WHERE password = ? AND id_usuario = ?";

    try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        pst.setString(1, contraseña);
        pst.setInt(2, idAdministrador);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "Contraseña validada correctamente");
            contra=obtenerContraseña(idUsuario);
        } else {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
        }

    } catch (SQLException e) {
        System.out.println("Error al ejecutar consulta1: " + e.getMessage());
    }

    return contra;
}

    
    @Override
    public void cambiarContraseña(String contraseña,Object Id,String contraNueva){
        String consulta="UPDATE usuario SET PASSWORD='"+contraNueva+"' WHERE id_usuario="+Id+"";
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Contraseña cambiada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al cambiar contraseña "+ e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
    public String obtenerContraseña(int idUsuario){
        String consulta2 = "SELECT password FROM usuario WHERE id_usuario = ?";
        String contraEmpleado="";
        try (PreparedStatement pst2 = conn.prepareStatement(consulta2)) {
                pst2.setInt(1, idUsuario);
                ResultSet rs2 = pst2.executeQuery();

                if (rs2.next()) {
                    contraEmpleado = rs2.getString("password");
                }
        } catch (SQLException e) {
                System.out.println("Error al ejecutar consulta2: " + e.getMessage());
        }
        return contraEmpleado;
    }
    
    @Override
    public String obtenerNombreAdministrador(int idAdministrador){
        String nombre="";
        String consulta="SELECT p.nombres FROM persona p JOIN usuario u ON u.id_persona=p.id_persona WHERE u.id_usuario="+idAdministrador+"";
        
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                nombre = rs.getString("nombres");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener nombre del administrador " + e.getMessage());
        }
        return nombre;
    }

    @Override
    public List<String> listaUsuarios() {
        List<String> listaUsuarios = new ArrayList<>();
        String consulta = "SELECT username FROM usuario";

        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                listaUsuarios.add(username);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener lista docentes: " + e.getMessage());
        }

        return listaUsuarios;
    }
    
    @Override
    public int obtenerIdUsuario(String username){
        int id=0;
        String consulta="SELECT id_usuario FROM usuario WHERE username='"+username+"'";
        
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_usuario");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener lista Diagnostico: " + e.getMessage());
        }
        return id;
    }
    
    @Override
        public void registrarReporteUsuario(int idTipoReporte,String criterio_filtro,int idEstudiante, int id,int idEmpleado, Timestamp timestamp){
        int idReporte=0;
        String estudianteString=null;
        String idAula=null;
        if(idEstudiante==0){
            estudianteString="NULL";
        }else{
            estudianteString=Integer.toString(idEstudiante);
        }
        if(id==0){
            idAula="NULL";
        }else{
            idAula=Integer.toString(id);
        }
        
        
        
        String consulta="INSERT INTO reporte (id_tipo_reporte, criterio_filtro, id_estudiante, id_aula, fecha_generacion, generado_por) VALUES (?,?,"+estudianteString+","+idAula+",?,?);";
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            pst.setInt(1, idTipoReporte);
            pst.setString(2, criterio_filtro);
            pst.setTimestamp(3, timestamp);
            pst.setInt(4, idEmpleado);
            pst.executeUpdate();
            System.out.println("reporte usuario registrado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al registrar reporte "+ e.getMessage());
            e.printStackTrace();
        }
    }
        
    @Override
    public Usuario obtenerDatosUsuario(int id_usuario){
        Persona persona=new Persona();
        Usuario usuario = new Usuario(
        0,
        "",
        "",
        "",
        "",
        persona
        );
        String consulta="SELECT u.id_usuario,u.username,u.rol,u.estado,p.id_persona,p.nombres,p.apellidos,p.dni, p.celular,p.correo,p.direccion,p.fecha_nacimiento,p.correo FROM persona p JOIN usuario u ON u.id_persona=p.id_persona WHERE u.id_usuario="+id_usuario+"";
        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
        ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setRol(rs.getString("rol"));
                usuario.setEstado(rs.getString("estado"));
                usuario.getPersona().setId(rs.getInt("id_persona"));
                usuario.getPersona().setNombres(rs.getString("nombres"));
                usuario.getPersona().setApellidos(rs.getString("apellidos"));
                usuario.getPersona().setDni(rs.getString("dni"));
                usuario.getPersona().setCelular(rs.getString("celular"));
                usuario.getPersona().setCorreo(rs.getString("correo"));
                usuario.getPersona().setDireccion(rs.getString("direccion"));
                usuario.getPersona().setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener objeto usuario: " + e.getMessage());
        }
        return usuario;
    }
    
    @Override
    public List<String[]> obtenerHistorialReportes(int id_usuario) {
        List<String[]> reportesUsuario = new ArrayList<>();
        String consulta = "SELECT id_reporte, criterio_filtro, id_aula, fecha_generacion, generado_por FROM reporte WHERE generado_por="+id_usuario;

        try (PreparedStatement pst = conn.prepareStatement(consulta)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String[] reporte = new String[5];
                reporte[0] = rs.getString("id_reporte");
                reporte[1] = rs.getString("criterio_filtro");
                reporte[2] = rs.getString("id_aula");
                reporte[3] = rs.getString("fecha_generacion");
                reporte[4] = rs.getString("generado_por");

                reportesUsuario.add(reporte);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener lista de reportes: " + e.getMessage());
        }

        return reportesUsuario;
    }

    
    
}
