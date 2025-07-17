package controller.docente;

import dao.funcionalidad.ReporteDao;
import java.io.File;
import java.util.Map;
import javax.swing.JOptionPane;
import model.entidades.*;
import model.funcionalidad.catalogo.TipoReporte;
import utilities.PdfTemplate.ReportePdfUtil;

public class ReporteCtrl {

    private final ReporteDao reporteDao;

    public ReporteCtrl(ReporteDao reporteDao) {
        this.reporteDao = reporteDao;
    }

    public File generarReporteConductas(int idEstudiante, Usuario usuario) {
        Map<String, Object> datos = reporteDao.obtenerDatosConductas(idEstudiante);

        if (!ReportePdfUtil.tieneContenido("conductas", datos)) {
            JOptionPane.showMessageDialog(null, "El estudiante no tiene conductas problemáticas registradas.");
            return null;
        }

        File pdf = ReportePdfUtil.generarPdfConductas(datos);
        registrarReporteGenerado(idEstudiante, 1, "Historial Completo", usuario);
        return pdf;
    }

    public File generarReporteSeguimientoConductual(int idEstudiante, Usuario usuario) {
        Map<String, Object> datos = reporteDao.obtenerDatosSeguimientoConductual(idEstudiante);

        if (!ReportePdfUtil.tieneContenido("seguimientos", datos)) {
            JOptionPane.showMessageDialog(null, "El estudiante no tiene seguimientos registrados.");
            return null;
        }

        File pdf = ReportePdfUtil.generarPdfSeguimientoConductual(datos);
        registrarReporteGenerado(idEstudiante, 2, "Promedio mensual", usuario);
        return pdf;
    }

    public File generarReportePlanActual(int idEstudiante, Usuario usuario) {
        Map<String, Object> datos = reporteDao.obtenerDatosPlanActual(idEstudiante);

        if (!ReportePdfUtil.tieneContenido("plan", datos)) {
            JOptionPane.showMessageDialog(null, "El estudiante no tiene un plan individual registrado.");
            return null;
        }

        File pdf = ReportePdfUtil.generarPdfPlanActual(datos);
        registrarReporteGenerado(idEstudiante, 3, "Plan vigente", usuario);
        return pdf;
    }

    public File generarReporteAsistencia(int idEstudiante, Usuario usuario) {
        Map<String, Object> datos = reporteDao.obtenerDatosAsistencia(idEstudiante);

        if (!ReportePdfUtil.tieneContenido("fechasAsistidas", datos)) {
            JOptionPane.showMessageDialog(null, "El estudiante no tiene asistencias registradas.");
            return null;
        }

        File pdf = ReportePdfUtil.generarPdfAsistencia(datos);
        registrarReporteGenerado(idEstudiante, 4, "Último mes", usuario);
        return pdf;
    }

    private void registrarReporteGenerado(int idEstudiante, int idTipoReporte, String criterio, Usuario usuario) {
        Reporte reporte = new Reporte();
        reporte.setTipoReporte(new TipoReporte(idTipoReporte, null));
        reporte.setCriterioFiltro(criterio);

        Estudiante estudiante = new Estudiante();
        estudiante.setId(idEstudiante);
        reporte.setEstudiante(estudiante);

        Aula aula = new Aula();
        aula.setId(1);
        reporte.setAula(aula);

        reporte.setUsuario(usuario);

        reporteDao.registrarReporte(reporte);
    }

}
