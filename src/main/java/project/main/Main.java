package project.main;

import configuration.Conexion;
import controller.UsuarioCtrl;
import model.dao.DocenteDao;
import model.dao.EstudianteDao;
import model.dao.IDocenteDao;
import model.dao.IEstudianteDao;
import model.dao.IUsuarioDao;
import model.dao.UsuarioDao;
import java.sql.SQLException;
import view.Docente.MenuDocenteView;
import view.Login;

public class Main {

    public static void main(String[] args) {

        Conexion.estableceConexion();

        IUsuarioDao usuarioDao = new UsuarioDao();
        IDocenteDao docenteDao = new DocenteDao();
        IEstudianteDao estudianteDao = new EstudianteDao();

        UsuarioCtrl usuarioCtrl = new UsuarioCtrl(usuarioDao, docenteDao, estudianteDao);

        Login loginView = new Login(usuarioCtrl);
        loginView.setVisible(true);
        loginView.setLocationRelativeTo(null);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Conexion.cerrarConexion();
                System.out.println("Conexion cerrada");
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion" + e.getMessage());
            }
        }));
    }
}
