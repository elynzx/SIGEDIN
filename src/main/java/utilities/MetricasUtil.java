package utilities;

import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import org.jfree.chart.renderer.category.StandardBarPainter;

public class MetricasUtil {

    private static final Color[] COLORES = {
        new Color(0x6F7DA6),
        new Color(0xF2DAAC),
        new Color(0xD9C3C1),
        new Color(0x1C1F59)
    };

    private static void mostrarNombreEstudiante(JLabel lbNombre, String nombreEstudiante) {
        lbNombre.setText("Estudiante: " + nombreEstudiante);
    }

    private static void limpiarPanel(JPanel panel, ChartPanel chartPanel) {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    private static void configurarEstiloPie(PiePlot plot, DefaultPieDataset dataset) {
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setShadowPaint(null);
        plot.setSectionOutlinesVisible(false);
        plot.setToolTipGenerator(null);
        plot.setLabelGenerator(null);

        for (int i = 0; i < dataset.getItemCount(); i++) {
            plot.setSectionPaint(dataset.getKey(i), COLORES[i % COLORES.length]);
        }
    }

    private static void configurarPanel(ChartPanel chartPanel, JPanel panel) {
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setPreferredSize(panel.getSize());
        chartPanel.setBorder(null);
        chartPanel.setDisplayToolTips(false);
        chartPanel.setToolTipText(null);
    }

    private static void mostrarMensaje(JPanel panel, String mensaje) {
        panel.removeAll();
        JLabel label = new JLabel(mensaje, SwingConstants.CENTER);
        label.setForeground(new Color(100, 100, 100));
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    public static void mostrarGraficoConductasPorTipo(JPanel panel, JLabel lbTitulo, JLabel lbNombre, String nombreEstudiante, Map<String, Integer> datos) {
        if (lbNombre != null) {
            mostrarNombreEstudiante(lbNombre,nombreEstudiante);
        }

        if (datos == null || datos.isEmpty()) {
            mostrarMensaje(panel, "No hay registros de conductas problemáticas.");
            return;
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        datos.forEach(dataset::setValue);

        JFreeChart chart = ChartFactory.createPieChart(
                null,
                dataset,
                true,
                false,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        configurarEstiloPie(plot, dataset);

        ChartPanel chartPanel = new ChartPanel(chart, false);
        configurarPanel(chartPanel, panel);

        limpiarPanel(panel, chartPanel);

    }

    public static void mostrarGraficoFichasABC(JPanel panel, JLabel lbTitulo, JLabel lbNombre, String nombreEstudiante, Map<String, Integer> datos) {

        if (lbTitulo != null) {
            lbTitulo.setText("Antecedente de Fichas ABC");
        }
        if (lbNombre != null) {
          mostrarNombreEstudiante(lbNombre,nombreEstudiante);
        }

        if (datos == null || datos.isEmpty()) {
            mostrarMensaje(panel, "No hay Fichas ABC registradas.");
            return;
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        datos.forEach(dataset::setValue);

        JFreeChart chart = ChartFactory.createPieChart(
                null,
                dataset,
                true,
                false,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        configurarEstiloPie(plot, dataset);

        ChartPanel chartPanel = new ChartPanel(chart, false);
        configurarPanel(chartPanel, panel);

        limpiarPanel(panel, chartPanel);
    }

    public static void mostrarGraficoConductasPorMes(JPanel panel, JLabel lbTitulo, JLabel lbNombre, String nombreEstudiante, Map<String, Integer> datos) {
        if (lbTitulo != null) {
            lbTitulo.setText("Conductas Problemáticas por Mes");
        }
        if (lbNombre != null) {
          mostrarNombreEstudiante(lbNombre,nombreEstudiante);
        }

        if (datos == null || datos.isEmpty()) {
            mostrarMensaje(panel, "No hay registros por mes.");
            return;
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        datos.forEach((mes, cantidad) -> dataset.addValue(cantidad, "Conductas", mes));

        JFreeChart chart = ChartFactory.createBarChart(null, "Mes", "Cantidad", dataset);
        CategoryPlot plot = chart.getCategoryPlot();

        BarRenderer renderer = new BarRenderer();
        renderer.setMaximumBarWidth(0.1);
        renderer.setItemMargin(0.05);
        renderer.setDefaultToolTipGenerator(null);
        renderer.setBarPainter(new StandardBarPainter());

        for (int i = 0; i < dataset.getRowCount(); i++) {
            renderer.setSeriesPaint(i, COLORES[i % COLORES.length]);
        }

        plot.setRenderer(renderer);
        plot.setOutlineVisible(false);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        chart.getLegend().setVisible(true);

        ChartPanel chartPanel = new ChartPanel(chart, false);
        configurarPanel(chartPanel, panel);
        limpiarPanel(panel, chartPanel);

    }

    public static void mostrarGraficoResumenABCyConductas(JPanel panel, JLabel lbTitulo, JLabel lbNombre, String nombreEstudiante, Map<String, Object> resumen) {

        if (lbTitulo != null) {
            lbTitulo.setText("Resumen de Conductas y Fichas ABC");
        }
        if (lbNombre != null) {
          mostrarNombreEstudiante(lbNombre,nombreEstudiante);
        }

        int totalConductas = (int) resumen.getOrDefault("conducta_total", 0);
        int totalABC = (int) resumen.getOrDefault("abc_total", 0);

        if (totalConductas == 0 && totalABC == 0) {
            mostrarMensaje(panel, "No hay incidentes registrados.");
            return;
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(totalConductas, "Total", "Conductas");
        dataset.addValue(totalABC, "Total", "Fichas ABC");

        JFreeChart chart = ChartFactory.createBarChart(null, "", "Cantidad", dataset);
        CategoryPlot plot = chart.getCategoryPlot();

        BarRenderer renderer = new BarRenderer();
        renderer.setMaximumBarWidth(0.1);
        renderer.setItemMargin(0.1);
        renderer.setDefaultToolTipGenerator(null);
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setSeriesPaint(0, COLORES[0]);
        renderer.setSeriesPaint(1, COLORES[1]);

        plot.setRenderer(renderer);
        plot.setOutlineVisible(false);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        chart.getLegend().setVisible(true);

        ChartPanel chartPanel = new ChartPanel(chart, false);
        configurarPanel(chartPanel, panel);
        limpiarPanel(panel, chartPanel);

    }
}
