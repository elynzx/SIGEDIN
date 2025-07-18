package controller.docente;

import dao.funcionalidad.SeguimientoDao;
import dao.funcionalidad.CatalogoDao;
import java.util.List;
import java.util.Map;
import model.funcionalidad.catalogo.Antecedente;
import model.funcionalidad.catalogo.CategoriaConducta;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.EstrategiaIntervencion;
import model.funcionalidad.catalogo.FuncionComportamiento;
import model.funcionalidad.catalogo.NivelFuncional;
import model.funcionalidad.catalogo.TipoConducta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SeguimientoConductualTest {


    class SeguimientoDaoStub implements SeguimientoDao {

        @Override
        public boolean guardarSeguimiento(int idEstudiante, int idCategoria, String descripcion, int frecuencia, String observaciones) {
            return idEstudiante == 1 && idCategoria == 1 && !descripcion.isBlank() && frecuencia > 0;
        }

        @Override
        public List<Map<String, Object>> obtenerPromedioFrecuencia(int idEstudiante) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
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
    public void testRegistrarSeguimientoValido() {
        SeguimientoCtrl ctrl = new SeguimientoCtrl(new SeguimientoDaoStub(), new CatalogoDaoStub());
        String resultado = ctrl.registrarSeguimiento(1, 1, "Participa activamente", 3, "Muy colaborador");
        assertEquals("Seguimiento conductual guardado correctamente.", resultado);
    }

    @Test
    public void testRegistrarSeguimientoIdEstudianteInvalido() {
        SeguimientoCtrl ctrl = new SeguimientoCtrl(new SeguimientoDaoStub(), new CatalogoDaoStub());
        String resultado = ctrl.registrarSeguimiento(0, 1, "Participa", 3, "Colabora");
        assertEquals("ID del estudiante no válido.", resultado);
    }

    @Test
    public void testRegistrarSeguimientoDescripcionVacia() {
        SeguimientoCtrl ctrl = new SeguimientoCtrl(new SeguimientoDaoStub(), new CatalogoDaoStub());
        String resultado = ctrl.registrarSeguimiento(1, 1, "", 3, "Colabora");
        assertEquals("Debe ingresar una descripción de la conducta analizada.", resultado);
    }
}
