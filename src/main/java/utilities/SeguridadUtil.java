package utilities;

import org.mindrot.jbcrypt.BCrypt;

public class SeguridadUtil {

    public static String encriptar(String passwordPlano) {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
    }

    public static boolean verificar(String passwordPlano, String passwordEncriptado) {
        return BCrypt.checkpw(passwordPlano, passwordEncriptado);
    }
}
