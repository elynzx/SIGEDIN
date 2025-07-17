
package controller;

import dao.AdministradorDao;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.entidades.Persona;
import model.entidades.Usuario;
import model.funcionalidad.ListaAulas;
import view.Administrador.MenuAdminView;
import model.funcionalidad.ListaUsuarios;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.Administrador.EstudiantesAdmin;
import view.Administrador.ReportesAdmin;
import view.Secretaria.ReportesMatricula;



public class AdministradorCtrl {
        private final AdministradorDao dao;
        public AdministradorCtrl(AdministradorDao dao) {
            this.dao=dao;
            
            
        }
    
        
    public List<ListaAulas> cargarTablaAulas() {
        List<ListaAulas> aulas = dao.obtenerListaAulas();
        return aulas;
    }
    
    public List<ListaUsuarios> cargarTablaUsuarios() {
        List<ListaUsuarios> usuarios = dao.obtenerListaUsuarios();
        return usuarios;
    }
    
    public void cambiarDato(String tipo_dato,String dato, int id){
        dao.registrarCambio(tipo_dato,dato,id);
    }
    
    public boolean verificarDni(String dni){
        boolean confirmacion;
        confirmacion=dao.VerificarDni(dni);
        return confirmacion;
    }
    
    public boolean registrar(Usuario usuario){
        boolean confirmacion = false;
        boolean confirmacion2 = false; 
        boolean confirmacion_final=false;
        
        confirmacion=dao.registrarPersona(usuario);
        int idPersona=dao.obtenerIdPersona(usuario);
        usuario.getPersona().setId(idPersona);
        confirmacion2=dao.registrar(usuario);
        if(confirmacion==true && confirmacion2==true){
            confirmacion_final=true;
        }else{
            JOptionPane.showMessageDialog(null, "Error registro");
        }
        return confirmacion_final;
    }
    
    public void llenarTablaEstudiantes(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        List<String[]> estudiantes = dao.listarEstudiantesMatriculados();
        for (String[] fila : estudiantes) {
            modelo.addRow(fila);
            }
    }
    
    public List<String> cargarAulas(JComboBox combo) {
        List<String> listaAulas = dao.listaAulas();
        return listaAulas;
    }
    
    public List<String> cargarDiagnostico(JComboBox combo) {
        List<String> listaDiagnostico = dao.listaDiagnostico();
        return listaDiagnostico;
    }
    
    public List<String> cargarDocentes(JComboBox combo) {
        List<String> listaDocentes = dao.listaDocentes();
        return listaDocentes;
    }
    
    public void actualizarValores(EstudiantesAdmin estudiantes, JTable tabla){
        String aula=(String) estudiantes.getJcmbaula().getSelectedItem();
        String diagnostico=(String) estudiantes.getJcmbdiagnostico().getSelectedItem();
        String docente=(String) estudiantes.getJcmbdocente().getSelectedItem();
        
        if (docente =="ninguno" && diagnostico=="ninguno" && aula=="ninguno"){
            JOptionPane.showMessageDialog(null, " Valores ingresados invalidos");
            llenarTablaEstudiantes(tabla);
            return;  
        }
        
        if (aula=="ninguno") {
        aula = null; 
        }
        if (diagnostico=="ninguno") {
        diagnostico = null; 
        }
        if (docente =="ninguno") {
        docente = null; 
        }
        
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        List<String[]> estudiante = dao.listarEstudiantesMatriculadosFiltro(aula,diagnostico,docente);
        for (String[] fila : estudiante) {
            modelo.addRow(fila);
            }
        
        dao.listarEstudiantesMatriculadosFiltro(aula,diagnostico,docente);
                        
    }
    
