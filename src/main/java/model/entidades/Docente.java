package model.entidades;

import java.sql.Date;


public class Docente extends Persona {

    private int idDocente;
    private Usuario usuario;

    public Docente(int idDocente, Usuario usuario, int id, String nombres, String apellidos, String dni, String celular, String correo, String direccion, Date fechaNacimiento, String genero) {
        super(id, nombres, apellidos, dni, celular, correo, direccion, fechaNacimiento, genero);
        this.idDocente = idDocente;
        this.usuario = usuario;
    }
    public Docente() {
    }
    

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
