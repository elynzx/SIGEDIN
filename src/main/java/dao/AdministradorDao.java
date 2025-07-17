/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.entidades.Persona;
import model.entidades.Usuario;
import model.funcionalidad.ListaAulas;
import model.funcionalidad.ListaUsuarios;

/**
 *
 * @author rpasc
 */
public interface AdministradorDao {

    int obtenerIdAdministradorporPersona(int idPersona);
    ArrayList<ListaAulas> obtenerListaAulas();
    ArrayList<ListaUsuarios> obtenerListaUsuarios();
    void registrarCambio(String tipo_dato,String dato, int id);
    boolean VerificarDni(String dni);
    boolean registrarPersona(Usuario usuario);
    int obtenerIdPersona(Usuario usuario);
    boolean registrar(Usuario usuario);
    public List<String[]> listarEstudiantesMatriculados();
    public List<String[]> listarEstudiantesMatriculadosFiltro(String aula, String diagnostico, String docente);
    public List<String> listaAulas();
    public List<String> listaDiagnostico();
    public List<String> listaDocentes();
    public int obtenerIdMatricula(String id_alumno);
    public void registrarCambioEstudiante(String dato_final,String idEstudiante,String Dato);
    public int obtenerIdPersona(int idEstudiante);
    public int obtenerIdAula(String Dato);
    public int obtenerId_Tipo_Matricula(String tipo_reporte);
    public List<String[]> obtenerListaMatriculasPorAula(String filtro,boolean aulas, boolean diagnostico);
    public void registrarReporte(int idTipoReporte,String criterio_filtro,int idEstudiante, int id,int idEmpleado, Timestamp timestamp);
    public int obtenerIdReporte(Timestamp timestamp, int idEmpleado);
    public int obtenerAula(String aula);
}
