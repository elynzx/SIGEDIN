package controller.docente;

import dao.funcionalidad.CatalogoDao;
import dao.funcionalidad.SeguimientoDao;
import model.funcionalidad.catalogo.CategoriaConducta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import model.funcionalidad.catalogo.Antecedente;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.EstrategiaIntervencion;
import model.funcionalidad.catalogo.FuncionComportamiento;
import model.funcionalidad.catalogo.NivelFuncional;
import model.funcionalidad.catalogo.TipoConducta;

public class SeguimientoCtrlTest {

    class SeguimientoDaoStub implements SeguimientoDao {

        @Override
        public boolean guardarSeguimiento(int idEstudiante, int idCategoria, String descripcion, int frecuencia, String observaciones) {
            return idEstudiante == 1 && idCategoria == 1;
        }

        @Override
        public List<Map<String, Object>> obtenerPromedioFrecuencia(int idEstudiante) {
            return List.of(Map.of("categoria", "Juego", "promedio", 4.2));
        }
    }

    class CatalogoDaoStub implements CatalogoDao {

        @Override
        public List<CategoriaConducta> ObtenerCategoriaConductas() {
            return List.of(
                    new CategoriaConducta(1, "Socialización"),
                    new CategoriaConducta(2, "Juego")
            );
        }

        @Override
        public List<TipoConducta> obtenerTipoConductas() {
            return List.of(
                    new TipoConducta(1, "Agresión física"),
                    new TipoConducta(2, "Gritos o llanto")
            );
        }

        @Override
        public List<FuncionComportamiento> obtenerFuncionComportamientos() {
            return List.of(
                    new FuncionComportamiento(1, "Atención"),
                    new FuncionComportamiento(2, "Escape")
            );
        }

        @Override
        public List<Antecedente> obtenerAntecedentes() {
            return List.of(
                    new Antecedente(1, "Transición entre actividades"),
                    new Antecedente(2, "Demanda académica")
            );
        }

        @Override
        public List<EstrategiaIntervencion> obtenerEstrategiasIntervencion() {
            return List.of(
                    new EstrategiaIntervencion(1, "Reforzamiento positivo"),
                    new EstrategiaIntervencion(2, "Economía de fichas")
            );
        }

        @Override
        public List<Diagnostico> obtenerDiagnosticos() {
            return List.of(
                    new Diagnostico(1, "Autismo"),
                    new Diagnostico(2, "Asperger")
            );
        }

        @Override
        public List<NivelFuncional> obtenerNivelesFuncionales() {
            return List.of(
                    new NivelFuncional(1, "Bajo"),
                    new NivelFuncional(2, "Medio"),
                    new NivelFuncional(3, "Alto")
            );
        }
    }

    @Test
    public void testRegistrarSeguimientoValido() {
        SeguimientoCtrl ctrl = new SeguimientoCtrl(new SeguimientoDaoStub(), new CatalogoDaoStub());

        String resultado = ctrl.registrarSeguimiento(1, 1, "Participa activamente", 3, "Muy colaborador");
        assertEquals("Seguimiento conductual guardado correctamente.", resultado);
    }

    @Test
    public void testRegistrarSeguimientoEstudianteInvalido() {
        SeguimientoCtrl ctrl = new SeguimientoCtrl(new SeguimientoDaoStub(), new CatalogoDaoStub());

        String resultado = ctrl.registrarSeguimiento(0, 1, "Participa", 3, "Observación");
        assertEquals("ID del estudiante no válido.", resultado);
    }

    @Test
    public void testObtenerCategorias() {
        SeguimientoCtrl ctrl = new SeguimientoCtrl(new SeguimientoDaoStub(), new CatalogoDaoStub());

        List<CategoriaConducta> categorias = ctrl.obtenerCategorias();
        assertEquals(2, categorias.size());
        assertEquals("Socialización", categorias.get(0).getNombre());
    }

    @Test
    public void testObtenerPromediosSeguimiento() {
        SeguimientoCtrl ctrl = new SeguimientoCtrl(new SeguimientoDaoStub(), new CatalogoDaoStub());

        List<Map<String, Object>> promedios = ctrl.obtenerPromediosSeguimiento(1);
        assertEquals(1, promedios.size());
        assertEquals("Juego", promedios.get(0).get("categoria"));
        assertEquals(4.2, promedios.get(0).get("promedio"));
    }
}
