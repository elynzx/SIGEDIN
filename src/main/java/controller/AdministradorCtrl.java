
package controller;

import dao.AdministradorDao;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.entidades.Persona;
import model.entidades.Usuario;
import model.funcionalidad.ListaAulas;
import view.Administrador.MenuAdminView;
import model.funcionalidad.ListaUsuarios;



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
    
    public void registrar(Usuario usuario){
        dao.registrarPersona(usuario);
        int idPersona=dao.obtenerIdPersona(usuario);
        usuario.getPersona().setId(idPersona);
        dao.registrar(usuario);
    }
    
    
}
