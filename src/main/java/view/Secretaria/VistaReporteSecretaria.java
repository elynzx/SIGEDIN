
package view.Secretaria;

import model.entidades.Aula;
import model.funcionalidad.catalogo.Diagnostico;


public class VistaReporteSecretaria extends javax.swing.JPanel {

    public VistaReporteSecretaria(int idSecretaria) {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lbNivel15 = new javax.swing.JLabel();
        btnReporteConductas = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbListaEstudiantes = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        lbNivel12 = new javax.swing.JLabel();
        btnReporteSeguimiento = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        cbListaEstudiantes1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbNivel13 = new javax.swing.JLabel();
        btnReportePlan = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lbNivel14 = new javax.swing.JLabel();
        btnReporteAsistencia = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1250, 710));
        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 710));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(81, 124, 191)));

        lbNivel15.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        lbNivel15.setForeground(new java.awt.Color(81, 124, 191));
        lbNivel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel15.setText("Reporte de matrícula por aula");
        lbNivel15.setToolTipText("");
        lbNivel15.setPreferredSize(new java.awt.Dimension(70, 25));

        btnReporteConductas.setBackground(new java.awt.Color(81, 124, 191));
        btnReporteConductas.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnReporteConductas.setForeground(new java.awt.Color(255, 255, 255));
        btnReporteConductas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/File_dock_fill-2.png"))); // NOI18N
        btnReporteConductas.setText("GENERAR PDF");
        btnReporteConductas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReporteConductas.setFocusPainted(false);
        btnReporteConductas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteConductasActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(81, 124, 191));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Reporte de estudiantes matriculados");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Selecciona un Aula:");

        cbListaEstudiantes.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        cbListaEstudiantes.setForeground(new java.awt.Color(102, 102, 102));
        cbListaEstudiantes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(cbListaEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbNivel15, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReporteConductas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(lbNivel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbListaEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(btnReporteConductas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 450, 290));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 94, 152)));

        lbNivel12.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        lbNivel12.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel12.setText("Reporte por diagnóstico");
        lbNivel12.setToolTipText("");
        lbNivel12.setPreferredSize(new java.awt.Dimension(70, 25));

        btnReporteSeguimiento.setBackground(new java.awt.Color(45, 94, 152));
        btnReporteSeguimiento.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnReporteSeguimiento.setForeground(new java.awt.Color(255, 255, 255));
        btnReporteSeguimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/File_dock_fill-2.png"))); // NOI18N
        btnReporteSeguimiento.setText("GENERAR PDF");
        btnReporteSeguimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReporteSeguimiento.setFocusPainted(false);
        btnReporteSeguimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteSeguimientoActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(45, 94, 152));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Reporte agrupado por diagnóstico y nivel funcional");

        cbListaEstudiantes1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        cbListaEstudiantes1.setForeground(new java.awt.Color(102, 102, 102));
        cbListaEstudiantes1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Selecciona un Diagnóstico:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNivel12, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cbListaEstudiantes1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(btnReporteSeguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(lbNivel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbListaEstudiantes1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(btnReporteSeguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 450, 290));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(23, 64, 112)));

        lbNivel13.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        lbNivel13.setForeground(new java.awt.Color(23, 64, 112));
        lbNivel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNivel13.setText("Reporte general de matrículas");
        lbNivel13.setToolTipText("");
        lbNivel13.setPreferredSize(new java.awt.Dimension(70, 25));

        btnReportePlan.setBackground(new java.awt.Color(23, 64, 112));
        btnReportePlan.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnReportePlan.setForeground(new java.awt.Color(255, 255, 255));
        btnReportePlan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/File_dock_fill-2.png"))); // NOI18N
        btnReportePlan.setText("GENERAR PDF");
        btnReportePlan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportePlan.setFocusPainted(false);
        btnReportePlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportePlanActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(23, 64, 112));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Lista completa de estudiantes y datos principales");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNivel13, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReportePlan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(lbNivel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReportePlan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 450, 210));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(23, 50, 78)));

        lbNivel14.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        lbNivel14.setForeground(new java.awt.Color(23, 50, 78));
        lbNivel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNivel14.setText("Reporte de vacantes");
        lbNivel14.setToolTipText("");
        lbNivel14.setPreferredSize(new java.awt.Dimension(70, 25));

        btnReporteAsistencia.setBackground(new java.awt.Color(23, 50, 78));
        btnReporteAsistencia.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnReporteAsistencia.setForeground(new java.awt.Color(255, 255, 255));
        btnReporteAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/File_dock_fill-2.png"))); // NOI18N
        btnReporteAsistencia.setText("GENERAR PDF");
        btnReporteAsistencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReporteAsistencia.setFocusPainted(false);
        btnReporteAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteAsistenciaActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(23, 50, 78));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Lista de aulas con vacantes disponibles");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNivel14, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(btnReporteAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(lbNivel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReporteAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 410, 450, 210));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnReporteConductasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteConductasActionPerformed

    }//GEN-LAST:event_btnReporteConductasActionPerformed

    private void btnReporteSeguimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteSeguimientoActionPerformed
 
    }//GEN-LAST:event_btnReporteSeguimientoActionPerformed

    private void btnReportePlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportePlanActionPerformed

    }//GEN-LAST:event_btnReportePlanActionPerformed

    private void btnReporteAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteAsistenciaActionPerformed

    }//GEN-LAST:event_btnReporteAsistenciaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporteAsistencia;
    private javax.swing.JButton btnReporteConductas;
    private javax.swing.JButton btnReportePlan;
    private javax.swing.JButton btnReporteSeguimiento;
    private javax.swing.JComboBox<Aula> cbListaEstudiantes;
    private javax.swing.JComboBox<Diagnostico> cbListaEstudiantes1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbNivel12;
    private javax.swing.JLabel lbNivel13;
    private javax.swing.JLabel lbNivel14;
    private javax.swing.JLabel lbNivel15;
    // End of variables declaration//GEN-END:variables
}