    public void abrirPDF(EstudiantesAdmin estudiantes){
        try{
            int filaSeleccionada = estudiantes.ObtenerValorSeleccionadojTableListaEstudiantes();
            String id = null;
            int id_matricula= 0;
            if (filaSeleccionada != -1) {
                id = (String) estudiantes.getjTableListaEstudiantes().getValueAt(filaSeleccionada, 0);
                System.out.println("ID seleccionado: " + id);
                id_matricula=dao.obtenerIdMatricula(id);
            } else {
                System.out.println("Por favor, selecciona una fila.");
                return;
            }
            String relativePath = "src/main/java/pdf/Matricula_estudiante_" + id_matricula + ".pdf";
            File file = new File(relativePath);
        
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(AdministradorCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
        public void registrarCambioEstudiante(String dato_final,String idEstudiante,String Dato){
        
        dao.registrarCambioEstudiante(dato_final, idEstudiante,Dato);
        
    }
    
        public int obtenerIdAula(String filtro){
            int id=dao.obtenerAula(filtro);
            return id;
        }
    
        public void generarPdfAula(String tipo_reporte, String criterio_filtro,int id,boolean aulas, boolean diagnostico, boolean docente, String filtro,int id_empleado) {
    
        
        int idTipoReporte = dao.obtenerId_Tipo_Matricula(tipo_reporte);
        int idEmpleado = id_empleado;
        int idEstudiante = 0;

        LocalDateTime now = LocalDateTime.now().withNano(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = Timestamp.valueOf(now);
        
        if(docente=true){
            filtro=dao.obtenerIdDocente(filtro);
        }
        
        List<String[]> estudiantes = dao.obtenerListaMatriculasPorAula(filtro,aulas,diagnostico,docente);

        dao.registrarReporte(idTipoReporte, criterio_filtro, idEstudiante, id, idEmpleado, timestamp);

        
        int idReporte = dao.obtenerIdReporte(timestamp, idEmpleado);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Datos");

        // Estilo con bordes para encabezado y celdas
        CellStyle borderedStyle = workbook.createCellStyle();
        borderedStyle.setBorderTop(BorderStyle.THIN);
        borderedStyle.setBorderBottom(BorderStyle.THIN);
        borderedStyle.setBorderLeft(BorderStyle.THIN);
        borderedStyle.setBorderRight(BorderStyle.THIN);

        // Fila de título
        Row titulo = sheet.createRow(3);
        titulo.createCell(5).setCellValue(tipo_reporte);
        titulo.createCell(6).setCellValue("Id de Reporte:");
        titulo.createCell(7).setCellValue(idReporte);

        // Encabezado
        Row header = sheet.createRow(5);
        String[] headers = {
            "ID", "Apellidos", "Nombres", "Diagnóstico", "Nivel Funcional",
            "Aula", "Docente a cargo", "Celular de contacto", "Fecha de matrícula"
        };
        for (int j = 0; j < headers.length; j++) {
            org.apache.poi.ss.usermodel.Cell cell = header.createCell(5 + j);
            cell.setCellValue(headers[j]);
            cell.setCellStyle(borderedStyle);
        }

        // Datos
        for (int i = 0; i < estudiantes.size(); i++) {
            String[] datos = estudiantes.get(i);
            Row fila = sheet.createRow(6 + i);
            for (int j = 0; j < datos.length; j++) {
                org.apache.poi.ss.usermodel.Cell cell = fila.createCell(5 + j);
                cell.setCellValue(datos[j]);
                cell.setCellStyle(borderedStyle);
            }
        }

        // Autoajustar columnas
        for (int i = 5; i <= 13; i++) {
            sheet.autoSizeColumn(i);
        }

        // Guardar archivo
        File archivo = new File("src/main/java/reportes/ReporteAula" + idReporte + ".xlsx");

        try (FileOutputStream fileOut = new FileOutputStream(archivo)) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Archivo generado: " + archivo.getAbsolutePath());

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(archivo);
            } else {
                System.out.println("Desktop no es compatible. Abre el archivo manualmente.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    public void verContraseña(String contraseña, Object Id, int idAdministrador){
        String contra=dao.verContraseña(contraseña,Id,idAdministrador);
        if(contra==""){
            JOptionPane.showMessageDialog(null, "Error al obtener contraseña");
            return;
        }else{
            JOptionPane.showMessageDialog(null, "La contraseña de el Id usuario: "+Id+" es: "+contra);
        }
        
    }
    
    public void cambiarContraseña(String contraseña, Object Id,int idAdministrador){
        String contra=dao.verContraseña(contraseña,Id,idAdministrador);
        if(contra==""){
            return;
        }else{
            JPasswordField passwordField = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(
                null, 
                passwordField, 
                "Ingresa contraseña nueva", 
                JOptionPane.OK_CANCEL_OPTION, 
                JOptionPane.PLAIN_MESSAGE
            );

            if (option == JOptionPane.OK_OPTION) {
                String contraNueva = new String(passwordField.getPassword());
                dao.cambiarContraseña(contraseña,Id,contraNueva);
            } else {
            JOptionPane.showMessageDialog(null, "Operación cancelada.");
            }
            
        }
        
    }
    
        public void vacantespdf(String tipo_reporte,String criterio_filtro,int id,String filtro,int idAdministrador){
        List<ListaAulas> vacantes = dao.obtenerListaAulas();
        

        LocalDateTime now = LocalDateTime.now().withNano(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = Timestamp.valueOf(now);
        
        int idTipoReporte = dao.obtenerId_Tipo_Matricula(tipo_reporte);

        dao.registrarReporte(idTipoReporte, criterio_filtro, 0, 0, idAdministrador, timestamp);

        
        int idReporte = dao.obtenerIdReporte(timestamp, idAdministrador);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Datos");

        // Estilo con bordes para encabezado y celdas
        CellStyle borderedStyle = workbook.createCellStyle();
        borderedStyle.setBorderTop(BorderStyle.THIN);
        borderedStyle.setBorderBottom(BorderStyle.THIN);
        borderedStyle.setBorderLeft(BorderStyle.THIN);
        borderedStyle.setBorderRight(BorderStyle.THIN);

        // Fila de título
        Row titulo = sheet.createRow(3);
        titulo.createCell(5).setCellValue(tipo_reporte);
        titulo.createCell(6).setCellValue("Id de Reporte:");
        titulo.createCell(7).setCellValue(idReporte);

        // Encabezado
        Row header = sheet.createRow(5);
        String[] headers = {
            "Aula", "Nivel Funcional", "Diagnostico", "Docente a cargo", "Vacantes totales",
            "Vacantes disponibles"
        };
        for (int j = 0; j < headers.length; j++) {
            org.apache.poi.ss.usermodel.Cell cell = header.createCell(5 + j);
            cell.setCellValue(headers[j]);
            cell.setCellStyle(borderedStyle);
        }

        // Datos
        for (int i = 0; i < vacantes.size(); i++) {
            ListaAulas datos = vacantes.get(i);
            String[] valores = datos.toArray();
            Row fila = sheet.createRow(6 + i);

        for (int j = 0; j < valores.length; j++) {
            org.apache.poi.ss.usermodel.Cell cell = fila.createCell(5 + j);
            cell.setCellValue(valores[j]);
            cell.setCellStyle(borderedStyle);
        }
}



        // Autoajustar columnas
        for (int i = 5; i <= 13; i++) {
            sheet.autoSizeColumn(i);
        }

        // Guardar archivo
        File archivo = new File("src/main/java/reportes/ReporteAula" + idReporte + ".xlsx");

        try (FileOutputStream fileOut = new FileOutputStream(archivo)) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Archivo generado: " + archivo.getAbsolutePath());

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(archivo);
            } else {
                System.out.println("Desktop no es compatible. Abre el archivo manualmente.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public String obtenerNombreAdministrador(int idAdministrador){
        String nombre=dao.obtenerNombreAdministrador(idAdministrador);
        
        
        return nombre;
    }
    
    
    
    
}
