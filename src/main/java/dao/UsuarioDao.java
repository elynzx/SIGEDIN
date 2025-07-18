package dao;

import configuration.UsuarioConectado;
import model.entidades.Usuario;

public interface UsuarioDao {

    UsuarioConectado validarLogin(String username, String password);

    int obtenerIdPersonaPorRol(String rol, int idPersona);

   Usuario buscarPorId(int idUsuario);

    boolean actualizarContraseña(int idUsuario, String nuevaContraseña);
    
}
