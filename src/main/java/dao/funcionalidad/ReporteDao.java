package dao.funcionalidad;

import java.util.Map;
import model.entidades.Reporte;

public interface ReporteDao {

    void registrarReporte(Reporte reporte);

    public Map<String, Object> obtenerDatosConductas(int idEstudiante);

    public Map<String, Object> obtenerDatosSeguimientoConductual(int idEstudiante);

    public Map<String, Object> obtenerDatosPlanActual(int idEstudiante);

    public Map<String, Object> obtenerDatosAsistencia(int idEstudiante);

    public Map<String, Object> obtenerDatosPersonalesBasicos(int idEstudiante);

}
