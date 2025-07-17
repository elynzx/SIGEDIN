/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.Secretaria;

import controller.secretaria.DashboardSecretariaCtrl;
import dao.AulaImp;
import dao.EstudianteImp;
import dao.funcionalidad.MatriculaImp;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.entidades.Aula;
import org.jfree.data.category.DefaultCategoryDataset;
import utilities.MetricasUtil;

public class VistaDashboardSecretaria extends javax.swing.JPanel {

    private final DashboardSecretariaCtrl dashboardSecretariaCtrl;

    public VistaDashboardSecretaria(int idSecretaria) {
        this.dashboardSecretariaCtrl = new DashboardSecretariaCtrl(
                MatriculaImp.obtenerInstancia(),
                AulaImp.obtenerInstancia(),
                EstudianteImp.obtenerInstancia()
        );

        initComponents();
        cargarMetricas();
        cargarTablaDetalleAulas();
        cargarGraficosPanel();
    }

    private void cargarGraficosPanel() {
        DefaultCategoryDataset datasetAula = dashboardSecretariaCtrl.obtenerGraficoMatriculaPorAula();
        MetricasUtil.mostrarGraficoMatriculaPorAula(panelGraficoAula, lbTituloGrafAula, datasetAula);

        DefaultCategoryDataset datasetDiagnostico = dashboardSecretariaCtrl.obtenerGraficoMatriculaPorDiagnostico();
        MetricasUtil.mostrarGraficoMatriculaPorDiagnostico(panelGraficoDiagnostico, lbTituloGrafDiagnostico, datasetDiagnostico);
    }

    private void cargarMetricas() {
        int totalEstudiantes = dashboardSecretariaCtrl.obtenerTotalMatriculados();
        int matriculasHoy = dashboardSecretariaCtrl.obtenerMatriculasHoy();
        int vacantesDisponibles = dashboardSecretariaCtrl.obtenerVacantesDisponibles();
        int aulasConVacantes = dashboardSecretariaCtrl.obtenerAulasConVacantes();

        txtTotalestudiantes.setText(String.valueOf(totalEstudiantes));
        txtMatriculaHoy.setText(String.valueOf(matriculasHoy));
        txtVacantesDisponibles.setText(String.valueOf(vacantesDisponibles));
        txtAulasDisponibles.setText(String.valueOf(aulasConVacantes));
    }

    private void cargarTablaDetalleAulas() {
        List<Aula> aulas = dashboardSecretariaCtrl.obtenerAulas();
        DefaultTableModel modelo = (DefaultTableModel) tbAulasDashboard.getModel();
        modelo.setRowCount(0);

        for (Aula aula : aulas) {
            modelo.addRow(new Object[]{
                aula.getNombre(),
                aula.getNivelFuncional().getNombre(),
                aula.getDiagnosticoReferente().getNombre(),
                aula.getDocente().getNombreCompleto(),
                aula.getVacantesTotales(),
                aula.getVacantesDisponibles()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpDashboardDocente = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtVacantesDisponibles = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtAulasDisponibles = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtMatriculaHoy = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtTotalestudiantes = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lbNivel12 = new javax.swing.JLabel();
        lbNivel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAulasDashboard = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jpGraficoIncidentes = new javax.swing.JPanel();
        panelGraficoAula = new javax.swing.JPanel();
        lbTituloGrafAula = new javax.swing.JLabel();
        lbNivel9 = new javax.swing.JLabel();
        jpGraficoIncidentes1 = new javax.swing.JPanel();
        panelGraficoDiagnostico = new javax.swing.JPanel();
        lbTituloGrafDiagnostico = new javax.swing.JLabel();

        jpDashboardDocente.setBackground(new java.awt.Color(236, 242, 248));
        jpDashboardDocente.setMinimumSize(new java.awt.Dimension(1250, 710));
        jpDashboardDocente.setPreferredSize(new java.awt.Dimension(1250, 710));
        jpDashboardDocente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel8.setFocusable(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(180, 60));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(45, 94, 152));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Vacantes disponibles");

        txtVacantesDisponibles.setFont(new java.awt.Font("Arial Black", 1, 26)); // NOI18N
        txtVacantesDisponibles.setForeground(new java.awt.Color(23, 64, 112));
        txtVacantesDisponibles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtVacantesDisponibles.setText("-");
        txtVacantesDisponibles.setToolTipText("Total de estudiantes matriculados");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVacantesDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVacantesDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpDashboardDocente.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 170, 95));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setForeground(new java.awt.Color(255, 255, 255));
        jPanel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel13.setFocusable(false);
        jPanel13.setPreferredSize(new java.awt.Dimension(180, 60));

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(45, 94, 152));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Aulas disponibles");

