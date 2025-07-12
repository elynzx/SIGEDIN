package controller.docente;

import dao.funcionalidad.AsistenciaDao;
import model.funcionalidad.Asistencia;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class AsistenciaCtrl {

    private final AsistenciaDao asistenciaDao;

    public AsistenciaCtrl(AsistenciaDao asistenciaDao) {
        this.asistenciaDao = asistenciaDao;
    }

    public List<Asistencia> listarAsistenciasDelDia(int idDocente) {
        return asistenciaDao.obtenerAsistenciasDelDia(idDocente, Date.valueOf(LocalDate.now()));
    }

    public List<Asistencia> listarEstudiantesSinAsistencia(int idDocente) {
        return asistenciaDao.obtenerEstudiantesSinAsistencia(idDocente);
    }

    public void registrarAsistencias(List<Asistencia> lista) {
        asistenciaDao.registrarAsistencias(lista);
    }

    public void modificarAsistencias(List<Asistencia> lista) {
        asistenciaDao.modificarAsistencias(lista);
    }

    public boolean verificarAsistenciasRegistradas(int idDocente) {
        return !listarAsistenciasDelDia(idDocente).isEmpty();
    }
}
