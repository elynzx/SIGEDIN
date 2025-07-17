package utilities.PdfTemplate;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.File;
import java.util.List;
import java.util.Map;

public class ReportePdfUtil {

    public static final BaseColor AZUL = new BaseColor(23, 64, 112);
    public static final BaseColor CELESTE = new BaseColor(218, 224, 242);

    public static final Font TITULO_FONT = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, AZUL);
    public static final Font SUBTITULO_FONT = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    public static final Font TEXTO_FONT = new Font(Font.FontFamily.HELVETICA, 9);

    public static void agregarEncabezadoConLogo(Document documento, String tituloReporte) {
        try {
            PdfPTable tablaEncabezado = new PdfPTable(2);
            tablaEncabezado.setWidths(new float[]{1, 4});
            tablaEncabezado.setWidthPercentage(100);
            tablaEncabezado.setSpacingAfter(10f);

            PdfPCell celdaLogo;
            try {
                Image logo = Image.getInstance("src/main/resources/images/logo02.png");
                logo.scaleToFit(60, 60);
                logo.setAlignment(Image.ALIGN_LEFT);
                celdaLogo = new PdfPCell(logo);
            } catch (Exception e) {
                celdaLogo = new PdfPCell(new Phrase("LOGO", SUBTITULO_FONT));
                System.out.println("No se pudo cargar el logo: " + e.getMessage());
            }
            celdaLogo.setBorder(Rectangle.NO_BORDER);
            celdaLogo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablaEncabezado.addCell(celdaLogo);

            PdfPCell celdaTexto = new PdfPCell();
            celdaTexto.setBorder(Rectangle.NO_BORDER);
            celdaTexto.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celdaTexto.setHorizontalAlignment(Element.ALIGN_CENTER);

            Paragraph nombreInstitucion = new Paragraph("ESCUELA DE EDUCACIÓN INCLUSIVA", SUBTITULO_FONT);
            nombreInstitucion.setAlignment(Element.ALIGN_CENTER);
            Paragraph titulo = new Paragraph(tituloReporte.toUpperCase(), TITULO_FONT);
            titulo.setAlignment(Element.ALIGN_CENTER);

            celdaTexto.addElement(nombreInstitucion);
            celdaTexto.addElement(titulo);

            tablaEncabezado.addCell(celdaTexto);

            documento.add(tablaEncabezado);

        } catch (Exception e) {
            System.out.println("Error al agregar encabezado con logo: " + e.getMessage());
        }
    }

    public static PdfPCell celdaEncabezado(String texto) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, SUBTITULO_FONT));
        celda.setBackgroundColor(new BaseColor(0xDDEEFF));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(8);
        return celda;
    }

    public static PdfPCell celdaDato(String texto, BaseColor fondo) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, TEXTO_FONT));
        celda.setBackgroundColor(fondo != null ? fondo : BaseColor.WHITE);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(6);
        return celda;
    }

    public static boolean tieneContenido(String clave, Map<String, Object> datos) {
        Object valor = datos.get(clave);
        if (valor instanceof List<?>) {
            return !((List<?>) valor).isEmpty();
        }
        return valor != null;
    }

    public static File generarPdfConductas(Map<String, Object> datos) {
        String nombre = obtenerNombreDesdeMapa(datos);
        return ReportePdfConductas.generarReporteConductas(nombre, datos);
    }

    public static File generarPdfSeguimientoConductual(Map<String, Object> datos) {
        String nombre = obtenerNombreDesdeMapa(datos);
        return ReportePdfSeguimientos.generarReporteSeguimiento(nombre, datos);
    }

    public static File generarPdfPlanActual(Map<String, Object> datos) {
        String nombre = obtenerNombreDesdeMapa(datos);
        return ReportePdfPlan.generarReportePlan(nombre, datos);
    }

    public static File generarPdfAsistencia(Map<String, Object> datos) {
        String nombre = obtenerNombreDesdeMapa(datos);
        return ReportePdfAsistencia.generarReporteAsistencia(nombre, datos);
    }

    private static String obtenerNombreDesdeMapa(Map<String, Object> datos) {
        Object nombreObj = datos.get("nombreEstudiante");
        return nombreObj != null ? nombreObj.toString() : "Estudiante no identificado";
    }

}
