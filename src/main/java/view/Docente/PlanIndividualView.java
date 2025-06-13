/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.Docente;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.dao.DocenteDao;
import model.dao.EstudianteDao;
import model.dao.IDocenteDao;
import model.dao.IEstudianteDao;
import model.dao.IPlanIndividual;
import model.dao.IRegistroIncidente;
import model.dao.PlanIndividualDao;
import model.dao.RegistroIncidenteDao;
import model.funcionalidad.catalogo.EstrategiaIntervencion;
import model.entidades.Estudiante;
import model.funcionalidad.PlanIntervencion;
import model.funcionalidad.ResumenIncidentes;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.FuncionComportamiento;
import model.funcionalidad.catalogo.TipoConducta;
import utillities.ExcelResumenIncidentes;
import utillities.ExcelHistorialIntervenciones;
import utillities.Utilidad;

/**
 *
 * @author rpasc
 */
public class PlanIndividualView extends javax.swing.JPanel {

    private IRegistroIncidente registroIncidenteDao = new RegistroIncidenteDao();
    private IPlanIndividual planIndividualDao = new PlanIndividualDao();
    private List<ResumenIncidentes> listaResumenIncidentes = new ArrayList<>();
    private List<PlanIntervencion> listaPlanes = new ArrayList<>();
    private IEstudianteDao estudianteDao = EstudianteDao.obtenerInstancia();
    private IDocenteDao docenteDao = DocenteDao.obtenerInstancia();
    private int idDocente;
    private Utilidad utilidad = new Utilidad();
    private int idEstudianteSeleccionado;
    private Estudiante estudianteSeleccionado;
    private FuncionComportamiento funcionComportamiento;
    private boolean listaEstudiantesInicializado = false;

    public PlanIndividualView(int idDocente) {
        this.idDocente = idDocente;
        initComponents();
        utilidad.mostrarDia(lbFechaHoy);
        cargarEstudiantes(idDocente);
        cargarEstrategiasIntervencion();
    }

    private void cargarEstudiantes(int idDocente) {
        cbListaEstudiantes.removeAllItems();
        List<Estudiante> listaEstudiantes = docenteDao.obtenerListaEstudiantes(idDocente);
        for (Estudiante estudiante : listaEstudiantes) {
            cbListaEstudiantes.addItem(estudiante);
        }
    }

    private void mostrarDatosEstudiante(int idEstudiante) {

        try {
            Estudiante estudiante = estudianteDao.obtenerDatosEstudiante(idEstudiante);
            txtIdEstudiante.setText(String.valueOf(estudiante.getIdEstudiante()));
            txtNombreEstudiante.setText(estudiante.getNombres() + " " + estudiante.getApellidos());
            txtDiagnosticoEstudiante.setText(estudiante.getDiagnosticos().stream().map(Diagnostico::getNombre).collect(Collectors.joining(", ")));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }

    }

    private void cargarResumenIncidentes(int idEstudiante) {

        listaResumenIncidentes = planIndividualDao.obtenerResumenIncidentes(idEstudiante);
        DefaultTableModel modelo = (DefaultTableModel) tbResumenIncidentes.getModel();
        modelo.setRowCount(0);

        for (ResumenIncidentes incidente : listaResumenIncidentes) {
            modelo.addRow(new Object[]{
                incidente.getTipoConducta().getNombre(),
                incidente.getFrecuencia(),
                incidente.getGravedadPromedio(),
                incidente.getUltComportamiento()
            });
        }

    }

    private String calcularEstadoPlan(Date fechaInicio) {
        LocalDate inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaEvaluacion = calcularFechaFinal(inicio, 14);

        return fechaEvaluacion.isAfter(LocalDate.now()) ? "En evaluacion: " : fechaEvaluacion.toString();
    }

    private LocalDate calcularFechaFinal(LocalDate fechaInicio, int diasHabiles) {
        LocalDate fechaFinal = fechaInicio;
        int contadorDias = 0;

        while (contadorDias < diasHabiles) {
            fechaFinal = fechaFinal.plusDays(1);
            if (fechaFinal.getDayOfWeek() != DayOfWeek.SATURDAY && fechaFinal.getDayOfWeek() != DayOfWeek.SUNDAY) {
                contadorDias++;
            }
        }

        return fechaFinal;
    }

