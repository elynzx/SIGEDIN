/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Docente;

import controller.EstudianteCtrl;
import controller.docente.AsistenciaCtrl;
import dao.EstudianteImp;
import dao.funcionalidad.AsistenciaImp;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.entidades.Estudiante;
import model.funcionalidad.Asistencia;
import utillities.FechaUtil;

public class VistaAsistencia extends javax.swing.JFrame {

    private final AsistenciaCtrl asistenciaCtrl;
    private final int idDocente;
    private final List<Asistencia> listaAsistencia = new ArrayList<>();

    public VistaAsistencia(int idDocente) {
        this.idDocente = idDocente;
        this.asistenciaCtrl = new AsistenciaCtrl(
                AsistenciaImp.obtenerInstancia());
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cargarAsistenciastabla();
        FechaUtil.mostrarDiaHora(lbFechaHora);
    }

    private void cargarAsistenciastabla() {
        List<Asistencia> asistenciasHoy = asistenciaCtrl.listarAsistenciasDelDia(idDocente);
        listaAsistencia.clear();

        if (!asistenciasHoy.isEmpty()) {
            listaAsistencia.addAll(asistenciasHoy);
        } else {
            listaAsistencia.addAll(asistenciaCtrl.listarEstudiantesSinAsistencia(idDocente));
        }
        cargarDatosEnTabla();
    }

    private void cargarDatosEnTabla() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 3 ? Boolean.class : String.class;
            }
        };

        model.setColumnIdentifiers(new String[]{"ID", "Apellidos", "Nombres", "Asistió"});

        for (Asistencia a : listaAsistencia) {
            Estudiante est = a.getEstudiante();
            model.addRow(new Object[]{
                est.getIdEstudiante(),
                est.getApellidos(),
                est.getNombres(),
                a.isAsiste()
            });
        }

        tbAsistencia.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAsistencia = new javax.swing.JTable();
        btnGuardarAsistencia = new javax.swing.JButton();
        lbFechaHora = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(480, 620));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(39, 84, 138));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("ASISTENCIA");

        jScrollPane1.setBorder(null);

        tbAsistencia.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tbAsistencia.setForeground(new java.awt.Color(39, 84, 138));
        tbAsistencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "Pascual Caycho", "Evelyn Roxana", null},
                {null, "Caceres", "Carlos", null},
                {null, "Pineda", "Paul", null},
                {null, "Montalvo", "Matias", null}
            },
            new String [] {
                "ID", "Apellidos", "Nombres", "Asistió"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbAsistencia.setFocusable(false);
        tbAsistencia.setGridColor(new java.awt.Color(204, 204, 204));
        tbAsistencia.setRowHeight(25);
        tbAsistencia.setSelectionBackground(new java.awt.Color(39, 84, 138));
        tbAsistencia.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbAsistencia.setShowGrid(false);
        jScrollPane1.setViewportView(tbAsistencia);

        btnGuardarAsistencia.setBackground(new java.awt.Color(66, 128, 191));
        btnGuardarAsistencia.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnGuardarAsistencia.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarAsistencia.setText("GUARDAR");
        btnGuardarAsistencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarAsistencia.setFocusPainted(false);
        btnGuardarAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarAsistenciaActionPerformed(evt);
            }
        });

        lbFechaHora.setFont(new java.awt.Font("Trebuchet MS", 2, 12)); // NOI18N
        lbFechaHora.setForeground(new java.awt.Color(51, 51, 51));
        lbFechaHora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbFechaHora.setText("Fecha de hoy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFechaHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardarAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarAsistenciaActionPerformed
      
        for (int i = 0; i < tbAsistencia.getRowCount(); i++) {
            int idEstudiante = (int) tbAsistencia.getValueAt(i, 0);
            boolean asistio = (boolean) tbAsistencia.getValueAt(i, 3);
            
            Date fecha = Date.valueOf(LocalDate.now());
            Estudiante estudiante = new Estudiante(idEstudiante);
            Asistencia asistencia = new Asistencia(0, estudiante, fecha, asistio);
            listaAsistencia.set(i, asistencia);
        }

        boolean yaRegistrado = asistenciaCtrl.verificarAsistenciasRegistradas(idDocente);

        if (yaRegistrado) {
            asistenciaCtrl.modificarAsistencias(listaAsistencia);
            JOptionPane.showMessageDialog(this, "Asistencia actualizada correctamente.");
        } else {
            asistenciaCtrl.registrarAsistencias(listaAsistencia);
            JOptionPane.showMessageDialog(this, "Asistencia registrada correctamente.");
        }

        dispose();

    }//GEN-LAST:event_btnGuardarAsistenciaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarAsistencia;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbFechaHora;
    private javax.swing.JTable tbAsistencia;
    // End of variables declaration//GEN-END:variables
}
