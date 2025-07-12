package controller.docente;

import com.google.common.base.Preconditions;
import dao.funcionalidad.CatalogoDao;
import dao.funcionalidad.PlanIndividualDao;
import model.funcionalidad.PlanIntervencion;
import model.funcionalidad.ResumenIncidentes;
import model.funcionalidad.catalogo.EstrategiaIntervencion;
import model.funcionalidad.catalogo.FuncionComportamiento;
import model.funcionalidad.catalogo.TipoConducta;

import java.util.List;

public class PlanIndividualCtrl {

    private final PlanIndividualDao planIndividualDao;
    private final CatalogoDao catalogoDao;

    public PlanIndividualCtrl(PlanIndividualDao planIndividualDao, CatalogoDao catalogoDao) {
        this.planIndividualDao = planIndividualDao;
        this.catalogoDao = catalogoDao;
    }

    public List<ResumenIncidentes> obtenerResumenIncidentes(int idEstudiante) {
        Preconditions.checkArgument(idEstudiante > 0, "ID de estudiante inválido");
        return planIndividualDao.obtenerResumenIncidentes(idEstudiante);
    }

    public List<PlanIntervencion> obtenerHistorialIntervenciones(int idEstudiante) {
        Preconditions.checkArgument(idEstudiante > 0, "ID de estudiante inválido");
        return planIndividualDao.obtenerHistorialPlanes(idEstudiante);
    }

    public List<TipoConducta> obtenerTipoConductasEstudiante(int idEstudiante) {
        Preconditions.checkArgument(idEstudiante > 0, "ID de estudiante inválido");
        return planIndividualDao.obtenerTipoConductasEstudiante(idEstudiante);
    }

    public FuncionComportamiento obtenerUltimaFuncion(int idEstudiante, int idConducta) {
        if (idEstudiante <= 0 || idConducta <= 0) {
            return null;
        }
        return planIndividualDao.obtenerUltimaFuncionComportamiento(idEstudiante, idConducta);
    }

    public List<EstrategiaIntervencion> obtenerEstrategiasIntervencion() {
        return catalogoDao.obtenerEstrategiasIntervencion();
    }

    public boolean verificarPlanExistente(int idEstudiante, int idConducta, int idEstrategia) {
        if (idEstudiante <= 0 || idConducta <= 0 || idEstrategia <= 0) {
            return false;
        }
        return planIndividualDao.verificarPlanExistente(idEstudiante, idConducta, idEstrategia);
    }

    public String registrarPlanIntervencion(
            int idEstudiante,
            int idConducta,
            int idFuncion,
            String objetivo,
            int idEstrategia,
            boolean implementado,
            String observaciones) {
        if (idEstudiante <= 0) {
            return "ID del estudiante no válido.";
        }
        if (idConducta <= 0) {
            return "Debe seleccionar una conducta.";
        }
        if (idFuncion <= 0) {
            return "No se encontró la función de comportamiento.";
        }
        if (objetivo == null || objetivo.isBlank()) {
            return "Debe ingresar el objetivo del plan.";
        }
        if (idEstrategia <= 0) {
            return "Debe seleccionar una estrategia de intervención.";
        }
        if (observaciones == null || observaciones.isBlank()) {
            return "Debe ingresar más detalles de la estrategia de intervención.";
        }
        boolean guardado = planIndividualDao.registrarPlanIntervencion(
                idEstudiante,
                idConducta,
                idFuncion,
                objetivo,
                idEstrategia,
                implementado,
                observaciones
        );
        return guardado
                ? "Plan de Intervención guardado correctamente."
                : "Error al guardar el Plan de Intervención.";
    }
}
