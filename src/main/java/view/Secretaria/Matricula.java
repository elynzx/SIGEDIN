/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Secretaria;

import controller.secretaria.SecretariaCtrl;
import java.sql.Date;
import javax.swing.JLabel;

/**
 *
 * @author rpasc
 */
public class Matricula extends javax.swing.JFrame {
    

   private SecretariaCtrl secretaria;
    public Matricula() {
        initComponents();
        secretaria = new SecretariaCtrl(this);
        jtxtmedicinas.setEditable(false);
        jtxtalergia.setEditable(false);
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
    
 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
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
        jPanel2 = new javax.swing.JPanel();
        panelDatos20 = new javax.swing.JPanel();
        jtxtnombreAlumno = new javax.swing.JTextField();
        jSeparator19 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        panelDatos21 = new javax.swing.JPanel();
        jtxtapellidoAlumno = new javax.swing.JTextField();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        panelDatos23 = new javax.swing.JPanel();
        jtxtdniAlumno = new javax.swing.JTextField();
        jSeparator22 = new javax.swing.JSeparator();
        jLabel30 = new javax.swing.JLabel();
        panelDatos22 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jcmbgeneroAlumno = new javax.swing.JComboBox<>();
        panelDatos24 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jDatenacimientoAlumno = new com.toedter.calendar.JDateChooser();
        jRadioButtonAlergiasSi = new javax.swing.JRadioButton();
        jRadioButtonAlergiasNo = new javax.swing.JRadioButton();
        jLabel37 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jRadioButtonMedicinasSi = new javax.swing.JRadioButton();
        jRadioButtonMedicinasNo = new javax.swing.JRadioButton();
        lbNivel3 = new javax.swing.JLabel();
        jtxtalergia = new javax.swing.JTextField();
        jtxtmedicinas = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        panelDatos26 = new javax.swing.JPanel();
        jtxtnombreApoderado = new javax.swing.JTextField();
        jSeparator24 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        panelDatos31 = new javax.swing.JPanel();
        jtxtdniApoderado = new javax.swing.JTextField();
        jSeparator27 = new javax.swing.JSeparator();
        jLabel43 = new javax.swing.JLabel();
        panelDatos33 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jcmbparentesco = new javax.swing.JComboBox<>();
        panelDatos42 = new javax.swing.JPanel();
        jtxtcelular = new javax.swing.JTextField();
        jSeparator36 = new javax.swing.JSeparator();
        jLabel53 = new javax.swing.JLabel();
        panelDatos35 = new javax.swing.JPanel();
        jtxtcorreo = new javax.swing.JTextField();
        jSeparator29 = new javax.swing.JSeparator();
        jLabel46 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreadireccion = new javax.swing.JTextArea();
        panelDatos39 = new javax.swing.JPanel();
        jtxtapellidoApoderado = new javax.swing.JTextField();
        jSeparator33 = new javax.swing.JSeparator();
        jLabel50 = new javax.swing.JLabel();
        lbNivel6 = new javax.swing.JLabel();
        btnCrearTicket2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        panelDatos25 = new javax.swing.JPanel();
        panelDatos27 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jcmbnivelFuncional = new javax.swing.JComboBox<>();
        panelDatos28 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jcmbaulaAsignada = new javax.swing.JComboBox<>();
        panelDatos30 = new javax.swing.JPanel();
        jtxtdocenteCargo = new javax.swing.JTextField();
        jSeparator26 = new javax.swing.JSeparator();
        jLabel38 = new javax.swing.JLabel();
        lbNivel4 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaobservaciones = new javax.swing.JTextArea();
        panelDatos29 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jcmbestado = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListdiagnostico = new javax.swing.JList<>();
        jLabel32 = new javax.swing.JLabel();
        panelDatos43 = new javax.swing.JPanel();
        jtxtfecha = new javax.swing.JTextField();
        jSeparator37 = new javax.swing.JSeparator();
        jLabel54 = new javax.swing.JLabel();
        btnCrearTicket5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        textBuscarTicket = new javax.swing.JTextField();
        btnBuscarTicket = new javax.swing.JButton();
        jbtnregistrar = new javax.swing.JButton();
        btnCrearTicket3 = new javax.swing.JButton();
        btnCrearTicket4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(237, 237, 237));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setForeground(new java.awt.Color(51, 0, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("asd");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 0, 74, 90));

