/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Docente;

import controller.LoginCtrl;
import dao.DocenteImp;
import dao.UsuarioImp;
import java.awt.Color;
import security.SeguridadSesion;
import utillities.FechaUtil;
import utillities.VistaUtil;
import view.Login;

public class VistaMenuDocente extends javax.swing.JFrame {

    private VistaReporteDocente vReporteDocente;
    private VistaDashboardDocente vDashboardDocente;
    private VistaSeguimiento vSeguimiento;
    private VistaIncidente vIncidente;
    private VistaPlanIndividual vPlanIndividual;
    private int idDocente;

    Color azul = new Color(10, 93, 175);

    public VistaMenuDocente(int idDocente) {
        this.idDocente = idDocente;
        setUndecorated(true);
        initComponents();
        setDashboardDocente();
        lbNomUsuario.setText(SeguridadSesion.getNombreCompleto());
        FechaUtil.mostrarDia(lbFechaHoy);

    }

    public void setReporteDocente() {
        if (vReporteDocente == null) {
            vReporteDocente = new VistaReporteDocente(idDocente);
        }
        VistaUtil.cambiarVistaPanel(jpContenedor, vReporteDocente);
    }

    public void setDashboardDocente() {
        if (vDashboardDocente == null) {
            vDashboardDocente = new VistaDashboardDocente(idDocente);
        } else {
            vDashboardDocente.actualizarDatosDashboard();
        }

        VistaUtil.cambiarVistaPanel(jpContenedor, vDashboardDocente);
    }

    public void setSeguimiento() {
        if (vSeguimiento == null) {
            vSeguimiento = new VistaSeguimiento(idDocente);
        }
        VistaUtil.cambiarVistaPanel(jpContenedor, vSeguimiento);
    }

    public void setIncidente() {
        if (vIncidente == null) {
            vIncidente = new VistaIncidente(idDocente);
        }
        VistaUtil.cambiarVistaPanel(jpContenedor, vIncidente);
    }

    public void setPlanIndividual() {
        if (vPlanIndividual == null) {
            vPlanIndividual = new VistaPlanIndividual(idDocente);
        }
        VistaUtil.cambiarVistaPanel(jpContenedor, vPlanIndividual);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpMenuBar = new javax.swing.JPanel();
        jpDashboard = new javax.swing.JPanel();
        lbDashboard = new javax.swing.JLabel();
        bDashboard = new javax.swing.JPanel();
        jpSeguimiento = new javax.swing.JPanel();
        lbSeguimiento = new javax.swing.JLabel();
        bSeguimiento = new javax.swing.JPanel();
        jpIncidentes = new javax.swing.JPanel();
        lbIncidentes = new javax.swing.JLabel();
        bIncidentes = new javax.swing.JPanel();
        jpPlanIndividual = new javax.swing.JPanel();
        lbPlanIndividual = new javax.swing.JLabel();
        bPlanIndividual = new javax.swing.JPanel();
        jpReportes = new javax.swing.JPanel();
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
        setMinimumSize(new java.awt.Dimension(1250, 800));
        setPreferredSize(new java.awt.Dimension(1250, 800));
        setResizable(false);

        jpMenuBar.setBackground(new java.awt.Color(255, 255, 255));
        jpMenuBar.setMinimumSize(new java.awt.Dimension(1250, 50));
        jpMenuBar.setPreferredSize(new java.awt.Dimension(1250, 50));
        jpMenuBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpDashboard.setBackground(new java.awt.Color(255, 255, 255));
        jpDashboard.setForeground(new java.awt.Color(255, 255, 255));
        jpDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbDashboard.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        lbDashboard.setForeground(new java.awt.Color(23, 64, 112));
        lbDashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Waterfall-1.png"))); // NOI18N
        lbDashboard.setText("Dashboard");
        lbDashboard.setToolTipText("");
        lbDashboard.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbDashboard.setIconTextGap(2);
        lbDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDashboardMouseClicked(evt);
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
                    .addComponent(lbDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpDashboardLayout.setVerticalGroup(
            jpDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDashboardLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbDashboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(bDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jpMenuBar.add(jpDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 125, 50));

        jpSeguimiento.setBackground(new java.awt.Color(255, 255, 255));
        jpSeguimiento.setForeground(new java.awt.Color(255, 255, 255));
        jpSeguimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbSeguimiento.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        lbSeguimiento.setForeground(new java.awt.Color(23, 64, 112));
        lbSeguimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSeguimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/User_scan-1.png"))); // NOI18N
        lbSeguimiento.setText("Seguimiento");
        lbSeguimiento.setToolTipText("");
        lbSeguimiento.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbSeguimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbSeguimiento.setIconTextGap(2);
        lbSeguimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSeguimientoMouseClicked(evt);
            }
        });

