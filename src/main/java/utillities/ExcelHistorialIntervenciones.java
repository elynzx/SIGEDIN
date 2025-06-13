package utillities;

import model.funcionalidad.PlanIntervencion;
import model.entidades.Estudiante;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

public class ExcelHistorialIntervenciones {

    public static void exportarHistorial(List<PlanIntervencion> historial,
            Estudiante estudiante,
            JPanel parent) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Historial Intervenciones");

            
            Row info1 = sheet.createRow(0);
            info1.createCell(0).setCellValue("Estudiante:");
            info1.createCell(1).setCellValue(estudiante.getNombres()
                    + " "
                    + estudiante.getApellidos());

            Row info2 = sheet.createRow(1);
            info2.createCell(0).setCellValue("ID:");
            info2.createCell(1).setCellValue(estudiante.getIdEstudiante());

            sheet.createRow(2); // Fila vacía

            // Cabeceras
            Row header = sheet.createRow(3);
            String[] columnas = {
                "Fecha", "Tipo de Conducta", "Función de Comportamiento",
                "Objetivo", "Estrategia", "Implementación", "Observaciones"
            };

            CellStyle estiloCabecera = workbook.createCellStyle();
            Font fuente = workbook.createFont();
            fuente.setBold(true);
            estiloCabecera.setFont(fuente);

            for (int i = 0; i < columnas.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(columnas[i]);
                cell.setCellStyle(estiloCabecera);
            }

            // Datos
            int rowNum = 4;
            for (PlanIntervencion i : historial) {
                Row fila = sheet.createRow(rowNum++);
                fila.createCell(0).setCellValue(i.getFechaInicio().toString());
                fila.createCell(1).setCellValue(i.getTipoConducta().getNombre());
                fila.createCell(2).setCellValue(i.getFuncion().getNombre());
                fila.createCell(3).setCellValue(i.getObjetivo());
                fila.createCell(4).setCellValue(i.getEstrategia().getNombre());
                fila.createCell(5).setCellValue(i.isAplicadoAntes() ? "Sí" : "No");
                fila.createCell(6).setCellValue(i.getObservaciones());
            }

            for (int i = 0; i < columnas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            String nombreArchivo = "HistorialIntervenciones_"
                    + estudiante.getApellidos().replace(" ", "_") + "_"
                    + estudiante.getNombres().replace(" ", "_") + "_"
                    + LocalDate.now() + ".xlsx";

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar historial de intervenciones");
            chooser.setSelectedFile(new File(nombreArchivo));

            if (chooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
                try (FileOutputStream out = new FileOutputStream(chooser.getSelectedFile())) {
                    workbook.write(out);
                    JOptionPane.showMessageDialog(parent, "¡Historial exportado correctamente!");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Error al exportar historial: " + e.getMessage());
        }
    }
}
