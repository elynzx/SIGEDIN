/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.Secretaria;

import controller.secretaria.EstudianteMatriculaCtrl;
import dao.EstudianteImp;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import model.entidades.Aula;
import model.entidades.Docente;
import model.entidades.Estudiante;
import model.funcionalidad.catalogo.Diagnostico;

/**
 *
 * @author rpasc
 */
public class VistaEstudiantes extends javax.swing.JPanel {

    private final EstudianteMatriculaCtrl estudianteMatriculaCtrl;

    public VistaEstudiantes(int idSecretaria) {
        this.estudianteMatriculaCtrl = new EstudianteMatriculaCtrl(
                EstudianteImp.obtenerInstancia()
        );
        initComponents();
        cargarTablaEstudiantes();
    }

    private void cargarTablaEstudiantes() {

        List<Estudiante> estudiantes = estudianteMatriculaCtrl.obtenerEstudiantesParaVistaSecretaria();

        DefaultTableModel modelo = (DefaultTableModel) tbListaEstudiantes.getModel();
        modelo.setRowCount(0);

        for (Estudiante est : estudiantes) {
            String diagnosticos = est.getDiagnosticos().stream()
                    .map(d -> d.getNombre())
                    .collect(Collectors.joining(", "));

            modelo.addRow(new Object[]{
                est.getIdEstudiante(),
                est.getApellidos(),
                est.getNombres(),
                diagnosticos,
                est.getNivelFuncional().getNombre(),
                est.getAulaAsignada().getNombre(),
                est.getObservaciones(),
                est.getApoderado().getCelular(),
                est.getFechaMatricula()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpDashboardDocente = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnDescHistorialConductas = new javax.swing.JLabel();
        textBuscarTicket = new javax.swing.JTextField();
        btnBuscarTicket = new javax.swing.JButton();
        btnGuardarPlan = new javax.swing.JButton();
        btnGuardarPlan2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListaEstudiantes = new javax.swing.JTable();
        panelDatos40 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jcmbgeneroAlumno1 = new javax.swing.JComboBox<>();
        panelDatos41 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jcmbgeneroAlumno2 = new javax.swing.JComboBox<>();
        panelDatos42 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jcmbgeneroAlumno3 = new javax.swing.JComboBox<>();

        jpDashboardDocente.setBackground(new java.awt.Color(255, 255, 255));
        jpDashboardDocente.setMinimumSize(new java.awt.Dimension(1250, 734));
        jpDashboardDocente.setPreferredSize(new java.awt.Dimension(1250, 734));
        jpDashboardDocente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));
        jpDashboardDocente.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 1160, 10));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Info_fill-1_1.png"))); // NOI18N
        jLabel6.setText("Registra observaciones so frecuencia diaria.");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jpDashboardDocente.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 290, -1));

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(45, 94, 152));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Lista de Estudiantes");
        jpDashboardDocente.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 330, 30));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Buscar DNI:");
        jpDashboardDocente.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 80, 40));

        btnDescHistorialConductas.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        btnDescHistorialConductas.setForeground(new java.awt.Color(255, 255, 255));
        btnDescHistorialConductas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnDescHistorialConductas.setText("Descargar Lista de Estudiantes");
        btnDescHistorialConductas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDescHistorialConductas.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnDescHistorialConductas.setIconTextGap(8);
        btnDescHistorialConductas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDescHistorialConductasMouseClicked(evt);
            }
        });
        jpDashboardDocente.add(btnDescHistorialConductas, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 660, 220, 30));

        textBuscarTicket.setBackground(new java.awt.Color(255, 255, 255));
        textBuscarTicket.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        textBuscarTicket.setForeground(new java.awt.Color(102, 102, 102));
        textBuscarTicket.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jpDashboardDocente.add(textBuscarTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 40, 180, 40));

        btnBuscarTicket.setBackground(new java.awt.Color(16, 58, 108));
        btnBuscarTicket.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        btnBuscarTicket.setForeground(new java.awt.Color(98, 66, 26));
        btnBuscarTicket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Search_alt-2.png"))); // NOI18N
        btnBuscarTicket.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(66, 128, 191)));
        btnBuscarTicket.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTicketActionPerformed(evt);
            }
        });
        jpDashboardDocente.add(btnBuscarTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, 40, 40));

        btnGuardarPlan.setBackground(new java.awt.Color(161, 34, 130));
        btnGuardarPlan.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnGuardarPlan.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarPlan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/File_dock_fill-2.png"))); // NOI18N
        btnGuardarPlan.setText("VER FICHA");
        btnGuardarPlan.setBorder(null);
        btnGuardarPlan.setBorderPainted(false);
        btnGuardarPlan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarPlan.setFocusPainted(false);
        btnGuardarPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPlanActionPerformed(evt);
            }
        });
        jpDashboardDocente.add(btnGuardarPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 135, 40));

        btnGuardarPlan2.setBackground(new java.awt.Color(58, 163, 163));
        btnGuardarPlan2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnGuardarPlan2.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarPlan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Refresh_2.png"))); // NOI18N
        btnGuardarPlan2.setText("ACTUALIZAR");
        btnGuardarPlan2.setBorder(null);
        btnGuardarPlan2.setBorderPainted(false);
        btnGuardarPlan2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarPlan2.setFocusPainted(false);
        btnGuardarPlan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPlan2ActionPerformed(evt);
            }
        });
        jpDashboardDocente.add(btnGuardarPlan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 40, 135, 40));

        jScrollPane1.setBorder(null);

        tbListaEstudiantes.setBackground(new java.awt.Color(255, 255, 255));
        tbListaEstudiantes.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tbListaEstudiantes.setForeground(new java.awt.Color(23, 64, 112));
        tbListaEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "Pascual Caycho", "Evelyn Roxana", null, null, null, null, null, null},
                {null, "Caceres", "Carlos", null, null, null, null, null, null},
                {null, "Pineda", "Paul", null, null, null, null, null, null},
                {null, "Montalvo", "Matias", null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Apellidos", "Nombres", "Diagnostico", "Nivel funcional", "Aula asignada", "Docente a cargo", "Celular", "Fecha de Matricula"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbListaEstudiantes.setGridColor(new java.awt.Color(204, 204, 204));
        tbListaEstudiantes.setRowHeight(25);
        tbListaEstudiantes.setSelectionBackground(new java.awt.Color(23, 64, 112));
        tbListaEstudiantes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbListaEstudiantes.setShowGrid(true);
        tbListaEstudiantes.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tbListaEstudiantes);

        jpDashboardDocente.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 1170, 460));

        panelDatos40.setBackground(new java.awt.Color(255, 255, 255));

        jLabel51.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(102, 102, 102));
        jLabel51.setText("Filtrar Aula:");

        jcmbgeneroAlumno1.setBackground(new java.awt.Color(255, 255, 255));
        jcmbgeneroAlumno1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jcmbgeneroAlumno1.setForeground(new java.awt.Color(23, 64, 112));
        jcmbgeneroAlumno1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(219, 234, 251)));

        javax.swing.GroupLayout panelDatos40Layout = new javax.swing.GroupLayout(panelDatos40);
        panelDatos40.setLayout(panelDatos40Layout);
        panelDatos40Layout.setHorizontalGroup(
            panelDatos40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos40Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcmbgeneroAlumno1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelDatos40Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addContainerGap(176, Short.MAX_VALUE))))
        );
        panelDatos40Layout.setVerticalGroup(
            panelDatos40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcmbgeneroAlumno1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpDashboardDocente.add(panelDatos40, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 240, -1));

        panelDatos41.setBackground(new java.awt.Color(255, 255, 255));

        jLabel52.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(102, 102, 102));
        jLabel52.setText("Filtrar Diagnóstico:");

        jcmbgeneroAlumno2.setBackground(new java.awt.Color(255, 255, 255));
        jcmbgeneroAlumno2.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jcmbgeneroAlumno2.setForeground(new java.awt.Color(23, 64, 112));
        jcmbgeneroAlumno2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(219, 234, 251)));

        javax.swing.GroupLayout panelDatos41Layout = new javax.swing.GroupLayout(panelDatos41);
        panelDatos41.setLayout(panelDatos41Layout);
        panelDatos41Layout.setHorizontalGroup(
            panelDatos41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos41Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcmbgeneroAlumno2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelDatos41Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addContainerGap(136, Short.MAX_VALUE))))
        );
        panelDatos41Layout.setVerticalGroup(
            panelDatos41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcmbgeneroAlumno2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpDashboardDocente.add(panelDatos41, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, 240, -1));

        panelDatos42.setBackground(new java.awt.Color(255, 255, 255));

        jLabel53.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(102, 102, 102));
        jLabel53.setText("Filtrar Docente:");

        jcmbgeneroAlumno3.setBackground(new java.awt.Color(255, 255, 255));
        jcmbgeneroAlumno3.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jcmbgeneroAlumno3.setForeground(new java.awt.Color(23, 64, 112));
        jcmbgeneroAlumno3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(219, 234, 251)));

        javax.swing.GroupLayout panelDatos42Layout = new javax.swing.GroupLayout(panelDatos42);
        panelDatos42.setLayout(panelDatos42Layout);
        panelDatos42Layout.setHorizontalGroup(
            panelDatos42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos42Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcmbgeneroAlumno3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelDatos42Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addContainerGap(142, Short.MAX_VALUE))))
        );
        panelDatos42Layout.setVerticalGroup(
            panelDatos42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcmbgeneroAlumno3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpDashboardDocente.add(panelDatos42, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, 230, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpDashboardDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpDashboardDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 710, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDescHistorialConductasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDescHistorialConductasMouseClicked

    }//GEN-LAST:event_btnDescHistorialConductasMouseClicked

    private void btnBuscarTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTicketActionPerformed

    }//GEN-LAST:event_btnBuscarTicketActionPerformed

    private void btnGuardarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPlanActionPerformed

    }//GEN-LAST:event_btnGuardarPlanActionPerformed

    private void btnGuardarPlan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPlan2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarPlan2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarTicket;
    private javax.swing.JLabel btnDescHistorialConductas;
    private javax.swing.JButton btnGuardarPlan;
    private javax.swing.JButton btnGuardarPlan2;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JComboBox<Aula> jcmbgeneroAlumno1;
    private javax.swing.JComboBox<Diagnostico> jcmbgeneroAlumno2;
    private javax.swing.JComboBox<Docente> jcmbgeneroAlumno3;
    private javax.swing.JPanel jpDashboardDocente;
    private javax.swing.JPanel panelDatos40;
    private javax.swing.JPanel panelDatos41;
    private javax.swing.JPanel panelDatos42;
    private javax.swing.JTable tbListaEstudiantes;
    private javax.swing.JTextField textBuscarTicket;
    // End of variables declaration//GEN-END:variables

}
