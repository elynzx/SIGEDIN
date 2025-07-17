/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.funcionalidad.catalogo;

/**
 *
 * @author rpasc
 */
public class NivelFuncional {

    private int id;
    private String nombre;

    public NivelFuncional(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public NivelFuncional() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NivelFuncional other = (NivelFuncional) obj;
        return id == other.id;
    }
}
