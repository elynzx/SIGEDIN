/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.Docente;

import controller.EstudianteCtrl;
import controller.docente.PlanIndividualCtrl;
import dao.EstudianteImp;
import dao.funcionalidad.CatalogoImp;
import dao.funcionalidad.PlanIndividualImp;
import java.awt.Color;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.funcionalidad.catalogo.EstrategiaIntervencion;
import model.entidades.Estudiante;
import model.funcionalidad.PlanIntervencion;
import model.funcionalidad.ResumenIncidentes;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.FuncionComportamiento;
import model.funcionalidad.catalogo.TipoConducta;
import utilities.ExcelTemplate.ExcelResumenIncidentes;
import utilities.ExcelTemplate.ExcelHistorialIntervenciones;

public class VistaPlanIndividual extends javax.swing.JPanel {

    private final PlanIndividualCtrl planIndividualCtrl;
    private final EstudianteCtrl estudianteCtrl;
    private int idDocente;
    private int idEstudianteSeleccionado;

    private FuncionComportamiento funcionComportamiento;
    private TipoConducta conductaSeleccionada;
    private EstrategiaIntervencion estrategiaSeleccionada;
    private Estudiante estudianteSeleccionado;

    private List<ResumenIncidentes> listaResumenIncidentes = new ArrayList<>();
    private List<PlanIntervencion> listaPlanes = new ArrayList<>();

    public VistaPlanIndividual(int idDocente) {
        this.idDocente = idDocente;
        this.estudianteCtrl = new EstudianteCtrl(EstudianteImp.obtenerInstancia());
        this.planIndividualCtrl = new PlanIndividualCtrl(
                PlanIndividualImp.obtenerInstancia(),
                CatalogoImp.obtenerInstancia()
        );
        initComponents();
        cargarEstudiantes();
        cargarEstrategiasIntervencion();
    }

    private void cargarEstudiantes() {
        cbListaEstudiantes.removeAllItems();
        List<Estudiante> listaEstudiantes = estudianteCtrl.obtenerListaEstudiantes(idDocente);
        for (Estudiante estudiante : listaEstudiantes) {
            cbListaEstudiantes.addItem(estudiante);
        }
        if (!listaEstudiantes.isEmpty()) {
            cbListaEstudiantes.setSelectedIndex(0);
        }
    }

    private void mostrarDatosEstudiante(int idEstudiante) {
        try {
            estudianteSeleccionado = estudianteCtrl.obtenerEstudiantePorId(idEstudiante);
            txtIdEstudiante.setText(String.valueOf(estudianteSeleccionado.getIdEstudiante()));
            txtNombreEstudiante.setText(estudianteSeleccionado.getNombres() + " " + estudianteSeleccionado.getApellidos());
            txtDiagnosticoEstudiante.setText(
                    estudianteSeleccionado.getDiagnosticos()
                            .stream()
                            .map(Diagnostico::getNombre)
                            .collect(Collectors.joining(", ")
                            ));
        } catch (Exception e) {
            System.out.println("Error al cargar datos del estudiante: " + e);
        }
    }

