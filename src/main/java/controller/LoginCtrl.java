package controller;

import configuration.UsuarioConectado;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.Administrador.MenuAdminView;
import security.SeguridadSesion;
import security.ValidarUsuario;
import utillities.VistaUtil;
import view.Docente.VistaMenuDocente;
import view.Secretaria.VistaDashboardMatricula;
import view.Secretaria.MenuSecretariaView;
import dao.DocenteDao;
import dao.UsuarioDao;


public class LoginCtrl {

    private final DocenteDao docenteDao;
    private final UsuarioDao usuarioDao;

    public LoginCtrl(UsuarioDao usuarioDao, DocenteDao docenteDao) {
        this.usuarioDao = usuarioDao;
        this.docenteDao = docenteDao;

    }

    public boolean validarLogin(String username, String password) {

        try {
            ValidarUsuario.validarLogin(username, password);

            UsuarioConectado usuario = usuarioDao.validarLogin(username, password);

            if (usuario != null) {

                SeguridadSesion.iniciarSesion(usuario);
                return true;

            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                return false;
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public void mostrarVista() {

        UsuarioConectado usuario = SeguridadSesion.getUsuarioActual().orNull();
        if (usuario == null) {
            return;
        }

        String rol = usuario.getRol();
        int idPersona = usuario.getIdPersona();
        int idRol = usuarioDao.obtenerIdPersonaPorRol(rol, idPersona);

        if (idRol == -1) {
            JOptionPane.showMessageDialog(null, "No se encontró información del rol " + rol);
            return;
        }

        switch (rol) {

            case "administrador" ->
                VistaUtil.mostrarVista(new MenuAdminView(idRol));
            case "secretaria" ->
                VistaUtil.mostrarVista(new MenuSecretariaView(idRol));
            case "docente" ->
                VistaUtil.mostrarVista(new VistaMenuDocente(idRol));
            default ->
                JOptionPane.showMessageDialog(
                        null, "Por favor contacte al administrador");
        }
    }

    public void iniciarSesion(String username, String password, JFrame loginView) {
        if (validarLogin(username, password)) {
            mostrarVista();
            loginView.dispose();
        }
    }
}
