
package utillities;

import java.io.File;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import model.entidades.Estudiante;
import model.funcionalidad.ResumenIncidentes;
import model.funcionalidad.catalogo.Diagnostico;

public class ExcelResumenIncidentes {

    public static void exportarResumen(List<ResumenIncidentes> listaResumen,
            Estudiante estudiante, JPanel parent) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Resumen Conductas");

            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);

            Row info1 = sheet.createRow(0);
            info1.createCell(0).setCellValue("Estudiante:");
            info1.createCell(1).setCellValue(estudiante.getNombres()
                    + " "
                    + estudiante.getApellidos());

            Row info2 = sheet.createRow(1);
            info2.createCell(0).setCellValue("Diagnóstico:");
            List<String> nombresDiagnosticos
                    = estudiante.getDiagnosticos()
                            .stream()
                            .map(Diagnostico::getNombre)
                            .collect(Collectors.toList());

            String diagnosticosTexto = String.join(", ", nombresDiagnosticos);

            info2.createCell(1).setCellValue(diagnosticosTexto);

            Row empty = sheet.createRow(2);

            Row header = sheet.createRow(3);
            header.createCell(0).setCellValue("Tipo de conducta");
            header.createCell(1).setCellValue("Frecuencia");
            header.createCell(2).setCellValue("Gravedad promedio");
            header.createCell(3).setCellValue("Última función");

            for (int i = 0; i < 4; i++) {
                header.getCell(i).setCellStyle(headerStyle);
            }

            int rowNum = 4;
            for (ResumenIncidentes ri : listaResumen) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(ri.getTipoConducta().getNombre());
                row.createCell(1).setCellValue(ri.getFrecuencia());
                row.createCell(2).setCellValue(ri.getGravedadPromedio());
                row.createCell(3).setCellValue(ri.getUltComportamiento().getNombre());
            }

            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar archivo Excel");
            String nombreArchivo = "Resumen_" + estudiante.getApellidos().replace(" ", "_")
                    + "_" + estudiante.getNombres().replace(" ", "_")
                    + "_" + java.time.LocalDate.now() + ".xlsx";

            chooser.setSelectedFile(new File(nombreArchivo));

            int userSelection = chooser.showSaveDialog(parent);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try (FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile())) {
                    workbook.write(fileOut);
                    JOptionPane.showMessageDialog(parent, "¡Archivo exportado correctamente!");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Error al exportar Excel: " + e.getMessage());
        }
    }
}

