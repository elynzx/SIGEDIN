/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities.ExcelTemplate;

import model.entidades.Estudiante;
import model.funcionalidad.ConductaProblematica;
import model.funcionalidad.catalogo.Diagnostico;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelHistorialConductas {

    public static void exportarHistorialConductas(List<ConductaProblematica> listaConductas,
                                                  Estudiante estudiante,
                                                  JPanel parent) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Conductas Problemáticas");

            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);

            // Info estudiante
            Row info1 = sheet.createRow(0);
            info1.createCell(0).setCellValue("Estudiante:");
            info1.createCell(1).setCellValue(estudiante.getNombres() + " " + estudiante.getApellidos());

            Row info2 = sheet.createRow(1);
            info2.createCell(0).setCellValue("Diagnóstico:");
            List<String> diagnosticos = estudiante.getDiagnosticos()
                    .stream()
                    .map(Diagnostico::getNombre)
                    .collect(Collectors.toList());
            info2.createCell(1).setCellValue(String.join(", ", diagnosticos));

            sheet.createRow(2); // Espacio

            Row header = sheet.createRow(3);
            header.createCell(0).setCellValue("Fecha");
            header.createCell(1).setCellValue("Tipo de conducta");
            header.createCell(2).setCellValue("Descripción");
            header.createCell(3).setCellValue("Gravedad");

            for (int i = 0; i < 4; i++) {
                header.getCell(i).setCellStyle(headerStyle);
            }

            int rowNum = 4;
            for (ConductaProblematica conducta : listaConductas) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(conducta.getFecha().toString());
                row.createCell(1).setCellValue(conducta.getTipo().getNombre());
                row.createCell(2).setCellValue(conducta.getDescripcion());
                row.createCell(3).setCellValue(conducta.getGravedad());
            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar historial de conductas");
            String nombreArchivo = "Conductas_" + estudiante.getApellidos().replace(" ", "_")
                    + "_" + estudiante.getNombres().replace(" ", "_")
                    + "_" + java.time.LocalDate.now() + ".xlsx";
            chooser.setSelectedFile(new File(nombreArchivo));

            if (chooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
                try (FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile())) {
                    workbook.write(fileOut);
                    JOptionPane.showMessageDialog(parent, "¡Historial exportado con éxito!");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Error al exportar historial: " + e.getMessage());
        }
    }
}
