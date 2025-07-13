package model.funcionalidad;

import model.entidades.Persona;

public class ListaUsuarios {
    private int id;
    private Persona persona;
    private String nombre_usuario;
    private String rol;
    private String estado;

    public ListaUsuarios(int id, Persona persona, String nombre_usuario, String rol, String estado) {
        this.id = id;
        this.persona = persona;
        this.nombre_usuario = nombre_usuario;
        this.rol = rol;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
