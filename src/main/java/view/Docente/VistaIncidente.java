/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.Docente;

import controller.EstudianteCtrl;
import controller.docente.IncidenteCtrl;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.funcionalidad.catalogo.Diagnostico;
import dao.EstudianteImp;
import dao.funcionalidad.CatalogoImp;
import model.entidades.Estudiante;
import model.funcionalidad.ConductaProblematica;
import model.funcionalidad.FichaAbc;
import dao.funcionalidad.ConductaProblematicaImp;
import dao.funcionalidad.FichaAbcImp;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import utillities.ExcelTemplate.ExcelHistorialConductas;
import utillities.ExcelTemplate.ExcelHistorialFichas;

public class VistaIncidente extends javax.swing.JPanel {

    private int idDocente;
    private int idEstudianteSeleccionado;
    private Estudiante estudianteSeleccionado;
    private final EstudianteCtrl estudianteCtrl;
    private final IncidenteCtrl incidenteCtrl;
    private List<FichaAbc> listaFichasAbc = new ArrayList<>();
    private List<ConductaProblematica> listaConductas = new ArrayList<>();

    public VistaIncidente(int idDocente) {
        this.idDocente = idDocente;
        this.estudianteCtrl = new EstudianteCtrl(EstudianteImp.obtenerInstancia());
        this.incidenteCtrl = new IncidenteCtrl(
                ConductaProblematicaImp.obtenerInstancia(),
                FichaAbcImp.obtenerInstancia(),
                CatalogoImp.obtenerInstancia()
        );
        initComponents();
        cargarEstudiantes(idDocente);
        setConductaPanel();
    }