    private void cargarResumenIncidentes(int idEstudiante) {
        listaResumenIncidentes = planIndividualCtrl.obtenerResumenIncidentes(idEstudiante);
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

    // Tabla historial Plan Intervencion
    private void cargarHistorialIntervenciones(int idEstudiante) {
        listaPlanes = planIndividualCtrl.obtenerHistorialIntervenciones(idEstudiante);
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

    private String calcularEstadoPlan(Date fechaInicio) {
        LocalDate inicio = fechaInicio.toLocalDate();
        LocalDate fechaEvaluacion = calcularFechaFinal(inicio, 14);
        if (fechaEvaluacion.isAfter(LocalDate.now())) {
            return "En evaluación hasta: " + fechaEvaluacion;
        } else {
            return "Finalizó el: " + fechaEvaluacion;
        }
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

    private void cargarConductasEstudiante(int idEstudiante) {
        cbTipoConducta.removeAllItems();
        List<TipoConducta> listaConductas = planIndividualCtrl.obtenerTipoConductasEstudiante(idEstudiante);
        if (listaConductas == null || listaConductas.isEmpty()) {
            cbTipoConducta.addItem(new TipoConducta(0, "Sin conductas problemáticas registradas"));
            btnGuardarPlan.setEnabled(false);
            btnGuardarPlan.setBackground(new Color(54, 54, 54));
            txtFuncionComportamiento.setText("N/A");
            return;
        }
        for (TipoConducta tipo : listaConductas) {
            cbTipoConducta.addItem(tipo);
        }
        btnGuardarPlan.setEnabled(true);
        btnGuardarPlan.setBackground(new Color(221, 168, 83));
    }

    private void cargarEstrategiasIntervencion() {
        cbEstrategiaIntervencion.removeAllItems();
        List<EstrategiaIntervencion> listaEstrategias = planIndividualCtrl.obtenerEstrategiasIntervencion();
        for (EstrategiaIntervencion estrategia : listaEstrategias) {
            cbEstrategiaIntervencion.addItem(estrategia);
        }
    }

    private void limpiarCampos() {
        cbEstrategiaIntervencion.setSelectedIndex(0);
        cbTipoConducta.setSelectedIndex(0);
        txtFuncionComportamiento.setText("");
        txtObjetivoPlan.setText("");
        txtObsPlan.setText("");
        chkImplementacion.setSelected(false);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpDashboardDocente = new javax.swing.JPanel();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHistorialIntervenciones = new javax.swing.JTable();
        descargaHistorialIntervenciones = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbResumenIncidentes = new javax.swing.JTable();
        descargaResumenIncidentes = new javax.swing.JLabel();
        lbNivel14 = new javax.swing.JLabel();
        lbNivel16 = new javax.swing.JLabel();
        cbListaEstudiantes = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        txtIdEstudiante = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        txtNombreEstudiante = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        txtDiagnosticoEstudiante = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        lbNivel12 = new javax.swing.JLabel();
        lbNivel17 = new javax.swing.JLabel();
        lbNivel18 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jpDashboardDocente.setBackground(new java.awt.Color(255, 255, 255));
        jpDashboardDocente.setMinimumSize(new java.awt.Dimension(1250, 734));
        jpDashboardDocente.setPreferredSize(new java.awt.Dimension(1250, 734));
        jpDashboardDocente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(16, 58, 108));

        lbNivel1.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        lbNivel1.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel1.setText("Objetivo del plan:");
        lbNivel1.setPreferredSize(new java.awt.Dimension(70, 25));

        lbNivel4.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
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

        lbNivel7.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        lbNivel7.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel7.setText("Función del comportamiento:");
        lbNivel7.setPreferredSize(new java.awt.Dimension(70, 25));

        txtFuncionComportamiento.setForeground(new java.awt.Color(51, 51, 51));
        txtFuncionComportamiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

        lbNivel8.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbNivel8.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Group_light.png"))); // NOI18N
        lbNivel8.setText("REGISTRO:");
        lbNivel8.setPreferredSize(new java.awt.Dimension(70, 25));

        lbNivel11.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        lbNivel11.setForeground(new java.awt.Color(221, 168, 83));
        lbNivel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNivel11.setText("*  Último registro");
        lbNivel11.setPreferredSize(new java.awt.Dimension(70, 25));

        txtObjetivoPlan.setColumns(20);
        txtObjetivoPlan.setForeground(new java.awt.Color(51, 51, 51));
        txtObjetivoPlan.setRows(5);
        txtObjetivoPlan.setBorder(null);
        jScrollPane2.setViewportView(txtObjetivoPlan);

        lbNivel5.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        lbNivel5.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel5.setText("Estrategia de Intervención");
        lbNivel5.setPreferredSize(new java.awt.Dimension(70, 25));

        chkImplementacion.setBackground(new java.awt.Color(16, 58, 108));
        chkImplementacion.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        chkImplementacion.setForeground(new java.awt.Color(255, 255, 255));
        chkImplementacion.setText("Sí");
        chkImplementacion.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Check_round_fill-2.png"))); // NOI18N
        chkImplementacion.setEnabled(false);
        chkImplementacion.setFocusable(false);
        chkImplementacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkImplementacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_ring_round-2.png"))); // NOI18N
        chkImplementacion.setIconTextGap(8);
        chkImplementacion.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Check_round_fill-2.png"))); // NOI18N

        lbNivel13.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lbNivel13.setForeground(new java.awt.Color(221, 168, 83));
        lbNivel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNivel13.setText("* ¿Ya se ha utilizado?");
        lbNivel13.setPreferredSize(new java.awt.Dimension(70, 25));

        lbNivel3.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
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
        btnGuardarPlan.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        btnGuardarPlan.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarPlan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Save_fill-2.png"))); // NOI18N
        btnGuardarPlan.setText("REGISTRAR");
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
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNivel7, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNivel8, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2)
                                .addComponent(txtFuncionComportamiento, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                                .addComponent(jScrollPane6)
                                .addComponent(cbTipoConducta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbNivel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnGuardarPlan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbNivel3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbNivel1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbNivel4, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbNivel5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbEstrategiaIntervencion, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(chkImplementacion, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbNivel13, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(35, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lbNivel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lbNivel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(cbTipoConducta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNivel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNivel11, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(txtFuncionComportamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNivel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNivel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNivel13, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chkImplementacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbEstrategiaIntervencion, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNivel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jpDashboardDocente.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, 430, 610));

        tbHistorialIntervenciones.setBackground(new java.awt.Color(255, 255, 255));
        tbHistorialIntervenciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tbHistorialIntervenciones.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tbHistorialIntervenciones.setForeground(new java.awt.Color(23, 64, 112));
        tbHistorialIntervenciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fecha Inicio", "Conducta Intervenida", "Estrategia aplicada", "Estado actual"
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
        tbHistorialIntervenciones.setGridColor(new java.awt.Color(204, 204, 204));
        tbHistorialIntervenciones.setRowHeight(25);
        tbHistorialIntervenciones.setSelectionBackground(new java.awt.Color(23, 64, 112));
        tbHistorialIntervenciones.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbHistorialIntervenciones.setShowGrid(false);
        jScrollPane4.setViewportView(tbHistorialIntervenciones);

        jpDashboardDocente.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 670, 150));

        descargaHistorialIntervenciones.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        descargaHistorialIntervenciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Import-2.png"))); // NOI18N
        descargaHistorialIntervenciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        descargaHistorialIntervenciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descargaHistorialIntervencionesMouseClicked(evt);
            }
        });
        jpDashboardDocente.add(descargaHistorialIntervenciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 470, 70, 30));

