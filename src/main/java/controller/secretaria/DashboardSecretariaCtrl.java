package controller.secretaria;

import dao.AulaDao;
import dao.EstudianteDao;
import dao.funcionalidad.MatriculaDao;
import model.entidades.Aula;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;

public class DashboardSecretariaCtrl {

    private final MatriculaDao matriculaDao;
    private final AulaDao aulaDao;
    private final EstudianteDao estudianteDao;

    public DashboardSecretariaCtrl(MatriculaDao matriculaDao, AulaDao aulaDao, EstudianteDao estudianteDao) {
        this.matriculaDao = matriculaDao;
        this.aulaDao = aulaDao;
        this.estudianteDao = estudianteDao;
    }

    public int obtenerTotalMatriculados() {
        return matriculaDao.contarMatriculasActivas();
    }

    public int obtenerMatriculasHoy() {
        return matriculaDao.contarMatriculasPorFechaActual();
    }

    public int obtenerVacantesDisponibles() {
        return aulaDao.contarVacantesDisponibles();
    }

    public int obtenerAulasConVacantes() {
        return aulaDao.contarAulasConVacantes();
    }

    public List<Aula> obtenerAulas() {
        return aulaDao.obtenerTodasLasAulas();
    }

    public DefaultCategoryDataset obtenerGraficoMatriculaPorAula() {
        return matriculaDao.generarGraficoMatriculaPorAula();
    }

    public DefaultCategoryDataset obtenerGraficoMatriculaPorDiagnostico() {
        return estudianteDao.generarGraficoMatriculaPorDiagnostico();
    }
}
