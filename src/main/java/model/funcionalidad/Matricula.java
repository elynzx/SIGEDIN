/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.funcionalidad;

import model.entidades.Estudiante;
import model.entidades.Aula;
import java.sql.Date;

public class Matricula {

    private int id;
    private Estudiante estudiante;
    private Aula aula;
    private Date fechaMatricula;
    private String estado;
    private String observaciones;

    public Matricula(int id, Estudiante estudiante, Aula aula, Date fechaMatricula, String estado, String observaciones) {
        this.id = id;
        this.estudiante = estudiante;
        this.aula = aula;
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public Matricula() {
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

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
