package dao.funcionalidad;
import model.funcionalidad.Matricula;
import java.util.List;
import model.entidades.Apoderado;
import model.entidades.Estudiante;
import org.jfree.data.category.DefaultCategoryDataset;

public interface MatriculaDao {

    int contarMatriculasActivas();

    int contarMatriculasPorFechaActual();

    DefaultCategoryDataset generarGraficoMatriculaPorAula();

    boolean registrarMatriculaCompleta(Estudiante estudiante, Apoderado apoderado,
            List<Integer> idsDiagnosticos, int idAula);

    Matricula obtenerMatriculaPorEstudiante(int idEstudiante);
}
