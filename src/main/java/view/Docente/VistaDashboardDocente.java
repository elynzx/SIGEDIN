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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JOptionPane;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.renderer.category.StandardBarPainter;

public class VistaDashboardDocente extends javax.swing.JPanel {

    private final EstudianteCtrl estudianteCtrl;
    private final DashboardDocenteCtrl dashboardDocenteCtrl;
    private List<Integer> listaIdsEstudiantesAlerta;
    private int idDocente;

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
        seleccionJListaAlumnos();
    }

    public void actualizarDatosDashboard() {
        mostrarDatosAula(idDocente);
        cargarEstudiantesTabla();
        jlHistorialRegistros.setListData(dashboardDocenteCtrl.obtenerHistorialIncidentesRegistrados(idDocente).toArray(new String[0]));
        List<String> alertas = dashboardDocenteCtrl.obtenerListaAlertas(idDocente);
        jlAlertaIncidentes.setListData(alertas.toArray(new String[0]));

        listaIdsEstudiantesAlerta = dashboardDocenteCtrl.obtenerIdsEstudiantesConAlertas(idDocente);
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
                }
            }
        });
    }

    public void mostrarGraficoFichasABC(int idEstudiante) {
        Map<String, Integer> datos = dashboardDocenteCtrl.obtenerFichasABCporAntecedente(idEstudiante);

        if (datos == null || datos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Este estudiante no tiene fichas ABC registradas.");
            return;
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        datos.forEach(dataset::setValue);

        JFreeChart chart = ChartFactory.createPieChart(
                null,
                dataset,
                false,
                false,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();

        Color[] colores = {
            new Color(0x6F7DA6),
            new Color(0xF2DAAC),
            new Color(0x1C1F59),
            new Color(0xD9C3C1)
        };

        int i = 0;
        for (Object key : dataset.getKeys()) {
            plot.setSectionPaint((Comparable) key, colores[i % colores.length]);
            i++;
        }

        Color fondo = new Color(23, 50, 78);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setShadowPaint(null);
        plot.setSectionOutlinesVisible(false);
        plot.setLabelGenerator(null);
        plot.setToolTipGenerator(null);

        ChartPanel panel = new ChartPanel(chart, false);
        panel.setOpaque(true);
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(jpGraficoPie.getWidth(), jpGraficoPie.getHeight()));
        panel.setToolTipText(null);
        panel.setDisplayToolTips(false);
        panel.setBorder(null);

        jpGraficoPie.removeAll();
        jpGraficoPie.setLayout(new BorderLayout());
        jpGraficoPie.add(panel, BorderLayout.CENTER);
        jpGraficoPie.revalidate();
        jpGraficoPie.repaint();

        jpLeyendaPie.removeAll();
        jpLeyendaPie.setLayout(new GridLayout(0, 1, 0, 0));

        i = 0;
        for (Map.Entry<String, Integer> entry : datos.entrySet()) {
            JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
            fila.setOpaque(false);

            JLabel colorBox = new JLabel();
            colorBox.setPreferredSize(new Dimension(10, 10));
            colorBox.setOpaque(true);
            colorBox.setBackground(colores[i % colores.length]);

            JLabel texto = new JLabel(entry.getKey() + ": " + entry.getValue());
            texto.setForeground(fondo);
            texto.setFont(new Font("SansSerif", Font.PLAIN, 11));

            fila.add(colorBox);
            fila.add(Box.createHorizontalStrut(6));
            fila.add(texto);
            jpLeyendaPie.add(fila);
            i++;
        }

        jpLeyendaPie.setBackground(Color.WHITE);
        jpLeyendaPie.revalidate();
        jpLeyendaPie.repaint();

        String nombre = dashboardDocenteCtrl.obtenerNombreEstudiante(idEstudiante);
        lbTituloGrafFicha.setText("Distribución de fichas ABC");
        lbNombreEstudianteGrafico.setText("Alumno: " + nombre);
    }

    public void mostrarGraficoConductas(int idEstudiante) {
        Map<String, Integer> datos = dashboardDocenteCtrl.obtenerConductasPorMes(idEstudiante);

        if (datos == null || datos.isEmpty()) {
            jpGraficoConductas.removeAll();
            jpGraficoConductas.repaint();
            return;
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        datos.forEach((mes, cantidad) -> dataset.addValue(cantidad, "Conductas", mes));

        JFreeChart chart = ChartFactory.createBarChart(
                "",
                "Mes",
                "Cantidad",
                dataset
        );

        chart.setBackgroundPaint(new Color(23, 50, 78));
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0xF2DAAC));
        renderer.setBarPainter(new StandardBarPainter());

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelPaint(Color.WHITE);
        domainAxis.setLabelPaint(Color.WHITE);

        plot.getRangeAxis().setTickLabelPaint(Color.WHITE);
        plot.getRangeAxis().setLabelPaint(Color.WHITE);

        chart.getTitle().setPaint(Color.WHITE);
        chart.getLegend().setItemPaint(Color.WHITE);
        chart.getLegend().setVisible(false);

        ChartPanel panel = new ChartPanel(chart);
        panel.setBackground(new Color(23, 50, 78));
        panel.setPreferredSize(new Dimension(jpGraficoConductas.getWidth(), 220));
        panel.setBorder(null);
        panel.setOpaque(true);

        jpGraficoConductas.removeAll();
        jpGraficoConductas.setLayout(new BorderLayout());
        jpGraficoConductas.add(panel, BorderLayout.CENTER);
        jpGraficoConductas.revalidate();
        jpGraficoConductas.repaint();
    }

    private void seleccionJListaAlumnos() {
        jlAlertaIncidentes.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = jlAlertaIncidentes.getSelectedIndex();
                if (index >= 0 && index < listaIdsEstudiantesAlerta.size()) {
                    int idEstudiante = listaIdsEstudiantesAlerta.get(index);
                    mostrarGraficoFichasABC(idEstudiante);
                    mostrarGraficoConductas(idEstudiante);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpDashboardDocente = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlHistorialRegistros = new javax.swing.JList<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
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
        jpGraficoEstudiantes = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        lbNivel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jlAlertaIncidentes = new javax.swing.JList<>();
        jpGraficoIncidentes = new javax.swing.JPanel();
        jpGraficoPie = new javax.swing.JPanel();
        jpLeyendaPie = new javax.swing.JPanel();
        lbNivel11 = new javax.swing.JLabel();
        lbNombreEstudianteGrafico = new javax.swing.JLabel();
        lbTituloGrafFicha = new javax.swing.JLabel();
        lbTituloGrafConductas = new javax.swing.JLabel();
        jpGraficoIncidentes1 = new javax.swing.JPanel();
        jpGraficoConductas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableListaEstudiantes = new javax.swing.JTable();
        btnAsistencia = new javax.swing.JButton();
        lbNivel10 = new javax.swing.JLabel();
        lbNivel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(1250, 710));
        setPreferredSize(new java.awt.Dimension(1250, 710));

        jpDashboardDocente.setBackground(new java.awt.Color(255, 255, 255));
        jpDashboardDocente.setMinimumSize(new java.awt.Dimension(1250, 710));
        jpDashboardDocente.setPreferredSize(new java.awt.Dimension(1250, 710));
        jpDashboardDocente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlHistorialRegistros.setBorder(null);
        jlHistorialRegistros.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jlHistorialRegistros.setForeground(new java.awt.Color(102, 102, 102));
        jlHistorialRegistros.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jlHistorialRegistros.setFocusable(false);
        jlHistorialRegistros.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jlHistorialRegistros.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(jlHistorialRegistros);

        jpDashboardDocente.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 810, 90));

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(45, 94, 152));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Waterfall-1.png"))); // NOI18N
        jLabel14.setText("DASHBOARD PRINCIPAL");
        jpDashboardDocente.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 280, 30));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Info_fill-1_1.png"))); // NOI18N
        jLabel4.setText("Registra observaciones sobre la conducta del estudiante y su frecuencia diaria.");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jpDashboardDocente.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 490, -1));

        jPanel8.setBackground(new java.awt.Color(23, 50, 78));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel8.setFocusable(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(180, 60));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Mortarboard-2.png"))); // NOI18N
        jLabel9.setText("Total Estudiantes");

        lbEstudiante.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbEstudiante.setForeground(new java.awt.Color(255, 255, 255));
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

        jpDashboardDocente.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 180, 75));

        jPanel13.setBackground(new java.awt.Color(23, 64, 112));
        jPanel13.setForeground(new java.awt.Color(255, 255, 255));
        jPanel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel13.setFocusable(false);
        jPanel13.setPreferredSize(new java.awt.Dimension(180, 60));

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_ring_round-2.png"))); // NOI18N
        jLabel11.setText("Presentes hoy");

        lbAsistentes.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbAsistentes.setForeground(new java.awt.Color(255, 255, 255));
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

        jpDashboardDocente.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 210, 180, 75));

        jPanel14.setBackground(new java.awt.Color(45, 94, 152));
        jPanel14.setForeground(new java.awt.Color(255, 255, 255));
        jPanel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel14.setFocusable(false);
        jPanel14.setPreferredSize(new java.awt.Dimension(180, 60));

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Sort_up_alt-2.png"))); // NOI18N
        jLabel12.setText("Nivel Funcional");

        lbNivel.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbNivel.setForeground(new java.awt.Color(255, 255, 255));
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

        jpDashboardDocente.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 180, 75));

        jPanel15.setBackground(new java.awt.Color(81, 124, 191));
        jPanel15.setForeground(new java.awt.Color(81, 124, 191));
        jPanel15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel15.setFocusable(false);
        jPanel15.setPreferredSize(new java.awt.Dimension(180, 60));

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Group_fill-2.png"))); // NOI18N
        jLabel13.setText("Aula asignada");

        lbAula.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbAula.setForeground(new java.awt.Color(255, 255, 255));
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
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
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

        jpDashboardDocente.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 180, 75));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setOpaque(false);
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Edad:");
        jLabel30.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel30.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel17.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 80, 20));

        txtEdad.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtEdad.setForeground(new java.awt.Color(23, 64, 112));
        txtEdad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtEdad.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel17.add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 150, 18));

        jSeparator7.setForeground(new java.awt.Color(204, 204, 204));
        jPanel17.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 150, 10));

        jLabel34.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Alergias:");
        jLabel34.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel34.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel17.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 80, 20));

        txtAlergias.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtAlergias.setForeground(new java.awt.Color(23, 64, 112));
        jPanel17.add(txtAlergias, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 150, 18));

        jSeparator5.setForeground(new java.awt.Color(204, 204, 204));
        jPanel17.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 150, 10));

        jLabel33.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 102));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Medicinas:");
        jLabel33.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel33.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel17.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 80, 20));

        txtMedicinas.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtMedicinas.setForeground(new java.awt.Color(23, 64, 112));
        txtMedicinas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtMedicinas.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel17.add(txtMedicinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 150, 18));

        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));
        jPanel17.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 150, 10));

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Diagnósticos:");
        jLabel31.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel31.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel17.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 80, 20));

        txtDiagnostico.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtDiagnostico.setForeground(new java.awt.Color(23, 64, 112));
        txtDiagnostico.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtDiagnostico.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel17.add(txtDiagnostico, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 150, 18));

        jSeparator6.setForeground(new java.awt.Color(204, 204, 204));
        jPanel17.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 150, 10));

        jLabel32.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Observaciones:");
        jLabel32.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel17.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 190, 20));

        txtObserv.setEditable(false);
        txtObserv.setBackground(new java.awt.Color(255, 255, 255));
        txtObserv.setColumns(20);
        txtObserv.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtObserv.setForeground(new java.awt.Color(23, 64, 112));
        txtObserv.setLineWrap(true);
        txtObserv.setRows(5);
        txtObserv.setBorder(null);
        txtObserv.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtObserv.setEnabled(false);
        jScrollPane5.setViewportView(txtObserv);

        jPanel17.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 240, 90));

        jpGraficoEstudiantes.setBackground(new java.awt.Color(255, 255, 255));
        jpGraficoEstudiantes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jpGraficoEstudiantesLayout = new javax.swing.GroupLayout(jpGraficoEstudiantes);
        jpGraficoEstudiantes.setLayout(jpGraficoEstudiantesLayout);
        jpGraficoEstudiantesLayout.setHorizontalGroup(
            jpGraficoEstudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );
        jpGraficoEstudiantesLayout.setVerticalGroup(
            jpGraficoEstudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        jPanel17.add(jpGraficoEstudiantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 280, 290));

        jpDashboardDocente.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, 560, 360));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbNivel7.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel7.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel7.setForeground(new java.awt.Color(161, 34, 130));
        lbNivel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Alarm_fill-2.png"))); // NOI18N
        lbNivel7.setText("Estudiantes con Incidentes");
        lbNivel7.setToolTipText("");
        lbNivel7.setIconTextGap(2);
        lbNivel7.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel16.add(lbNivel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 260, 30));

        jlAlertaIncidentes.setBorder(null);
        jlAlertaIncidentes.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jlAlertaIncidentes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jlAlertaIncidentes.setFocusable(false);
        jlAlertaIncidentes.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jlAlertaIncidentes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setViewportView(jlAlertaIncidentes);

        jPanel16.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 280, 90));

        jpGraficoIncidentes.setBackground(new java.awt.Color(255, 255, 255));
        jpGraficoIncidentes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jpGraficoIncidentes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpGraficoPie.setBackground(new java.awt.Color(255, 255, 255));
        jpGraficoPie.setMinimumSize(new java.awt.Dimension(200, 200));
        jpGraficoPie.setOpaque(false);
        jpGraficoPie.setPreferredSize(new java.awt.Dimension(248, 248));

        javax.swing.GroupLayout jpGraficoPieLayout = new javax.swing.GroupLayout(jpGraficoPie);
        jpGraficoPie.setLayout(jpGraficoPieLayout);
        jpGraficoPieLayout.setHorizontalGroup(
            jpGraficoPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jpGraficoPieLayout.setVerticalGroup(
            jpGraficoPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jpGraficoIncidentes.add(jpGraficoPie, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 220, 120));

        jpLeyendaPie.setBackground(new java.awt.Color(255, 255, 255));
        jpLeyendaPie.setPreferredSize(new java.awt.Dimension(248, 80));
        jpLeyendaPie.setLayout(new java.awt.BorderLayout());
        jpGraficoIncidentes.add(jpLeyendaPie, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 260, 40));

        jPanel16.add(jpGraficoIncidentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 280, 180));

        lbNivel11.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel11.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbNivel11.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel11.setText("Selecciona un estudiante:");
        lbNivel11.setToolTipText("");
        lbNivel11.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel16.add(lbNivel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 240, -1));

        lbNombreEstudianteGrafico.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbNombreEstudianteGrafico.setForeground(new java.awt.Color(102, 102, 102));
        lbNombreEstudianteGrafico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombreEstudianteGrafico.setText("Estudiante");
        jPanel16.add(lbNombreEstudianteGrafico, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 280, 20));

        lbTituloGrafFicha.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lbTituloGrafFicha.setForeground(new java.awt.Color(45, 94, 152));
        lbTituloGrafFicha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTituloGrafFicha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pie_chart_fill-1.png"))); // NOI18N
        lbTituloGrafFicha.setText("Gráfico de Análisis");
        jPanel16.add(lbTituloGrafFicha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 280, 20));

        lbTituloGrafConductas.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lbTituloGrafConductas.setForeground(new java.awt.Color(45, 94, 152));
        lbTituloGrafConductas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTituloGrafConductas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pie_chart_fill-1.png"))); // NOI18N
        lbTituloGrafConductas.setText("Gráfico de Análisis");
        jPanel16.add(lbTituloGrafConductas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 280, 20));

        jpGraficoIncidentes1.setBackground(new java.awt.Color(255, 255, 255));
        jpGraficoIncidentes1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jpGraficoIncidentes1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpGraficoConductas.setBackground(new java.awt.Color(255, 255, 255));
        jpGraficoConductas.setMinimumSize(new java.awt.Dimension(200, 200));
        jpGraficoConductas.setOpaque(false);
        jpGraficoConductas.setPreferredSize(new java.awt.Dimension(248, 248));

        javax.swing.GroupLayout jpGraficoConductasLayout = new javax.swing.GroupLayout(jpGraficoConductas);
        jpGraficoConductas.setLayout(jpGraficoConductasLayout);
        jpGraficoConductasLayout.setHorizontalGroup(
            jpGraficoConductasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jpGraficoConductasLayout.setVerticalGroup(
            jpGraficoConductasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jpGraficoIncidentes1.add(jpGraficoConductas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 260, 160));

        jPanel16.add(jpGraficoIncidentes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 280, 180));

        jpDashboardDocente.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 30, 330, 650));

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
                "Id", "Nombre", "Apellido"
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
        tableListaEstudiantes.setToolTipText("Selecciona un alumno para ver sus métricas");
        tableListaEstudiantes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableListaEstudiantes.setGridColor(new java.awt.Color(204, 204, 204));
        tableListaEstudiantes.setRowHeight(25);
        tableListaEstudiantes.setSelectionBackground(new java.awt.Color(45, 94, 152));
        tableListaEstudiantes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tableListaEstudiantes.setShowGrid(false);
        jScrollPane1.setViewportView(tableListaEstudiantes);

        jpDashboardDocente.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 240, 290));

        btnAsistencia.setBackground(new java.awt.Color(221, 168, 83));
        btnAsistencia.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnAsistencia.setForeground(new java.awt.Color(255, 255, 255));
        btnAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Tumer-2.png"))); // NOI18N
        btnAsistencia.setText("ASISTENCIAS");
        btnAsistencia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAsistencia.setBorderPainted(false);
        btnAsistencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAsistencia.setFocusPainted(false);
        btnAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsistenciaActionPerformed(evt);
            }
        });
        jpDashboardDocente.add(btnAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, 170, 50));

        lbNivel10.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel10.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel10.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel10.setText("Métricas del estudiante");
        lbNivel10.setToolTipText("");
        lbNivel10.setPreferredSize(new java.awt.Dimension(70, 25));
        jpDashboardDocente.add(lbNivel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 250, 30));

        lbNivel15.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel15.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbNivel15.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel15.setText("Selecciona un estudiante para ver más detalles:");
        lbNivel15.setToolTipText("");
        lbNivel15.setPreferredSize(new java.awt.Dimension(70, 25));
        jpDashboardDocente.add(lbNivel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 330, 20));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        jpDashboardDocente.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 30, -1, 620));

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsistencia;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
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
    private javax.swing.JList<String> jlHistorialRegistros;
    private javax.swing.JPanel jpDashboardDocente;
    private javax.swing.JPanel jpGraficoConductas;
    private javax.swing.JPanel jpGraficoEstudiantes;
    private javax.swing.JPanel jpGraficoIncidentes;
    private javax.swing.JPanel jpGraficoIncidentes1;
    private javax.swing.JPanel jpGraficoPie;
    private javax.swing.JPanel jpLeyendaPie;
    private javax.swing.JLabel lbAsistentes;
    private javax.swing.JLabel lbAula;
    private javax.swing.JLabel lbEstudiante;
    private javax.swing.JLabel lbNivel;
    private javax.swing.JLabel lbNivel10;
    private javax.swing.JLabel lbNivel11;
    private javax.swing.JLabel lbNivel15;
    private javax.swing.JLabel lbNivel7;
    private javax.swing.JLabel lbNombreEstudianteGrafico;
    private javax.swing.JLabel lbTituloGrafConductas;
    private javax.swing.JLabel lbTituloGrafFicha;
    private javax.swing.JTable tableListaEstudiantes;
    private javax.swing.JLabel txtAlergias;
    private javax.swing.JLabel txtDiagnostico;
    private javax.swing.JLabel txtEdad;
    private javax.swing.JLabel txtMedicinas;
    private javax.swing.JTextArea txtObserv;
    // End of variables declaration//GEN-END:variables
}
