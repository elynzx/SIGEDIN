package dao.funcionalidad;

import model.funcionalidad.Matricula;
import org.jfree.data.category.DefaultCategoryDataset;

public interface MatriculaDao {

    int contarMatriculasActivas();

    int contarMatriculasPorFechaActual();

    DefaultCategoryDataset generarGraficoMatriculaPorAula();

    Matricula obtenerMatriculaPorEstudiante(int idEstudiante);

    void registrarMatricula(Matricula matricula);

    void actualizarMatricula(Matricula matricula);

    void cambiarEstadoMatricula(int idMatricula, String nuevoEstado);

}
