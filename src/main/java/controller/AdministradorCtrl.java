
package controller;

import dao.AdministradorDao;
import java.util.List;
import javax.swing.table.DefaultTableModel;
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
    
    
}