        tbResumenIncidentes.setBackground(new java.awt.Color(255, 255, 255));
        tbResumenIncidentes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tbResumenIncidentes.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tbResumenIncidentes.setForeground(new java.awt.Color(23, 64, 112));
        tbResumenIncidentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
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
        tbResumenIncidentes.setGridColor(new java.awt.Color(204, 204, 204));
        tbResumenIncidentes.setRowHeight(25);
        tbResumenIncidentes.setSelectionBackground(new java.awt.Color(23, 64, 112));
        tbResumenIncidentes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbResumenIncidentes.setShowGrid(false);
        jScrollPane3.setViewportView(tbResumenIncidentes);

        jpDashboardDocente.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 670, 140));

        descargaResumenIncidentes.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        descargaResumenIncidentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Import-2.png"))); // NOI18N
        descargaResumenIncidentes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        descargaResumenIncidentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descargaResumenIncidentesMouseClicked(evt);
            }
        });
        jpDashboardDocente.add(descargaResumenIncidentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 250, 80, 30));

        lbNivel14.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel14.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel14.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel14.setText("Resumen de Incidentes");
        lbNivel14.setToolTipText("");
        lbNivel14.setPreferredSize(new java.awt.Dimension(70, 25));
        jpDashboardDocente.add(lbNivel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 490, 30));

        lbNivel16.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel16.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbNivel16.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel16.setText("Selecciona un estudiante para realizar el registro:");
        lbNivel16.setToolTipText("");
        lbNivel16.setPreferredSize(new java.awt.Dimension(70, 25));
        jpDashboardDocente.add(lbNivel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 530, 20));

        cbListaEstudiantes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbListaEstudiantes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cbListaEstudiantes.setOpaque(true);
        cbListaEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbListaEstudiantesActionPerformed(evt);
            }
        });
        jpDashboardDocente.add(cbListaEstudiantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 230, 30));

        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("ID :");
        jLabel30.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel30.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jpDashboardDocente.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 40, 20));

        txtIdEstudiante.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtIdEstudiante.setForeground(new java.awt.Color(23, 64, 112));
        txtIdEstudiante.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtIdEstudiante.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jpDashboardDocente.add(txtIdEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 70, 18));

        jSeparator7.setForeground(new java.awt.Color(204, 204, 204));
        jpDashboardDocente.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 70, 10));

        jLabel34.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Nombres y Apellidos :");
        jLabel34.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel34.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jpDashboardDocente.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 110, 20));

        txtNombreEstudiante.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtNombreEstudiante.setForeground(new java.awt.Color(23, 64, 112));
        jpDashboardDocente.add(txtNombreEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 260, 18));

        jSeparator5.setForeground(new java.awt.Color(204, 204, 204));
        jpDashboardDocente.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 260, 10));

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Diagnósticos :");
        jLabel31.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel31.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jpDashboardDocente.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 80, 20));

        txtDiagnosticoEstudiante.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtDiagnosticoEstudiante.setForeground(new java.awt.Color(23, 64, 112));
        txtDiagnosticoEstudiante.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtDiagnosticoEstudiante.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jpDashboardDocente.add(txtDiagnosticoEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 230, 18));

        jSeparator6.setForeground(new java.awt.Color(204, 204, 204));
        jpDashboardDocente.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, 230, 10));

        lbNivel12.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel12.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel12.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel12.setText("Datos del estudiante");
        lbNivel12.setToolTipText("");
        lbNivel12.setPreferredSize(new java.awt.Dimension(70, 25));
        jpDashboardDocente.add(lbNivel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 250, 30));

        lbNivel17.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel17.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel17.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel17.setText("Historial de Intervenciones Aplicadas");
        lbNivel17.setToolTipText("");
        lbNivel17.setPreferredSize(new java.awt.Dimension(70, 25));
        jpDashboardDocente.add(lbNivel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 490, 30));

        lbNivel18.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel18.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbNivel18.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel18.setText("Selecciona un estudiante para realizar el registro:");
        lbNivel18.setToolTipText("");
        lbNivel18.setPreferredSize(new java.awt.Dimension(70, 25));
        jpDashboardDocente.add(lbNivel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 530, 20));

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        jpDashboardDocente.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 670, 20));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Selecciona un estudiante:");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jpDashboardDocente.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 150, 20));

        jLabel15.setBackground(new java.awt.Color(51, 51, 51));
        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(45, 94, 152));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Plan Individual Conductual");
        jpDashboardDocente.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 330, 30));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Info_fill-1_1.png"))); // NOI18N
        jLabel6.setText("Registra observaciones so frecuencia diaria.");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jpDashboardDocente.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 290, -1));

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
            .addComponent(jpDashboardDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 710, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbListaEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbListaEstudiantesActionPerformed
        Estudiante estudiante = (Estudiante) cbListaEstudiantes.getSelectedItem();
        if (estudiante != null) {
            idEstudianteSeleccionado = estudiante.getIdEstudiante();
            mostrarDatosEstudiante(idEstudianteSeleccionado);
            cargarHistorialIntervenciones(idEstudianteSeleccionado);
            cargarResumenIncidentes(idEstudianteSeleccionado);
            cargarConductasEstudiante(idEstudianteSeleccionado);
        }
    }//GEN-LAST:event_cbListaEstudiantesActionPerformed

    private void cbTipoConductaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoConductaActionPerformed
        conductaSeleccionada = (TipoConducta) cbTipoConducta.getSelectedItem();
        if (conductaSeleccionada == null || conductaSeleccionada.getId() <= 0) {
            funcionComportamiento = null;
            txtFuncionComportamiento.setText("N/A");
            return;
        }
        funcionComportamiento = planIndividualCtrl.obtenerUltimaFuncion(
                idEstudianteSeleccionado,
                conductaSeleccionada.getId());

        txtFuncionComportamiento.setText(
                funcionComportamiento != null ? funcionComportamiento.getNombre() : "Funcion de comportamiento no disponible");
    }//GEN-LAST:event_cbTipoConductaActionPerformed

    private void cbEstrategiaIntervencionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstrategiaIntervencionActionPerformed

        estrategiaSeleccionada = (EstrategiaIntervencion) cbEstrategiaIntervencion.getSelectedItem();

        if (conductaSeleccionada != null && estrategiaSeleccionada != null
                && conductaSeleccionada.getId() > 0 && estrategiaSeleccionada.getId() > 0) {

            boolean planExistente = planIndividualCtrl.verificarPlanExistente(
                    idEstudianteSeleccionado,
                    conductaSeleccionada.getId(),
                    estrategiaSeleccionada.getId());
            chkImplementacion.setSelected(planExistente);
        }
    }//GEN-LAST:event_cbEstrategiaIntervencionActionPerformed

    private void btnGuardarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPlanActionPerformed
        if (conductaSeleccionada == null || conductaSeleccionada.getId() == 0) {
            JOptionPane.showMessageDialog(this, "No se puede registrar el plan. No hay conducta seleccionada.");
            return;
        }
        if (funcionComportamiento == null) {
            JOptionPane.showMessageDialog(this, "No hay función de comportamiento disponible.");
            return;
        }
        estrategiaSeleccionada = (EstrategiaIntervencion) cbEstrategiaIntervencion.getSelectedItem();
        String objetivo = txtObjetivoPlan.getText().trim();
        String observaciones = txtObsPlan.getText().trim();
        boolean implementado = chkImplementacion.isSelected();
        String mensaje = planIndividualCtrl.registrarPlanIntervencion(
                idEstudianteSeleccionado,
                conductaSeleccionada.getId(),
                funcionComportamiento.getId(),
                objetivo,
                estrategiaSeleccionada.getId(),
                implementado,
                observaciones
        );
        JOptionPane.showMessageDialog(this, mensaje);
        if (mensaje.contains("guardado correctamente")) {
            limpiarCampos();
            cargarHistorialIntervenciones(idEstudianteSeleccionado);
        }
    }//GEN-LAST:event_btnGuardarPlanActionPerformed

    private void descargaResumenIncidentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descargaResumenIncidentesMouseClicked
        if (estudianteSeleccionado == null || listaResumenIncidentes.isEmpty()) {
            JOptionPane.showMessageDialog(VistaPlanIndividual.this, "No hay datos para exportar.");
            return;
        }
        ExcelResumenIncidentes.exportarResumenIncidentes(listaResumenIncidentes, estudianteSeleccionado, this);
    }//GEN-LAST:event_descargaResumenIncidentesMouseClicked

    private void descargaHistorialIntervencionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descargaHistorialIntervencionesMouseClicked
        if (estudianteSeleccionado == null || listaPlanes.isEmpty()) {
            JOptionPane.showMessageDialog(VistaPlanIndividual.this, "No hay datos para exportar.");
            return;
        }
        ExcelHistorialIntervenciones.exportarHistorial(listaPlanes, estudianteSeleccionado, this);
    }//GEN-LAST:event_descargaHistorialIntervencionesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarPlan;
    private javax.swing.JComboBox<EstrategiaIntervencion> cbEstrategiaIntervencion;
    private javax.swing.JComboBox<Estudiante> cbListaEstudiantes;
    private javax.swing.JComboBox<TipoConducta> cbTipoConducta;
    private javax.swing.JCheckBox chkImplementacion;
    private javax.swing.JLabel descargaHistorialIntervenciones;
    private javax.swing.JLabel descargaResumenIncidentes;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel jpDashboardDocente;
    private javax.swing.JLabel lbNivel1;
    private javax.swing.JLabel lbNivel11;
    private javax.swing.JLabel lbNivel12;
    private javax.swing.JLabel lbNivel13;
    private javax.swing.JLabel lbNivel14;
    private javax.swing.JLabel lbNivel16;
    private javax.swing.JLabel lbNivel17;
    private javax.swing.JLabel lbNivel18;
    private javax.swing.JLabel lbNivel3;
    private javax.swing.JLabel lbNivel4;
    private javax.swing.JLabel lbNivel5;
    private javax.swing.JLabel lbNivel7;
    private javax.swing.JLabel lbNivel8;
    private javax.swing.JTable tbHistorialIntervenciones;
    private javax.swing.JTable tbResumenIncidentes;
    private javax.swing.JLabel txtDiagnosticoEstudiante;
    private javax.swing.JTextField txtFuncionComportamiento;
    private javax.swing.JLabel txtIdEstudiante;
    private javax.swing.JLabel txtNombreEstudiante;
    private javax.swing.JTextArea txtObjetivoPlan;
    private javax.swing.JTextArea txtObsPlan;
    // End of variables declaration//GEN-END:variables
}
