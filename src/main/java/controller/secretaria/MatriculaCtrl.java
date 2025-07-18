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

    public Matricula obtenerMatriculaPorEstudiante(int idEstudiante) {
        return matriculaDao.obtenerMatriculaPorEstudiante(idEstudiante);
    }

    public Apoderado buscarApoderadoPorDNI(String dni) {
        return apoderadoDao.obtenerApoderadoPorDNI(dni);
    }

    public void registrarApoderado(Apoderado apoderado) {
        apoderadoDao.registrarApoderado(apoderado);
    }

    public void registrarEstudiante(Estudiante estudiante) {
        estudianteDao.registrarEstudiante(estudiante);
    }

    public void registrarMatricula(Matricula matricula) {
        matriculaDao.registrarMatricula(matricula);
    }

    public void actualizarApoderado(Apoderado apoderado) {
        apoderadoDao.actualizarApoderado(apoderado);
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        estudianteDao.actualizarEstudiante(estudiante);
    }

    public void actualizarMatricula(Matricula matricula) {
        matriculaDao.actualizarMatricula(matricula);
    }

    public void cambiarEstadoMatricula(int idMatricula, String nuevoEstado) {
        matriculaDao.cambiarEstadoMatricula(idMatricula, nuevoEstado);
    }


    public int obtenerIdEstudiantePorDNI(String dni) {
        return estudianteDao.obtenerIdPorDNI(dni);
    }

    public List<Aula> obtenerTodasLasAulasPorNivel(int idNivelFuncional) {
        return aulaDao.obtenerAulasPorNivelSinFiltrar(idNivelFuncional);
    }

}
