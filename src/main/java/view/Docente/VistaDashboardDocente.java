package view.Docente;

import controller.docente.DashboardDocenteCtrl;
import controller.EstudianteCtrl;
import java.util.stream.Collectors;
import model.funcionalidad.catalogo.Diagnostico;
import dao.DocenteImp;
import dao.EstudianteImp;
import model.entidades.Aula;
import model.entidades.Estudiante;
import dao.funcionalidad.IncidenteImp;
import java.util.List;
import utilities.MetricasUtil;

public class VistaDashboardDocente extends javax.swing.JPanel {

    private final EstudianteCtrl estudianteCtrl;
    private final DashboardDocenteCtrl dashboardDocenteCtrl;
    private int idDocente;
    private int idEstudianteSeleccionado = -1;

    public VistaDashboardDocente(int idDocente) {
        this.idDocente = idDocente;
        this.dashboardDocenteCtrl = new DashboardDocenteCtrl(
                DocenteImp.obtenerInstancia(),
                EstudianteImp.obtenerInstancia(),
                IncidenteImp.obtenerInstancia()
        );

        this.estudianteCtrl = new EstudianteCtrl(EstudianteImp.obtenerInstancia());
        initComponents();

        actualizarDatosDashboard();
        seleccionTbAlumnos();
    }

    public void actualizarDatosDashboard() {
        mostrarDatosAula(idDocente);
        cargarEstudiantesTabla();
        jlHistorialActividad.setListData(
                dashboardDocenteCtrl.obtenerActividadIncidentesRegistrados(idDocente).toArray(new String[0])
        );
        jlAlertaIncidentes.setListData(
                dashboardDocenteCtrl.obtenerEstudiantesConIncidentes(idDocente).toArray(new String[0]));
    }

    private void mostrarDatosAula(int idDocente) {
        Aula aula = dashboardDocenteCtrl.obtenerDatosAula(idDocente);
        if (aula != null) {
            lbAula.setText(aula.getNombre());
            lbNivel.setText(aula.getNivelFuncional().getNombre());
            lbEstudiante.setText(String.valueOf(aula.getVacantesTotales()));
            lbAsistentes.setText(aula.getVacantesDisponibles() + " / " + aula.getVacantesTotales());
        }
    }

    private void cargarEstudiantesTabla() {
        estudianteCtrl.cargarListaTablaEstudiantes(idDocente, tableListaEstudiantes);
    }

