
package dao;

import configuration.UsuarioConectado;


public interface UsuarioDao {

    UsuarioConectado validarLogin(String username, String password);
    int obtenerIdPersonaPorRol(String rol, int idPersona);
}
