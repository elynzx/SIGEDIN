/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Administrador;

import controller.AdministradorCtrl;
import dao.AdministradorImp;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author HAYDEE
 */
public class ReportesAdmin extends javax.swing.JFrame {

    private int idAdministrador;
    private final AdministradorCtrl adminCtrl;

    public ReportesAdmin(int idAdministrador) {
        this.idAdministrador = idAdministrador;
        this.adminCtrl= new AdministradorCtrl(
        AdministradorImp.obtenerInstancia()
        );
        initComponents();
        cargarAulas();
        cargarDiagnostico();
    }

    public JButton getJbtnpdfmatricula() {
        return jbtnpdfmatricula;
    }

    public void setJbtnpdfmatricula(JButton jbtnpdfmatricula) {
        this.jbtnpdfmatricula = jbtnpdfmatricula;
    }

    public JButton getJbtnpdfvacantes() {
        return jbtnpdfvacantes;
    }

    public void setJbtnpdfvacantes(JButton jbtnpdfvacantes) {
        this.jbtnpdfvacantes = jbtnpdfvacantes;
    }

    public JComboBox<String> getJcmbaulas() {
        return jcmbaulas;
    }

    public void setJcmbaulas(JComboBox<String> jcmbaulas) {
        this.jcmbaulas = jcmbaulas;
    }

    public JComboBox<String> getJcmbdiagnostico() {
        return jcmbdiagnostico;
    }

    public void setJcmbdiagnostico(JComboBox<String> jcmbdiagnostico) {
        this.jcmbdiagnostico = jcmbdiagnostico;
    }

    public JLabel getJlblempleado() {
        return jlblempleado;
    }

    public void setJlblempleado(JLabel jlblempleado) {
        this.jlblempleado = jlblempleado;
    }

    public JLabel getJlblestudiante() {
        return jlblestudiante;
    }

    public void setJlblestudiante(JLabel jlblestudiante) {
        this.jlblestudiante = jlblestudiante;
    }
    
    
    public void cargarAulas() {
        List<String> listaAulas = adminCtrl.cargarAulas(jcmbaulas);
        jcmbaulas.removeAllItems();
        jcmbaulas.addItem("ninguno");
            for (String nombre : listaAulas) {
                jcmbaulas.addItem(nombre);
            }
        
    }
    
    public void cargarDiagnostico(){
        List<String> listaDiagnostico = adminCtrl.cargarDiagnostico(jcmbdiagnostico);
        jcmbdiagnostico.removeAllItems();
        jcmbdiagnostico.addItem("ninguno");

            for (String nombre : listaDiagnostico) {
                jcmbdiagnostico.addItem(nombre);
            }
    }
    
    

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jlblnombre = new javax.swing.JLabel();
        jlblempleado = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jlblreportes = new javax.swing.JLabel();
        jlblmenu = new javax.swing.JLabel();
        jlblestudiante = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lbNivel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jcmbaulas = new javax.swing.JComboBox<>();
        jbtnpdfaula = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbNivel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcmbdiagnostico = new javax.swing.JComboBox<>();
        jbtnpdfdiagnostico = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbNivel12 = new javax.swing.JLabel();
        jbtnpdfmatricula = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbNivel13 = new javax.swing.JLabel();
        jbtnpdfvacantes = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Hola,");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 40, -1));