    private void mostrarDatosEstudiante(int idEstudiante) {
        this.idEstudianteSeleccionado = idEstudiante;
        try {
            Estudiante estudiante = estudianteCtrl.obtenerEstudiantePorId(idEstudiante);
            txtEdad.setText(String.valueOf(estudiante.getEdad()) + " años");
            txtDiagnostico.setText(estudiante.getDiagnosticos().stream()
                    .map(Diagnostico::getNombre)
                    .collect(Collectors.joining(", ")));
            txtAlergias.setText(estudiante.getTipoAlergia());
            txtMedicinas.setText(estudiante.getMedicamentos());
            txtObserv.setText(estudiante.getObservaciones());

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    private void seleccionTbAlumnos() {
        tableListaEstudiantes.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = tableListaEstudiantes.getSelectedRow();
                if (selectedRow != -1) {
                    int idEstudiante = (int) tableListaEstudiantes.getValueAt(selectedRow, 0);
                    mostrarDatosEstudiante(idEstudiante);
                    actualizarGraficoMetrica(idEstudiante); // <-- aquí
                }
            }
        });
    }

    private void actualizarGraficoMetrica(int idEstudiante) {
        String tipoMetrica = cbTipoMetrica.getSelectedItem().toString();
        String nombre = dashboardDocenteCtrl.obtenerNombreEstudiante(idEstudiante);

        switch (tipoMetrica) {
            case "Conductas por Tipo" -> {
                var datos = dashboardDocenteCtrl.obtenerConductasPorTipo(idEstudiante);
                MetricasUtil.mostrarGraficoConductasPorTipo(
                        jpGrafico, 
                        lbTituloGrafFicha, 
                        lbNombreEstudianteGrafico, 
                        nombre, 
                        datos);
            }
            
            case "Antecedente de Fichas ABC" -> {
                var datos = dashboardDocenteCtrl.obtenerFichasABCporAntecedente(idEstudiante);
                MetricasUtil.mostrarGraficoFichasABC(
                        jpGrafico, 
                        lbTituloGrafFicha, 
                        lbNombreEstudianteGrafico, 
                        nombre, 
                        datos);
            }

            case "Conductas por Mes" -> {
                var datos = dashboardDocenteCtrl.obtenerConductasPorMes(idEstudiante);
                MetricasUtil.mostrarGraficoConductasPorMes(
                        jpGrafico, 
                        lbTituloGrafFicha, 
                        lbNombreEstudianteGrafico, 
                        nombre, datos);
            }
            case "Resumen ABC y Conducta" -> {
                var resumen = dashboardDocenteCtrl.obtenerResumenABCyConductas(idEstudiante);
                MetricasUtil.mostrarGraficoResumenABCyConductas(
                        jpGrafico, 
                        lbTituloGrafFicha, 
                        lbNombreEstudianteGrafico, 
                        nombre, 
                        resumen);
            }
            default -> {
                jpGrafico.removeAll();
                jpGrafico.revalidate();
                jpGrafico.repaint();
            }
        }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpDashboardDocente = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lbEstudiante = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lbAsistentes = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lbNivel = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lbAula = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jpGraficoIncidentes = new javax.swing.JPanel();
        jpGrafico = new javax.swing.JPanel();
        lbTituloGrafFicha = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableListaEstudiantes = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        txtAlergias = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        txtMedicinas = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        txtDiagnostico = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtObserv = new javax.swing.JTextArea();
        lbNivel15 = new javax.swing.JLabel();
        cbTipoMetrica = new javax.swing.JComboBox<>();
        lbNivel16 = new javax.swing.JLabel();
        lbNivel12 = new javax.swing.JLabel();
        lbNombreEstudianteGrafico = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlHistorialActividad = new javax.swing.JList<>();
        lbNivel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jlAlertaIncidentes = new javax.swing.JList<>();
        lbNivel11 = new javax.swing.JLabel();
        btnAsistencia = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(1250, 710));
        setPreferredSize(new java.awt.Dimension(1250, 710));

        jpDashboardDocente.setBackground(new java.awt.Color(218, 224, 242));
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
        jLabel9.setText("Total Estudiantes");

        lbEstudiante.setFont(new java.awt.Font("Arial Black", 1, 26)); // NOI18N
        lbEstudiante.setForeground(new java.awt.Color(23, 64, 112));
        lbEstudiante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEstudiante.setText("-");
        lbEstudiante.setToolTipText("Total de estudiantes matriculados");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpDashboardDocente.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 200, 95));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setForeground(new java.awt.Color(255, 255, 255));
        jPanel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel13.setFocusable(false);
        jPanel13.setPreferredSize(new java.awt.Dimension(180, 60));

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(45, 94, 152));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Presentes hoy");

        lbAsistentes.setFont(new java.awt.Font("Arial Black", 1, 26)); // NOI18N
        lbAsistentes.setForeground(new java.awt.Color(23, 50, 78));
        lbAsistentes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAsistentes.setText("-");
        lbAsistentes.setToolTipText("Total de estudiantes matriculados");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbAsistentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAsistentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpDashboardDocente.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 550, 200, 95));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setForeground(new java.awt.Color(255, 255, 255));
        jPanel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel14.setFocusable(false);
        jPanel14.setPreferredSize(new java.awt.Dimension(180, 60));

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(45, 94, 152));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Nivel Funcional");

        lbNivel.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        lbNivel.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNivel.setText("-");
        lbNivel.setToolTipText("Total de estudiantes matriculados");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpDashboardDocente.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 200, 95));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setForeground(new java.awt.Color(81, 124, 191));
        jPanel15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel15.setFocusable(false);
        jPanel15.setPreferredSize(new java.awt.Dimension(180, 60));

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(45, 94, 152));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Aula asignada");

        lbAula.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        lbAula.setForeground(new java.awt.Color(81, 124, 191));
        lbAula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAula.setText("-");
        lbAula.setToolTipText("Total de estudiantes matriculados");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbAula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpDashboardDocente.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 200, 95));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpGraficoIncidentes.setBackground(new java.awt.Color(255, 255, 255));
        jpGraficoIncidentes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jpGraficoIncidentes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpGrafico.setBackground(new java.awt.Color(255, 255, 255));
        jpGrafico.setMinimumSize(new java.awt.Dimension(200, 200));
        jpGrafico.setOpaque(false);
        jpGrafico.setPreferredSize(new java.awt.Dimension(248, 248));

        javax.swing.GroupLayout jpGraficoLayout = new javax.swing.GroupLayout(jpGrafico);
        jpGrafico.setLayout(jpGraficoLayout);
        jpGraficoLayout.setHorizontalGroup(
            jpGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jpGraficoLayout.setVerticalGroup(
            jpGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        jpGraficoIncidentes.add(jpGrafico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 300, 270));

        lbTituloGrafFicha.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbTituloGrafFicha.setForeground(new java.awt.Color(45, 94, 152));
        lbTituloGrafFicha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTituloGrafFicha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pie_chart_fill-1.png"))); // NOI18N
        lbTituloGrafFicha.setText("Gráfico de Análisis");
        jpGraficoIncidentes.add(lbTituloGrafFicha, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 320, 30));

        jPanel16.add(jpGraficoIncidentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, 320, 330));

        tableListaEstudiantes.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tableListaEstudiantes.setForeground(new java.awt.Color(39, 84, 138));
        tableListaEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Apellido", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableListaEstudiantes.setToolTipText("Selecciona un estudiante para ver sus métricas");
        tableListaEstudiantes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableListaEstudiantes.setGridColor(new java.awt.Color(204, 204, 204));
        tableListaEstudiantes.setRowHeight(25);
        tableListaEstudiantes.setSelectionBackground(new java.awt.Color(45, 94, 152));
        tableListaEstudiantes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tableListaEstudiantes.setShowGrid(false);
        jScrollPane1.setViewportView(tableListaEstudiantes);

        jPanel16.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 260, 320));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setOpaque(false);
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Edad:");
        jLabel30.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel30.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel17.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 80, 20));

        txtEdad.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtEdad.setForeground(new java.awt.Color(45, 94, 152));
        txtEdad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtEdad.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel17.add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 130, 18));

        jSeparator7.setForeground(new java.awt.Color(204, 204, 204));
        jPanel17.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 130, 10));

        jLabel34.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Alergias:");
        jLabel34.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel34.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel17.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 80, 20));

        txtAlergias.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtAlergias.setForeground(new java.awt.Color(45, 94, 152));
        jPanel17.add(txtAlergias, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 130, 18));

        jSeparator5.setForeground(new java.awt.Color(204, 204, 204));
        jPanel17.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 130, 10));

        jLabel33.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 102));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Medicinas:");
        jLabel33.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel33.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel17.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 80, 20));

        txtMedicinas.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtMedicinas.setForeground(new java.awt.Color(45, 94, 152));
        txtMedicinas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtMedicinas.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel17.add(txtMedicinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 130, 18));

        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));
        jPanel17.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 130, 10));

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Diagnósticos:");
        jLabel31.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel31.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel17.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 80, 20));

        txtDiagnostico.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtDiagnostico.setForeground(new java.awt.Color(45, 94, 152));
        txtDiagnostico.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtDiagnostico.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel17.add(txtDiagnostico, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 130, 18));

        jSeparator6.setForeground(new java.awt.Color(204, 204, 204));
        jPanel17.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 130, 10));

        jLabel32.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Observaciones:");
        jLabel32.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel17.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 190, 20));

        txtObserv.setEditable(false);
        txtObserv.setBackground(new java.awt.Color(255, 255, 255));
        txtObserv.setColumns(20);
        txtObserv.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtObserv.setForeground(new java.awt.Color(45, 94, 152));
        txtObserv.setLineWrap(true);
        txtObserv.setRows(5);
        txtObserv.setBorder(null);
        txtObserv.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtObserv.setEnabled(false);
        jScrollPane5.setViewportView(txtObserv);

        jPanel17.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 220, 90));

        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 240, 280));

        lbNivel15.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel15.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbNivel15.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel15.setText("Métrica:");
        lbNivel15.setToolTipText("");
        lbNivel15.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel16.add(lbNivel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 60, 20));

        cbTipoMetrica.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        cbTipoMetrica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Conductas por Tipo", "Antecedente de Fichas ABC", "Conductas por Mes", "Resumen ABC y Conducta" }));
        cbTipoMetrica.setBorder(null);
        cbTipoMetrica.setOpaque(true);
        cbTipoMetrica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoMetricaActionPerformed(evt);
            }
        });
        jPanel16.add(cbTipoMetrica, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 260, -1));

        lbNivel16.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel16.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbNivel16.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel16.setText("Selecciona un estudiante para ver más detalles:");
        lbNivel16.setToolTipText("");
        lbNivel16.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel16.add(lbNivel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 330, 20));

        lbNivel12.setBackground(new java.awt.Color(23, 50, 78));
        lbNivel12.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel12.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel12.setText("MÉTRICAS DE ESTUDIANTE");
        lbNivel12.setToolTipText("");
        lbNivel12.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel16.add(lbNivel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 280, 30));

        lbNombreEstudianteGrafico.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        lbNombreEstudianteGrafico.setForeground(new java.awt.Color(45, 94, 152));
        lbNombreEstudianteGrafico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombreEstudianteGrafico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/User_fill-1.png"))); // NOI18N
        lbNombreEstudianteGrafico.setText("Estudiante");
        jPanel16.add(lbNombreEstudianteGrafico, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 220, 30));

        jpDashboardDocente.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 910, 430));

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

        jpDashboardDocente.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 20, 95));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Info_fill-1_2.png"))); // NOI18N
        jLabel6.setText("Mi actividad reciente");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 340, 30));

        jlHistorialActividad.setBorder(null);
        jlHistorialActividad.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jlHistorialActividad.setForeground(new java.awt.Color(51, 51, 51));
        jlHistorialActividad.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jlHistorialActividad.setFocusable(false);
        jlHistorialActividad.setSelectionBackground(new java.awt.Color(204, 221, 255));
        jlHistorialActividad.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jScrollPane2.setViewportView(jlHistorialActividad);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 660, 110));

        lbNivel10.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel10.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbNivel10.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Info_fill-1_2.png"))); // NOI18N
        lbNivel10.setText("Estudiantes con incidentes");
        lbNivel10.setToolTipText("");
        lbNivel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lbNivel10.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel2.add(lbNivel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 270, 30));

        jlAlertaIncidentes.setBorder(null);
        jlAlertaIncidentes.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jlAlertaIncidentes.setForeground(new java.awt.Color(153, 0, 0));
        jlAlertaIncidentes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jlAlertaIncidentes.setFocusable(false);
        jlAlertaIncidentes.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jlAlertaIncidentes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setViewportView(jlAlertaIncidentes);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 270, 110));

        lbNivel11.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel11.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbNivel11.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNivel11.setText("Marcar asistencias");
        lbNivel11.setToolTipText("");
        lbNivel11.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel2.add(lbNivel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 70, 190, 30));

        btnAsistencia.setBackground(new java.awt.Color(221, 168, 83));
        btnAsistencia.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        btnAsistencia.setForeground(new java.awt.Color(255, 255, 255));
        btnAsistencia.setText("ASISTENCIAS");
        btnAsistencia.setBorderPainted(false);
        btnAsistencia.setFocusPainted(false);
        btnAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsistenciaActionPerformed(evt);
            }
        });
        jPanel2.add(btnAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 100, 190, 48));

        jpDashboardDocente.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 190));

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

        jpDashboardDocente.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 20, 95));

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

        jpDashboardDocente.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 20, 95));

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

        jpDashboardDocente.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 20, 95));

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

    private void btnAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsistenciaActionPerformed
        VistaAsistencia vAsistencia = new VistaAsistencia(idDocente);
        vAsistencia.setVisible(true);
        vAsistencia.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnAsistenciaActionPerformed

    private void cbTipoMetricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoMetricaActionPerformed

        int selectedRow = tableListaEstudiantes.getSelectedRow();
        if (selectedRow != -1) {
            int idEstudiante = (int) tableListaEstudiantes.getValueAt(selectedRow, 0);
            actualizarGraficoMetrica(idEstudiante);
        }
    }//GEN-LAST:event_cbTipoMetricaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsistencia;
    private javax.swing.JComboBox<String> cbTipoMetrica;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JList<String> jlAlertaIncidentes;
    private javax.swing.JList<String> jlHistorialActividad;
    private javax.swing.JPanel jpDashboardDocente;
    private javax.swing.JPanel jpGrafico;
    private javax.swing.JPanel jpGraficoIncidentes;
    private javax.swing.JLabel lbAsistentes;
    private javax.swing.JLabel lbAula;
    private javax.swing.JLabel lbEstudiante;
    private javax.swing.JLabel lbNivel;
    private javax.swing.JLabel lbNivel10;
    private javax.swing.JLabel lbNivel11;
    private javax.swing.JLabel lbNivel12;
    private javax.swing.JLabel lbNivel15;
    private javax.swing.JLabel lbNivel16;
    private javax.swing.JLabel lbNombreEstudianteGrafico;
    private javax.swing.JLabel lbTituloGrafFicha;
    private javax.swing.JTable tableListaEstudiantes;
    private javax.swing.JLabel txtAlergias;
    private javax.swing.JLabel txtDiagnostico;
    private javax.swing.JLabel txtEdad;
    private javax.swing.JLabel txtMedicinas;
    private javax.swing.JTextArea txtObserv;
    // End of variables declaration//GEN-END:variables
}
