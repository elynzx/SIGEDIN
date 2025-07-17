package controller;

import configuration.UsuarioConectado;
import com.google.common.base.Optional;

import model.entidades.Usuario;
import model.entidades.Persona;
import security.SeguridadSesion;

public class SesionCtrl {

    public static Usuario obtenerUsuarioLogueado() {
        Optional<UsuarioConectado> usuarioConectadoOpt = SeguridadSesion.getUsuarioActual();

        if (usuarioConectadoOpt.isPresent()) {
            UsuarioConectado conectado = usuarioConectadoOpt.get();

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(conectado.getIdUsuario());
            usuario.setUsername(conectado.getUsername());
            usuario.setRol(conectado.getRol());

            Persona persona = new Persona();
            persona.setId(conectado.getIdPersona());
            persona.setNombres(conectado.getNombres());
            persona.setApellidos(conectado.getApellidos());

            usuario.setPersona(persona);
            return usuario;
        }

        return null;
    }
}
