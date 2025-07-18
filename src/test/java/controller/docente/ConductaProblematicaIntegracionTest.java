package controller.docente;

import controller.docente.IncidenteCtrl;
import dao.funcionalidad.ConductaProblematicaImp;
import dao.funcionalidad.FichaAbcDao;
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

public class ConductaProblematicaIntegracionTest {

    ConductaProblematicaImp daoReal = new ConductaProblematicaImp();

    class FichaAbcDaoStub implements FichaAbcDao {
        @Override public boolean guardarFichaAbc(int idEstudiante, int idAntecedente, String comportamiento, String consecuencia, int gravedad) { return false; }
        @Override public java.util.List<model.funcionalidad.FichaAbc> obtenerFichasPorEstudiante(int idEstudiante) { return java.util.Collections.emptyList(); }
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
    public void testRegistroRealConductaProblematica() {
        IncidenteCtrl ctrl = new IncidenteCtrl(daoReal, new FichaAbcDaoStub(), new CatalogoDaoStub());

        // Usa datos reales que existan en tu BD
        String resultado = ctrl.guardarConductaProblematica(1, 2, 1, 3, "Interrumpe constantemente en clase");

        assertEquals("Conducta registrada correctamente.", resultado);
    }
}