    private void cargarHistorialIntervenciones(int idEstudiante) {

        listaPlanes = planIndividualDao.obtenerHistorialPlanes(idEstudiante);

        DefaultTableModel modelo = (DefaultTableModel) tbHistorialIntervenciones.getModel();
        modelo.setRowCount(0);

        for (PlanIntervencion plan : listaPlanes) {
            modelo.addRow(new Object[]{
                plan.getFechaInicio(),
                plan.getTipoConducta().getNombre(),
                plan.getEstrategia().getNombre(),
                calcularEstadoPlan(plan.getFechaInicio())
            });
        }

    }

    private void cargarConductasEstudiante(int idEstudiante) {
        cbTipoConducta.removeAllItems();
        List<TipoConducta> listaConductas = planIndividualDao.obtenerTipoConductasEstudiante(idEstudiante);
        for (TipoConducta tipo : listaConductas) {
            cbTipoConducta.addItem(tipo);
        }
    }

    private void cargarEstrategiasIntervencion() {
        cbEstrategiaIntervencion.removeAllItems();
        List<EstrategiaIntervencion> listaEstrategias = planIndividualDao.obtenerEstrategiasIntervencion();
        for (EstrategiaIntervencion estrategia : listaEstrategias) {
            cbEstrategiaIntervencion.addItem(estrategia);
        }
    }

