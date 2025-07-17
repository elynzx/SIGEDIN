package controller.docente;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import dao.DocenteDao;
import dao.EstudianteDao;
import dao.funcionalidad.IncidenteDao;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.entidades.Aula;
import model.entidades.Estudiante;
import org.apache.commons.lang3.StringUtils;

public class DashboardDocenteCtrl {

    private final DocenteDao docenteDao;
    private final EstudianteDao estudianteDao;
    private final IncidenteDao incidenteDao;

    public DashboardDocenteCtrl(DocenteDao docenteDao, EstudianteDao estudianteDao, IncidenteDao incidenteDao) {
        this.docenteDao = docenteDao;
        this.estudianteDao = estudianteDao;
        this.incidenteDao = incidenteDao;
    }

    public List<String> obtenerActividadIncidentesRegistrados(int idDocente) {
        Preconditions.checkArgument(idDocente > 0, "ID de docente inválido");
        return incidenteDao.obtenerActividadIncidentesRegistrados(idDocente);
    }

    public Aula obtenerDatosAula(int idDocente) {
        Preconditions.checkArgument(idDocente > 0, "ID de docente inválido");
        return docenteDao.obtenerDatosAula(idDocente);
    }

    public List<Estudiante> obtenerListaEstudiantes(int idDocente) {
        Preconditions.checkArgument(idDocente > 0, "ID de docente inválido");
        return ImmutableList.copyOf(estudianteDao.obtenerListaEstudiantes(idDocente));
    }

    public List<String> obtenerEstudiantesConIncidentes(int idDocente) {
        Map<Integer, Integer> mapaConductas = incidenteDao.obtenerTopEstudiantesConConductasProblematicas(idDocente);
        Map<Integer, Integer> mapaFichas = incidenteDao.obtenerTopEstudiantesConFichasABC(idDocente);

        Set<Integer> idsUnicos = new HashSet<>();
        idsUnicos.addAll(mapaConductas.keySet());
        idsUnicos.addAll(mapaFichas.keySet());

        List<String> lista = new ArrayList<>();
        for (Integer id : idsUnicos) {
            Estudiante est = estudianteDao.obtenerEstudiantePorId(id);
            if (est != null) {
                int conductas = mapaConductas.getOrDefault(id, 0);
                int fichas = mapaFichas.getOrDefault(id, 0);

                String nombre = est.getApellidos() + ", " + est.getNombres();
                String alerta = String.format("%s | Conductas: %d | Fichas ABC: %d", nombre, conductas, fichas);
                lista.add(alerta);
            }
        }
        return lista;
    }

    // == metricas ==
    public Map<String, Integer> obtenerConductasPorTipo(int idEstudiante) {
        return incidenteDao.obtenerConductasPorTipo(idEstudiante);
    }

    public Map<String, Integer> obtenerFichasABCporAntecedente(int idEstudiante) {
        return incidenteDao.obtenerFichasABCporAntecedente(idEstudiante);
    }

    public Map<String, Integer> obtenerConductasPorMes(int idEstudiante) {
        return incidenteDao.obtenerConductasProblematicasPorMes(idEstudiante);
    }

    public Map<String, Object> obtenerResumenABCyConductas(int idEstudiante) {
        return incidenteDao.obtenerResumenIncidentes(idEstudiante); // Este método ya existe
    }

    public String obtenerNombreEstudiante(int idEstudiante) {
        Estudiante est = estudianteDao.obtenerEstudiantePorId(idEstudiante);
        return est != null
                ? StringUtils.capitalize(est.getNombres()) + " " + StringUtils.capitalize(est.getApellidos())
                : "Estudiante desconocido";
    }
}
