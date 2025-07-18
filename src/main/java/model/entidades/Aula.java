/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entidades;

import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.NivelFuncional;


public class Aula {

    private int id;
    private String nombre;
    private NivelFuncional nivelFuncional;
    private Diagnostico diagnosticoReferente;
    private int vacantesTotales;
    private int vacantesDisponibles;
    private Docente docente;

    public Aula(int id, String nombre, NivelFuncional nivelFuncional, Diagnostico diagnosticoReferente, int vacantesTotales, int vacantesDisponibles, Docente docente) {
        this.id = id;
        this.nombre = nombre;
        this.nivelFuncional = nivelFuncional;
        this.diagnosticoReferente = diagnosticoReferente;
        this.vacantesTotales = vacantesTotales;
        this.vacantesDisponibles = vacantesDisponibles;
        this.docente = docente;
    }

    public Aula() {
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

    public NivelFuncional getNivelFuncional() {
        return nivelFuncional;
    }

    public void setNivelFuncional(NivelFuncional nivelFuncional) {
        this.nivelFuncional = nivelFuncional;
    }

    public Diagnostico getDiagnosticoReferente() {
        return diagnosticoReferente;
    }

    public void setDiagnosticoReferente(Diagnostico diagnosticoReferente) {
        this.diagnosticoReferente = diagnosticoReferente;
    }

    public int getVacantesTotales() {
        return vacantesTotales;
    }

    public void setVacantesTotales(int vacantesTotales) {
        this.vacantesTotales = vacantesTotales;
    }

    public int getVacantesDisponibles() {
        return vacantesDisponibles;
    }

    public void setVacantesDisponibles(int vacantesDisponibles) {
        this.vacantesDisponibles = vacantesDisponibles;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
