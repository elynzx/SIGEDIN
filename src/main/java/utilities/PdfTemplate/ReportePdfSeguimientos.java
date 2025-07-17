package utilities.PdfTemplate;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static utilities.PdfTemplate.ReportePdfUtil.*;

public class ReportePdfSeguimientos {

    public static File generarReporteSeguimiento(String nombreEstudiante, Map<String, Object> datos) {
        Document documento = new Document(PageSize.A4);
        File archivo = null;

        try {
            archivo = new File("reporte_seguimiento_" + nombreEstudiante.replace(" ", "_") + ".pdf");
            PdfWriter.getInstance(documento, new FileOutputStream(archivo));
            documento.open();

            // Encabezado institucional
            agregarEncabezadoConLogo(documento, "Seguimiento Conductual");

            // Datos del estudiante
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

            documento.add(new Paragraph("\n"));

            // Tabla de seguimiento
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidths(new int[]{2, 2, 3, 2, 3});
            tabla.setWidthPercentage(100);

            tabla.addCell(celdaEncabezado("Fecha"));
            tabla.addCell(celdaEncabezado("Categoría"));
            tabla.addCell(celdaEncabezado("Descripción"));
            tabla.addCell(celdaEncabezado("Frecuencia"));
            tabla.addCell(celdaEncabezado("Observaciones"));

            Object seguimientoObj = datos.get("seguimientos");

            if (seguimientoObj instanceof List<?>) {
                List<?> lista = (List<?>) seguimientoObj;
                BaseColor fondoClaro = new BaseColor(245, 245, 255);
                boolean alternar = false;

                for (Object item : lista) {
                    if (item instanceof Map<?, ?>) {
                        Map<?, ?> row = (Map<?, ?>) item;
                        BaseColor fondo = alternar ? fondoClaro : BaseColor.WHITE;

                        tabla.addCell(celdaDato(String.valueOf(row.get("fecha")), fondo));
                        tabla.addCell(celdaDato(String.valueOf(row.get("categoria")), fondo));
                        tabla.addCell(celdaDato(String.valueOf(row.get("descripcion")), fondo));
                        tabla.addCell(celdaDato(String.valueOf(row.get("frecuencia")), fondo));
                        tabla.addCell(celdaDato(String.valueOf(row.get("observaciones")), fondo));

                        alternar = !alternar;
                    }
                }
            }

            documento.add(tabla);
            documento.close();

            // Confirmación visual
            JOptionPane.showMessageDialog(null, "PDF generado correctamente");

        } catch (DocumentException | IOException e) {
            System.out.println("Error al generar PDF: " + e.getMessage());
            e.printStackTrace();
        }

        return archivo;
    }
}