        txtAulasDisponibles.setFont(new java.awt.Font("Arial Black", 1, 26)); // NOI18N
        txtAulasDisponibles.setForeground(new java.awt.Color(23, 50, 78));
        txtAulasDisponibles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAulasDisponibles.setText("-");
        txtAulasDisponibles.setToolTipText("Total de estudiantes matriculados");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAulasDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAulasDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpDashboardDocente.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 170, 95));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setForeground(new java.awt.Color(255, 255, 255));
        jPanel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel14.setFocusable(false);
        jPanel14.setPreferredSize(new java.awt.Dimension(180, 60));

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(45, 94, 152));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Matriculas de hoy");

        txtMatriculaHoy.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        txtMatriculaHoy.setForeground(new java.awt.Color(45, 94, 152));
        txtMatriculaHoy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtMatriculaHoy.setText("-");
        txtMatriculaHoy.setToolTipText("Total de estudiantes matriculados");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMatriculaHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatriculaHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpDashboardDocente.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 170, 95));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setForeground(new java.awt.Color(81, 124, 191));
        jPanel15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel15.setFocusable(false);
        jPanel15.setPreferredSize(new java.awt.Dimension(180, 60));

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(45, 94, 152));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Total estudiantes");

        txtTotalestudiantes.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        txtTotalestudiantes.setForeground(new java.awt.Color(81, 124, 191));
        txtTotalestudiantes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotalestudiantes.setText("-");
        txtTotalestudiantes.setToolTipText("Total de estudiantes matriculados");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalestudiantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalestudiantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpDashboardDocente.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 170, 95));

        jPanel1.setBackground(new java.awt.Color(81, 124, 191));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );

        jpDashboardDocente.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 20, 95));

        jPanel3.setBackground(new java.awt.Color(45, 94, 152));
        jPanel3.setForeground(new java.awt.Color(45, 94, 152));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );

        jpDashboardDocente.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 20, 95));

        jPanel4.setBackground(new java.awt.Color(23, 64, 112));
        jPanel4.setForeground(new java.awt.Color(23, 50, 78));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );

        jpDashboardDocente.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 20, 95));

        jPanel5.setBackground(new java.awt.Color(23, 50, 78));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );

        jpDashboardDocente.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 20, 95));

        lbNivel12.setBackground(new java.awt.Color(23, 50, 78));
        lbNivel12.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel12.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel12.setText("DETALLE DE AULAS");
        lbNivel12.setToolTipText("");
        lbNivel12.setPreferredSize(new java.awt.Dimension(70, 25));
        jpDashboardDocente.add(lbNivel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 280, 30));

        lbNivel16.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel16.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbNivel16.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel16.setText("Selecciona un estudiante para ver más detalles:");
        lbNivel16.setToolTipText("");
        lbNivel16.setPreferredSize(new java.awt.Dimension(70, 25));
        jpDashboardDocente.add(lbNivel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 330, 20));

        tbAulasDashboard.setBackground(new java.awt.Color(255, 255, 255));
        tbAulasDashboard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tbAulasDashboard.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tbAulasDashboard.setForeground(new java.awt.Color(23, 64, 112));
        tbAulasDashboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        tbAulasDashboard.setGridColor(new java.awt.Color(204, 204, 204));
        tbAulasDashboard.setRowHeight(25);
        tbAulasDashboard.setSelectionBackground(new java.awt.Color(23, 64, 112));
        tbAulasDashboard.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbAulasDashboard.setShowGrid(false);
        tbAulasDashboard.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(tbAulasDashboard);

        jpDashboardDocente.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 800, 410));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpGraficoIncidentes.setBackground(new java.awt.Color(255, 255, 255));
        jpGraficoIncidentes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jpGraficoIncidentes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelGraficoAula.setBackground(new java.awt.Color(255, 255, 255));
        panelGraficoAula.setMinimumSize(new java.awt.Dimension(200, 200));
        panelGraficoAula.setOpaque(false);
        panelGraficoAula.setPreferredSize(new java.awt.Dimension(248, 248));

        javax.swing.GroupLayout panelGraficoAulaLayout = new javax.swing.GroupLayout(panelGraficoAula);
        panelGraficoAula.setLayout(panelGraficoAulaLayout);
        panelGraficoAulaLayout.setHorizontalGroup(
            panelGraficoAulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        panelGraficoAulaLayout.setVerticalGroup(
            panelGraficoAulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jpGraficoIncidentes.add(panelGraficoAula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 280, 200));

        lbTituloGrafAula.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbTituloGrafAula.setForeground(new java.awt.Color(45, 94, 152));
        lbTituloGrafAula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTituloGrafAula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pie_chart_fill-1.png"))); // NOI18N
        lbTituloGrafAula.setText("Gráfico de Análisis");
        jpGraficoIncidentes.add(lbTituloGrafAula, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 300, 30));

        jPanel2.add(jpGraficoIncidentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 300, 250));

        lbNivel9.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        lbNivel9.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel9.setText("Métricas");
        lbNivel9.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel2.add(lbNivel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 230, -1));

        jpGraficoIncidentes1.setBackground(new java.awt.Color(255, 255, 255));
        jpGraficoIncidentes1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jpGraficoIncidentes1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelGraficoDiagnostico.setBackground(new java.awt.Color(255, 255, 255));
        panelGraficoDiagnostico.setMinimumSize(new java.awt.Dimension(200, 200));
        panelGraficoDiagnostico.setOpaque(false);
        panelGraficoDiagnostico.setPreferredSize(new java.awt.Dimension(248, 248));

        javax.swing.GroupLayout panelGraficoDiagnosticoLayout = new javax.swing.GroupLayout(panelGraficoDiagnostico);
        panelGraficoDiagnostico.setLayout(panelGraficoDiagnosticoLayout);
        panelGraficoDiagnosticoLayout.setHorizontalGroup(
            panelGraficoDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        panelGraficoDiagnosticoLayout.setVerticalGroup(
            panelGraficoDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jpGraficoIncidentes1.add(panelGraficoDiagnostico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 280, 200));

        lbTituloGrafDiagnostico.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbTituloGrafDiagnostico.setForeground(new java.awt.Color(45, 94, 152));
        lbTituloGrafDiagnostico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTituloGrafDiagnostico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pie_chart_fill-1.png"))); // NOI18N
        lbTituloGrafDiagnostico.setText("Gráfico de Análisis");
        jpGraficoIncidentes1.add(lbTituloGrafDiagnostico, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 300, 30));

        jPanel2.add(jpGraficoIncidentes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, 250));

        jpDashboardDocente.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, 340, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpDashboardDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpDashboardDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpDashboardDocente;
    private javax.swing.JPanel jpGraficoIncidentes;
    private javax.swing.JPanel jpGraficoIncidentes1;
    private javax.swing.JLabel lbNivel12;
    private javax.swing.JLabel lbNivel16;
    private javax.swing.JLabel lbNivel9;
    private javax.swing.JLabel lbTituloGrafAula;
    private javax.swing.JLabel lbTituloGrafDiagnostico;
    private javax.swing.JPanel panelGraficoAula;
    private javax.swing.JPanel panelGraficoDiagnostico;
    private javax.swing.JTable tbAulasDashboard;
    private javax.swing.JLabel txtAulasDisponibles;
    private javax.swing.JLabel txtMatriculaHoy;
    private javax.swing.JLabel txtTotalestudiantes;
    private javax.swing.JLabel txtVacantesDisponibles;
    // End of variables declaration//GEN-END:variables
}