    private void cargarEstudiantes(int idDocente) {
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
            txtDiagnosticoEstudiante.setText(estudianteSeleccionado.getDiagnosticos().stream()
                    .map(Diagnostico::getNombre)
                    .collect(Collectors.joining(", ")));
        } catch (Exception e) {
            System.out.println("Error al cargar datos del estudiante: " + e);
        }
    }

    private void cargarConductasEstudiante(int idEstudiante) {
        listaConductas = incidenteCtrl.obtenerHistorialConductas(idEstudiante);
        DefaultTableModel modelo = (DefaultTableModel) tbHistorialConductasProbEstudiante.getModel();
        modelo.setRowCount(0);
        for (ConductaProblematica conducta : listaConductas) {
            modelo.addRow(new Object[]{
                conducta.getFecha(),
                conducta.getTipo().getNombre(),
                conducta.getDescripcion(),
                conducta.getGravedad()
            });
        }
    }

    private void cargarFichasEstudiante(int idEstudiante) {
        listaFichasAbc = incidenteCtrl.obtenerHistorialFichas(idEstudiante);
        DefaultTableModel modelo = (DefaultTableModel) tbHistorialFichasAbc.getModel();
        modelo.setRowCount(0);
        for (FichaAbc fichaAbc : listaFichasAbc) {
            modelo.addRow(new Object[]{
                fichaAbc.getFecha(),
                fichaAbc.getAntecedente().getNombre(),
                fichaAbc.getComportamiento(),
                fichaAbc.getGravedad()
            });
        }
    }

    public void setFichaPanel() {
        VistaFichaAbc vFichaAbc = new VistaFichaAbc(idEstudianteSeleccionado, incidenteCtrl);
        jpIncidente.removeAll();
        jpIncidente.add(vFichaAbc);
        SwingUtilities.updateComponentTreeUI(jpIncidente);
    }

    public void setConductaPanel() {
        VistaConductaProblematica vConducta = new VistaConductaProblematica(idEstudianteSeleccionado, incidenteCtrl);
        jpIncidente.removeAll();
        jpIncidente.add(vConducta);
        SwingUtilities.updateComponentTreeUI(jpIncidente);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupConducta = new javax.swing.ButtonGroup();
        btnGroupFicha = new javax.swing.ButtonGroup();
        jpDashboardDocente = new javax.swing.JPanel();
        jpIncidente = new javax.swing.JPanel();
        btnConductaProb = new javax.swing.JButton();
        btnFichaAbc = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbListaEstudiantes = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        lbNivel12 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtIdEstudiante = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        txtNombreEstudiante = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        txtDiagnosticoEstudiante = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        lbNivel14 = new javax.swing.JLabel();
        lbNivel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHistorialConductasProbEstudiante = new javax.swing.JTable();
        btnDescHistorialConductas = new javax.swing.JLabel();
        lbNivel17 = new javax.swing.JLabel();
        lbNivel18 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHistorialFichasAbc = new javax.swing.JTable();
        btnDescHistorialFichas = new javax.swing.JLabel();

        jpDashboardDocente.setBackground(new java.awt.Color(255, 255, 255));
        jpDashboardDocente.setMinimumSize(new java.awt.Dimension(1250, 734));
        jpDashboardDocente.setPreferredSize(new java.awt.Dimension(1250, 734));
        jpDashboardDocente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpIncidente.setBackground(new java.awt.Color(110, 146, 203));
        jpIncidente.setMinimumSize(new java.awt.Dimension(450, 560));
        jpIncidente.setPreferredSize(new java.awt.Dimension(450, 560));
        jpIncidente.setLayout(new java.awt.BorderLayout());
        jpDashboardDocente.add(jpIncidente, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 90, 430, 560));

        btnConductaProb.setBackground(new java.awt.Color(110, 146, 203));
        btnConductaProb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConductaProb.setForeground(new java.awt.Color(255, 255, 255));
        btnConductaProb.setText("1. Conducta problemática");
        btnConductaProb.setBorder(null);
        btnConductaProb.setBorderPainted(false);
        btnConductaProb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConductaProb.setFocusPainted(false);
        btnConductaProb.setMinimumSize(new java.awt.Dimension(120, 30));
        btnConductaProb.setPreferredSize(new java.awt.Dimension(120, 30));
        btnConductaProb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConductaProbActionPerformed(evt);
            }
        });
        jpDashboardDocente.add(btnConductaProb, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, 230, 50));

        btnFichaAbc.setBackground(new java.awt.Color(75, 75, 75));
        btnFichaAbc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnFichaAbc.setForeground(new java.awt.Color(255, 255, 255));
        btnFichaAbc.setText("2. Ficha ABC");
        btnFichaAbc.setBorder(null);
        btnFichaAbc.setBorderPainted(false);
        btnFichaAbc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFichaAbc.setFocusPainted(false);
        btnFichaAbc.setPreferredSize(new java.awt.Dimension(120, 30));
        btnFichaAbc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFichaAbcActionPerformed(evt);
            }
        });
        jpDashboardDocente.add(btnFichaAbc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 40, 200, 50));

        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));
        jpDashboardDocente.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 670, 20));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Info_fill-1_1.png"))); // NOI18N
        jLabel6.setText("Registra observaciones so frecuencia diaria.");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jpDashboardDocente.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 290, -1));

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(45, 94, 152));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Waterfall-1.png"))); // NOI18N
        jLabel14.setText("REGISTRO DE INCIDENTES");
        jpDashboardDocente.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 330, 30));

        cbListaEstudiantes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbListaEstudiantes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cbListaEstudiantes.setOpaque(true);
        cbListaEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbListaEstudiantesActionPerformed(evt);
            }
        });
        jpDashboardDocente.add(cbListaEstudiantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 270, 30));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Selecciona un estudiante:");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jpDashboardDocente.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 130, 20));

        lbNivel12.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel12.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel12.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel12.setText("Datos del estudiante");
        lbNivel12.setToolTipText("");
        lbNivel12.setPreferredSize(new java.awt.Dimension(70, 25));
        jpDashboardDocente.add(lbNivel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 250, 30));

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

        lbNivel14.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel14.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel14.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel14.setText("Historial de Conductas Problemáticas");
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
        jpDashboardDocente.add(lbNivel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 530, 25));

        tbHistorialConductasProbEstudiante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tbHistorialConductasProbEstudiante.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tbHistorialConductasProbEstudiante.setForeground(new java.awt.Color(23, 64, 112));
        tbHistorialConductasProbEstudiante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fecha", "Conducta problemática", "Descripción", "Gravedad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHistorialConductasProbEstudiante.setGridColor(new java.awt.Color(204, 204, 204));
        tbHistorialConductasProbEstudiante.setMinimumSize(new java.awt.Dimension(60, 100));
        tbHistorialConductasProbEstudiante.setPreferredSize(new java.awt.Dimension(300, 100));
        tbHistorialConductasProbEstudiante.setRowHeight(25);
        tbHistorialConductasProbEstudiante.setSelectionBackground(new java.awt.Color(23, 64, 112));
        tbHistorialConductasProbEstudiante.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbHistorialConductasProbEstudiante.setShowGrid(false);
        jScrollPane2.setViewportView(tbHistorialConductasProbEstudiante);

        jpDashboardDocente.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 670, 140));

        btnDescHistorialConductas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnDescHistorialConductas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Import-2.png"))); // NOI18N
        btnDescHistorialConductas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDescHistorialConductas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDescHistorialConductasMouseClicked(evt);
            }
        });
        jpDashboardDocente.add(btnDescHistorialConductas, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 250, 40, 30));

        lbNivel17.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel17.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel17.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel17.setText("Historial de Fichas ABC");
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
        jpDashboardDocente.add(lbNivel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 530, 25));

        tbHistorialFichasAbc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tbHistorialFichasAbc.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tbHistorialFichasAbc.setForeground(new java.awt.Color(23, 64, 112));
        tbHistorialFichasAbc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fecha", "Antecedente", "Comportamiendo", "Gravedad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHistorialFichasAbc.setGridColor(new java.awt.Color(204, 204, 204));
        tbHistorialFichasAbc.setRowHeight(25);
        tbHistorialFichasAbc.setSelectionBackground(new java.awt.Color(23, 64, 112));
        tbHistorialFichasAbc.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbHistorialFichasAbc.setShowGrid(false);
        jScrollPane3.setViewportView(tbHistorialFichasAbc);

        jpDashboardDocente.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 670, 150));

        btnDescHistorialFichas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnDescHistorialFichas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Import-2.png"))); // NOI18N
        btnDescHistorialFichas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDescHistorialFichas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDescHistorialFichasMouseClicked(evt);
            }
        });
        jpDashboardDocente.add(btnDescHistorialFichas, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 470, 40, 30));

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

    private void btnConductaProbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConductaProbActionPerformed

        setConductaPanel();

    }//GEN-LAST:event_btnConductaProbActionPerformed

    private void btnFichaAbcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFichaAbcActionPerformed

        setFichaPanel();


    }//GEN-LAST:event_btnFichaAbcActionPerformed

    private void btnDescHistorialConductasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDescHistorialConductasMouseClicked

        if (estudianteSeleccionado == null || listaFichasAbc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay datos para exportar.");
            return;
        }
        ExcelHistorialFichas.exportarHistorialFichas(listaFichasAbc, estudianteSeleccionado, this);

    }//GEN-LAST:event_btnDescHistorialConductasMouseClicked

    private void btnDescHistorialFichasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDescHistorialFichasMouseClicked

        if (estudianteSeleccionado == null || listaConductas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay datos para exportar.");
            return;
        }
        ExcelHistorialConductas.exportarHistorialConductas(listaConductas, estudianteSeleccionado, this);
    }//GEN-LAST:event_btnDescHistorialFichasMouseClicked

    private void cbListaEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbListaEstudiantesActionPerformed

        Estudiante estudiante = (Estudiante) cbListaEstudiantes.getSelectedItem();

        if (estudiante != null) {
            idEstudianteSeleccionado = estudiante.getIdEstudiante();
            estudianteSeleccionado = estudianteCtrl.obtenerEstudiantePorId(idEstudianteSeleccionado);
            System.out.println("ID actualizado en selección de estudiante: " + idEstudianteSeleccionado);

            mostrarDatosEstudiante(idEstudianteSeleccionado);
            cargarFichasEstudiante(idEstudianteSeleccionado);
            cargarConductasEstudiante(idEstudianteSeleccionado);
            setConductaPanel();
        }


    }//GEN-LAST:event_cbListaEstudiantesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConductaProb;
    private javax.swing.JLabel btnDescHistorialConductas;
    private javax.swing.JLabel btnDescHistorialFichas;
    private javax.swing.JButton btnFichaAbc;
    private javax.swing.ButtonGroup btnGroupConducta;
    private javax.swing.ButtonGroup btnGroupFicha;
    private javax.swing.JComboBox<Estudiante> cbListaEstudiantes;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel jpDashboardDocente;
    private javax.swing.JPanel jpIncidente;
    private javax.swing.JLabel lbNivel12;
    private javax.swing.JLabel lbNivel14;
    private javax.swing.JLabel lbNivel16;
    private javax.swing.JLabel lbNivel17;
    private javax.swing.JLabel lbNivel18;
    private javax.swing.JTable tbHistorialConductasProbEstudiante;
    private javax.swing.JTable tbHistorialFichasAbc;
    private javax.swing.JLabel txtDiagnosticoEstudiante;
    private javax.swing.JLabel txtIdEstudiante;
    private javax.swing.JLabel txtNombreEstudiante;
    // End of variables declaration//GEN-END:variables
}
