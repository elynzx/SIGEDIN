
package view.Secretaria;

import controller.LoginCtrl;
import dao.DocenteImp;
import dao.UsuarioImp;
import security.SeguridadSesion;
import utilities.FechaUtil;
import utilities.VistaUtil;
import view.Login;

public class VistaMenuSecretaria extends javax.swing.JFrame {

    private VistaDashboardSecretaria vDashboardSecretaria;
    private VistaEstudiantes vEstudiantes;
    private VistaMatricula vMatricula;
    private VistaReporteSecretaria vReporteSecretaria;
    private int idSecretaria;

    public VistaMenuSecretaria(int idSecretaria) {
        this.idSecretaria = idSecretaria;
        initComponents();
        setDashboardSecretaria();
        lbNomUsuario.setText(SeguridadSesion.getNombreCompleto());
        FechaUtil.mostrarDia(lbFechaHoy);
    }


    public void setDashboardSecretaria() {
        if (vDashboardSecretaria == null) {
            vDashboardSecretaria = new VistaDashboardSecretaria(idSecretaria);
        } else {
//            vDashboardSecretaria.actualizarDatosDashboard();
        }
        VistaUtil.cambiarVistaPanel(jpContenedor, vDashboardSecretaria);
    }

    public void setMatricula() {
        if (vMatricula == null) {
            vMatricula = new VistaMatricula(idSecretaria);
        }
        VistaUtil.cambiarVistaPanel(jpContenedor, vMatricula);
    }

    public void setEstudiante() {
        if (vEstudiantes == null) {
            vEstudiantes = new VistaEstudiantes(idSecretaria);
        }
        VistaUtil.cambiarVistaPanel(jpContenedor, vEstudiantes);
    }

