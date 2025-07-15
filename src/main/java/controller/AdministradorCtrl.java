
package controller;

import dao.AdministradorDao;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.entidades.Persona;
import model.entidades.Usuario;
import model.funcionalidad.ListaAulas;
import view.Administrador.MenuAdminView;
import model.funcionalidad.ListaUsuarios;
import view.Administrador.EstudiantesAdmin;
import view.Administrador.ReportesAdmin;



public class AdministradorCtrl {
        private final AdministradorDao dao;
        public AdministradorCtrl(AdministradorDao dao) {
            this.dao=dao;
            
            
        }
    
        
    public List<ListaAulas> cargarTablaAulas() {
        List<ListaAulas> aulas = dao.obtenerListaAulas();
        return aulas;
    }
    
    public List<ListaUsuarios> cargarTablaUsuarios() {
        List<ListaUsuarios> usuarios = dao.obtenerListaUsuarios();
        return usuarios;
    }
    
    public void cambiarDato(String tipo_dato,String dato, int id){
        dao.registrarCambio(tipo_dato,dato,id);
    }
    
    public boolean verificarDni(String dni){
        boolean confirmacion;
        confirmacion=dao.VerificarDni(dni);
        return confirmacion;
    }
    
    public boolean registrar(Usuario usuario){
        boolean confirmacion = false;
        boolean confirmacion2 = false; 
        boolean confirmacion_final=false;
        
        confirmacion=dao.registrarPersona(usuario);
        int idPersona=dao.obtenerIdPersona(usuario);
        usuario.getPersona().setId(idPersona);
        confirmacion2=dao.registrar(usuario);
        if(confirmacion==true && confirmacion2==true){
            confirmacion_final=true;
        }else{
            JOptionPane.showMessageDialog(null, "Error registro");
        }
        return confirmacion_final;
    }
    
    public void llenarTablaEstudiantes(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        List<String[]> estudiantes = dao.listarEstudiantesMatriculados();
        for (String[] fila : estudiantes) {
            modelo.addRow(fila);
            }
    }
    
    public List<String> cargarAulas(JComboBox combo) {
        List<String> listaAulas = dao.listaAulas();
        return listaAulas;
    }
    
    public List<String> cargarDiagnostico(JComboBox combo) {
        List<String> listaDiagnostico = dao.listaDiagnostico();
        return listaDiagnostico;
    }
    
    public List<String> cargarDocentes(JComboBox combo) {
        List<String> listaDocentes = dao.listaDocentes();
        return listaDocentes;
    }
    
    public void actualizarValores(EstudiantesAdmin estudiantes, JTable tabla){
        String aula=(String) estudiantes.getJcmbaula().getSelectedItem();
        String diagnostico=(String) estudiantes.getJcmbdiagnostico().getSelectedItem();
        String docente=(String) estudiantes.getJcmbdocente().getSelectedItem();
        
        if (docente =="ninguno" && diagnostico=="ninguno" && aula=="ninguno"){
            JOptionPane.showMessageDialog(null, " Valores ingresados invalidos");
            llenarTablaEstudiantes(tabla);
            return;  
        }
        
        if (aula=="ninguno") {
        aula = null; 
        }
        if (diagnostico=="ninguno") {
        diagnostico = null; 
        }
        if (docente =="ninguno") {
        docente = null; 
        }
        
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        List<String[]> estudiante = dao.listarEstudiantesMatriculadosFiltro(aula,diagnostico,docente);
        for (String[] fila : estudiante) {
            modelo.addRow(fila);
            }
        
        dao.listarEstudiantesMatriculadosFiltro(aula,diagnostico,docente);
                        
    }
    
    
    
}
