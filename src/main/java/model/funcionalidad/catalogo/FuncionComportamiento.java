/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.funcionalidad.catalogo;

/**
 *
 * @author rpasc
 */
public class FuncionComportamiento {

    private int id;
    private String nombre;

    public FuncionComportamiento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public FuncionComportamiento(String nombre) {
        this.nombre = nombre;
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
        return this.nombre;
    }

}
