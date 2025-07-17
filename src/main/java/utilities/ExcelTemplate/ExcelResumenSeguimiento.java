/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities.ExcelTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.*;
import model.entidades.Estudiante;
import model.funcionalidad.catalogo.Diagnostico;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelResumenSeguimiento {

    public static void exportarResumenSeguimiento(List<Map<String, Object>> listaPromedios,
            Estudiante estudiante, JPanel parent) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Seguimiento Conductual");

            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);

            // Info del estudiante
            Row info1 = sheet.createRow(0);
            info1.createCell(0).setCellValue("Estudiante:");
            info1.createCell(1).setCellValue(estudiante.getNombres() + " " + estudiante.getApellidos());

            Row info2 = sheet.createRow(1);
            info2.createCell(0).setCellValue("Diagnóstico:");
            List<String> nombresDiagnosticos = estudiante.getDiagnosticos()
                    .stream()
                    .map(Diagnostico::getNombre)
                    .collect(Collectors.toList());
            info2.createCell(1).setCellValue(String.join(", ", nombresDiagnosticos));

            sheet.createRow(2); // Fila vacía

            Row header = sheet.createRow(3);
            header.createCell(0).setCellValue("Categoría");
            header.createCell(1).setCellValue("Promedio de Frecuencia");

            for (int i = 0; i < 2; i++) {
                header.getCell(i).setCellStyle(headerStyle);
            }

            int rowNum = 4;
            for (Map<String, Object> promedio : listaPromedios) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(promedio.get("categoria").toString());
                row.createCell(1).setCellValue(Double.parseDouble(promedio.get("promedio").toString()));
            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar archivo Excel");

            String nombreArchivo = "Seguimiento_" + estudiante.getApellidos().replace(" ", "_")
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
