package utilities.PdfTemplate;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static utilities.PdfTemplate.ReportePdfUtil.*;

public class ReportePdfConductas {

    public static File generarReporteConductas(String nombreEstudiante, Map<String, Object> datos) {
        Document documento = new Document(PageSize.A4);
        File archivo = null;

        try {
            archivo = new File("reporte_conductas_" + nombreEstudiante.replace(" ", "_") + ".pdf");
            PdfWriter.getInstance(documento, new FileOutputStream(archivo));
            documento.open();

            agregarEncabezadoConLogo(documento, "Reporte de Conductas Problemáticas");

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

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            documento.add(new Paragraph("Fecha de generación: " + sdf.format(new Date()), TEXTO_FONT));
            documento.add(new Paragraph("\n"));

            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidths(new int[]{2, 2, 4, 4, 2});
            tabla.setWidthPercentage(100);

            tabla.addCell(celdaEncabezado("Fecha"));
            tabla.addCell(celdaEncabezado("Tipo de Conducta"));
            tabla.addCell(celdaEncabezado("Función del comportamiento"));
            tabla.addCell(celdaEncabezado("Descripción"));
            tabla.addCell(celdaEncabezado("Gravedad"));

            @SuppressWarnings("unchecked")
            List<Map<String, String>> lista = (List<Map<String, String>>) datos.get("conductas");

            BaseColor fondoClaro = new BaseColor(245, 245, 255);
            boolean alternar = false;

            for (Map<String, String> row : lista) {
                BaseColor fondo = alternar ? fondoClaro : BaseColor.WHITE;
                tabla.addCell(celdaDato(row.get("fecha"), fondo));
                tabla.addCell(celdaDato(row.get("tipo"), fondo));
                tabla.addCell(celdaDato(row.get("funcion"), fondo));
                tabla.addCell(celdaDato(row.get("descripcion"), fondo));
                tabla.addCell(celdaDato(row.get("gravedad"), fondo));
                alternar = !alternar;
            }

            documento.add(tabla);
            documento.close();

            JOptionPane.showMessageDialog(null, "PDF generado correctamente");

        } catch (DocumentException | IOException e) {
            System.out.println("Error al generar PDF: " + e.getMessage());
            e.printStackTrace();
        }

        return archivo;
    }
}
