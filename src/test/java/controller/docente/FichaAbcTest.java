package controller.docente;

import dao.funcionalidad.FichaAbcDao;
import dao.funcionalidad.ConductaProblematicaDao;
import dao.funcionalidad.CatalogoDao;
import java.util.List;
import model.funcionalidad.catalogo.Antecedente;
import model.funcionalidad.catalogo.CategoriaConducta;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.EstrategiaIntervencion;
import model.funcionalidad.catalogo.FuncionComportamiento;
import model.funcionalidad.catalogo.NivelFuncional;
import model.funcionalidad.catalogo.TipoConducta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FichaAbcTest {

    // Stub para FichaAbcDao
    class FichaAbcDaoStub implements FichaAbcDao {

        @Override
        public boolean guardarFichaAbc(int idEstudiante, int idAntecedente, String comportamiento, String consecuencia, int gravedad) {
            return idEstudiante == 1 && idAntecedente == 2 && !comportamiento.isBlank() && !consecuencia.isBlank() && gravedad == 3;
        }

        @Override
        public java.util.List<model.funcionalidad.FichaAbc> obtenerFichasPorEstudiante(int idEstudiante) {
            return java.util.Collections.emptyList(); // No se usa en esta prueba
        }
    }

    // Stub para ConductaProblematicaDao (no usado aquí)
    class ConductaProblematicaDaoStub implements ConductaProblematicaDao {

        @Override
        public boolean guardarConductaProblematica(int idEstudiante, int idConducta, int idFuncionComportamiento, int gravedad, String descripcion) {
            return false;
        }

        @Override
        public java.util.List<model.funcionalidad.ConductaProblematica> obtenerConductasPorEstudiante(int idEstudiante) {
            return java.util.Collections.emptyList();
        }
    }

    // Stub para CatalogoDao (no usado aquí)
    class CatalogoDaoStub implements CatalogoDao {

        @Override
        public List<TipoConducta> obtenerTipoConductas() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<FuncionComportamiento> obtenerFuncionComportamientos() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<Antecedente> obtenerAntecedentes() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<CategoriaConducta> ObtenerCategoriaConductas() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<EstrategiaIntervencion> obtenerEstrategiasIntervencion() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<Diagnostico> obtenerDiagnosticos() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<NivelFuncional> obtenerNivelesFuncionales() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        // Métodos no implementados porque no se usan en esta prueba
    }

    @Test
    public void testGuardarFichaAbcValida() {
        IncidenteCtrl ctrl = new IncidenteCtrl(new ConductaProblematicaDaoStub(), new FichaAbcDaoStub(), new CatalogoDaoStub());
        String resultado = ctrl.guardarFichaAbc(1, 2, "Llanto", "Evita tarea", 3);
        assertEquals("Ficha ABC guardada correctamente.", resultado);
    }

    @Test
    public void testGuardarFichaAbcComportamientoVacio() {
        IncidenteCtrl ctrl = new IncidenteCtrl(new ConductaProblematicaDaoStub(), new FichaAbcDaoStub(), new CatalogoDaoStub());
        String resultado = ctrl.guardarFichaAbc(1, 2, "", "Evita tarea", 3);
        assertEquals("Ingresa el comportamiento observado.", resultado);
    }

    @Test
    public void testGuardarFichaAbcConsecuenciaVacia() {
        IncidenteCtrl ctrl = new IncidenteCtrl(new ConductaProblematicaDaoStub(), new FichaAbcDaoStub(), new CatalogoDaoStub());
        String resultado = ctrl.guardarFichaAbc(1, 2, "Llanto", "", 3);
        assertEquals("Ingresa la consecuencia.", resultado);
    }
}
