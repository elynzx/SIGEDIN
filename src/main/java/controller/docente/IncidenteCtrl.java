/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.docente;

import com.google.common.base.Preconditions;
import dao.funcionalidad.CatalogoDao;
import dao.funcionalidad.ConductaProblematicaDao;
import dao.funcionalidad.FichaAbcDao;
import java.util.List;
import model.funcionalidad.ConductaProblematica;
import model.funcionalidad.FichaAbc;
import model.funcionalidad.catalogo.Antecedente;
import model.funcionalidad.catalogo.FuncionComportamiento;
import model.funcionalidad.catalogo.TipoConducta;

public class IncidenteCtrl {

    private final ConductaProblematicaDao conductaProblematicaDao;
    private final FichaAbcDao fichaAbcDao;
    private final CatalogoDao catalogoDao;

    public IncidenteCtrl(ConductaProblematicaDao conductaProblematicaDao, FichaAbcDao fichaAbcDao, CatalogoDao catalogoDao) {
        this.conductaProblematicaDao = conductaProblematicaDao;
        this.fichaAbcDao = fichaAbcDao;
        this.catalogoDao = catalogoDao;
    }

    public List<ConductaProblematica> obtenerHistorialConductas(int idEstudiante) {
        Preconditions.checkArgument(idEstudiante > 0, "ID de estudiante inválido");
        return conductaProblematicaDao.obtenerConductasPorEstudiante(idEstudiante);
    }

    public List<FichaAbc> obtenerHistorialFichas(int idEstudiante) {
        Preconditions.checkArgument(idEstudiante > 0, "ID de estudiante inválido");
        return fichaAbcDao.obtenerFichasPorEstudiante(idEstudiante);
    }

    public List<TipoConducta> obtenerTipoConductas() {
        return catalogoDao.obtenerTipoConductas();
    }

    public List<FuncionComportamiento> obtenerFuncionComportamientos() {
        return catalogoDao.obtenerFuncionComportamientos();
    }

    public List<Antecedente> obtenerAntecedentes() {
        return catalogoDao.obtenerAntecedentes();
    }

    public String guardarConductaProblematica(int idEstudiante, int idConducta, int idFuncionComportamiento, int gravedad, String descripcion) {
        if (idEstudiante <= 0) {
            return "ID del estudiante no válido.";
        }
        if (idConducta <= 0) {
            return "Debes seleccionar un tipo de conducta.";
        }
        if (idFuncionComportamiento <= 0) {
            return "Debes seleccionar una función del comportamiento.";
        }
        if (descripcion == null || descripcion.isBlank()) {
            return "Ingresa una descripción del comportamiento.";
        }
        if (gravedad <= 0) {
            return "Debes seleccionar la gravedad.";
        }

        boolean guardado = conductaProblematicaDao.guardarConductaProblematica(idEstudiante, idConducta, idFuncionComportamiento, gravedad, descripcion);
        return guardado ? "Conducta registrada correctamente." : "Error al registrar la conducta.";
    }

    public String guardarFichaAbc(int idEstudiante, int idAntecedente, String comportamiento, String consecuencia, int gravedad) {
        if (idEstudiante <= 0) {
            return "ID del estudiante no válido.";
        }
        if (idAntecedente <= 0) {
            return "Debes seleccionar un antecedente.";
        }
        if (comportamiento == null || comportamiento.isBlank()) {
            return "Ingresa el comportamiento observado.";
        }
        if (consecuencia == null || consecuencia.isBlank()) {
            return "Ingresa la consecuencia.";
        }
        if (gravedad <= 0) {
            return "Debes seleccionar la gravedad.";
        }

        boolean guardado = fichaAbcDao.guardarFichaAbc(idEstudiante, idAntecedente, comportamiento, consecuencia, gravedad);
        return guardado ? "Ficha ABC guardada correctamente." : "Error al guardar la ficha ABC.";
    }

}

//    public boolean guardarConductaProblematica(int idEstudiante, int idConducta, int idFuncionComportamiento, int gravedad, String descripcion) {
//        Preconditions.checkArgument(idEstudiante > 0, "ID de estudiante inválido");
//        Preconditions.checkArgument(idConducta > 0, "ID de categoría inválido");
//        Preconditions.checkArgument(idFuncionComportamiento > 0, "ID de función de comportamiento inválido");
//        Preconditions.checkArgument(gravedad > 0, "Gravedad inválida");
//
//        return conductaProblematicaDao.guardarConductaProblematica(idEstudiante, idConducta, idFuncionComportamiento, gravedad, descripcion);
//    }
//
//    public boolean guardarFichaAbc(int idEstudiante, int idAntecedente, String comportamiento, String consecuencia, int gravedad) {
//        Preconditions.checkArgument(idEstudiante > 0, "ID de estudiante inválido");
//        Preconditions.checkArgument(idAntecedente > 0, "ID de antecedente inválido");
//        Preconditions.checkArgument(gravedad > 0, "Gravedad inválida");
//        Preconditions.checkArgument(comportamiento != null && !comportamiento.isBlank(), "Comportamiento requerido");
//        Preconditions.checkArgument(consecuencia != null && !consecuencia.isBlank(), "Consecuencia requerida");
//
//        return fichaAbcDao.guardarFichaAbc(idEstudiante, idAntecedente, comportamiento, consecuencia, gravedad);
//    }
