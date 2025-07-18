/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Secretaria;

import controller.secretaria.SecretariaCtrl;
import javax.swing.JLabel;

/**
 *
 * @author rpasc
 */
public class ReportesMatricula extends javax.swing.JFrame {

   private SecretariaCtrl secretaria;
    public ReportesMatricula() {
        initComponents();
        secretaria = new SecretariaCtrl(this);
    }

    public JLabel getJlblestudiantes() {
        return jlblestudiantes;
    }

    public JLabel getJlblinicio() {
        return jlblinicio;
    }

    public JLabel getJlblmatricula() {
        return jlblmatricula;
    }

    public JLabel getJlblreportes() {
        return jlblreportes;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        mSalir = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbNomUsuario = new javax.swing.JLabel();
        mInicio1 = new javax.swing.JPanel();
        jlblinicio = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        mEvaluaciones = new javax.swing.JPanel();
        jlblmatricula = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        mIndividual = new javax.swing.JPanel();
        jlblestudiantes = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        mReportes = new javax.swing.JPanel();
        jlblreportes = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbNivel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        btnCrearTicket4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lbNivel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        btnCrearTicket7 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbNivel13 = new javax.swing.JLabel();
        btnCrearTicket9 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbNivel12 = new javax.swing.JLabel();
        btnCrearTicket8 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(237, 237, 237));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo02.png"))); // NOI18N
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 0, 74, 90));

        mSalir.setBackground(new java.awt.Color(237, 237, 237));
        mSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Log Out_2.png"))); // NOI18N
        jLabel23.setToolTipText("");
        jLabel23.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        jLabel24.setBackground(new java.awt.Color(51, 51, 51));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("SALIR");
        jLabel24.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout mSalirLayout = new javax.swing.GroupLayout(mSalir);
        mSalir.setLayout(mSalirLayout);
        mSalirLayout.setHorizontalGroup(
            mSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mSalirLayout.setVerticalGroup(
            mSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mSalirLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel24)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel5.add(mSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 0, 70, 90));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Hola,");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 40, -1));

        lbNomUsuario.setFont(new java.awt.Font("Segoe UI Black", 0, 16)); // NOI18N
        lbNomUsuario.setForeground(new java.awt.Color(51, 51, 51));
        lbNomUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNomUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/User Alt3.png"))); // NOI18N
        lbNomUsuario.setText("Evelyn Pascual");
        lbNomUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(lbNomUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 170, -1));

        mInicio1.setBackground(new java.awt.Color(237, 237, 237));
        mInicio1.setForeground(new java.awt.Color(255, 255, 255));
        mInicio1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlblinicio.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jlblinicio.setForeground(new java.awt.Color(255, 255, 255));
        jlblinicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblinicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Alarmclock-2.png"))); // NOI18N
        jlblinicio.setToolTipText("");
        jlblinicio.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jlblinicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblinicioMouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(58, 163, 163));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("INICIO");
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout mInicio1Layout = new javax.swing.GroupLayout(mInicio1);
        mInicio1.setLayout(mInicio1Layout);
        mInicio1Layout.setHorizontalGroup(
            mInicio1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblinicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        mInicio1Layout.setVerticalGroup(
            mInicio1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mInicio1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jlblinicio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel17)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel5.add(mInicio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 120, 90));

        mEvaluaciones.setBackground(new java.awt.Color(237, 237, 237));
        mEvaluaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlblmatricula.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jlblmatricula.setForeground(new java.awt.Color(255, 255, 255));
        jlblmatricula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Alarmclock-2.png"))); // NOI18N
        jlblmatricula.setToolTipText("");
        jlblmatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblmatriculaMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(66, 128, 191));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("MATRICULA");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout mEvaluacionesLayout = new javax.swing.GroupLayout(mEvaluaciones);
        mEvaluaciones.setLayout(mEvaluacionesLayout);
        mEvaluacionesLayout.setHorizontalGroup(
            mEvaluacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblmatricula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        mEvaluacionesLayout.setVerticalGroup(
            mEvaluacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mEvaluacionesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jlblmatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel16)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel5.add(mEvaluaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 120, 90));

        mIndividual.setBackground(new java.awt.Color(237, 237, 237));
        mIndividual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlblestudiantes.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jlblestudiantes.setForeground(new java.awt.Color(255, 255, 255));
        jlblestudiantes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblestudiantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Alarmclock-2.png"))); // NOI18N
        jlblestudiantes.setToolTipText("");
        jlblestudiantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblestudiantesMouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(126, 3, 126));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("ESTUDIANTES");
        jLabel20.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout mIndividualLayout = new javax.swing.GroupLayout(mIndividual);
        mIndividual.setLayout(mIndividualLayout);
        mIndividualLayout.setHorizontalGroup(
            mIndividualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblestudiantes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        mIndividualLayout.setVerticalGroup(
            mIndividualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mIndividualLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jlblestudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel20)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel5.add(mIndividual, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 120, 90));

        mReportes.setBackground(new java.awt.Color(255, 255, 255));
        mReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mReportes.setPreferredSize(new java.awt.Dimension(120, 90));

        jlblreportes.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jlblreportes.setForeground(new java.awt.Color(255, 255, 255));
        jlblreportes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblreportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Alarmclock-2.png"))); // NOI18N
        jlblreportes.setToolTipText("");
        jlblreportes.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jlblreportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblreportesMouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(214, 64, 64));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("REPORTES");
        jLabel22.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout mReportesLayout = new javax.swing.GroupLayout(mReportes);
        mReportes.setLayout(mReportesLayout);
        mReportesLayout.setHorizontalGroup(
            mReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblreportes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        mReportesLayout.setVerticalGroup(
            mReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mReportesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jlblreportes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel22)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel5.add(mReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 120, 90));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Reportes");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 490, -1));

        jPanel6.setBackground(new java.awt.Color(249, 246, 231));

        lbNivel10.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbNivel10.setForeground(new java.awt.Color(51, 51, 51));
        lbNivel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel10.setText("Reporte por diagnóstico");
        lbNivel10.setToolTipText("");
        lbNivel10.setPreferredSize(new java.awt.Dimension(70, 25));

        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Selecciona diagnóstico:");

        jComboBox6.setForeground(new java.awt.Color(102, 102, 102));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Atención", "Escape", "Sensorial", "Acceso a objetos o actividades", "Control / evitar tarea", "Evación de exigencias", "Ninguna identificada (requiere observación)" }));
        jComboBox6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        btnCrearTicket4.setBackground(new java.awt.Color(102, 102, 102));
        btnCrearTicket4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        btnCrearTicket4.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearTicket4.setText("GENERAR PDF");
        btnCrearTicket4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearTicket4.setFocusPainted(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Reporte agrupado por diagnóstico y nivel funcional");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(btnCrearTicket4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNivel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbNivel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCrearTicket4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 430, -1));

        jPanel7.setBackground(new java.awt.Color(249, 246, 231));

        lbNivel11.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbNivel11.setForeground(new java.awt.Color(51, 51, 51));
        lbNivel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel11.setText("Reporte de matrícula por aula");
        lbNivel11.setToolTipText("");
        lbNivel11.setPreferredSize(new java.awt.Dimension(70, 25));

        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Selecciona aula:");

        jComboBox7.setForeground(new java.awt.Color(102, 102, 102));
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Atención", "Escape", "Sensorial", "Acceso a objetos o actividades", "Control / evitar tarea", "Evación de exigencias", "Ninguna identificada (requiere observación)" }));
        jComboBox7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        btnCrearTicket7.setBackground(new java.awt.Color(102, 102, 102));
        btnCrearTicket7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        btnCrearTicket7.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearTicket7.setText("GENERAR PDF");
        btnCrearTicket7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearTicket7.setFocusPainted(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Reporte de estudiantes matriculados");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9)
                    .addComponent(btnCrearTicket7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox7, 0, 315, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNivel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbNivel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCrearTicket7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 430, -1));

        jPanel9.setBackground(new java.awt.Color(249, 246, 231));

        lbNivel13.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbNivel13.setForeground(new java.awt.Color(51, 51, 51));
        lbNivel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel13.setText("Reporte de vacantes");
        lbNivel13.setToolTipText("");
        lbNivel13.setPreferredSize(new java.awt.Dimension(70, 25));

        btnCrearTicket9.setBackground(new java.awt.Color(102, 102, 102));
        btnCrearTicket9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        btnCrearTicket9.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearTicket9.setText("GENERAR PDF");
        btnCrearTicket9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearTicket9.setFocusPainted(false);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("Lista de aulas con vacantes disponibles");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrearTicket9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbNivel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)))
                .addGap(54, 54, 54))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbNivel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCrearTicket9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, 430, 170));

        jPanel8.setBackground(new java.awt.Color(249, 246, 231));

        lbNivel12.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbNivel12.setForeground(new java.awt.Color(51, 51, 51));
        lbNivel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel12.setText("Reporte general de matrícula");
        lbNivel12.setToolTipText("");
        lbNivel12.setPreferredSize(new java.awt.Dimension(70, 25));

        btnCrearTicket8.setBackground(new java.awt.Color(102, 102, 102));
        btnCrearTicket8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        btnCrearTicket8.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearTicket8.setText("GENERAR PDF");
        btnCrearTicket8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearTicket8.setFocusPainted(false);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Lista completa de estudiantes y datos principales");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrearTicket8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                        .addComponent(lbNivel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbNivel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCrearTicket8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, -1, 170));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlblinicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblinicioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlblinicioMouseClicked

    private void jlblmatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmatriculaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlblmatriculaMouseClicked

    private void jlblestudiantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblestudiantesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlblestudiantesMouseClicked

    private void jlblreportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblreportesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlblreportesMouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel23MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearTicket4;
    private javax.swing.JButton btnCrearTicket7;
    private javax.swing.JButton btnCrearTicket8;
    private javax.swing.JButton btnCrearTicket9;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jlblestudiantes;
    private javax.swing.JLabel jlblinicio;
    private javax.swing.JLabel jlblmatricula;
    private javax.swing.JLabel jlblreportes;
    private javax.swing.JLabel lbNivel10;
    private javax.swing.JLabel lbNivel11;
    private javax.swing.JLabel lbNivel12;
    private javax.swing.JLabel lbNivel13;
    private javax.swing.JLabel lbNomUsuario;
    private javax.swing.JPanel mEvaluaciones;
    private javax.swing.JPanel mIndividual;
    private javax.swing.JPanel mInicio1;
    private javax.swing.JPanel mReportes;
    private javax.swing.JPanel mSalir;
    // End of variables declaration//GEN-END:variables
}
