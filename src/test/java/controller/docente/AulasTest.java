package controller.docente;

import controller.secretaria.DashboardSecretariaCtrl;
import dao.AulaDao;
import dao.EstudianteDao;
import dao.funcionalidad.MatriculaDao;
import model.entidades.Aula;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.NivelFuncional;
import model.entidades.Docente;
import model.entidades.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.util.List;
import model.entidades.Estudiante;

public class AulasTest {

    class AulaDaoStubConDatos implements AulaDao {

        @Override
        public List<Aula> obtenerTodasLasAulas() {
            NivelFuncional nivel = new NivelFuncional(1, "Moderado");
            Diagnostico diagnostico = new Diagnostico(1, "TEA");
            Usuario usuario = new Usuario();
            Date fechaNacimiento = Date.valueOf("1990-05-10");

            Docente docente = new Docente(
                    1, usuario, 100, "Ana", "Pérez", "12345678", "987654321",
                    "ana.perez@colegio.edu.pe", "Av. Siempre Viva 123", fechaNacimiento, "Femenino"
            );

            Aula aula = new Aula();
            aula.setNombre("Aula Azul");
            aula.setNivelFuncional(nivel);
            aula.setDiagnosticoReferente(diagnostico);
            aula.setDocente(docente);
            aula.setVacantesTotales(10);
            aula.setVacantesDisponibles(3);

            return List.of(aula);
        }

        // Métodos no usados
        @Override
        public int contarVacantesDisponibles() {
            return 0;
        }

        @Override
        public int contarAulasConVacantes() {
            return 0;
        }

        @Override
        public List<Aula> filtrarAulasPorNivelYDiagnostico(int idNivelFuncional, List<Integer> idsDiagnosticos) {
            return null;
        }

        @Override
        public List<Aula> obtenerAulasPorNivelSinFiltrar(int idNivelFuncional) {
            return null;
        }
    }

    // Stub para prueba 2: lista vacía
    class AulaDaoStubVacio implements AulaDao {

        @Override
        public List<Aula> obtenerTodasLasAulas() {
            return List.of();
        }

        @Override
        public int contarVacantesDisponibles() {
            return 0;
        }

        @Override
        public int contarAulasConVacantes() {
            return 0;
        }

        @Override
        public List<Aula> filtrarAulasPorNivelYDiagnostico(int idNivelFuncional, List<Integer> idsDiagnosticos) {
            return null;
        }

        @Override
        public List<Aula> obtenerAulasPorNivelSinFiltrar(int idNivelFuncional) {
            return null;
        }
    }

    // Stub para prueba 3: aula con atributos nulos
    class AulaDaoStubConNulos implements AulaDao {

        @Override
        public List<Aula> obtenerTodasLasAulas() {
            Aula aula = new Aula();
            aula.setNombre(null);
            aula.setNivelFuncional(null);
            aula.setDiagnosticoReferente(null);
            aula.setDocente(null);
            aula.setVacantesTotales(0);
            aula.setVacantesDisponibles(0);
            return List.of(aula);
        }

        @Override
        public int contarVacantesDisponibles() {
            return 0;
        }

        @Override
        public int contarAulasConVacantes() {
            return 0;
        }

        @Override
        public List<Aula> filtrarAulasPorNivelYDiagnostico(int idNivelFuncional, List<Integer> idsDiagnosticos) {
            return null;
        }

        @Override
        public List<Aula> obtenerAulasPorNivelSinFiltrar(int idNivelFuncional) {
            return null;
        }
    }

    // Stubs vacíos para MatriculaDao y EstudianteDao
    class MatriculaDaoStub implements MatriculaDao {

        @Override
        public int contarMatriculasActivas() {
            return 0;
        }

        @Override
        public int contarMatriculasPorFechaActual() {
            return 0;
        }

        @Override
        public org.jfree.data.category.DefaultCategoryDataset generarGraficoMatriculaPorAula() {
            return null;
        }

        @Override
        public model.funcionalidad.Matricula obtenerMatriculaPorEstudiante(int idEstudiante) {
            return null;
        }

        @Override
        public void registrarMatricula(model.funcionalidad.Matricula matricula) {
        }

        @Override
        public void actualizarMatricula(model.funcionalidad.Matricula matricula) {
        }

        @Override
        public void cambiarEstadoMatricula(int idMatricula, String nuevoEstado) {
        }
    }

    class EstudianteDaoStub implements EstudianteDao {

        @Override
        public model.entidades.Estudiante obtenerEstudiantePorId(int idEstudiante) {
            return null;
        }

        @Override
        public model.entidades.Estudiante obtenerEstudiantePorDNI(String dni) {
            return null;
        }

        @Override
        public List<model.entidades.Estudiante> obtenerListaEstudiantes(int idDocente) {
            return null;
        }

        @Override
        public org.jfree.data.category.DefaultCategoryDataset generarGraficoMatriculaPorDiagnostico() {
            return null;
        }

        @Override
        public void registrarEstudiante(model.entidades.Estudiante estudiante) {
        }

        @Override
        public void actualizarEstudiante(model.entidades.Estudiante estudiante) {
        }

        @Override
        public int obtenerIdPorDNI(String dni) {
            return 0;
        }

        @Override
        public List<Estudiante> listarEstudiantesParaVistaMatricula() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    @Test
    public void testObtenerAulasConDatos() {
        DashboardSecretariaCtrl ctrl = new DashboardSecretariaCtrl(new MatriculaDaoStub(), new AulaDaoStubConDatos(), new EstudianteDaoStub());
        List<Aula> aulas = ctrl.obtenerAulas();
        assertEquals(1, aulas.size());
        Aula aula = aulas.get(0);
        assertEquals("Aula Azul", aula.getNombre());
        assertEquals("Moderado", aula.getNivelFuncional().getNombre());
        assertEquals("TEA", aula.getDiagnosticoReferente().getNombre());
        assertEquals("Ana Pérez", aula.getDocente().getNombreCompleto());
        assertEquals(10, aula.getVacantesTotales());
        assertEquals(3, aula.getVacantesDisponibles());
    }

    @Test
    public void testObtenerAulasVacio() {
        DashboardSecretariaCtrl ctrl = new DashboardSecretariaCtrl(new MatriculaDaoStub(), new AulaDaoStubVacio(), new EstudianteDaoStub());
        List<Aula> aulas = ctrl.obtenerAulas();
        assertTrue(aulas.isEmpty());
    }

    @Test
    public void testObtenerAulasConNulos() {
        DashboardSecretariaCtrl ctrl = new DashboardSecretariaCtrl(new MatriculaDaoStub(), new AulaDaoStubConNulos(), new EstudianteDaoStub());
        List<Aula> aulas = ctrl.obtenerAulas();
        assertEquals(1, aulas.size());
        Aula aula = aulas.get(0);
        assertNull(aula.getNombre());
        assertNull(aula.getNivelFuncional());
        assertNull(aula.getDiagnosticoReferente());
        assertNull(aula.getDocente());
        assertEquals(0, aula.getVacantesTotales());
        assertEquals(0, aula.getVacantesDisponibles());
    }
}
