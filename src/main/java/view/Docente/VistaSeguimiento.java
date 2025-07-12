/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.Docente;

import controller.EstudianteCtrl;
import controller.docente.SeguimientoCtrl;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.funcionalidad.catalogo.CategoriaConducta;
import model.funcionalidad.catalogo.Diagnostico;
import dao.EstudianteImp;
import dao.funcionalidad.SeguimientoImp;
import model.entidades.Estudiante;
import dao.funcionalidad.CatalogoImp;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import utillities.ExcelTemplate.ExcelResumenSeguimiento;

public class VistaSeguimiento extends javax.swing.JPanel {

    private int idDocente;
    private int idEstudianteSeleccionado;
    private Estudiante estudianteSeleccionado;

    List<Map<String, Object>> listaPromedios = new ArrayList<>();
    private final EstudianteCtrl estudianteCtrl;
    private final SeguimientoCtrl seguimientoCtrl;

    public VistaSeguimiento(int idDocente) {
        this.idDocente = idDocente;
        this.estudianteCtrl = new EstudianteCtrl(EstudianteImp.obtenerInstancia());
        this.seguimientoCtrl = new SeguimientoCtrl(
                SeguimientoImp.obtenerInstancia(),
                CatalogoImp.obtenerInstancia()
        );

        initComponents();

        cargarEstudiantesTabla();
        seleccionTbAlumnos();
        cargarCategorias();
    }

    private void cargarEstudiantesTabla() {
        estudianteCtrl.cargarListaTablaEstudiantes(idDocente, tableListaEstudiantes);
    }

    private void mostrarDatosEstudiante(int idEstudiante) {

        try {
            estudianteSeleccionado = estudianteCtrl.obtenerEstudiantePorId(idEstudiante);
            txtIdEstudiante.setText(String.valueOf(estudianteSeleccionado.getIdEstudiante()));
            txtNombreEstudiante.setText(estudianteSeleccionado.getNombres());
            txtApellidoEstudiante.setText(estudianteSeleccionado.getApellidos());
            txtDiagnosticoEstudiante.setText(estudianteSeleccionado.getDiagnosticos().stream().map(Diagnostico::getNombre).collect(Collectors.joining(", ")));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }

    }

    private void cargarCategorias() {
        cbCategoriaSeguimiento.removeAllItems();

        try {
            List<CategoriaConducta> listaCategoria = seguimientoCtrl.obtenerCategorias();
            if (listaCategoria != null && !listaCategoria.isEmpty()) {
                for (CategoriaConducta categoria : listaCategoria) {
                    cbCategoriaSeguimiento.addItem(categoria);
                }
            } else {
                System.out.println("No hay categorías disponibles.");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de categorías: " + e.getMessage());
        }
    }

    private DefaultTableModel cargarPromedioSeguimiento(int idEstudiante) {

        listaPromedios = seguimientoCtrl.obtenerPromediosSeguimiento(idEstudiante);
        DefaultTableModel modelo = (DefaultTableModel) tbPromedioSeguimiento.getModel();
        modelo.setRowCount(0);
        for (Map<String, Object> promedio : listaPromedios) {
            modelo.addRow(new Object[]{promedio.get("categoria"), promedio.get("promedio")});
        }

        configurarColorPromedios();

        return modelo;
    }

    private void configurarColorPromedios() {
        tbPromedioSeguimiento.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable tabla, Object valor,
                    boolean seleccion, boolean resaltar,
                    int fila, int columna) {
                Component c = super.getTableCellRendererComponent(tabla, valor, seleccion, resaltar, fila, columna);

                setHorizontalAlignment(SwingConstants.CENTER);

                try {

                    if (columna == 1 && valor instanceof Number) {
                        double promedio = ((Number) valor).doubleValue();
                        if (promedio < 2) {
                            c.setBackground(new Color(148, 56, 70));
                            c.setForeground(Color.WHITE);
                        } else if (promedio >= 2 && promedio < 4) {
                            c.setBackground(new Color(255, 245, 217));
                            c.setForeground(new Color(23, 64, 112));
                        } else {
                            c.setBackground(new Color(26, 117, 105));
                            c.setForeground(Color.WHITE);
                        }
                    } else {
                        c.setBackground(Color.WHITE);
                        c.setForeground(new Color(23, 64, 112));
                    }

                } catch (Exception e) {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });
    }

