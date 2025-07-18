package controller.docente;

import dao.funcionalidad.PlanIndividualDao;
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

public class PlanIndividualTest {

    class PlanIndividualDaoStub implements PlanIndividualDao {
        @Override
        public boolean registrarPlanIntervencion(int idEstudiante, int idConducta, int idFuncion, String objetivo, int idEstrategia, boolean implementado, String observaciones) {
            return idEstudiante == 1 && idConducta == 2 && idFuncion == 1 && !objetivo.isBlank() && idEstrategia == 3 && !observaciones.isBlank();
        }

        @Override public java.util.List<model.funcionalidad.PlanIntervencion> obtenerHistorialPlanes(int idEstudiante) { return java.util.Collections.emptyList(); }
        @Override public java.util.List<model.funcionalidad.ResumenIncidentes> obtenerResumenIncidentes(int idEstudiante) { return java.util.Collections.emptyList(); }
        @Override public java.util.List<model.funcionalidad.catalogo.TipoConducta> obtenerTipoConductasEstudiante(int idEstudiante) { return java.util.Collections.emptyList(); }
        @Override public model.funcionalidad.catalogo.FuncionComportamiento obtenerUltimaFuncionComportamiento(int idEstudiante, int idConducta) { return null; }
        @Override public boolean verificarPlanExistente(int idEstudiante, int idConducta, int idEstrategia) { return false; }
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
        // Métodos no implementados porque no se usan en esta prueba
    }

    @Test
    public void testRegistrarPlanIntervencionValido() {
        PlanIndividualCtrl ctrl = new PlanIndividualCtrl(new PlanIndividualDaoStub(), new CatalogoDaoStub());
        String resultado = ctrl.registrarPlanIntervencion(1, 2, 1, "Reducir llanto", 3, true, "Aplicar refuerzo positivo");
        assertEquals("Plan de Intervención guardado correctamente.", resultado);
    }

    @Test
    public void testRegistrarPlanIntervencionObjetivoVacio() {
        PlanIndividualCtrl ctrl = new PlanIndividualCtrl(new PlanIndividualDaoStub(), new CatalogoDaoStub());
        String resultado = ctrl.registrarPlanIntervencion(1, 2, 1, "", 3, true, "Aplicar refuerzo positivo");
        assertEquals("Debe ingresar el objetivo del plan.", resultado);
    }

    @Test
    public void testRegistrarPlanIntervencionObservacionesVacias() {
        PlanIndividualCtrl ctrl = new PlanIndividualCtrl(new PlanIndividualDaoStub(), new CatalogoDaoStub());
        String resultado = ctrl.registrarPlanIntervencion(1, 2, 1, "Reducir llanto", 3, true, "");
        assertEquals("Debe ingresar más detalles de la estrategia de intervención.", resultado);
    }
}