
package controller;

import configuration.SesionUsuario;
import configuration.UsuarioConectado;
import model.dao.IDocenteDao;
import model.dao.IUsuarioDao;
import java.awt.HeadlessException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import view.Administrador.InicioAdmin;
import view.Secretaria.DashboardMatricula;
import com.google.common.base.Preconditions;
import model.dao.IEstudianteDao;
import view.Docente.MenuDocenteView;

public class UsuarioCtrl {

    private IDocenteDao docenteDao;
    private IUsuarioDao usuarioDao;
    private IEstudianteDao estudianteDao;

    public UsuarioCtrl(IUsuarioDao usuarioDao, IDocenteDao docenteDao, IEstudianteDao estudianteDao) {
        this.usuarioDao = usuarioDao;
        this.docenteDao = docenteDao;
        this.estudianteDao = estudianteDao;

    }

    public boolean validarLogin(String username, String password) {

        try {
            Preconditions.checkNotNull(username, "El usuario no puede ser null");
            Preconditions.checkNotNull(password, "La contraseña no puede ser null");
            Preconditions.checkArgument(!username.isEmpty(), "El usuario no puede estar vacío");
            Preconditions.checkArgument(!password.isEmpty(), "La contraseña no puede estar vacía");

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingresa todos los datos.");
            return false;
        }

        UsuarioConectado usuario = usuarioDao.validarLogin(username, password);

        if (usuario != null) {
            SesionUsuario.setUsuarioActual(usuario);
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
            return false;
        }
    }

    public void mostrarVista() {

        String rol = SesionUsuario.getUsuarioActual().getRol();
        int idPersona = SesionUsuario.getUsuarioActual().getIdPersona();
        int idDocente = docenteDao.obtenerIdDocenteporPersona(idPersona);

        try {
            switch (rol) {
                case "administrador" -> {
                    InicioAdmin vAdmin = new InicioAdmin();
                    vAdmin.setVisible(true);
                    vAdmin.setLocationRelativeTo(null);
                }

                case "docente" -> {
                    if (idDocente != -1) {

                        MenuDocenteView vMenuDocente = new MenuDocenteView(idDocente);
                        vMenuDocente.setVisible(true);
                        vMenuDocente.setLocationRelativeTo(null);

                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró información del docente");
                    }
                }

                case "secretaria" -> {
                    DashboardMatricula vMatricula = new DashboardMatricula();
                    vMatricula.setVisible(true);
                    vMatricula.setLocationRelativeTo(null);
                }

                default ->
                    JOptionPane.showMessageDialog(null, "Por favor contacte al administrador");
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar vistar: " + e.getMessage());
        }
    }

    public void mostrarDatosUsuario(JLabel lbNomUserConectado) {
        lbNomUserConectado.setText(SesionUsuario.getUsuarioActual().getNombres() + " " + SesionUsuario.getUsuarioActual().getApellidos());
    }

}
