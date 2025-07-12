/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.funcionalidad;

import model.funcionalidad.catalogo.Antecedente;
import java.sql.Date;
import model.entidades.Estudiante;


public class FichaAbc {

    private int id;
    private Estudiante estudiante;
    private Date fecha;
    private Antecedente antecedente;
    private String comportamiento;
    private String consecuencia;
    private int gravedad;

    public FichaAbc(Date fecha, Antecedente antecedente, String comportamiento, int gravedad) {
        this.fecha = fecha;
        this.antecedente = antecedente;
        this.comportamiento = comportamiento;
        this.gravedad = gravedad;
    }
    
        public FichaAbc(Date fecha, Antecedente antecedente, String comportamiento, String consecuencia, int gravedad) {
        this.fecha = fecha;
        this.antecedente = antecedente;
        this.comportamiento = comportamiento;
        this.gravedad = gravedad;
    }


    public FichaAbc(int id, Estudiante estudiante, Date fecha, Antecedente antecedente, String comportamiento, String consecuencia, int gravedad) {
        this.id = id;
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.antecedente = antecedente;
        this.comportamiento = comportamiento;
        this.consecuencia = consecuencia;
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

    public Antecedente getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(Antecedente antecedente) {
        this.antecedente = antecedente;
    }

    public String getComportamiento() {
        return comportamiento;
    }

    public void setComportamiento(String comportamiento) {
        this.comportamiento = comportamiento;
    }

    public String getConsecuencia() {
        return consecuencia;
    }

    public void setConsecuencia(String consecuencia) {
        this.consecuencia = consecuencia;
    }

    public int getGravedad() {
        return gravedad;
    }

    public void setGravedad(int gravedad) {
        this.gravedad = gravedad;
    }

}