        bSeguimiento.setBackground(new java.awt.Color(255, 255, 255));
        bSeguimiento.setPreferredSize(new java.awt.Dimension(120, 2));

        javax.swing.GroupLayout bSeguimientoLayout = new javax.swing.GroupLayout(bSeguimiento);
        bSeguimiento.setLayout(bSeguimientoLayout);
        bSeguimientoLayout.setHorizontalGroup(
            bSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bSeguimientoLayout.setVerticalGroup(
            bSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpSeguimientoLayout = new javax.swing.GroupLayout(jpSeguimiento);
        jpSeguimiento.setLayout(jpSeguimientoLayout);
        jpSeguimientoLayout.setHorizontalGroup(
            jpSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSeguimientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSeguimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bSeguimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpSeguimientoLayout.setVerticalGroup(
            jpSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSeguimientoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbSeguimiento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bSeguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jpMenuBar.add(jpSeguimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 125, 50));

        jpIncidentes.setBackground(new java.awt.Color(255, 255, 255));
        jpIncidentes.setForeground(new java.awt.Color(255, 255, 255));
        jpIncidentes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbIncidentes.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        lbIncidentes.setForeground(new java.awt.Color(23, 64, 112));
        lbIncidentes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIncidentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Flag_fill-1.png"))); // NOI18N
        lbIncidentes.setText("Incidentes");
        lbIncidentes.setToolTipText("");
        lbIncidentes.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbIncidentes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbIncidentes.setIconTextGap(2);
        lbIncidentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbIncidentesMouseClicked(evt);
            }
        });

        bIncidentes.setBackground(new java.awt.Color(255, 255, 255));
        bIncidentes.setPreferredSize(new java.awt.Dimension(120, 2));

        javax.swing.GroupLayout bIncidentesLayout = new javax.swing.GroupLayout(bIncidentes);
        bIncidentes.setLayout(bIncidentesLayout);
        bIncidentesLayout.setHorizontalGroup(
            bIncidentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bIncidentesLayout.setVerticalGroup(
            bIncidentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpIncidentesLayout = new javax.swing.GroupLayout(jpIncidentes);
        jpIncidentes.setLayout(jpIncidentesLayout);
        jpIncidentesLayout.setHorizontalGroup(
            jpIncidentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpIncidentesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpIncidentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbIncidentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bIncidentes, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpIncidentesLayout.setVerticalGroup(
            jpIncidentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpIncidentesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbIncidentes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bIncidentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jpMenuBar.add(jpIncidentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 125, 50));

        jpPlanIndividual.setBackground(new java.awt.Color(255, 255, 255));
        jpPlanIndividual.setForeground(new java.awt.Color(255, 255, 255));
        jpPlanIndividual.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbPlanIndividual.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        lbPlanIndividual.setForeground(new java.awt.Color(23, 64, 112));
        lbPlanIndividual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPlanIndividual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/File_dock_add-1.png"))); // NOI18N
        lbPlanIndividual.setText("Plan Individual");
        lbPlanIndividual.setToolTipText("");
        lbPlanIndividual.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbPlanIndividual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbPlanIndividual.setIconTextGap(2);
        lbPlanIndividual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPlanIndividualMouseClicked(evt);
            }
        });

        bPlanIndividual.setBackground(new java.awt.Color(255, 255, 255));
        bPlanIndividual.setPreferredSize(new java.awt.Dimension(120, 2));

