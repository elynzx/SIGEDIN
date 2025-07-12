/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.funcionalidad;

import java.sql.Date;
import model.entidades.Estudiante;


public class Asistencia {

    private int id;
    private Estudiante estudiante;
    private Date fecha;
    private boolean asiste;

    public Asistencia(int id, Estudiante estudiante, Date fecha, boolean asiste) {
        this.id = id;
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.asiste = asiste;
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

    public boolean isAsiste() {
        return asiste;
    }

    public void setAsiste(boolean asiste) {
        this.asiste = asiste;
    }

}
