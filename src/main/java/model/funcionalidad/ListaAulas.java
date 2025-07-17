package model.funcionalidad;

public class ListaAulas {
    private int idAula;
    private String nombre;
    private String nivelFuncional;
    private String diagnostico;
    private int vacantesTotales;
    private int vacantesDisponibles;
    private String dniDocente;

    public ListaAulas(int idAula, String nombre, String nivelFuncional, String diagnostico,int vacantesTotales, int vacantesDisponibles, String dniDocente) {
        this.idAula = idAula;
        this.nombre = nombre;
        this.nivelFuncional = nivelFuncional;
        this.diagnostico = diagnostico;
        this.vacantesTotales = vacantesTotales;
        this.vacantesDisponibles = vacantesDisponibles;
        this.dniDocente = dniDocente;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivelFuncional() {
        return nivelFuncional;
    }

    public void setNivelFuncional(String nivelFuncional) {
        this.nivelFuncional = nivelFuncional;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
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

    public String getDniDocente() {
        return dniDocente;
    }

    public void setDniDocente(String dniDocente) {
        this.dniDocente = dniDocente;
    }
    
    public String[] toArray() {
            return new String[] {
            nombre, // getNombre()
            nivelFuncional,
            diagnostico,
            dniDocente,
            String.valueOf(vacantesTotales),
            String.valueOf(vacantesDisponibles)
        };
    }
    
    
}