    private void seleccionTbAlumnos() {
        tableListaEstudiantes.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = tableListaEstudiantes.getSelectedRow();
                if (selectedRow != -1) {
                    idEstudianteSeleccionado = (int) tableListaEstudiantes.getValueAt(selectedRow, 0);
                    estudianteSeleccionado = estudianteCtrl.obtenerEstudiantePorId(idEstudianteSeleccionado);
                    mostrarDatosEstudiante(idEstudianteSeleccionado);
                    cargarPromedioSeguimiento(idEstudianteSeleccionado);

                }
            }
        });
    }

    private int obtenerFrecuenciaSeleccionada() {
        for (Enumeration<AbstractButton> botones = btnGroupFrecuencia.getElements(); botones.hasMoreElements();) {
            AbstractButton boton = botones.nextElement();
            if (boton.isSelected()) {
                return Integer.parseInt(boton.getText());
            }
        }
        return -1;
    }

    private void limpiarCampos() {
        cbCategoriaSeguimiento.setSelectedIndex(0);
        txtDscConducta.setText("");
        btnGroupFrecuencia.clearSelection();
        txtObsConducta1.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupFrecuencia = new javax.swing.ButtonGroup();
        jpDashboardDocente = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbNivel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        rbF0 = new javax.swing.JRadioButton();
        rbF1 = new javax.swing.JRadioButton();
        rbF2 = new javax.swing.JRadioButton();
        rbF3 = new javax.swing.JRadioButton();
        rbF4 = new javax.swing.JRadioButton();
        rbF5 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDscConducta = new javax.swing.JTextArea();
        lbNivel2 = new javax.swing.JLabel();
        lbNivel4 = new javax.swing.JLabel();
        cbCategoriaSeguimiento = new javax.swing.JComboBox<>();
        lbNivel7 = new javax.swing.JLabel();
        btnGuardarSeguimiento = new javax.swing.JButton();
        lbNivel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtObsConducta1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtDiagnosticoEstudiante = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        txtApellidoEstudiante = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        txtNombreEstudiante = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        txtIdEstudiante = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableListaEstudiantes = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPromedioSeguimiento = new javax.swing.JTable();
        btnDescargarResumenSeg = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbNivel15 = new javax.swing.JLabel();
        lbNivel11 = new javax.swing.JLabel();
        lbNivel12 = new javax.swing.JLabel();
        lbNivel16 = new javax.swing.JLabel();

        jpDashboardDocente.setBackground(new java.awt.Color(255, 255, 255));
        jpDashboardDocente.setMinimumSize(new java.awt.Dimension(1250, 710));
        jpDashboardDocente.setPreferredSize(new java.awt.Dimension(1250, 710));
        jpDashboardDocente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(45, 94, 152));

        lbNivel1.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        lbNivel1.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel1.setText("Frencuencia de la conducta:");
        lbNivel1.setPreferredSize(new java.awt.Dimension(70, 25));

        jPanel5.setBackground(new java.awt.Color(45, 94, 152));

        rbF0.setBackground(new java.awt.Color(45, 94, 152));
        btnGroupFrecuencia.add(rbF0);
        rbF0.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        rbF0.setForeground(new java.awt.Color(255, 255, 255));
        rbF0.setText("0");
        rbF0.setToolTipText("No se observó la conducta en ninguna ocasión.");
        rbF0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        rbF0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbF0.setFocusPainted(false);
        rbF0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rbF0.setIconTextGap(10);
        rbF0.setOpaque(true);

        rbF1.setBackground(new java.awt.Color(45, 94, 152));
        btnGroupFrecuencia.add(rbF1);
        rbF1.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        rbF1.setForeground(new java.awt.Color(255, 255, 255));
        rbF1.setText("1");
        rbF1.setToolTipText("Se observó una sola vez de forma muy leve.");
        rbF1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        rbF1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbF1.setFocusPainted(false);
        rbF1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rbF1.setIconTextGap(10);
        rbF1.setOpaque(true);

        rbF2.setBackground(new java.awt.Color(45, 94, 152));
        btnGroupFrecuencia.add(rbF2);
        rbF2.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        rbF2.setForeground(new java.awt.Color(255, 255, 255));
        rbF2.setText("2");
        rbF2.setToolTipText("Se observó de forma ocasional (2 -3 veces)");
        rbF2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        rbF2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbF2.setFocusPainted(false);
        rbF2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rbF2.setIconTextGap(10);
        rbF2.setOpaque(true);

        rbF3.setBackground(new java.awt.Color(45, 94, 152));
        btnGroupFrecuencia.add(rbF3);
        rbF3.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        rbF3.setForeground(new java.awt.Color(255, 255, 255));
        rbF3.setText("3");
        rbF3.setToolTipText("Se observó con frecuencia moderada (4-5 veces)");
        rbF3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        rbF3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbF3.setFocusPainted(false);
        rbF3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rbF3.setIconTextGap(10);
        rbF3.setOpaque(true);

        rbF4.setBackground(new java.awt.Color(45, 94, 152));
        btnGroupFrecuencia.add(rbF4);
        rbF4.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        rbF4.setForeground(new java.awt.Color(255, 255, 255));
        rbF4.setText("4");
        rbF4.setToolTipText("Se observó con frecuencia alta (más de 5 veces)");
        rbF4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        rbF4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbF4.setFocusPainted(false);
        rbF4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rbF4.setIconTextGap(10);
        rbF4.setOpaque(true);

        rbF5.setBackground(new java.awt.Color(45, 94, 152));
        btnGroupFrecuencia.add(rbF5);
        rbF5.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        rbF5.setForeground(new java.awt.Color(255, 255, 255));
        rbF5.setText("5");
        rbF5.setToolTipText("Se observó de forma constante y positiva.");
        rbF5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        rbF5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbF5.setFocusPainted(false);
        rbF5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rbF5.setIconTextGap(10);
        rbF5.setOpaque(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbF0)
                .addGap(25, 25, 25)
                .addComponent(rbF1)
                .addGap(25, 25, 25)
                .addComponent(rbF2)
                .addGap(25, 25, 25)
                .addComponent(rbF3)
                .addGap(25, 25, 25)
                .addComponent(rbF4)
                .addGap(25, 25, 25)
                .addComponent(rbF5)
                .addGap(10, 10, 10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbF0)
                    .addComponent(rbF1)
                    .addComponent(rbF2)
                    .addComponent(rbF3)
                    .addComponent(rbF4)
                    .addComponent(rbF5)))
        );

        txtDscConducta.setColumns(20);
        txtDscConducta.setForeground(new java.awt.Color(51, 51, 51));
        txtDscConducta.setRows(5);
        txtDscConducta.setBorder(null);
        jScrollPane1.setViewportView(txtDscConducta);

        lbNivel2.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        lbNivel2.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel2.setText("Observaciones:");
        lbNivel2.setPreferredSize(new java.awt.Dimension(70, 25));

        lbNivel4.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        lbNivel4.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel4.setText("Selecciona una categoría a evaluar:");
        lbNivel4.setPreferredSize(new java.awt.Dimension(70, 25));

        cbCategoriaSeguimiento.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        cbCategoriaSeguimiento.setForeground(new java.awt.Color(51, 51, 51));
        cbCategoriaSeguimiento.setBorder(null);

        lbNivel7.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        lbNivel7.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel7.setText("Descripción de la conducta:");
        lbNivel7.setPreferredSize(new java.awt.Dimension(70, 25));

        btnGuardarSeguimiento.setBackground(new java.awt.Color(221, 168, 83));
        btnGuardarSeguimiento.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        btnGuardarSeguimiento.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarSeguimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Save_fill-2.png"))); // NOI18N
        btnGuardarSeguimiento.setText("REGISTRAR");
        btnGuardarSeguimiento.setBorder(null);
        btnGuardarSeguimiento.setBorderPainted(false);
        btnGuardarSeguimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarSeguimiento.setFocusPainted(false);
        btnGuardarSeguimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarSeguimientoActionPerformed(evt);
            }
        });

        lbNivel8.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbNivel8.setForeground(new java.awt.Color(255, 255, 255));
        lbNivel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_ring_round-2.png"))); // NOI18N
        lbNivel8.setText("REGISTRO:");
        lbNivel8.setPreferredSize(new java.awt.Dimension(70, 25));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Sad_alt-2_1.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/happy-5.png"))); // NOI18N

        txtObsConducta1.setColumns(20);
        txtObsConducta1.setForeground(new java.awt.Color(51, 51, 51));
        txtObsConducta1.setRows(5);
        txtObsConducta1.setBorder(null);
        jScrollPane4.setViewportView(txtObsConducta1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGuardarSeguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane4)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 360, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(cbCategoriaSeguimiento, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbNivel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNivel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNivel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNivel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNivel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lbNivel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lbNivel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCategoriaSeguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbNivel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addComponent(lbNivel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbNivel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnGuardarSeguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        jpDashboardDocente.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, 430, 610));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpDashboardDocente.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("ID :");
        jLabel30.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel30.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 80, 20));

        jLabel34.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Nombres :");
        jLabel34.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel34.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 20));

        jLabel33.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 102));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Apellidos :");
        jLabel33.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel33.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 80, 20));

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Diagnósticos :");
        jLabel31.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel31.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 80, 20));

        txtDiagnosticoEstudiante.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtDiagnosticoEstudiante.setForeground(new java.awt.Color(23, 64, 112));
        txtDiagnosticoEstudiante.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtDiagnosticoEstudiante.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel3.add(txtDiagnosticoEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 230, 18));

        jSeparator6.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 230, 10));

        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 230, 10));

        txtApellidoEstudiante.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtApellidoEstudiante.setForeground(new java.awt.Color(23, 64, 112));
        txtApellidoEstudiante.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtApellidoEstudiante.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel3.add(txtApellidoEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 230, 18));

        jSeparator5.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 230, 10));

        txtNombreEstudiante.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtNombreEstudiante.setForeground(new java.awt.Color(23, 64, 112));
        jPanel3.add(txtNombreEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 230, 18));

        jSeparator7.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 230, 10));

        txtIdEstudiante.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtIdEstudiante.setForeground(new java.awt.Color(23, 64, 112));
        txtIdEstudiante.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtIdEstudiante.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel3.add(txtIdEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 230, 18));

        jpDashboardDocente.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 350, 150));

        tableListaEstudiantes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tableListaEstudiantes.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tableListaEstudiantes.setForeground(new java.awt.Color(23, 64, 112));
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
        tableListaEstudiantes.setGridColor(new java.awt.Color(204, 204, 204));
        tableListaEstudiantes.setRowHeight(25);
        tableListaEstudiantes.setSelectionBackground(new java.awt.Color(45, 94, 152));
        tableListaEstudiantes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tableListaEstudiantes.setShowGrid(false);
        jScrollPane3.setViewportView(tableListaEstudiantes);

        jpDashboardDocente.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 290, 450));

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        jpDashboardDocente.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 670, 20));

        tbPromedioSeguimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tbPromedioSeguimiento.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tbPromedioSeguimiento.setForeground(new java.awt.Color(23, 64, 112));
        tbPromedioSeguimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Transición", null},
                {"Juego", null},
                {"Alimentación", null},
                {"Seguridad", null},
                {"Socialización", null},
                {"Trabajo independiente", null}
            },
            new String [] {
                "Categoria", "Frecuencia Promedio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPromedioSeguimiento.setGridColor(new java.awt.Color(204, 204, 204));
        tbPromedioSeguimiento.setRowHeight(27);
        tbPromedioSeguimiento.setSelectionBackground(new java.awt.Color(221, 230, 242));
        tbPromedioSeguimiento.setShowGrid(false);
        jScrollPane2.setViewportView(tbPromedioSeguimiento);

        jpDashboardDocente.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 340, 200));

        btnDescargarResumenSeg.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnDescargarResumenSeg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Import-2.png"))); // NOI18N
        btnDescargarResumenSeg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDescargarResumenSeg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDescargarResumenSegMouseClicked(evt);
            }
        });
        jpDashboardDocente.add(btnDescargarResumenSeg, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 420, 30, 30));

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(45, 94, 152));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Waterfall-1.png"))); // NOI18N
        jLabel14.setText("SEGUIMIENTO CONDUCTUAL DIARIO");
        jpDashboardDocente.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 330, 30));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Info_fill-1_1.png"))); // NOI18N
        jLabel6.setText("Registra observaciones sobre la conducta del estudiante y su frecuencia diaria.");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jpDashboardDocente.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 490, -1));

        lbNivel15.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel15.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbNivel15.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel15.setText("Selecciona un estudiante para realizar el registro:");
        lbNivel15.setToolTipText("");
        lbNivel15.setPreferredSize(new java.awt.Dimension(70, 25));
        jpDashboardDocente.add(lbNivel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 330, 20));

        lbNivel11.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel11.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel11.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel11.setText("Datos del estudiante");
        lbNivel11.setToolTipText("");
        lbNivel11.setPreferredSize(new java.awt.Dimension(70, 25));
        jpDashboardDocente.add(lbNivel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 250, 30));

        lbNivel12.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel12.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel12.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel12.setText("Resumen de seguimiento");
        lbNivel12.setToolTipText("");
        lbNivel12.setPreferredSize(new java.awt.Dimension(70, 25));
        jpDashboardDocente.add(lbNivel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, 240, 30));

        lbNivel16.setBackground(new java.awt.Color(255, 255, 255));
        lbNivel16.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        lbNivel16.setForeground(new java.awt.Color(102, 102, 102));
        lbNivel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel16.setText("Tabla con el promedio total por categoria");
        lbNivel16.setToolTipText("");
        lbNivel16.setPreferredSize(new java.awt.Dimension(70, 25));
        jpDashboardDocente.add(lbNivel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, 280, 25));

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

    private void btnGuardarSeguimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarSeguimientoActionPerformed
        if (idEstudianteSeleccionado <= 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un estudiante");
            return;
        }

        CategoriaConducta categoria = (CategoriaConducta) cbCategoriaSeguimiento.getSelectedItem();
        String descripcion = txtDscConducta.getText();
        int frecuencia = obtenerFrecuenciaSeleccionada();
        String observaciones = txtObsConducta1.getText();

        String mensaje = seguimientoCtrl.registrarSeguimiento(
                idEstudianteSeleccionado,
                categoria.getId(),
                descripcion,
                frecuencia,
                observaciones
        );
        JOptionPane.showMessageDialog(this, mensaje);
        if (mensaje.contains("guardado correctamente")) {
            limpiarCampos();
            cargarPromedioSeguimiento(idEstudianteSeleccionado);
        }
    }//GEN-LAST:event_btnGuardarSeguimientoActionPerformed

    private void btnDescargarResumenSegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDescargarResumenSegMouseClicked

        if (listaPromedios == null || listaPromedios.isEmpty()) {
            JOptionPane.showMessageDialog(VistaSeguimiento.this, "No hay datos para exportar.");
            return;
        }
        ExcelResumenSeguimiento.exportarResumenSeguimiento(listaPromedios, estudianteSeleccionado, VistaSeguimiento.this);
    }//GEN-LAST:event_btnDescargarResumenSegMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnDescargarResumenSeg;
    private javax.swing.ButtonGroup btnGroupFrecuencia;
    private javax.swing.JButton btnGuardarSeguimiento;
    private javax.swing.JComboBox<CategoriaConducta> cbCategoriaSeguimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel jpDashboardDocente;
    private javax.swing.JLabel lbNivel1;
    private javax.swing.JLabel lbNivel11;
    private javax.swing.JLabel lbNivel12;
    private javax.swing.JLabel lbNivel15;
    private javax.swing.JLabel lbNivel16;
    private javax.swing.JLabel lbNivel2;
    private javax.swing.JLabel lbNivel4;
    private javax.swing.JLabel lbNivel7;
    private javax.swing.JLabel lbNivel8;
    private javax.swing.JRadioButton rbF0;
    private javax.swing.JRadioButton rbF1;
    private javax.swing.JRadioButton rbF2;
    private javax.swing.JRadioButton rbF3;
    private javax.swing.JRadioButton rbF4;
    private javax.swing.JRadioButton rbF5;
    private javax.swing.JTable tableListaEstudiantes;
    private javax.swing.JTable tbPromedioSeguimiento;
    private javax.swing.JLabel txtApellidoEstudiante;
    private javax.swing.JLabel txtDiagnosticoEstudiante;
    private javax.swing.JTextArea txtDscConducta;
    private javax.swing.JLabel txtIdEstudiante;
    private javax.swing.JLabel txtNombreEstudiante;
    private javax.swing.JTextArea txtObsConducta1;
    // End of variables declaration//GEN-END:variables
}
