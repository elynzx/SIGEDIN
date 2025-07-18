/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Secretaria;

import javax.swing.JLabel;
import controller.secretaria.SecretariaCtrl;
import javax.swing.JTable;
import utilities.FechaUtil;

/**
 *
 * @author rpasc
 */
public class VDashboardMatricula extends javax.swing.JFrame {

 
    private SecretariaCtrl secretaria;
    private view.Secretaria.VEstudiantes estudiantes;
    private view.Secretaria.Matricula matricula;
    private view.Secretaria.ReportesMatricula reportes;
    private FechaUtil fecha = new FechaUtil();

    public VDashboardMatricula() {
  
        initComponents();
        fecha.mostrarDiaHora(lbFechaCompleta);
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

    public void setTbAlumnosDashboard(JTable tbAlumnosDashboard) {
        this.tbAlumnosDashboard = tbAlumnosDashboard;
    }

    public javax.swing.JTable getTbAlumnosDashboard() {
        return tbAlumnosDashboard;
    }

    public javax.swing.JLabel getJlblnombre() {
        return jlblnombre;
    }

    public javax.swing.JLabel setJlblnombre() {
        return jlblnombre;
    }

    public void setJlblmatriculas(JLabel jlblmatriculas) {
        this.jlblmatriculas = jlblmatriculas;
    }

    public javax.swing.JLabel getJlbltotal() {
        return jlbltotal;
    }

    public void setJlbltotal(JLabel jlbltotal) {
        this.jlbltotal = jlbltotal;
    }

    public javax.swing.JLabel getJlblvacantes() {
        return jlblvacantes;
    }

    public void setJlblvacantes(JLabel jlblvacantes) {
        this.jlblvacantes = jlblvacantes;
    }

    public javax.swing.JLabel getLbAsistentes() {
        return jlblasistentes;
    }

    public void setLbAsistentes(JLabel lbAsistentes) {
        this.jlblasistentes = lbAsistentes;
    }

    public javax.swing.JLabel getJlblmatriculas() {
        return jlblmatriculas;
    }

    public javax.swing.JLabel getJlblasistentes() {
        return jlblasistentes;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        mEvaluaciones = new javax.swing.JPanel();
        jlblmatricula = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        mIndividual = new javax.swing.JPanel();
        jlblestudiantes = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        mReportes = new javax.swing.JPanel();
        jlblreportes = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        mSalir = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        mInicio1 = new javax.swing.JPanel();
        jlblinicio = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jlblnombre = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jlbltotal = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jlblasistentes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAlumnosDashboard = new javax.swing.JTable();
        lbFechaCompleta = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jlblmatriculas = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jlblvacantes = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbNivel7 = new javax.swing.JLabel();
        lbNivel9 = new javax.swing.JLabel();
        lbNivel10 = new javax.swing.JLabel();
        lbNivel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(237, 237, 237));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo02.png"))); // NOI18N
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 0, 74, 90));

        mEvaluaciones.setBackground(new java.awt.Color(237, 237, 237));
        mEvaluaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mEvaluaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mEvaluacionesMouseClicked(evt);
            }
        });

        jlblmatricula.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jlblmatricula.setForeground(new java.awt.Color(255, 255, 255));
        jlblmatricula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Sertificate-2.png"))); // NOI18N
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
        jlblestudiantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/User_fill-2.png"))); // NOI18N
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
        jlblreportes.setForeground(new java.awt.Color(255, 255, 255));
        jlblreportes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblreportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/paper_clip-2_1.png"))); // NOI18N
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

        mSalir.setBackground(new java.awt.Color(237, 237, 237));
        mSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/User_cicrle-2_1.png"))); // NOI18N
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

        mInicio1.setBackground(new java.awt.Color(255, 255, 255));
        mInicio1.setForeground(new java.awt.Color(255, 255, 255));
        mInicio1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlblinicio.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jlblinicio.setForeground(new java.awt.Color(255, 255, 255));
        jlblinicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblinicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Widget-2_1.png"))); // NOI18N
        jlblinicio.setToolTipText("");
        jlblinicio.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jlblinicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblinicioMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(58, 163, 163));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("INICIO");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout mInicio1Layout = new javax.swing.GroupLayout(mInicio1);
        mInicio1.setLayout(mInicio1Layout);
        mInicio1Layout.setHorizontalGroup(
            mInicio1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblinicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        mInicio1Layout.setVerticalGroup(
            mInicio1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mInicio1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jlblinicio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel14)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel5.add(mInicio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 120, 90));

        jlblnombre.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlblnombre.setForeground(new java.awt.Color(51, 51, 51));
        jlblnombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblnombre.setText("nombre");
        jPanel5.add(jlblnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 178, 28));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Inicio");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 400, -1));

        jPanel10.setBackground(new java.awt.Color(230, 248, 248));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(58, 163, 163));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Alarm_fill.png"))); // NOI18N
        jLabel30.setText("Total Estudiantes");

        jlbltotal.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jlbltotal.setForeground(new java.awt.Color(51, 51, 51));
        jlbltotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbltotal.setText("120");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbltotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 190, 90));

        jPanel13.setBackground(new java.awt.Color(230, 248, 248));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(58, 163, 163));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Alarm_fill.png"))); // NOI18N
        jLabel32.setText("Aulas Disponibles");

        jlblasistentes.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jlblasistentes.setForeground(new java.awt.Color(51, 51, 51));
        jlblasistentes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblasistentes.setText("2 / 12");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblasistentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblasistentes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 190, 90));

        tbAlumnosDashboard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tbAlumnosDashboard.setForeground(new java.awt.Color(51, 51, 51));
        tbAlumnosDashboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Aula", "Nivel Funcional", "Diagnóstico", "Docente a Cargo", "Vacantes totales", "Vacantes Disponibles"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbAlumnosDashboard.setGridColor(new java.awt.Color(153, 153, 153));
        tbAlumnosDashboard.setSelectionBackground(new java.awt.Color(0, 153, 153));
        tbAlumnosDashboard.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tbAlumnosDashboard);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 790, 370));

        lbFechaCompleta.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        lbFechaCompleta.setForeground(new java.awt.Color(51, 51, 51));
        lbFechaCompleta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbFechaCompleta.setText("Fecha de hoy");
        jPanel1.add(lbFechaCompleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 230, 40));

        jPanel14.setBackground(new java.awt.Color(230, 248, 248));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(58, 163, 163));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Alarm_fill.png"))); // NOI18N
        jLabel33.setText("Matrículas de Hoy");

        jlblmatriculas.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jlblmatriculas.setForeground(new java.awt.Color(51, 51, 51));
        jlblmatriculas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmatriculas.setText("2");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblmatriculas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblmatriculas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 190, 90));

        jPanel15.setBackground(new java.awt.Color(230, 248, 248));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(58, 163, 163));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Alarm_fill.png"))); // NOI18N
        jLabel34.setText("Vacantes Disponibles");

        jlblvacantes.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jlblvacantes.setForeground(new java.awt.Color(51, 51, 51));
        jlblvacantes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblvacantes.setText("30");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblvacantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblvacantes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 190, 90));

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));

        lbNivel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbNivel7.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel7.setText("Matrículas por nivel funcional:");
        lbNivel7.setToolTipText("");
        lbNivel7.setPreferredSize(new java.awt.Dimension(70, 25));

        lbNivel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbNivel9.setForeground(new java.awt.Color(58, 163, 163));
        lbNivel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel9.setText("Métricas");
        lbNivel9.setPreferredSize(new java.awt.Dimension(70, 25));

        lbNivel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbNivel10.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel10.setText("Matrículas por diagnóstico:");
        lbNivel10.setToolTipText("");
        lbNivel10.setPreferredSize(new java.awt.Dimension(70, 25));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNivel10, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNivel9, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(lbNivel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbNivel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lbNivel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216)
                .addComponent(lbNivel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 30, 310, 580));

        lbNivel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbNivel8.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel8.setText("Detalle de Aulas");
        lbNivel8.setToolTipText("");
        lbNivel8.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel1.add(lbNivel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 340, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1200, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlblestudiantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblestudiantesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlblestudiantesMouseClicked

    private void jlblreportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblreportesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlblreportesMouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel23MouseClicked

    private void mEvaluacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mEvaluacionesMouseClicked

        Matricula vMatricula = new Matricula();
        vMatricula.setVisible(true);
        vMatricula.setLocationRelativeTo(null);
        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_mEvaluacionesMouseClicked

    private void jlblmatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmatriculaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlblmatriculaMouseClicked

    private void jlblinicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblinicioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlblinicioMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlblasistentes;
    private javax.swing.JLabel jlblestudiantes;
    private javax.swing.JLabel jlblinicio;
    private javax.swing.JLabel jlblmatricula;
    private javax.swing.JLabel jlblmatriculas;
    private javax.swing.JLabel jlblnombre;
    private javax.swing.JLabel jlblreportes;
    private javax.swing.JLabel jlbltotal;
    private javax.swing.JLabel jlblvacantes;
    private javax.swing.JLabel lbFechaCompleta;
    private javax.swing.JLabel lbNivel10;
    private javax.swing.JLabel lbNivel7;
    private javax.swing.JLabel lbNivel8;
    private javax.swing.JLabel lbNivel9;
    private javax.swing.JPanel mEvaluaciones;
    private javax.swing.JPanel mIndividual;
    private javax.swing.JPanel mInicio1;
    private javax.swing.JPanel mReportes;
    private javax.swing.JPanel mSalir;
    private javax.swing.JTable tbAlumnosDashboard;
    // End of variables declaration//GEN-END:variables
}