        javax.swing.GroupLayout bPlanIndividualLayout = new javax.swing.GroupLayout(bPlanIndividual);
        bPlanIndividual.setLayout(bPlanIndividualLayout);
        bPlanIndividualLayout.setHorizontalGroup(
            bPlanIndividualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bPlanIndividualLayout.setVerticalGroup(
            bPlanIndividualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpPlanIndividualLayout = new javax.swing.GroupLayout(jpPlanIndividual);
        jpPlanIndividual.setLayout(jpPlanIndividualLayout);
        jpPlanIndividualLayout.setHorizontalGroup(
            jpPlanIndividualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPlanIndividualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPlanIndividualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPlanIndividual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bPlanIndividual, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpPlanIndividualLayout.setVerticalGroup(
            jpPlanIndividualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPlanIndividualLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbPlanIndividual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bPlanIndividual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jpMenuBar.add(jpPlanIndividual, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 125, 50));

        jpReportes.setBackground(new java.awt.Color(255, 255, 255));
        jpReportes.setForeground(new java.awt.Color(255, 255, 255));
        jpReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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

        javax.swing.GroupLayout jpReportesLayout = new javax.swing.GroupLayout(jpReportes);
        jpReportes.setLayout(jpReportesLayout);
        jpReportesLayout.setHorizontalGroup(
            jpReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpReportesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReportes, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpReportesLayout.setVerticalGroup(
            jpReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpReportesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbReportes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jpMenuBar.add(jpReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 125, 50));

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

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDashboardMouseClicked
        VistaUtil.resaltarOpcionMenu(bDashboard, bSeguimiento, bIncidentes, bPlanIndividual, bReportes);
        setDashboardDocente();


    }//GEN-LAST:event_lbDashboardMouseClicked

    private void lbReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbReportesMouseClicked

        VistaUtil.resaltarOpcionMenu(bReportes, bSeguimiento, bIncidentes, bPlanIndividual, bDashboard);
        setReporteDocente();


    }//GEN-LAST:event_lbReportesMouseClicked

    private void lbSeguimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSeguimientoMouseClicked

        VistaUtil.resaltarOpcionMenu(bSeguimiento, bDashboard, bIncidentes, bPlanIndividual, bReportes);
        setSeguimiento();


    }//GEN-LAST:event_lbSeguimientoMouseClicked

    private void lbIncidentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbIncidentesMouseClicked

        VistaUtil.resaltarOpcionMenu(bIncidentes, bSeguimiento, bDashboard, bPlanIndividual, bReportes);
        setIncidente();


    }//GEN-LAST:event_lbIncidentesMouseClicked

    private void lbPlanIndividualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPlanIndividualMouseClicked

        VistaUtil.resaltarOpcionMenu(bPlanIndividual, bSeguimiento, bDashboard, bIncidentes, bReportes);
        setPlanIndividual();


    }//GEN-LAST:event_lbPlanIndividualMouseClicked

    private void lbSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSalirMouseClicked

        if (VistaUtil.modalConfirmarSalir(this)) {
            SeguridadSesion.cerrarSesion();

            LoginCtrl loginCtrl = new LoginCtrl(new UsuarioImp(), new DocenteImp());
            VistaUtil.mostrarVista(new Login(loginCtrl));
            this.dispose();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_lbSalirMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bDashboard;
    private javax.swing.JPanel bIncidentes;
    private javax.swing.JPanel bPlanIndividual;
    private javax.swing.JPanel bReportes;
    private javax.swing.JPanel bSeguimiento;
    private javax.swing.JPanel jpBarraSup;
    private javax.swing.JPanel jpContenedor;
    private javax.swing.JPanel jpDashboard;
    private javax.swing.JPanel jpIncidentes;
    private javax.swing.JPanel jpMenuBar;
    private javax.swing.JPanel jpPlanIndividual;
    private javax.swing.JPanel jpReportes;
    private javax.swing.JPanel jpSeguimiento;
    private javax.swing.JLabel lbBienvenida;
    private javax.swing.JLabel lbDashboard;
    private javax.swing.JLabel lbFechaHoy;
    private javax.swing.JLabel lbIncidentes;
    private javax.swing.JLabel lbMax;
    private javax.swing.JLabel lbMin;
    private javax.swing.JLabel lbNomUsuario;
    private javax.swing.JLabel lbNombreSistema;
    private javax.swing.JLabel lbPlanIndividual;
    private javax.swing.JLabel lbReportes;
    private javax.swing.JLabel lbSalir;
    private javax.swing.JLabel lbSeguimiento;
    // End of variables declaration//GEN-END:variables
}
