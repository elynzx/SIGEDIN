package controller.secretaria;

import dao.AulaDao;
import dao.EstudianteDao;
import dao.ApoderadoDao;
import dao.funcionalidad.CatalogoDao;
import dao.funcionalidad.MatriculaDao;
import model.entidades.Aula;
import model.entidades.Estudiante;
import model.entidades.Apoderado;
import model.funcionalidad.Matricula;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.NivelFuncional;

import java.util.List;

public class MatriculaCtrl {

    private final EstudianteDao estudianteDao;
    private final ApoderadoDao apoderadoDao;
    private final CatalogoDao catalogoDao;
    private final AulaDao aulaDao;
    private final MatriculaDao matriculaDao;

    public MatriculaCtrl(EstudianteDao estudianteDao, ApoderadoDao apoderadoDao,
            CatalogoDao catalogoDao, AulaDao aulaDao, MatriculaDao matriculaDao) {
        this.estudianteDao = estudianteDao;
        this.apoderadoDao = apoderadoDao;
        this.catalogoDao = catalogoDao;
        this.aulaDao = aulaDao;
        this.matriculaDao = matriculaDao;
    }

    public Estudiante buscarEstudiantePorDNI(String dni) {
        return estudianteDao.obtenerEstudiantePorDNI(dni);
    }

    public List<Diagnostico> obtenerDiagnosticos() {
        return catalogoDao.obtenerDiagnosticos();
    }

    public List<NivelFuncional> obtenerNivelesFuncionales() {
        return catalogoDao.obtenerNivelesFuncionales();
    }
    
    public List<Aula> filtrarAulas(int idNivelFuncional, List<Integer> idsDiagnosticos) {
        return aulaDao.filtrarAulasPorNivelYDiagnostico(idNivelFuncional, idsDiagnosticos);
    }

    public boolean registrarMatricula(Estudiante estudiante, Apoderado apoderado,
            List<Integer> idsDiagnosticos, int idAula) {
        return matriculaDao.registrarMatriculaCompleta(estudiante, apoderado, idsDiagnosticos, idAula);
    }

    public Matricula obtenerMatriculaPorEstudiante(int idEstudiante) {
        return matriculaDao.obtenerMatriculaPorEstudiante(idEstudiante);
    }

}
