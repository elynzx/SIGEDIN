/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entidades;

import model.funcionalidad.catalogo.TipoReporte;
import java.sql.Date;

/**
 *
 * @author rpasc
 */
public class Reporte {

    private int id;
    private TipoReporte tipoReporte;
    private String criterioFiltro;
    private Estudiante estudiante;
    private Aula aula;
    private Date fechaGeneracion;
    private Usuario usuario;

    public Reporte(int id, TipoReporte tipoReporte, String criterioFiltro, Estudiante estudiante, Aula aula, Date fechaGeneracion, Usuario usuario) {
        this.id = id;
        this.tipoReporte = tipoReporte;
        this.criterioFiltro = criterioFiltro;
        this.estudiante = estudiante;
        this.aula = aula;
        this.fechaGeneracion = fechaGeneracion;
        this.usuario = usuario;
    }

    public Reporte() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoReporte getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(TipoReporte tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getCriterioFiltro() {
        return criterioFiltro;
    }

    public void setCriterioFiltro(String criterioFiltro) {
        this.criterioFiltro = criterioFiltro;
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

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