    private void limpiarSeccionPlan() {
        cbEstrategiaIntervencion.setSelectedIndex(0);
        cbTipoConducta.setSelectedIndex(0);
        txtFuncionComportamiento.setText("");
        txtObjetivoPlan.setText("");
        txtObsPlan.setText("");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpDashboardDocente = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cbListaEstudiantes = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        lbNivel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbResumenIncidentes = new javax.swing.JTable();
        lbNivel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHistorialIntervenciones = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtDiagnosticoEstudiante = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtIdEstudiante = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNombreEstudiante = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lbNivel6 = new javax.swing.JLabel();
        descargaResumen = new javax.swing.JLabel();
        descargaHistorial = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbNivel1 = new javax.swing.JLabel();
        lbNivel4 = new javax.swing.JLabel();
        cbEstrategiaIntervencion = new javax.swing.JComboBox<>();
        lbNivel7 = new javax.swing.JLabel();
        txtFuncionComportamiento = new javax.swing.JTextField();
        lbNivel8 = new javax.swing.JLabel();
        lbNivel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObjetivoPlan = new javax.swing.JTextArea();
        lbNivel5 = new javax.swing.JLabel();
        chkImplementacion = new javax.swing.JCheckBox();
        lbNivel13 = new javax.swing.JLabel();
        lbNivel3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtObsPlan = new javax.swing.JTextArea();
        cbTipoConducta = new javax.swing.JComboBox<>();
        btnGuardarPlan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lbFechaHoy = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        jpDashboardDocente.setBackground(new java.awt.Color(255, 255, 255));
        jpDashboardDocente.setMinimumSize(new java.awt.Dimension(1250, 734));
        jpDashboardDocente.setPreferredSize(new java.awt.Dimension(1250, 734));
        jpDashboardDocente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbListaEstudiantes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbListaEstudiantes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cbListaEstudiantes.setOpaque(true);
        cbListaEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbListaEstudiantesActionPerformed(evt);
            }
        });
        jPanel3.add(cbListaEstudiantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 240, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Selecciona un estudiante:");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 152, 20));

        lbNivel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbNivel9.setForeground(new java.awt.Color(39, 84, 138));
        lbNivel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel9.setText("Resumen de Incidentes");
        lbNivel9.setToolTipText("");
        lbNivel9.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel3.add(lbNivel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 340, 30));

        tbResumenIncidentes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tbResumenIncidentes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbResumenIncidentes.setForeground(new java.awt.Color(51, 51, 51));
        tbResumenIncidentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Conducta problemática", "Frecuencia en 14 días", "Gravedad Promedio", "Última función registrada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbResumenIncidentes.setGridColor(new java.awt.Color(153, 153, 153));
        tbResumenIncidentes.setSelectionBackground(new java.awt.Color(247, 173, 213));
        jScrollPane3.setViewportView(tbResumenIncidentes);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 630, 130));

        lbNivel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbNivel10.setForeground(new java.awt.Color(39, 84, 138));
        lbNivel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel10.setText("Historial de Intervenciones Aplicadas");
        lbNivel10.setToolTipText("");
        lbNivel10.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel3.add(lbNivel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 340, 30));

        tbHistorialIntervenciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tbHistorialIntervenciones.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbHistorialIntervenciones.setForeground(new java.awt.Color(51, 51, 51));
        tbHistorialIntervenciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha Inicio", "Conducta Intervenida", "Estrategiavaplicada", "Estado actual"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHistorialIntervenciones.setGridColor(new java.awt.Color(153, 153, 153));
        tbHistorialIntervenciones.setSelectionBackground(new java.awt.Color(247, 173, 213));
        jScrollPane4.setViewportView(tbHistorialIntervenciones);

        jPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 414, 630, 134));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Diagnósticos:");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 240, 20));

        txtDiagnosticoEstudiante.setEditable(false);
        txtDiagnosticoEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        txtDiagnosticoEstudiante.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDiagnosticoEstudiante.setForeground(new java.awt.Color(51, 51, 51));
        txtDiagnosticoEstudiante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.add(txtDiagnosticoEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 240, 25));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("ID:");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 20, 20));

        txtIdEstudiante.setEditable(false);
        txtIdEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        txtIdEstudiante.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIdEstudiante.setForeground(new java.awt.Color(51, 51, 51));
        txtIdEstudiante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.add(txtIdEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 110, 25));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Nombres y apellidos:");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 111, 20));

        txtNombreEstudiante.setEditable(false);
        txtNombreEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreEstudiante.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNombreEstudiante.setForeground(new java.awt.Color(51, 51, 51));
        txtNombreEstudiante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.add(txtNombreEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 240, 25));

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 628, 10));

        lbNivel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lbNivel6.setForeground(new java.awt.Color(39, 84, 138));
        lbNivel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/User_fill-2.png"))); // NOI18N
        lbNivel6.setText("Datos de estudiante:");
        lbNivel6.setToolTipText("");
        lbNivel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbNivel6.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel3.add(lbNivel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 330, 50));

        descargaResumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Import-2.png"))); // NOI18N
        descargaResumen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        descargaResumen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descargaResumenMouseClicked(evt);
            }
        });
        jPanel3.add(descargaResumen, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, 33, 25));

        descargaHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Import-2.png"))); // NOI18N
        descargaHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        descargaHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descargaHistorialMouseClicked(evt);
            }
        });
        jPanel3.add(descargaHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 380, 33, 25));

        jpDashboardDocente.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 680, 580));

        jPanel4.setBackground(new java.awt.Color(45, 94, 152));

        lbNivel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbNivel1.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel1.setText("Objetivo del plan:");
        lbNivel1.setPreferredSize(new java.awt.Dimension(70, 25));

        lbNivel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbNivel4.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel4.setText("Selecciona la conducta a intervenir:");
        lbNivel4.setPreferredSize(new java.awt.Dimension(70, 25));

        cbEstrategiaIntervencion.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        cbEstrategiaIntervencion.setForeground(new java.awt.Color(51, 51, 51));
        cbEstrategiaIntervencion.setBorder(null);
        cbEstrategiaIntervencion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstrategiaIntervencionActionPerformed(evt);
            }
        });

        lbNivel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbNivel7.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel7.setText("Función del comportamiento:");
        lbNivel7.setPreferredSize(new java.awt.Dimension(70, 25));

        txtFuncionComportamiento.setForeground(new java.awt.Color(51, 51, 51));
        txtFuncionComportamiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

        lbNivel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbNivel8.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Group_light.png"))); // NOI18N
        lbNivel8.setText("Plan de intervención");
        lbNivel8.setPreferredSize(new java.awt.Dimension(70, 25));

        lbNivel11.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lbNivel11.setForeground(new java.awt.Color(221, 168, 83));
        lbNivel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNivel11.setText("*  Último registro");
        lbNivel11.setPreferredSize(new java.awt.Dimension(70, 25));

        txtObjetivoPlan.setColumns(20);
        txtObjetivoPlan.setForeground(new java.awt.Color(51, 51, 51));
        txtObjetivoPlan.setRows(5);
        txtObjetivoPlan.setBorder(null);
        jScrollPane2.setViewportView(txtObjetivoPlan);

        lbNivel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbNivel5.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel5.setText("Estrategia de Intervención");
        lbNivel5.setPreferredSize(new java.awt.Dimension(70, 25));

        chkImplementacion.setBackground(new java.awt.Color(45, 94, 152));
        chkImplementacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        chkImplementacion.setForeground(new java.awt.Color(255, 255, 255));
        chkImplementacion.setText("Sí");
        chkImplementacion.setEnabled(false);
        chkImplementacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkImplementacion.setOpaque(true);

        lbNivel13.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lbNivel13.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNivel13.setText("¿Ya se ha utilizado?");
        lbNivel13.setPreferredSize(new java.awt.Dimension(70, 25));

        lbNivel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbNivel3.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel3.setText("Observaciones finales:");
        lbNivel3.setPreferredSize(new java.awt.Dimension(70, 25));

        txtObsPlan.setColumns(20);
        txtObsPlan.setForeground(new java.awt.Color(51, 51, 51));
        txtObsPlan.setRows(5);
        txtObsPlan.setBorder(null);
        jScrollPane6.setViewportView(txtObsPlan);

        cbTipoConducta.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        cbTipoConducta.setForeground(new java.awt.Color(51, 51, 51));
        cbTipoConducta.setBorder(null);
        cbTipoConducta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoConductaActionPerformed(evt);
            }
        });

        btnGuardarPlan.setBackground(new java.awt.Color(221, 168, 83));
        btnGuardarPlan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardarPlan.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarPlan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Save_fill-2.png"))); // NOI18N
        btnGuardarPlan.setText("GUARDAR");
        btnGuardarPlan.setBorder(null);
        btnGuardarPlan.setBorderPainted(false);
        btnGuardarPlan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarPlan.setFocusPainted(false);
        btnGuardarPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPlanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNivel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNivel8, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(156, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNivel7, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbNivel11, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbEstrategiaIntervencion, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFuncionComportamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbTipoConducta, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbNivel3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbNivel1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbNivel4, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(lbNivel13, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(chkImplementacion, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnGuardarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 37, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbNivel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lbNivel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTipoConducta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNivel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNivel11, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFuncionComportamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNivel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNivel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNivel13, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkImplementacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEstrategiaIntervencion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNivel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jpDashboardDocente.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, 450, 540));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 243));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(39, 84, 138));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Plan Individual Conductual");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 33, 387, 50));

        lbFechaHoy.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbFechaHoy.setForeground(new java.awt.Color(39, 84, 138));
        lbFechaHoy.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbFechaHoy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Date_fill-1_1.png"))); // NOI18N
        lbFechaHoy.setText("Fecha de hoy");
        jPanel1.add(lbFechaHoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 36, 456, 40));

        jpDashboardDocente.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 90));

        jSeparator2.setForeground(new java.awt.Color(45, 94, 152));
        jpDashboardDocente.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 1160, 12));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpDashboardDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpDashboardDocente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbListaEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbListaEstudiantesActionPerformed

        if (!listaEstudiantesInicializado) {
            listaEstudiantesInicializado = true;
            return;
        }

        Estudiante estudiante = (Estudiante) cbListaEstudiantes.getSelectedItem();
        if (estudiante != null) {

            estudiante = estudianteDao.obtenerDatosEstudiante(estudiante.getIdEstudiante());
            estudianteSeleccionado = estudiante;
            idEstudianteSeleccionado = estudiante.getIdEstudiante();

            mostrarDatosEstudiante(idEstudianteSeleccionado);
            cargarHistorialIntervenciones(idEstudianteSeleccionado);
            cargarResumenIncidentes(idEstudianteSeleccionado);
            cargarConductasEstudiante(idEstudianteSeleccionado);
        }

    }//GEN-LAST:event_cbListaEstudiantesActionPerformed

    private void cbTipoConductaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoConductaActionPerformed

        TipoConducta conductaSeleccionada = (TipoConducta) cbTipoConducta.getSelectedItem();
        if (conductaSeleccionada != null) {
            funcionComportamiento = planIndividualDao.obtenerUltimaFuncionComportamiento(idEstudianteSeleccionado, conductaSeleccionada.getId());
            txtFuncionComportamiento.setText(funcionComportamiento.getNombre());
        }


    }//GEN-LAST:event_cbTipoConductaActionPerformed

    private void cbEstrategiaIntervencionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstrategiaIntervencionActionPerformed

        TipoConducta conductaSeleccionada = (TipoConducta) cbTipoConducta.getSelectedItem();
        EstrategiaIntervencion estrategia = (EstrategiaIntervencion) cbEstrategiaIntervencion.getSelectedItem();

        if (conductaSeleccionada != null && estrategia != null) {
            chkImplementacion.setSelected(planIndividualDao.verificarPlanExistente(idEstudianteSeleccionado, conductaSeleccionada.getId(), estrategia.getId()));
        }


    }//GEN-LAST:event_cbEstrategiaIntervencionActionPerformed

    private void btnGuardarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPlanActionPerformed

        if (planIndividualDao.registrarPlanIntervencion(
                idEstudianteSeleccionado,
                ((TipoConducta) cbTipoConducta.getSelectedItem()).getId(),
                funcionComportamiento.getId(),
                txtObjetivoPlan.getText(),
                ((EstrategiaIntervencion) cbEstrategiaIntervencion.getSelectedItem()).getId(),
                chkImplementacion.isSelected(),
                txtObsPlan.getText()
        )) {
            JOptionPane.showMessageDialog(this, "Plan de intervención registrado correctamente.");
            cargarHistorialIntervenciones(idEstudianteSeleccionado);
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el plan de intervención.");
        }

        cargarHistorialIntervenciones(idDocente);
        limpiarSeccionPlan();

    }//GEN-LAST:event_btnGuardarPlanActionPerformed

    private void descargaResumenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descargaResumenMouseClicked

        if (listaResumenIncidentes == null || listaResumenIncidentes.isEmpty()) {
            JOptionPane.showMessageDialog(PlanIndividualView.this, "No hay datos para exportar.");
            return;
        }
        ExcelResumenIncidentes.exportarResumen(listaResumenIncidentes, estudianteSeleccionado, PlanIndividualView.this);

     
    }//GEN-LAST:event_descargaResumenMouseClicked

    private void descargaHistorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descargaHistorialMouseClicked
        // TODO add your handling code here:

        if (listaPlanes == null || listaPlanes.isEmpty()) {
            JOptionPane.showMessageDialog(PlanIndividualView.this, "No hay datos para exportar.");
            return;
        }
        ExcelHistorialIntervenciones.exportarHistorial(listaPlanes, estudianteSeleccionado, PlanIndividualView.this);

    }//GEN-LAST:event_descargaHistorialMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarPlan;
    private javax.swing.JComboBox<EstrategiaIntervencion> cbEstrategiaIntervencion;
    private javax.swing.JComboBox<Estudiante> cbListaEstudiantes;
    private javax.swing.JComboBox<TipoConducta> cbTipoConducta;
    private javax.swing.JCheckBox chkImplementacion;
    private javax.swing.JLabel descargaHistorial;
    private javax.swing.JLabel descargaResumen;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel jpDashboardDocente;
    private javax.swing.JLabel lbFechaHoy;
    private javax.swing.JLabel lbNivel1;
    private javax.swing.JLabel lbNivel10;
    private javax.swing.JLabel lbNivel11;
    private javax.swing.JLabel lbNivel13;
    private javax.swing.JLabel lbNivel3;
    private javax.swing.JLabel lbNivel4;
    private javax.swing.JLabel lbNivel5;
    private javax.swing.JLabel lbNivel6;
    private javax.swing.JLabel lbNivel7;
    private javax.swing.JLabel lbNivel8;
    private javax.swing.JLabel lbNivel9;
    private javax.swing.JTable tbHistorialIntervenciones;
    private javax.swing.JTable tbResumenIncidentes;
    private javax.swing.JTextField txtDiagnosticoEstudiante;
    private javax.swing.JTextField txtFuncionComportamiento;
    private javax.swing.JTextField txtIdEstudiante;
    private javax.swing.JTextField txtNombreEstudiante;
    private javax.swing.JTextArea txtObjetivoPlan;
    private javax.swing.JTextArea txtObsPlan;
    // End of variables declaration//GEN-END:variables
}
