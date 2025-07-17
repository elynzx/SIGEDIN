package utilities.PdfTemplate;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import static utilities.PdfTemplate.ReportePdfUtil.*;

public class ReportePdfPlan {

    @SuppressWarnings("unchecked")
    public static File generarReportePlan(String nombreEstudiante, Map<String, Object> datos) {
        Document document = new Document();
        File archivo = null;

        try {
            archivo = new File("reporte_plan_" + nombreEstudiante.replace(" ", "_") + ".pdf");
            PdfWriter.getInstance(document, new FileOutputStream(archivo));
            document.open();

            agregarEncabezadoConLogo(document, "Plan Individual de Intervención");

            document.add(new Paragraph("Estudiante: " + nombreEstudiante, TEXTO_FONT));
            if (datos.containsKey("nivelFuncional")) {
                document.add(new Paragraph("Nivel funcional: " + datos.get("nivelFuncional"), TEXTO_FONT));
            }
            if (datos.containsKey("diagnosticos")) {
                document.add(new Paragraph("Diagnósticos: " + datos.get("diagnosticos"), TEXTO_FONT));
            }
            if (datos.containsKey("nombreAula")) {
                document.add(new Paragraph("Aula: " + datos.get("nombreAula"), TEXTO_FONT));
            }
            if (datos.containsKey("nombreDocente")) {
                document.add(new Paragraph("Docente a cargo: " + datos.get("nombreDocente"), TEXTO_FONT));
            }

            document.add(new Paragraph("\n"));

            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(10);
            tabla.setSpacingAfter(10);
            tabla.setWidths(new float[]{3, 7});

            List<Map<String, String>> planes = (List<Map<String, String>>) datos.get("plan");

            for (Map<String, String> fila : planes) {
                tabla.addCell(celdaEncabezado("Fecha de Inicio"));
                tabla.addCell(celdaDato(fila.get("fechaInicio"), BaseColor.WHITE));

                tabla.addCell(celdaEncabezado("Tipo de Conducta"));
                tabla.addCell(celdaDato(fila.get("tipoConducta"), BaseColor.WHITE));

                tabla.addCell(celdaEncabezado("Función del Comportamiento"));
                tabla.addCell(celdaDato(fila.get("funcion"), BaseColor.WHITE));

                tabla.addCell(celdaEncabezado("Objetivo del Plan"));
                tabla.addCell(celdaDato(fila.get("objetivo"), BaseColor.WHITE));

                tabla.addCell(celdaEncabezado("Estrategia Aplicada"));
                tabla.addCell(celdaDato(fila.get("estrategia"), BaseColor.WHITE));

                tabla.addCell(celdaEncabezado("¿Aplicado Antes?"));
                tabla.addCell(celdaDato(fila.get("aplicadoAntes"), BaseColor.WHITE));

                tabla.addCell(celdaEncabezado("Observaciones"));
                tabla.addCell(celdaDato(fila.get("observaciones"), BaseColor.WHITE));

                PdfPCell espacio = new PdfPCell(new Phrase(" "));
                espacio.setColspan(2);
                espacio.setBorder(Rectangle.NO_BORDER);
                espacio.setMinimumHeight(10f);
                tabla.addCell(espacio);
            }

            document.add(tabla);
            document.close();

            JOptionPane.showMessageDialog(null, "PDF generado correctamente");

        } catch (Exception e) {
            System.out.println("Error generando PDF plan: " + e.getMessage());
            e.printStackTrace();
        }

        return archivo;
    }
}
