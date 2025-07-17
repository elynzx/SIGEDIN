package model.entidades;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.NivelFuncional;

public class Estudiante extends Persona {

    private int idEstudiante;
    private boolean alergias;
    private String tipoAlergia;
    private boolean tomaMedicamentos;
    private String medicamentos;
    private NivelFuncional nivelFuncional;
    private List<Diagnostico> diagnosticos;
    private Apoderado apoderado;
    private String observaciones;

    public Estudiante(int idEstudiante, String nombres, String apellidos, Date fechaNacimiento,
            String tipoAlergia, String medicamentos, String observaciones,
            List<Diagnostico> diagnosticos) {
        super(nombres, apellidos, fechaNacimiento);
        this.idEstudiante = idEstudiante;
        this.tipoAlergia = tipoAlergia;
        this.medicamentos = medicamentos;
        this.observaciones = observaciones;
        this.diagnosticos = diagnosticos;
    }

    public Estudiante(int idEstudiante, List<Diagnostico> diagnosticos, String nombres, String apellidos) {
        super(nombres, apellidos);
        this.idEstudiante = idEstudiante;
        this.diagnosticos = diagnosticos;
    }

    public Estudiante() {
    }

    public Estudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
    
    

    public Estudiante(int idEstudiante, boolean alergias, String tipoAlergia, boolean tomaMedicamentos, String medicamentos, NivelFuncional nivelFuncional, Apoderado apoderado, String observaciones, int id, String nombres, String apellidos, String dni, String celular, String correo, String direccion, Date fechaNacimiento, String genero) {
        super(id, nombres, apellidos, dni, celular, correo, direccion, fechaNacimiento, genero);
        this.idEstudiante = idEstudiante;
        this.alergias = alergias;
        this.tipoAlergia = tipoAlergia;
        this.tomaMedicamentos = tomaMedicamentos;
        this.medicamentos = medicamentos;
        this.nivelFuncional = nivelFuncional;
        this.apoderado = apoderado;
        this.observaciones = observaciones;
    }

    public Estudiante(int idEstudiante, String nombres, String apellidos) {
        super(nombres, apellidos);
        this.idEstudiante = idEstudiante;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public boolean isAlergias() {
        return alergias;
    }

    public void setAlergias(boolean alergias) {
        this.alergias = alergias;
    }

    public String getTipoAlergia() {
        return tipoAlergia;
    }

    public void setTipoAlergia(String tipoAlergia) {
        this.tipoAlergia = tipoAlergia;
    }

    public boolean isTomaMedicamentos() {
        return tomaMedicamentos;
    }

    public void setTomaMedicamentos(boolean tomaMedicamentos) {
        this.tomaMedicamentos = tomaMedicamentos;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public NivelFuncional getNivelFuncional() {
        return nivelFuncional;
    }

    public void setNivelFuncional(NivelFuncional nivelFuncional) {
        this.nivelFuncional = nivelFuncional;
    }

    public Apoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getEdad() {
        if (getFechaNacimiento() != null) {
            LocalDate nacimiento = getFechaNacimiento().toLocalDate();
            return Period.between(nacimiento, LocalDate.now()).getYears();
        }
        return 0;
    }

    public List<Diagnostico> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(List<Diagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    @Override
    public String toString() {
        return getNombres() + " " + getApellidos();
    }

}
