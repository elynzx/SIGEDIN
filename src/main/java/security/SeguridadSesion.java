/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package security;

import com.google.common.base.Optional;
import configuration.UsuarioConectado;

public class SeguridadSesion {

    private static Optional<UsuarioConectado> usuarioActual = Optional.absent();

    public static void iniciarSesion(UsuarioConectado usuario) {
        usuarioActual = Optional.of(usuario);
    }

    public static void cerrarSesion() {
        usuarioActual = Optional.absent();
        System.out.println("Sesión cerrada");
    }

    public static boolean usuarioAutenticado() {
        return usuarioActual.isPresent();
    }

    public static Optional<UsuarioConectado> getUsuarioActual() {
        return usuarioActual;
    }

    public static String getNombreCompleto() {
        return usuarioActual.isPresent() ? usuarioActual.get().getNombres() + " " + usuarioActual.get().getApellidos() : "Invitado";
    }

}
