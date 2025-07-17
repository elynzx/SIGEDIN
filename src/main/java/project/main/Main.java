package project.main;

import configuration.Conexion;
import controller.LoginCtrl;
import dao.DocenteImp;
import dao.UsuarioImp;
import java.sql.SQLException;
import view.Login;
import dao.DocenteDao;
import dao.UsuarioDao;

public class Main {

    public static void main(String[] args) {
  
        Conexion.estableceConexion();
        UsuarioDao usuarioDao = new UsuarioImp();
        LoginCtrl loginCtrl = new LoginCtrl(usuarioDao);

        Login loginView = new Login(loginCtrl);
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
