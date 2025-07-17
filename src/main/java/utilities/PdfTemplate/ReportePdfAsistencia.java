package utilities.PdfTemplate;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import static utilities.PdfTemplate.ReportePdfUtil.*;

public class ReportePdfAsistencia {

    public static File generarReporteAsistencia(String nombreEstudiante, Map<String, Object> datos) {
        Document documento = new Document();
        File archivo = null;

        try {
            @SuppressWarnings("unchecked")
            List<LocalDate> fechasAsistidas = (List<LocalDate>) datos.get("fechasAsistidas");

            if (fechasAsistidas == null || fechasAsistidas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El estudiante no tiene registros de asistencia.");
                return null;
            }

            String nombreArchivo = "reporte_asistencia_" + nombreEstudiante.replaceAll(" ", "_") + ".pdf";
            archivo = new File(nombreArchivo);
            PdfWriter.getInstance(documento, new FileOutputStream(archivo));
            documento.open();

            agregarEncabezadoConLogo(documento, "Reporte de Asistencia");

            documento.add(new Paragraph("Estudiante: " + nombreEstudiante, TEXTO_FONT));
            if (datos.containsKey("nivelFuncional")) {
                documento.add(new Paragraph("Nivel funcional: " + datos.get("nivelFuncional"), TEXTO_FONT));
            }
            if (datos.containsKey("diagnosticos")) {
                documento.add(new Paragraph("Diagnósticos: " + datos.get("diagnosticos"), TEXTO_FONT));
            }
            if (datos.containsKey("nombreAula")) {
                documento.add(new Paragraph("Aula: " + datos.get("nombreAula"), TEXTO_FONT));
            }
            if (datos.containsKey("nombreDocente")) {
                documento.add(new Paragraph("Docente a cargo: " + datos.get("nombreDocente"), TEXTO_FONT));
            }

            LocalDate hoy = LocalDate.now();
            YearMonth mesActual = YearMonth.of(hoy.getYear(), hoy.getMonth());
            LocalDate primerDia = mesActual.atDay(1);
            LocalDate ultimoDia = mesActual.atEndOfMonth();

            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new int[]{1, 4, 3});
            
            tabla.addCell(celdaEncabezado("N°"));
            tabla.addCell(celdaEncabezado("Fecha"));
            tabla.addCell(celdaEncabezado("Asistió"));

            int contador = 1;
            for (LocalDate fecha = primerDia; !fecha.isAfter(ultimoDia); fecha = fecha.plusDays(1)) {
                DayOfWeek dia = fecha.getDayOfWeek();
                if (dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY) {
                    tabla.addCell(String.valueOf(contador++));
                    tabla.addCell(fecha.toString());
                    boolean asistio = fechasAsistidas.contains(fecha);
                    tabla.addCell(asistio ? "Sí" : "-");
                }
            }

            documento.add(tabla);
            documento.close();
            JOptionPane.showMessageDialog(null, "PDF generado correctamente");

        } catch (Exception e) {
            System.out.println("Error generando PDF asistencia: " + e.getMessage());
            e.printStackTrace();
        }

        return archivo;
    }
}
