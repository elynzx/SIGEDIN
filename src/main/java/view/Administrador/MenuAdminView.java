/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Administrador;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.funcionalidad.ListaAulas;
import controller.AdministradorCtrl;
import dao.AdministradorImp;
import model.funcionalidad.ListaUsuarios;

/**
 *
 * @author rpasc
 */
public class MenuAdminView extends javax.swing.JFrame {

    private int idAdministrador;
    private final AdministradorCtrl adminCtrl;
    
    
    
    public MenuAdminView(int idAdministrador) {
        this.idAdministrador = idAdministrador;
        this.adminCtrl= new AdministradorCtrl(
        AdministradorImp.obtenerInstancia()
        );
        initComponents();
        cargarTablaAulas();
        cargarTablaUsuarios();
    }

    public JTable getjTableEstudiantes() {
        return jTableEstudiantes;
    }

    public void setjTableEstudiantes(JTable jTableEstudiantes) {
        this.jTableEstudiantes = jTableEstudiantes;
    }

    public JTable getjTableUsuarios() {
        return jTableUsuarios;
    }

    public void setjTableUsuarios(JTable jTableUsuarios) {
        this.jTableUsuarios = jTableUsuarios;
    }
    
    private DefaultTableModel cargarTablaAulas(){
        DefaultTableModel tablaAulas=(DefaultTableModel) getjTableEstudiantes().getModel();
        tablaAulas.setRowCount(0);
        List <ListaAulas> aulas=adminCtrl.cargarTablaAulas();
        
        for (ListaAulas a : aulas) {
            tablaAulas.addRow(new Object[]{
                a.getNombre(),
                a.getNivelFuncional(),
                a.getDiagnostico(),
                a.getDniDocente(),
                a.getVacantesTotales(),
                a.getVacantesDisponibles()
            });
        }
        
        return tablaAulas;
    }
    
    private DefaultTableModel cargarTablaUsuarios(){
        DefaultTableModel tablaAulas=(DefaultTableModel) getjTableUsuarios().getModel();
        tablaAulas.setRowCount(0);
        List <ListaUsuarios> usuarios=adminCtrl.cargarTablaUsuarios();
        tablaAulas.setColumnIdentifiers(new String[]{
            "ID Usuario", 
            "ID Persona", 
            "Nombres", 
            "Apellidos", 
            "Nombre de Usuario", 
            "Rol", 
            "Estado"
        });
        for (ListaUsuarios u : usuarios) {
            tablaAulas.addRow(new Object[]{
                u.getId(),
                u.getPersona().getId(),
                u.getPersona().getNombres(),
                u.getPersona().getApellidos(),
                u.getNombre_usuario(),
                u.getRol(),
                u.getEstado()
            });
        }
        
        return tablaAulas;
    }
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jlblnombre = new javax.swing.JLabel();
        jlblempleado = new javax.swing.JLabel();
        jlblreportes = new javax.swing.JLabel();
        jlblestudiante = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jlblmenu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsuarios = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEstudiantes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel1.setText("Menu Administrador");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Hola,");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 40, -1));

        jlblnombre.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlblnombre.setForeground(new java.awt.Color(51, 51, 51));
        jlblnombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblnombre.setText("nombre");
        jPanel2.add(jlblnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 178, 28));

        jlblempleado.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblempleado.setText("Empleados");
        jlblempleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblempleadoMouseClicked(evt);
            }
        });
        jPanel2.add(jlblempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        jlblreportes.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblreportes.setText("Reportes");
        jPanel2.add(jlblreportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, -1));

        jlblestudiante.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblestudiante.setText("Estudiantes");
        jlblestudiante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblestudianteMouseClicked(evt);
            }
        });
        jPanel2.add(jlblestudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        jlblmenu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblmenu.setText("Menu");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jlblmenu)
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jlblmenu)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 120, 80));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 80));

        jTableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableUsuarios);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 740, 150));

        jTableEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Nivel Funcional", "Diagnostico", "Docente a cargo", "Vacantes totales", "Vacantes disponibles"
            }
        ));
        jScrollPane2.setViewportView(jTableEstudiantes);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 740, 150));

        jLabel2.setText("Estudiantes");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 70, -1));

        jLabel3.setText("Usuarios");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 80, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlblempleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblempleadoMouseClicked
        EmpleadosAdmin empleadosvista = new EmpleadosAdmin(idAdministrador);
        empleadosvista.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jlblempleadoMouseClicked

    private void jlblestudianteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblestudianteMouseClicked
        EstudiantesAdmin estudiantesvista = new EstudiantesAdmin(idAdministrador);
        estudiantesvista.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jlblestudianteMouseClicked
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTableEstudiantes;
    public javax.swing.JTable jTableUsuarios;
    private javax.swing.JLabel jlblempleado;
    private javax.swing.JLabel jlblestudiante;
    private javax.swing.JLabel jlblmenu;
    private javax.swing.JLabel jlblnombre;
    private javax.swing.JLabel jlblreportes;
    // End of variables declaration//GEN-END:variables
}
