/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.funcionalidad;

import model.funcionalidad.catalogo.TipoConducta;
import model.funcionalidad.catalogo.FuncionComportamiento;
import java.sql.Date;
import model.entidades.Estudiante;

public class ConductaProblematica {

    private int id;
    private Estudiante estudiante;
    private Date fecha;
    private TipoConducta tipo;
    private FuncionComportamiento funcion;
    private String descripcion;
    private int gravedad;

    public ConductaProblematica(int id, Estudiante estudiante, Date fecha, TipoConducta tipo, FuncionComportamiento funcion, String descripcion, int gravedad) {
        this.id = id;
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.tipo = tipo;
        this.funcion = funcion;
        this.descripcion = descripcion;
        this.gravedad = gravedad;
    }

    public ConductaProblematica() {
    }

    public ConductaProblematica(int id, TipoConducta tipo, String descripcion, int gravedad, Date fecha) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.gravedad = gravedad;
        this.fecha = fecha;
    }

    public ConductaProblematica(Estudiante estudiante, TipoConducta tipo, FuncionComportamiento funcion, int gravedad, String descripcion) {
        this.estudiante = estudiante;
        this.tipo = tipo;
        this.funcion = funcion;
        this.gravedad = gravedad;
        this.descripcion = descripcion;
    }

    public ConductaProblematica(int id, Date fecha, TipoConducta tipo, int gravedad) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.gravedad = gravedad;
    }

    public ConductaProblematica(int id, Date fecha, TipoConducta tipo, String descripcion, int gravedad) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.gravedad = gravedad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoConducta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConducta tipo) {
        this.tipo = tipo;
    }

    public FuncionComportamiento getFuncion() {
        return funcion;
    }

    public void setFuncion(FuncionComportamiento funcion) {
        this.funcion = funcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getGravedad() {
        return gravedad;
    }

    public void setGravedad(int gravedad) {
        this.gravedad = gravedad;
    }

}
