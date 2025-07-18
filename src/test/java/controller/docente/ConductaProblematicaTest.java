package controller.docente;

import dao.funcionalidad.ConductaProblematicaDao;
import dao.funcionalidad.FichaAbcDao;
import dao.funcionalidad.CatalogoDao;
import model.funcionalidad.ConductaProblematica;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import model.funcionalidad.FichaAbc;
import model.funcionalidad.catalogo.Antecedente;
import model.funcionalidad.catalogo.CategoriaConducta;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.EstrategiaIntervencion;
import model.funcionalidad.catalogo.FuncionComportamiento;
import model.funcionalidad.catalogo.NivelFuncional;
import model.funcionalidad.catalogo.TipoConducta;

public class ConductaProblematicaTest {

    class ConductaProblematicaDaoStub implements ConductaProblematicaDao {

        @Override
        public boolean guardarConductaProblematica(int idEstudiante, int idConducta, int idFuncionComportamiento, int gravedad, String descripcion) {
            return idEstudiante == 1 && idConducta == 2 && idFuncionComportamiento == 1 && gravedad == 3 && !descripcion.isBlank();
        }

        @Override
        public List<ConductaProblematica> obtenerConductasPorEstudiante(int idEstudiante) {
            ConductaProblematica cp = new ConductaProblematica();

            TipoConducta tipo = new TipoConducta("Gritos");
            FuncionComportamiento funcion = new FuncionComportamiento("Atención");

            cp.setTipo(tipo);
            cp.setFuncion(funcion);
            cp.setDescripcion("Llanto prolongado");
            cp.setGravedad(3);

            return List.of(cp);
        }
    }

    class FichaAbcDaoStub implements FichaAbcDao {

        @Override
        public boolean guardarFichaAbc(int idEstudiante, int idAntecedente, String comportamiento, String consecuencia, int gravedad) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<FichaAbc> obtenerFichasPorEstudiante(int idEstudiante) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        // No se usa en esta prueba
    }

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
    }

    @Test
    public void testGuardarConductaProblematicaValida() {
        IncidenteCtrl ctrl = new IncidenteCtrl(new ConductaProblematicaDaoStub(), new FichaAbcDaoStub(), new CatalogoDaoStub());
        String resultado = ctrl.guardarConductaProblematica(1, 2, 1, 3, "Llanto prolongado");
        assertEquals("Conducta registrada correctamente.", resultado);
    }

    @Test
    public void testGuardarConductaProblematicaDescripcionVacia() {
        IncidenteCtrl ctrl = new IncidenteCtrl(new ConductaProblematicaDaoStub(), new FichaAbcDaoStub(), new CatalogoDaoStub());
        String resultado = ctrl.guardarConductaProblematica(1, 2, 1, 3, "");
        assertEquals("Ingresa una descripción del comportamiento.", resultado);
    }

    @Test
    public void testObtenerHistorialConductas() {
        IncidenteCtrl ctrl = new IncidenteCtrl(new ConductaProblematicaDaoStub(), new FichaAbcDaoStub(), new CatalogoDaoStub());
        List<ConductaProblematica> historial = ctrl.obtenerHistorialConductas(1);
        assertEquals(1, historial.size());
        assertEquals("Gritos", historial.get(0).getTipo().getNombre());
    }

}
