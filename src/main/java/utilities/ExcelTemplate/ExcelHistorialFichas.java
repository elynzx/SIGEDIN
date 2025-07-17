/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities.ExcelTemplate;

import model.entidades.Estudiante;
import model.funcionalidad.FichaAbc;
import model.funcionalidad.catalogo.Diagnostico;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelHistorialFichas {

    public static void exportarHistorialFichas(List<FichaAbc> listaFichas,
            Estudiante estudiante,
            JPanel parent) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Fichas ABC");

            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);

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
            header.createCell(1).setCellValue("Antecedente");
            header.createCell(2).setCellValue("Comportamiento");
            header.createCell(3).setCellValue("Gravedad");

            for (int i = 0; i < 4; i++) {
                header.getCell(i).setCellStyle(headerStyle);
            }

            int rowNum = 4;
            for (FichaAbc ficha : listaFichas) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(ficha.getFecha().toString());
                row.createCell(1).setCellValue(ficha.getAntecedente().getNombre());
                row.createCell(2).setCellValue(ficha.getComportamiento());
                row.createCell(3).setCellValue(ficha.getGravedad());
            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar historial de fichas");
            String nombreArchivo = "FichasABC_" + estudiante.getApellidos().replace(" ", "_")
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
