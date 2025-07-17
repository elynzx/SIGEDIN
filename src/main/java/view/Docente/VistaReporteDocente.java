package view.Docente;

import controller.EstudianteCtrl;
import controller.SesionCtrl;
import controller.docente.ReporteCtrl;
import dao.EstudianteImp;
import dao.funcionalidad.ReporteImp;
import java.awt.Desktop;
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import model.entidades.Estudiante;
import model.entidades.Usuario;

public class VistaReporteDocente extends javax.swing.JPanel {

    private final EstudianteCtrl estudianteCtrl;
    private final ReporteCtrl reporteCtrl;
    private int idDocente;
    Usuario usuario = SesionCtrl.obtenerUsuarioLogueado();

    public VistaReporteDocente(int idDocente) {
        this.idDocente = idDocente;
        this.estudianteCtrl = new EstudianteCtrl(EstudianteImp.obtenerInstancia());
        this.reporteCtrl = new ReporteCtrl(
                ReporteImp.obtenerInstancia()
        );
        initComponents();
        cargarEstudiantes();
    }

    private void cargarEstudiantes() {
        cbListaEstudiantes.removeAllItems();
        List<Estudiante> listaEstudiantes = estudianteCtrl.obtenerListaEstudiantes(idDocente);
        for (Estudiante estudiante : listaEstudiantes) {
            cbListaEstudiantes.addItem(estudiante);
        }
        if (!listaEstudiantes.isEmpty()) {
            cbListaEstudiantes.setSelectedIndex(0);
        }
    }

    private Estudiante obtenerEstudianteSeleccionado() {
        return (Estudiante) cbListaEstudiantes.getSelectedItem();
    }

    private void abrirArchivo(File archivo) {
        if (archivo != null && archivo.exists()) {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(archivo);
                } else {
                    JOptionPane.showMessageDialog(this, "Tu sistema no soporta esta función para abrir archivos.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al abrir el archivo: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "El archivo no existe.");
        }
    }

    private void generarReporteConductas() {
        Estudiante est = obtenerEstudianteSeleccionado();
        if (est == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante.");
            return;
        }
        File pdf = reporteCtrl.generarReporteConductas(est.getIdEstudiante(), usuario);
        abrirArchivo(pdf);
    }

    private void generarReporteSeguimiento() {
        Estudiante est = obtenerEstudianteSeleccionado();
        if (est == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante.");
            return;
        }
        File pdf = reporteCtrl.generarReporteSeguimientoConductual(est.getIdEstudiante(), usuario);
        abrirArchivo(pdf);
    }

    private void generarReportePlan() {
        Estudiante est = obtenerEstudianteSeleccionado();
        if (est == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante.");
            return;
        }
        File pdf = reporteCtrl.generarReportePlanActual(est.getIdEstudiante(), usuario);
        abrirArchivo(pdf);
    }

    private void generarReporteAsistencia() {
        Estudiante est = obtenerEstudianteSeleccionado();
        if (est == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante.");
            return;
        }
        File pdf = reporteCtrl.generarReporteAsistencia(est.getIdEstudiante(), usuario);
        abrirArchivo(pdf);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbListaEstudiantes = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        lbNivel15 = new javax.swing.JLabel();
        btnReporteConductas = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbNivel12 = new javax.swing.JLabel();
        btnReporteSeguimiento = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbNivel13 = new javax.swing.JLabel();
        btnReportePlan = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lbNivel14 = new javax.swing.JLabel();
        btnReporteAsistencia = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btnReporteAula = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1250, 710));
        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 710));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Selecciona el Estudiante:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 140, 30));

        cbListaEstudiantes.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        cbListaEstudiantes.setForeground(new java.awt.Color(102, 102, 102));
        cbListaEstudiantes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel1.add(cbListaEstudiantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 450, 30));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(81, 124, 191)));

        lbNivel15.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        lbNivel15.setForeground(new java.awt.Color(81, 124, 191));
        lbNivel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNivel15.setText("Conductas Problemáticas");
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
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Reporte de las conductas problemáticas del estudiante");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNivel15, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(btnReporteConductas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbNivel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReporteConductas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 450, 180));
        jPanel11.getAccessibleContext().setAccessibleName("");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 94, 152)));

        lbNivel12.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        lbNivel12.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNivel12.setText("Seguimiento Conductual Diario");
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
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Reporte promedio mensual agrupado por categorías");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNivel12, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReporteSeguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbNivel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReporteSeguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 240, 450, 180));
        jPanel8.getAccessibleContext().setAccessibleName("");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(23, 64, 112)));

        lbNivel13.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        lbNivel13.setForeground(new java.awt.Color(23, 64, 112));
        lbNivel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNivel13.setText("Plan Individual Actual");
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
        jLabel25.setText("Resumen del plan de intervención actual vigente");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNivel13, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReportePlan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbNivel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnReportePlan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 450, 180));
        jPanel9.getAccessibleContext().setAccessibleName("");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(23, 50, 78)));

        lbNivel14.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        lbNivel14.setForeground(new java.awt.Color(23, 50, 78));
        lbNivel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNivel14.setText("Reporte de Asistencia");
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
        jLabel27.setText("Asistencia del estudiante del último mes");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNivel14, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(btnReporteAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(lbNivel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReporteAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 440, 450, 180));
        jPanel10.getAccessibleContext().setAccessibleName("");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Reporte resumen del Aula:");

        btnReporteAula.setBackground(new java.awt.Color(102, 102, 102));
        btnReporteAula.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnReporteAula.setForeground(new java.awt.Color(255, 255, 255));
        btnReporteAula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/File_dock_fill-2.png"))); // NOI18N
        btnReporteAula.setText("GENERAR PDF");
        btnReporteAula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReporteAula.setFocusPainted(false);
        btnReporteAula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteAulaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                .addComponent(btnReporteAula, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnReporteAula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 950, -1));

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

    private void btnReporteSeguimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteSeguimientoActionPerformed
        generarReporteSeguimiento();
    }//GEN-LAST:event_btnReporteSeguimientoActionPerformed

    private void btnReporteConductasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteConductasActionPerformed
        generarReporteConductas();
    }//GEN-LAST:event_btnReporteConductasActionPerformed

    private void btnReportePlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportePlanActionPerformed
        generarReportePlan();
    }//GEN-LAST:event_btnReportePlanActionPerformed

    private void btnReporteAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteAsistenciaActionPerformed
        generarReporteAsistencia();
    }//GEN-LAST:event_btnReporteAsistenciaActionPerformed

    private void btnReporteAulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteAulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReporteAulaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporteAsistencia;
    private javax.swing.JButton btnReporteAula;
    private javax.swing.JButton btnReporteConductas;
    private javax.swing.JButton btnReportePlan;
    private javax.swing.JButton btnReporteSeguimiento;
    private javax.swing.JComboBox<Estudiante> cbListaEstudiantes;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbNivel12;
    private javax.swing.JLabel lbNivel13;
    private javax.swing.JLabel lbNivel14;
    private javax.swing.JLabel lbNivel15;
    // End of variables declaration//GEN-END:variables
}