        jlblnombre.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlblnombre.setForeground(new java.awt.Color(51, 51, 51));
        jlblnombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblnombre.setText("nombre");
        jPanel2.add(jlblnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 178, 28));

        jlblempleado.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblempleado.setText("Empleados");
        jlblempleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblempleadoMouseClicked(evt);
            }
        });
        jPanel2.add(jlblempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        jlblreportes.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblreportes.setText("Reportes");
        jlblreportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblreportesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jlblreportes)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jlblreportes)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 140, 80));

        jlblmenu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblmenu.setText("Menu");
        jlblmenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblmenuMouseClicked(evt);
            }
        });
        jPanel2.add(jlblmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));

        jlblestudiante.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblestudiante.setText("Estudiantes");
        jlblestudiante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblestudianteMouseClicked(evt);
            }
        });
        jPanel2.add(jlblestudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 80));

        jPanel7.setBackground(new java.awt.Color(249, 246, 231));

        lbNivel11.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbNivel11.setForeground(new java.awt.Color(51, 51, 51));
        lbNivel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel11.setText("Reporte de matrícula por aula");
        lbNivel11.setToolTipText("");
        lbNivel11.setPreferredSize(new java.awt.Dimension(70, 25));

        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Selecciona aula:");

        jcmbaulas.setForeground(new java.awt.Color(102, 102, 102));
        jcmbaulas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Atención", "Escape", "Sensorial", "Acceso a objetos o actividades", "Control / evitar tarea", "Evación de exigencias", "Ninguna identificada (requiere observación)" }));
        jcmbaulas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jcmbaulas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbaulasActionPerformed(evt);
            }
        });

        jbtnpdfaula.setBackground(new java.awt.Color(102, 102, 102));
        jbtnpdfaula.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jbtnpdfaula.setForeground(new java.awt.Color(255, 255, 255));
        jbtnpdfaula.setText("GENERAR PDF");
        jbtnpdfaula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnpdfaula.setFocusPainted(false);
        jbtnpdfaula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnpdfaulaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Reporte de estudiantes matriculados");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9)
                    .addComponent(jbtnpdfaula, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcmbaulas, 0, 315, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNivel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbNivel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jcmbaulas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnpdfaula, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 430, -1));

        jPanel6.setBackground(new java.awt.Color(249, 246, 231));

        lbNivel10.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbNivel10.setForeground(new java.awt.Color(51, 51, 51));
        lbNivel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel10.setText("Reporte por diagnóstico");
        lbNivel10.setToolTipText("");
        lbNivel10.setPreferredSize(new java.awt.Dimension(70, 25));

        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Selecciona diagnóstico:");

        jcmbdiagnostico.setForeground(new java.awt.Color(102, 102, 102));
        jcmbdiagnostico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Atención", "Escape", "Sensorial", "Acceso a objetos o actividades", "Control / evitar tarea", "Evación de exigencias", "Ninguna identificada (requiere observación)" }));
        jcmbdiagnostico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jbtnpdfdiagnostico.setBackground(new java.awt.Color(102, 102, 102));
        jbtnpdfdiagnostico.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jbtnpdfdiagnostico.setForeground(new java.awt.Color(255, 255, 255));
        jbtnpdfdiagnostico.setText("GENERAR PDF");
        jbtnpdfdiagnostico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnpdfdiagnostico.setFocusPainted(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Reporte agrupado por diagnóstico y nivel funcional");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jbtnpdfdiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcmbdiagnostico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNivel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbNivel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jcmbdiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnpdfdiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 430, -1));

        jPanel8.setBackground(new java.awt.Color(249, 246, 231));

        lbNivel12.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbNivel12.setForeground(new java.awt.Color(51, 51, 51));
        lbNivel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel12.setText("Reporte general de matrícula");
        lbNivel12.setToolTipText("");
        lbNivel12.setPreferredSize(new java.awt.Dimension(70, 25));

        jbtnpdfmatricula.setBackground(new java.awt.Color(102, 102, 102));
        jbtnpdfmatricula.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jbtnpdfmatricula.setForeground(new java.awt.Color(255, 255, 255));
        jbtnpdfmatricula.setText("GENERAR PDF");
        jbtnpdfmatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnpdfmatricula.setFocusPainted(false);
        jbtnpdfmatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnpdfmatriculaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Lista completa de estudiantes y datos principales");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnpdfmatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                        .addComponent(lbNivel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbNivel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnpdfmatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, -1, 170));

        jPanel9.setBackground(new java.awt.Color(249, 246, 231));

        lbNivel13.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbNivel13.setForeground(new java.awt.Color(51, 51, 51));
        lbNivel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel13.setText("Reporte de vacantes");
        lbNivel13.setToolTipText("");
        lbNivel13.setPreferredSize(new java.awt.Dimension(70, 25));

        jbtnpdfvacantes.setBackground(new java.awt.Color(102, 102, 102));
        jbtnpdfvacantes.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jbtnpdfvacantes.setForeground(new java.awt.Color(255, 255, 255));
        jbtnpdfvacantes.setText("GENERAR PDF");
        jbtnpdfvacantes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnpdfvacantes.setFocusPainted(false);
        jbtnpdfvacantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnpdfvacantesActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("Lista de aulas con vacantes disponibles");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnpdfvacantes, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbNivel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)))
                .addGap(54, 54, 54))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbNivel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnpdfvacantes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 420, 430, 170));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlblempleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblempleadoMouseClicked
        EmpleadosAdmin empleadosvista = new EmpleadosAdmin(idAdministrador);
        empleadosvista.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jlblempleadoMouseClicked

    private void jlblreportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblreportesMouseClicked
        ReportesAdmin reportes = new ReportesAdmin(idAdministrador);
        reportes.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jlblreportesMouseClicked

    private void jlblestudianteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblestudianteMouseClicked
        EstudiantesAdmin estudiantesvista = new EstudiantesAdmin(idAdministrador);
        estudiantesvista.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jlblestudianteMouseClicked

    private void jlblmenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuMouseClicked
        MenuAdminView menu = new MenuAdminView(idAdministrador);
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jlblmenuMouseClicked

    private void jcmbaulasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbaulasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbaulasActionPerformed

    private void jbtnpdfaulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnpdfaulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnpdfaulaActionPerformed

    private void jbtnpdfmatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnpdfmatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnpdfmatriculaActionPerformed

    private void jbtnpdfvacantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnpdfvacantesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnpdfvacantesActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbtnpdfaula;
    private javax.swing.JButton jbtnpdfdiagnostico;
    public javax.swing.JButton jbtnpdfmatricula;
    public javax.swing.JButton jbtnpdfvacantes;
    public javax.swing.JComboBox<String> jcmbaulas;
    public javax.swing.JComboBox<String> jcmbdiagnostico;
    private javax.swing.JLabel jlblempleado;
    private javax.swing.JLabel jlblestudiante;
    private javax.swing.JLabel jlblmenu;
    private javax.swing.JLabel jlblnombre;
    private javax.swing.JLabel jlblreportes;
    private javax.swing.JLabel lbNivel10;
    private javax.swing.JLabel lbNivel11;
    private javax.swing.JLabel lbNivel12;
    private javax.swing.JLabel lbNivel13;
    // End of variables declaration//GEN-END:variables
}