    public void setReporteSecretaria() {
        if (vReporteSecretaria == null) {
            vReporteSecretaria = new VistaReporteSecretaria(idSecretaria);
        }
        VistaUtil.cambiarVistaPanel(jpContenedor, vReporteSecretaria);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpMenuBar = new javax.swing.JPanel();
        jpDashboard = new javax.swing.JPanel();
        lbDashboardSecretaria = new javax.swing.JLabel();
        bDashboard = new javax.swing.JPanel();
        jpSeguimiento = new javax.swing.JPanel();
        lbMatricula = new javax.swing.JLabel();
        bMatricula = new javax.swing.JPanel();
        jpIncidentes = new javax.swing.JPanel();
        lbEstudiantes = new javax.swing.JLabel();
        bEstudiantes = new javax.swing.JPanel();
        jpPlanIndividual = new javax.swing.JPanel();
        lbReportes = new javax.swing.JLabel();
        bReportes = new javax.swing.JPanel();
        lbNomUsuario = new javax.swing.JLabel();
        lbBienvenida = new javax.swing.JLabel();
        lbFechaHoy = new javax.swing.JLabel();
        jpContenedor = new javax.swing.JPanel();
        jpBarraSup = new javax.swing.JPanel();
        lbNombreSistema = new javax.swing.JLabel();
        lbSalir = new javax.swing.JLabel();
        lbMin = new javax.swing.JLabel();
        lbMax = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpMenuBar.setBackground(new java.awt.Color(255, 255, 255));
        jpMenuBar.setMinimumSize(new java.awt.Dimension(1250, 50));
        jpMenuBar.setPreferredSize(new java.awt.Dimension(1250, 50));
        jpMenuBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpDashboard.setBackground(new java.awt.Color(255, 255, 255));
        jpDashboard.setForeground(new java.awt.Color(255, 255, 255));
        jpDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbDashboardSecretaria.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        lbDashboardSecretaria.setForeground(new java.awt.Color(23, 64, 112));
        lbDashboardSecretaria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDashboardSecretaria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Waterfall-1.png"))); // NOI18N
        lbDashboardSecretaria.setText("Dashboard");
        lbDashboardSecretaria.setToolTipText("");
        lbDashboardSecretaria.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbDashboardSecretaria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbDashboardSecretaria.setIconTextGap(2);
        lbDashboardSecretaria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDashboardSecretariaMouseClicked(evt);
            }
        });

        bDashboard.setBackground(new java.awt.Color(23, 64, 112));
        bDashboard.setPreferredSize(new java.awt.Dimension(120, 2));

        javax.swing.GroupLayout bDashboardLayout = new javax.swing.GroupLayout(bDashboard);
        bDashboard.setLayout(bDashboardLayout);
        bDashboardLayout.setHorizontalGroup(
            bDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bDashboardLayout.setVerticalGroup(
            bDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpDashboardLayout = new javax.swing.GroupLayout(jpDashboard);
        jpDashboard.setLayout(jpDashboardLayout);
        jpDashboardLayout.setHorizontalGroup(
            jpDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDashboardSecretaria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpDashboardLayout.setVerticalGroup(
            jpDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDashboardLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbDashboardSecretaria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(bDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jpMenuBar.add(jpDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 125, 50));

        jpSeguimiento.setBackground(new java.awt.Color(255, 255, 255));
        jpSeguimiento.setForeground(new java.awt.Color(255, 255, 255));
        jpSeguimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbMatricula.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        lbMatricula.setForeground(new java.awt.Color(23, 64, 112));
        lbMatricula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Sertificate-2.png"))); // NOI18N
        lbMatricula.setText("Matricula");
        lbMatricula.setToolTipText("");
        lbMatricula.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbMatricula.setIconTextGap(2);
        lbMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMatriculaMouseClicked(evt);
            }
        });

        bMatricula.setBackground(new java.awt.Color(255, 255, 255));
        bMatricula.setPreferredSize(new java.awt.Dimension(120, 2));

        javax.swing.GroupLayout bMatriculaLayout = new javax.swing.GroupLayout(bMatricula);
        bMatricula.setLayout(bMatriculaLayout);
        bMatriculaLayout.setHorizontalGroup(
            bMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bMatriculaLayout.setVerticalGroup(
            bMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpSeguimientoLayout = new javax.swing.GroupLayout(jpSeguimiento);
        jpSeguimiento.setLayout(jpSeguimientoLayout);
        jpSeguimientoLayout.setHorizontalGroup(
            jpSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSeguimientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpSeguimientoLayout.setVerticalGroup(
            jpSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSeguimientoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbMatricula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jpMenuBar.add(jpSeguimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 125, 50));

        jpIncidentes.setBackground(new java.awt.Color(255, 255, 255));
        jpIncidentes.setForeground(new java.awt.Color(255, 255, 255));
        jpIncidentes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbEstudiantes.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        lbEstudiantes.setForeground(new java.awt.Color(23, 64, 112));
        lbEstudiantes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEstudiantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/User_fill-1.png"))); // NOI18N
        lbEstudiantes.setText("Estudiantes");
        lbEstudiantes.setToolTipText("");
        lbEstudiantes.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbEstudiantes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbEstudiantes.setIconTextGap(2);
        lbEstudiantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbEstudiantesMouseClicked(evt);
            }
        });

        bEstudiantes.setBackground(new java.awt.Color(255, 255, 255));
        bEstudiantes.setPreferredSize(new java.awt.Dimension(120, 2));

        javax.swing.GroupLayout bEstudiantesLayout = new javax.swing.GroupLayout(bEstudiantes);
        bEstudiantes.setLayout(bEstudiantesLayout);
        bEstudiantesLayout.setHorizontalGroup(
            bEstudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bEstudiantesLayout.setVerticalGroup(
            bEstudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpIncidentesLayout = new javax.swing.GroupLayout(jpIncidentes);
        jpIncidentes.setLayout(jpIncidentesLayout);
        jpIncidentesLayout.setHorizontalGroup(
            jpIncidentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpIncidentesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpIncidentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEstudiantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bEstudiantes, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpIncidentesLayout.setVerticalGroup(
            jpIncidentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpIncidentesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbEstudiantes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jpMenuBar.add(jpIncidentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 125, 50));

        jpPlanIndividual.setBackground(new java.awt.Color(255, 255, 255));
        jpPlanIndividual.setForeground(new java.awt.Color(255, 255, 255));
        jpPlanIndividual.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbReportes.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        lbReportes.setForeground(new java.awt.Color(23, 64, 112));
        lbReportes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/paper_clip-1.png"))); // NOI18N
        lbReportes.setText("Reportes");
        lbReportes.setToolTipText("");
        lbReportes.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbReportes.setIconTextGap(2);
        lbReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbReportesMouseClicked(evt);
            }
        });

        bReportes.setBackground(new java.awt.Color(255, 255, 255));
        bReportes.setPreferredSize(new java.awt.Dimension(120, 2));

        javax.swing.GroupLayout bReportesLayout = new javax.swing.GroupLayout(bReportes);
        bReportes.setLayout(bReportesLayout);
        bReportesLayout.setHorizontalGroup(
            bReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bReportesLayout.setVerticalGroup(
            bReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpPlanIndividualLayout = new javax.swing.GroupLayout(jpPlanIndividual);
        jpPlanIndividual.setLayout(jpPlanIndividualLayout);
        jpPlanIndividualLayout.setHorizontalGroup(
            jpPlanIndividualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPlanIndividualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPlanIndividualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReportes, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpPlanIndividualLayout.setVerticalGroup(
            jpPlanIndividualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPlanIndividualLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbReportes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jpMenuBar.add(jpPlanIndividual, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 125, 50));

        lbNomUsuario.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        lbNomUsuario.setForeground(new java.awt.Color(161, 34, 130));
        lbNomUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNomUsuario.setText("Evelyn Pascual");
        lbNomUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpMenuBar.add(lbNomUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 140, 40));

        lbBienvenida.setBackground(new java.awt.Color(255, 255, 255));
        lbBienvenida.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbBienvenida.setForeground(new java.awt.Color(161, 34, 130));
        lbBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbBienvenida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/User_cicrle-2_1.png"))); // NOI18N
        lbBienvenida.setText("Hola,");
        lbBienvenida.setToolTipText("");
        lbBienvenida.setPreferredSize(new java.awt.Dimension(70, 25));
        jpMenuBar.add(lbBienvenida, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 70, 40));

        lbFechaHoy.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        lbFechaHoy.setForeground(new java.awt.Color(39, 84, 138));
        lbFechaHoy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbFechaHoy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Date_fill-1_1.png"))); // NOI18N
        lbFechaHoy.setText("Fecha de hoy");
        jpMenuBar.add(lbFechaHoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 250, 40));

        jpContenedor.setBackground(new java.awt.Color(255, 255, 255));
        jpContenedor.setMinimumSize(new java.awt.Dimension(1250, 710));
        jpContenedor.setPreferredSize(new java.awt.Dimension(1250, 710));
        jpContenedor.setLayout(new java.awt.BorderLayout());

        jpBarraSup.setBackground(new java.awt.Color(161, 34, 130));
        jpBarraSup.setMinimumSize(new java.awt.Dimension(1250, 40));
        jpBarraSup.setPreferredSize(new java.awt.Dimension(1250, 40));
        jpBarraSup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbNombreSistema.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        lbNombreSistema.setForeground(new java.awt.Color(255, 255, 255));
        lbNombreSistema.setText("Sistema de Gestion de Educación Inclusiva");
        jpBarraSup.add(lbNombreSistema, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 330, 38));

        lbSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_1.png"))); // NOI18N
        lbSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSalirMouseClicked(evt);
            }
        });
        jpBarraSup.add(lbSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1206, 0, 38, 38));

        lbMin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimize.png"))); // NOI18N
        lbMin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpBarraSup.add(lbMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1118, 0, 38, 38));

        lbMax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/maximize.png"))); // NOI18N
        lbMax.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpBarraSup.add(lbMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(1162, 0, 38, 38));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMenuBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpBarraSup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpBarraSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpMenuBar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbDashboardSecretariaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDashboardSecretariaMouseClicked
        VistaUtil.resaltarOpcionMenu(bDashboard, bMatricula, bEstudiantes, bReportes);
        setDashboardSecretaria();

    }//GEN-LAST:event_lbDashboardSecretariaMouseClicked

    private void lbMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMatriculaMouseClicked

        VistaUtil.resaltarOpcionMenu(bMatricula, bDashboard, bEstudiantes, bReportes);
        setMatricula();

    }//GEN-LAST:event_lbMatriculaMouseClicked

    private void lbEstudiantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbEstudiantesMouseClicked

        VistaUtil.resaltarOpcionMenu(bEstudiantes, bMatricula, bDashboard, bReportes);
        setEstudiante();

    }//GEN-LAST:event_lbEstudiantesMouseClicked

    private void lbReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbReportesMouseClicked

        VistaUtil.resaltarOpcionMenu(bReportes, bMatricula, bDashboard, bEstudiantes);
        setReporteSecretaria();

    }//GEN-LAST:event_lbReportesMouseClicked

    private void lbSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSalirMouseClicked

        if (VistaUtil.modalConfirmarSalir(this)) {
            SeguridadSesion.cerrarSesion();
            LoginCtrl loginCtrl = new LoginCtrl(new UsuarioImp());
            VistaUtil.mostrarVista(new Login(loginCtrl));
            this.dispose();
        }
    }//GEN-LAST:event_lbSalirMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bDashboard;
    private javax.swing.JPanel bEstudiantes;
    private javax.swing.JPanel bMatricula;
    private javax.swing.JPanel bReportes;
    private javax.swing.JPanel jpBarraSup;
    private javax.swing.JPanel jpContenedor;
    private javax.swing.JPanel jpDashboard;
    private javax.swing.JPanel jpIncidentes;
    private javax.swing.JPanel jpMenuBar;
    private javax.swing.JPanel jpPlanIndividual;
    private javax.swing.JPanel jpSeguimiento;
    private javax.swing.JLabel lbBienvenida;
    private javax.swing.JLabel lbDashboardSecretaria;
    private javax.swing.JLabel lbEstudiantes;
    private javax.swing.JLabel lbFechaHoy;
    private javax.swing.JLabel lbMatricula;
    private javax.swing.JLabel lbMax;
    private javax.swing.JLabel lbMin;
    private javax.swing.JLabel lbNomUsuario;
    private javax.swing.JLabel lbNombreSistema;
    private javax.swing.JLabel lbReportes;
    private javax.swing.JLabel lbSalir;
    // End of variables declaration//GEN-END:variables
}