        mSalir.setBackground(new java.awt.Color(237, 237, 237));
        mSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 0, 51));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("asd");
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
        jlblinicio.setForeground(new java.awt.Color(51, 0, 51));
        jlblinicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblinicio.setText("asd");
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

        mEvaluaciones.setBackground(new java.awt.Color(255, 255, 255));
        mEvaluaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlblmatricula.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jlblmatricula.setForeground(new java.awt.Color(51, 0, 51));
        jlblmatricula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmatricula.setText("asd");
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
        jlblestudiantes.setForeground(new java.awt.Color(51, 0, 51));
        jlblestudiantes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblestudiantes.setText("asd");
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

        mReportes.setBackground(new java.awt.Color(237, 237, 237));
        mReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mReportes.setPreferredSize(new java.awt.Dimension(120, 90));

        jlblreportes.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jlblreportes.setForeground(new java.awt.Color(51, 0, 51));
        jlblreportes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblreportes.setText("asd");
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

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 90));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Ficha de Matrícula");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 400, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setPreferredSize(new java.awt.Dimension(310, 480));

        panelDatos20.setBackground(new java.awt.Color(255, 255, 255));

        jtxtnombreAlumno.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtxtnombreAlumno.setForeground(new java.awt.Color(66, 128, 191));
        jtxtnombreAlumno.setBorder(null);
        jtxtnombreAlumno.setCaretColor(new java.awt.Color(51, 51, 51));

        jSeparator19.setForeground(new java.awt.Color(102, 102, 102));

        jLabel27.setForeground(new java.awt.Color(153, 153, 153));
        jLabel27.setText("Nombres:");

        javax.swing.GroupLayout panelDatos20Layout = new javax.swing.GroupLayout(panelDatos20);
        panelDatos20.setLayout(panelDatos20Layout);
        panelDatos20Layout.setHorizontalGroup(
            panelDatos20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos20Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator19)
                    .addGroup(panelDatos20Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jtxtnombreAlumno)))
        );
        panelDatos20Layout.setVerticalGroup(
            panelDatos20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos20Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtnombreAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator19)
                .addContainerGap())
        );

        panelDatos21.setBackground(new java.awt.Color(255, 255, 255));

        jtxtapellidoAlumno.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtxtapellidoAlumno.setForeground(new java.awt.Color(66, 128, 191));
        jtxtapellidoAlumno.setBorder(null);
        jtxtapellidoAlumno.setCaretColor(new java.awt.Color(51, 51, 51));

        jSeparator20.setForeground(new java.awt.Color(102, 102, 102));

        jLabel28.setForeground(new java.awt.Color(153, 153, 153));
        jLabel28.setText("Apellidos:");

        javax.swing.GroupLayout panelDatos21Layout = new javax.swing.GroupLayout(panelDatos21);
        panelDatos21.setLayout(panelDatos21Layout);
        panelDatos21Layout.setHorizontalGroup(
            panelDatos21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos21Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator20)
                    .addGroup(panelDatos21Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jtxtapellidoAlumno)))
        );
        panelDatos21Layout.setVerticalGroup(
            panelDatos21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos21Layout.createSequentialGroup()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtapellidoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDatos23.setBackground(new java.awt.Color(255, 255, 255));

        jtxtdniAlumno.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtxtdniAlumno.setForeground(new java.awt.Color(66, 128, 191));
        jtxtdniAlumno.setBorder(null);
        jtxtdniAlumno.setCaretColor(new java.awt.Color(51, 51, 51));

        jSeparator22.setForeground(new java.awt.Color(102, 102, 102));

        jLabel30.setForeground(new java.awt.Color(153, 153, 153));
        jLabel30.setText("DNI:");

        javax.swing.GroupLayout panelDatos23Layout = new javax.swing.GroupLayout(panelDatos23);
        panelDatos23.setLayout(panelDatos23Layout);
        panelDatos23Layout.setHorizontalGroup(
            panelDatos23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos23Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator22)
                    .addGroup(panelDatos23Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jtxtdniAlumno)))
        );
        panelDatos23Layout.setVerticalGroup(
            panelDatos23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos23Layout.createSequentialGroup()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtdniAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelDatos22.setBackground(new java.awt.Color(255, 255, 255));

        jLabel29.setForeground(new java.awt.Color(153, 153, 153));
        jLabel29.setText("Género:");

        jcmbgeneroAlumno.setBackground(new java.awt.Color(239, 239, 239));
        jcmbgeneroAlumno.setForeground(new java.awt.Color(102, 102, 102));
        jcmbgeneroAlumno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Item 2", "Item 3", "Item 4" }));
        jcmbgeneroAlumno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout panelDatos22Layout = new javax.swing.GroupLayout(panelDatos22);
        panelDatos22.setLayout(panelDatos22Layout);
        panelDatos22Layout.setHorizontalGroup(
            panelDatos22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos22Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcmbgeneroAlumno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelDatos22Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelDatos22Layout.setVerticalGroup(
            panelDatos22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos22Layout.createSequentialGroup()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcmbgeneroAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelDatos24.setBackground(new java.awt.Color(255, 255, 255));

        jLabel31.setForeground(new java.awt.Color(153, 153, 153));
        jLabel31.setText("Fecha de Nacimiento:");

        jDatenacimientoAlumno.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelDatos24Layout = new javax.swing.GroupLayout(panelDatos24);
        panelDatos24.setLayout(panelDatos24Layout);
        panelDatos24Layout.setHorizontalGroup(
            panelDatos24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos24Layout.createSequentialGroup()
                .addComponent(jLabel31)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jDatenacimientoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelDatos24Layout.setVerticalGroup(
            panelDatos24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos24Layout.createSequentialGroup()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDatenacimientoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        buttonGroup1.add(jRadioButtonAlergiasSi);
        jRadioButtonAlergiasSi.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButtonAlergiasSi.setText("Sí");
        jRadioButtonAlergiasSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAlergiasSiActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonAlergiasNo);
        jRadioButtonAlergiasNo.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButtonAlergiasNo.setText("No");
        jRadioButtonAlergiasNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAlergiasNoActionPerformed(evt);
            }
        });

        jLabel37.setForeground(new java.awt.Color(153, 153, 153));
        jLabel37.setText("Alergias:");

        jLabel40.setForeground(new java.awt.Color(153, 153, 153));
        jLabel40.setText("Medicinas:");

        buttonGroup2.add(jRadioButtonMedicinasSi);
        jRadioButtonMedicinasSi.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButtonMedicinasSi.setText("Sí");
        jRadioButtonMedicinasSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMedicinasSiActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButtonMedicinasNo);
        jRadioButtonMedicinasNo.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButtonMedicinasNo.setText("No");
        jRadioButtonMedicinasNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMedicinasNoActionPerformed(evt);
            }
        });

        lbNivel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbNivel3.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel3.setText("1. Datos del estudiante");
        lbNivel3.setPreferredSize(new java.awt.Dimension(70, 25));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jtxtmedicinas, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(29, 29, 29)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbNivel3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(panelDatos21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panelDatos23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panelDatos22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panelDatos24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panelDatos20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jRadioButtonAlergiasSi)
                                        .addGap(76, 76, 76)
                                        .addComponent(jRadioButtonAlergiasNo))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                        .addComponent(jRadioButtonMedicinasSi)
                                        .addGap(76, 76, 76)
                                        .addComponent(jRadioButtonMedicinasNo)))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jtxtalergia, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbNivel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(panelDatos20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatos21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatos23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatos22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatos24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonAlergiasNo)
                    .addComponent(jRadioButtonAlergiasSi))
                .addGap(8, 8, 8)
                .addComponent(jtxtalergia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonMedicinasNo)
                    .addComponent(jRadioButtonMedicinasSi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtmedicinas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, 510));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setPreferredSize(new java.awt.Dimension(280, 460));

        panelDatos26.setBackground(new java.awt.Color(255, 255, 255));

        jtxtnombreApoderado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtxtnombreApoderado.setForeground(new java.awt.Color(66, 128, 191));
        jtxtnombreApoderado.setBorder(null);
        jtxtnombreApoderado.setCaretColor(new java.awt.Color(51, 51, 51));

        jSeparator24.setForeground(new java.awt.Color(102, 102, 102));

        jLabel41.setForeground(new java.awt.Color(153, 153, 153));
        jLabel41.setText("Nombres:");

        javax.swing.GroupLayout panelDatos26Layout = new javax.swing.GroupLayout(panelDatos26);
        panelDatos26.setLayout(panelDatos26Layout);
        panelDatos26Layout.setHorizontalGroup(
            panelDatos26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos26Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatos26Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos26Layout.createSequentialGroup()
                        .addGroup(panelDatos26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator24, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtnombreApoderado))
                        .addContainerGap())))
        );
        panelDatos26Layout.setVerticalGroup(
            panelDatos26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos26Layout.createSequentialGroup()
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtnombreApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator24, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDatos31.setBackground(new java.awt.Color(255, 255, 255));

        jtxtdniApoderado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtxtdniApoderado.setForeground(new java.awt.Color(66, 128, 191));
        jtxtdniApoderado.setBorder(null);
        jtxtdniApoderado.setCaretColor(new java.awt.Color(51, 51, 51));

        jSeparator27.setForeground(new java.awt.Color(102, 102, 102));

        jLabel43.setForeground(new java.awt.Color(153, 153, 153));
        jLabel43.setText("DNI:");

        javax.swing.GroupLayout panelDatos31Layout = new javax.swing.GroupLayout(panelDatos31);
        panelDatos31.setLayout(panelDatos31Layout);
        panelDatos31Layout.setHorizontalGroup(
            panelDatos31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos31Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatos31Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos31Layout.createSequentialGroup()
                        .addGroup(panelDatos31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator27)
                            .addComponent(jtxtdniApoderado))
                        .addContainerGap())))
        );
        panelDatos31Layout.setVerticalGroup(
            panelDatos31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos31Layout.createSequentialGroup()
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtdniApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator27, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelDatos33.setBackground(new java.awt.Color(255, 255, 255));

        jLabel44.setForeground(new java.awt.Color(153, 153, 153));
        jLabel44.setText("Parentesco");

        jcmbparentesco.setBackground(new java.awt.Color(239, 239, 239));
        jcmbparentesco.setForeground(new java.awt.Color(102, 102, 102));
        jcmbparentesco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Padre", "Madre", "Abuelo/a", "Tío/a", "Hermano/a", "Tutor legal", "Otro" }));
        jcmbparentesco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout panelDatos33Layout = new javax.swing.GroupLayout(panelDatos33);
        panelDatos33.setLayout(panelDatos33Layout);
        panelDatos33Layout.setHorizontalGroup(
            panelDatos33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos33Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(jcmbparentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        panelDatos33Layout.setVerticalGroup(
            panelDatos33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos33Layout.createSequentialGroup()
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcmbparentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelDatos42.setBackground(new java.awt.Color(255, 255, 255));

        jtxtcelular.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtxtcelular.setForeground(new java.awt.Color(66, 128, 191));
        jtxtcelular.setBorder(null);
        jtxtcelular.setCaretColor(new java.awt.Color(51, 51, 51));

        jSeparator36.setForeground(new java.awt.Color(102, 102, 102));

        jLabel53.setForeground(new java.awt.Color(153, 153, 153));
        jLabel53.setText("Celular");

        javax.swing.GroupLayout panelDatos42Layout = new javax.swing.GroupLayout(panelDatos42);
        panelDatos42.setLayout(panelDatos42Layout);
        panelDatos42Layout.setHorizontalGroup(
            panelDatos42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos42Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator36)
                    .addComponent(jLabel53)
                    .addComponent(jtxtcelular, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        panelDatos42Layout.setVerticalGroup(
            panelDatos42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos42Layout.createSequentialGroup()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtcelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator36, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelDatos35.setBackground(new java.awt.Color(255, 255, 255));

        jtxtcorreo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtxtcorreo.setForeground(new java.awt.Color(66, 128, 191));
        jtxtcorreo.setBorder(null);
        jtxtcorreo.setCaretColor(new java.awt.Color(51, 51, 51));

        jSeparator29.setForeground(new java.awt.Color(102, 102, 102));

        jLabel46.setForeground(new java.awt.Color(153, 153, 153));
        jLabel46.setText("Correo");

        javax.swing.GroupLayout panelDatos35Layout = new javax.swing.GroupLayout(panelDatos35);
        panelDatos35.setLayout(panelDatos35Layout);
        panelDatos35Layout.setHorizontalGroup(
            panelDatos35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos35Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatos35Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelDatos35Layout.createSequentialGroup()
                        .addGroup(panelDatos35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtxtcorreo)
                            .addComponent(jSeparator29, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        panelDatos35Layout.setVerticalGroup(
            panelDatos35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos35Layout.createSequentialGroup()
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel39.setForeground(new java.awt.Color(153, 153, 153));
        jLabel39.setText("Dirección:");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextAreadireccion.setColumns(20);
        jTextAreadireccion.setForeground(new java.awt.Color(66, 128, 191));
        jTextAreadireccion.setLineWrap(true);
        jTextAreadireccion.setRows(5);
        jTextAreadireccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextAreadireccion.setCaretColor(new java.awt.Color(51, 51, 51));
        jTextAreadireccion.setHighlighter(null);
        jTextAreadireccion.setMinimumSize(new java.awt.Dimension(235, 18));
        jTextAreadireccion.setPreferredSize(new java.awt.Dimension(228, 82));
        jScrollPane2.setViewportView(jTextAreadireccion);

        panelDatos39.setBackground(new java.awt.Color(255, 255, 255));

        jtxtapellidoApoderado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtxtapellidoApoderado.setForeground(new java.awt.Color(66, 128, 191));
        jtxtapellidoApoderado.setBorder(null);
        jtxtapellidoApoderado.setCaretColor(new java.awt.Color(51, 51, 51));

        jSeparator33.setForeground(new java.awt.Color(102, 102, 102));

        jLabel50.setForeground(new java.awt.Color(153, 153, 153));
        jLabel50.setText("Apellidos:");

        javax.swing.GroupLayout panelDatos39Layout = new javax.swing.GroupLayout(panelDatos39);
        panelDatos39.setLayout(panelDatos39Layout);
        panelDatos39Layout.setHorizontalGroup(
            panelDatos39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos39Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatos39Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos39Layout.createSequentialGroup()
                        .addGroup(panelDatos39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator33, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtapellidoApoderado))
                        .addContainerGap())))
        );
        panelDatos39Layout.setVerticalGroup(
            panelDatos39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos39Layout.createSequentialGroup()
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtapellidoApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator33)
                .addContainerGap())
        );

        lbNivel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbNivel6.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel6.setText("2. Datos del apoderado");
        lbNivel6.setPreferredSize(new java.awt.Dimension(70, 25));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNivel6, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(panelDatos42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                        .addComponent(panelDatos35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(panelDatos33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelDatos31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelDatos39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelDatos26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(29, 29, 29))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbNivel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(panelDatos26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatos39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatos31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatos33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatos42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatos35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 310, 510));

        btnCrearTicket2.setBackground(new java.awt.Color(102, 102, 102));
        btnCrearTicket2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        btnCrearTicket2.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearTicket2.setText("EXPORTAR PDF");
        btnCrearTicket2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearTicket2.setFocusPainted(false);
        jPanel1.add(btnCrearTicket2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 30, 137, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        panelDatos25.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelDatos25Layout = new javax.swing.GroupLayout(panelDatos25);
        panelDatos25.setLayout(panelDatos25Layout);
        panelDatos25Layout.setHorizontalGroup(
            panelDatos25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDatos25Layout.setVerticalGroup(
            panelDatos25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        panelDatos27.setBackground(new java.awt.Color(255, 255, 255));

        jLabel35.setForeground(new java.awt.Color(153, 153, 153));
        jLabel35.setText("Nivel Funcional:");

        jcmbnivelFuncional.setBackground(new java.awt.Color(239, 239, 239));
        jcmbnivelFuncional.setForeground(new java.awt.Color(102, 102, 102));
        jcmbnivelFuncional.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bajo: Requiere ayuda constante", "Medio: Requiere apoyo ocasional", "Alto: Mayor autonomía" }));
        jcmbnivelFuncional.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout panelDatos27Layout = new javax.swing.GroupLayout(panelDatos27);
        panelDatos27.setLayout(panelDatos27Layout);
        panelDatos27Layout.setHorizontalGroup(
            panelDatos27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos27Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcmbnivelFuncional, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelDatos27Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelDatos27Layout.setVerticalGroup(
            panelDatos27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos27Layout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcmbnivelFuncional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        panelDatos28.setBackground(new java.awt.Color(255, 255, 255));

        jLabel36.setForeground(new java.awt.Color(153, 153, 153));
        jLabel36.setText("Aula asignada:");

        jcmbaulaAsignada.setBackground(new java.awt.Color(239, 239, 239));
        jcmbaulaAsignada.setForeground(new java.awt.Color(102, 102, 102));
        jcmbaulaAsignada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Bajo", "Medio", "Alto" }));
        jcmbaulaAsignada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout panelDatos28Layout = new javax.swing.GroupLayout(panelDatos28);
        panelDatos28.setLayout(panelDatos28Layout);
        panelDatos28Layout.setHorizontalGroup(
            panelDatos28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos28Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcmbaulaAsignada, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelDatos28Layout.setVerticalGroup(
            panelDatos28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos28Layout.createSequentialGroup()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcmbaulaAsignada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        panelDatos30.setBackground(new java.awt.Color(255, 255, 255));

        jtxtdocenteCargo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtxtdocenteCargo.setForeground(new java.awt.Color(66, 128, 191));
        jtxtdocenteCargo.setBorder(null);
        jtxtdocenteCargo.setCaretColor(new java.awt.Color(51, 51, 51));

        jSeparator26.setForeground(new java.awt.Color(102, 102, 102));

        jLabel38.setForeground(new java.awt.Color(153, 153, 153));
        jLabel38.setText("Docente a cargo:");

        javax.swing.GroupLayout panelDatos30Layout = new javax.swing.GroupLayout(panelDatos30);
        panelDatos30.setLayout(panelDatos30Layout);
        panelDatos30Layout.setHorizontalGroup(
            panelDatos30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos30Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator26)
                    .addComponent(jtxtdocenteCargo)
                    .addGroup(panelDatos30Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelDatos30Layout.setVerticalGroup(
            panelDatos30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos30Layout.createSequentialGroup()
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtdocenteCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator26, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        lbNivel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbNivel4.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel4.setText("3. Información Educativa");
        lbNivel4.setPreferredSize(new java.awt.Dimension(70, 25));

        jLabel42.setForeground(new java.awt.Color(153, 153, 153));
        jLabel42.setText("Observaciones:");

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextAreaobservaciones.setColumns(20);
        jTextAreaobservaciones.setForeground(new java.awt.Color(66, 128, 191));
        jTextAreaobservaciones.setLineWrap(true);
        jTextAreaobservaciones.setRows(5);
        jTextAreaobservaciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextAreaobservaciones.setCaretColor(new java.awt.Color(51, 51, 51));
        jTextAreaobservaciones.setHighlighter(null);
        jTextAreaobservaciones.setMinimumSize(new java.awt.Dimension(235, 18));
        jTextAreaobservaciones.setPreferredSize(new java.awt.Dimension(228, 82));
        jScrollPane4.setViewportView(jTextAreaobservaciones);

        panelDatos29.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setForeground(new java.awt.Color(153, 153, 153));
        jLabel33.setText("Estado:");

        jcmbestado.setBackground(new java.awt.Color(239, 239, 239));
        jcmbestado.setForeground(new java.awt.Color(102, 102, 102));
        jcmbestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo", "Retirado", "Grduado" }));
        jcmbestado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout panelDatos29Layout = new javax.swing.GroupLayout(panelDatos29);
        panelDatos29.setLayout(panelDatos29Layout);
        panelDatos29Layout.setHorizontalGroup(
            panelDatos29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos29Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jcmbestado, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDatos29Layout.setVerticalGroup(
            panelDatos29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcmbestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jListdiagnostico.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Trastorno del Espectro Autista (TEA)", "Síndrome de Asperger", "Síndrome de Down", "Retraso mental leve", "Retraso mental moderado" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jListdiagnostico);

        jLabel32.setForeground(new java.awt.Color(153, 153, 153));
        jLabel32.setText("Diagnóstico:");

        panelDatos43.setBackground(new java.awt.Color(255, 255, 255));

        jtxtfecha.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtxtfecha.setForeground(new java.awt.Color(66, 128, 191));
        jtxtfecha.setBorder(null);
        jtxtfecha.setCaretColor(new java.awt.Color(51, 51, 51));

        jSeparator37.setForeground(new java.awt.Color(102, 102, 102));

        jLabel54.setForeground(new java.awt.Color(153, 153, 153));
        jLabel54.setText("Fecha:");

        javax.swing.GroupLayout panelDatos43Layout = new javax.swing.GroupLayout(panelDatos43);
        panelDatos43.setLayout(panelDatos43Layout);
        panelDatos43Layout.setHorizontalGroup(
            panelDatos43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos43Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator37, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(jtxtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelDatos43Layout.setVerticalGroup(
            panelDatos43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos43Layout.createSequentialGroup()
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator37, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNivel4, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelDatos28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel42)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(panelDatos27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel32)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(panelDatos29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(panelDatos43, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelDatos25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jScrollPane4)
                        .addComponent(panelDatos30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbNivel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(panelDatos25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelDatos29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelDatos43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(109, 109, 109))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(panelDatos27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelDatos28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatos30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 100, 310, 510));

        btnCrearTicket5.setBackground(new java.awt.Color(66, 128, 191));
        btnCrearTicket5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        btnCrearTicket5.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearTicket5.setText("HISTORIAL DE MATRICULAS");
        btnCrearTicket5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearTicket5.setFocusPainted(false);
        jPanel1.add(btnCrearTicket5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 30, 220, 40));

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Buscar  DNI:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 80, 30));

        textBuscarTicket.setForeground(new java.awt.Color(51, 51, 51));
        textBuscarTicket.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(textBuscarTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 200, 30));

        btnBuscarTicket.setBackground(new java.awt.Color(66, 128, 191));
        btnBuscarTicket.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        btnBuscarTicket.setForeground(new java.awt.Color(98, 66, 26));
        btnBuscarTicket.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(66, 128, 191)));
        btnBuscarTicket.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTicketActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, 30, 30));

        jbtnregistrar.setBackground(new java.awt.Color(66, 128, 191));
        jbtnregistrar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jbtnregistrar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnregistrar.setText("REGISTRAR");
        jbtnregistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnregistrar.setFocusPainted(false);
        jbtnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnregistrarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnregistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 100, 137, 40));

        btnCrearTicket3.setBackground(new java.awt.Color(58, 163, 163));
        btnCrearTicket3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        btnCrearTicket3.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearTicket3.setText("MODIFICAR");
        btnCrearTicket3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearTicket3.setFocusPainted(false);
        jPanel1.add(btnCrearTicket3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 170, 137, 40));

        btnCrearTicket4.setBackground(new java.awt.Color(200, 80, 145));
        btnCrearTicket4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        btnCrearTicket4.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearTicket4.setText("ELIMINAR");
        btnCrearTicket4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearTicket4.setFocusPainted(false);
        jPanel1.add(btnCrearTicket4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 240, 137, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1200, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTicketActionPerformed

    }//GEN-LAST:event_btnBuscarTicketActionPerformed

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

    private void jRadioButtonAlergiasSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAlergiasSiActionPerformed
        jtxtalergia.setEditable(true);
    }//GEN-LAST:event_jRadioButtonAlergiasSiActionPerformed

    private void jRadioButtonMedicinasSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMedicinasSiActionPerformed
        jtxtmedicinas.setEditable(true);
    }//GEN-LAST:event_jRadioButtonMedicinasSiActionPerformed

    private void jRadioButtonAlergiasNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAlergiasNoActionPerformed
        jtxtalergia.setEditable(false);
    }//GEN-LAST:event_jRadioButtonAlergiasNoActionPerformed

    private void jRadioButtonMedicinasNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMedicinasNoActionPerformed
        jtxtmedicinas.setEditable(false);
    }//GEN-LAST:event_jRadioButtonMedicinasNoActionPerformed

    private void jbtnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnregistrarActionPerformed
       
    }//GEN-LAST:event_jbtnregistrarActionPerformed

    public String getNombresAlumno() {
        return jtxtnombreAlumno.getText();
    }
    
    public String getApellidosAlumno(){
        return jtxtapellidoAlumno.getText();
    }
    
    public String getDniAlumno(){
        return jtxtdniAlumno.getText();
    }
    
    public String getGeneroAlumno(){
        return (String) jcmbgeneroAlumno.getSelectedItem();
    }
    
    public Date getJDatenacimientoAlumno(){
        return (Date) jDatenacimientoAlumno.getDate();
    }
    
    public boolean getJRadioButtonAlergiasSi(){
        return true;
    }
    
    public boolean getJRadioButtonAlergiasNo(){
        return false;
    }
    
    public String getJtxtalergia(){
        return jtxtalergia.getText();
    }
    
    public boolean getJRadioButtonMedicinasSi(){
        return true;
    }
    
    public boolean getJRadioButtonMedicinasNo(){
        return false;
    }
    
    public String getjtxtmedicinas(){
        return jtxtmedicinas.getText();
    }
    
    public String getJtxtnombreApoderado(){
        return jtxtnombreApoderado.getText();
    }
    
    public String getJtxtapellidoApoderado(){
        return jtxtapellidoApoderado.getText();
    }
    
    public String getJtxtdniApoderado(){
        return jtxtdniApoderado.getText();
    }
    
    public String getJcmbparentesco(){
        return (String) jcmbparentesco.getSelectedItem();
    }
    
    public String getJtxtcelular(){
        return jtxtcelular.getText();
    }
    
    public String getJtxtcorreo(){
        return jtxtcorreo.getText();
    }
    
    public String getJTextAreadireccion(){
        return jTextAreadireccion.getText();
    }
    
    public String getJcmbestado(){
        return (String) jcmbestado.getSelectedItem();
    }
    
    public String getJtxtfecha(){
        return jtxtfecha.getText();
    }
    
    public String getJListdiagnostico(){
        return jListdiagnostico.getSelectedValue();
    }
    
    public String getJcmbnivelFuncional(){
        return (String) jcmbnivelFuncional.getSelectedItem();
    }
    
    public String getJcmbaulaAsignada(){
        return (String) jcmbaulaAsignada.getSelectedItem();
    }
    
    public String getJtxtdocenteCargo(){
        return jtxtdocenteCargo.getText();
    }
    
    public String getJTextAreaobservaciones(){
        return jTextAreaobservaciones.getText();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarTicket;
    private javax.swing.JButton btnCrearTicket2;
    private javax.swing.JButton btnCrearTicket3;
    private javax.swing.JButton btnCrearTicket4;
    private javax.swing.JButton btnCrearTicket5;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser jDatenacimientoAlumno;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jListdiagnostico;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButtonAlergiasNo;
    private javax.swing.JRadioButton jRadioButtonAlergiasSi;
    private javax.swing.JRadioButton jRadioButtonMedicinasNo;
    private javax.swing.JRadioButton jRadioButtonMedicinasSi;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator33;
    private javax.swing.JSeparator jSeparator36;
    private javax.swing.JSeparator jSeparator37;
    private javax.swing.JTextArea jTextAreadireccion;
    private javax.swing.JTextArea jTextAreaobservaciones;
    public javax.swing.JButton jbtnregistrar;
    private javax.swing.JComboBox<String> jcmbaulaAsignada;
    private javax.swing.JComboBox<String> jcmbestado;
    private javax.swing.JComboBox<String> jcmbgeneroAlumno;
    private javax.swing.JComboBox<String> jcmbnivelFuncional;
    private javax.swing.JComboBox<String> jcmbparentesco;
    private javax.swing.JLabel jlblestudiantes;
    private javax.swing.JLabel jlblinicio;
    private javax.swing.JLabel jlblmatricula;
    private javax.swing.JLabel jlblreportes;
    private javax.swing.JTextField jtxtalergia;
    private javax.swing.JTextField jtxtapellidoAlumno;
    private javax.swing.JTextField jtxtapellidoApoderado;
    private javax.swing.JTextField jtxtcelular;
    private javax.swing.JTextField jtxtcorreo;
    private javax.swing.JTextField jtxtdniAlumno;
    private javax.swing.JTextField jtxtdniApoderado;
    private javax.swing.JTextField jtxtdocenteCargo;
    private javax.swing.JTextField jtxtfecha;
    private javax.swing.JTextField jtxtmedicinas;
    private javax.swing.JTextField jtxtnombreAlumno;
    private javax.swing.JTextField jtxtnombreApoderado;
    private javax.swing.JLabel lbNivel3;
    private javax.swing.JLabel lbNivel4;
    private javax.swing.JLabel lbNivel6;
    private javax.swing.JLabel lbNomUsuario;
    private javax.swing.JPanel mEvaluaciones;
    private javax.swing.JPanel mIndividual;
    private javax.swing.JPanel mInicio1;
    private javax.swing.JPanel mReportes;
    private javax.swing.JPanel mSalir;
    private javax.swing.JPanel panelDatos20;
    private javax.swing.JPanel panelDatos21;
    private javax.swing.JPanel panelDatos22;
    private javax.swing.JPanel panelDatos23;
    private javax.swing.JPanel panelDatos24;
    private javax.swing.JPanel panelDatos25;
    private javax.swing.JPanel panelDatos26;
    private javax.swing.JPanel panelDatos27;
    private javax.swing.JPanel panelDatos28;
    private javax.swing.JPanel panelDatos29;
    private javax.swing.JPanel panelDatos30;
    private javax.swing.JPanel panelDatos31;
    private javax.swing.JPanel panelDatos33;
    private javax.swing.JPanel panelDatos35;
    private javax.swing.JPanel panelDatos39;
    private javax.swing.JPanel panelDatos42;
    private javax.swing.JPanel panelDatos43;
    private javax.swing.JTextField textBuscarTicket;
    // End of variables declaration//GEN-END:variables
}
