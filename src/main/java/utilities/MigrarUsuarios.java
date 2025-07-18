package utilities;

import org.mindrot.jbcrypt.BCrypt;
import dao.UsuarioDao;
import dao.UsuarioImp;

public class MigrarUsuarios {

    public static void main(String[] args) {
        UsuarioDao usuarioDao = UsuarioImp.obtenerInstancia();

        int[] ids = {1, 2};
        String[] contraseñas = {"1234", "1234"};

        for (int i = 0; i < ids.length; i++) {
            String hash = BCrypt.hashpw(contraseñas[i], BCrypt.gensalt());
            boolean actualizado = usuarioDao.actualizarContraseña(ids[i], hash);
            System.out.println("Usuario ID " + ids[i] + ": " + (actualizado ? "Actualizado" : "Error"));
        }
    }
}
