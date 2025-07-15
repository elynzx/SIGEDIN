
package view.Administrador;

import controller.AdministradorCtrl;
import dao.AdministradorImp;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import view.Administrador.ReportesAdmin;

/**
 *
 * @author HAYDEE
 */
public class EstudiantesAdmin extends javax.swing.JFrame {

    private int idAdministrador;
    private final AdministradorCtrl adminCtrl;
    private String dato_final;

    public EstudiantesAdmin(int idAdministrador) {
        this.idAdministrador = idAdministrador;
        this.adminCtrl= new AdministradorCtrl(
        AdministradorImp.obtenerInstancia()
        );
        initComponents();
        adminCtrl.llenarTablaEstudiantes(jTableListaEstudiantes);
        cargarAulas(jcmbaula);
        cargarDiagnostico(jcmbdiagnostico);
        cargarDocentes(jcmbdocente);
        jcmbaula.setVisible(false);
        jtxtdato.setVisible(false);
        jbtncambiar.setVisible(false);
        jbtncancelar.setVisible(false);
        jcmbdatoVariable.setVisible(false);
    }

    public JTable getjTableListaEstudiantes() {
        return jTableListaEstudiantes;
    }

    public void setjTableListaEstudiantes(JTable jTableListaEstudiantes) {
        this.jTableListaEstudiantes = jTableListaEstudiantes;
    }

    public JButton getJbtnabrirpdf() {
        return jbtnabrirpdf;
    }

    public void setJbtnabrirpdf(JButton jbtnabrirpdf) {
        this.jbtnabrirpdf = jbtnabrirpdf;
    }

    public JButton getJbtnactualizar() {
        return jbtnactualizar;
    }

    public void setJbtnactualizar(JButton jbtnactualizar) {
        this.jbtnactualizar = jbtnactualizar;
    }

    public JComboBox<String> getJcmbaula() {
        return jcmbaula;
    }

    public void setJcmbaula(JComboBox<String> jcmbaula) {
        this.jcmbaula = jcmbaula;
    }

    public JComboBox<String> getJcmbdiagnostico() {
        return jcmbdiagnostico;
    }

    public void setJcmbdiagnostico(JComboBox<String> jcmbdiagnostico) {
        this.jcmbdiagnostico = jcmbdiagnostico;
    }

    public JComboBox<String> getJcmbdocente() {
        return jcmbdocente;
    }

    public void setJcmbdocente(JComboBox<String> jcmbdocente) {
        this.jcmbdocente = jcmbdocente;
    }
    
    

    public void cargarAulas(JComboBox combo) {
        List<String> listaAulas = adminCtrl.cargarAulas(combo);
        jcmbaula.removeAllItems();
        jcmbaula.addItem("ninguno");
            for (String nombre : listaAulas) {
                combo.addItem(nombre);
            }
        
    }
    
    public void cargarDiagnostico(JComboBox combo){
        List<String> listaDiagnostico = adminCtrl.cargarDiagnostico(combo);
        jcmbdiagnostico.removeAllItems();
        jcmbdiagnostico.addItem("ninguno");

            for (String nombre : listaDiagnostico) {
                combo.addItem(nombre);
            }
    }
    
    public void cargarDocentes(JComboBox combo){
        List<String> listaDocentes = adminCtrl.cargarDocentes(combo);
        jcmbdocente.removeAllItems();
        jcmbdocente.addItem("ninguno");
        for (String nombre : listaDocentes) {
            combo.addItem(nombre);
        }
    }
    

    
    public int ObtenerValorSeleccionadojTableListaEstudiantes() {
        return jTableListaEstudiantes.getSelectedRow();
    }
    
    private int idEstudiante(){
            int filaSeleccionada = jTableListaEstudiantes.getSelectedRow();
            int Id_estudiante = (int) jTableListaEstudiantes.getValueAt(filaSeleccionada, 0);
            
            return Id_estudiante;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JlblEstudiantes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListaEstudiantes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jlblnombre = new javax.swing.JLabel();
        jlblempleado = new javax.swing.JLabel();
        jlblreportes = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jlblestudiante = new javax.swing.JLabel();
        jlblmenu = new javax.swing.JLabel();
        jcmbaula = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jcmbdiagnostico = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jcmbdocente = new javax.swing.JComboBox<>();
        jbtnactualizar = new javax.swing.JButton();
        jbtnabrirpdf = new javax.swing.JButton();
        jcmbdato = new javax.swing.JComboBox<>();
        jbtneditar = new javax.swing.JButton();
        jtxtdato = new javax.swing.JTextField();
        jbtncancelar = new javax.swing.JButton();
        jbtncambiar = new javax.swing.JButton();
        jcmbdatoVariable = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JlblEstudiantes.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        JlblEstudiantes.setText("Estudiantes");
        jPanel1.add(JlblEstudiantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, -1));

        jScrollPane1.setBorder(null);

        jTableListaEstudiantes.setForeground(new java.awt.Color(51, 51, 51));
        jTableListaEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "Pascual Caycho", "Evelyn Roxana", null, null, null, null, null, null},
                {null, "Caceres", "Carlos", null, null, null, null, null, null},
                {null, "Pineda", "Paul", null, null, null, null, null, null},
                {null, "Montalvo", "Matias", null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Apellidos", "Nombres", "Diagnostico", "Nivel funcional", "Aula asignada", "Docente a cargo", "Celular", "Fecha de Matricula"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableListaEstudiantes.setFocusable(false);
        jTableListaEstudiantes.setGridColor(new java.awt.Color(204, 204, 204));
        jTableListaEstudiantes.setSelectionBackground(new java.awt.Color(66, 128, 191));
        jTableListaEstudiantes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTableListaEstudiantes.setShowGrid(true);
        jTableListaEstudiantes.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTableListaEstudiantes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 1060, 380));

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

        jlblreportes.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblreportes.setText("Reportes");
        jlblreportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblreportesMouseClicked(evt);
            }
        });
        jPanel2.add(jlblreportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        jlblestudiante.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblestudiante.setText("Estudiantes");
        jlblestudiante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblestudianteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jlblestudiante)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jlblestudiante)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 140, 80));

        jlblmenu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblmenu.setText("Menu");
        jlblmenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblmenuMouseClicked(evt);
            }
        });
        jPanel2.add(jlblmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1420, 80));

        jcmbaula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jcmbaula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbaulaActionPerformed(evt);
            }
        });
        jPanel1.add(jcmbaula, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 210, 30));

        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("Filtrar Aula:");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Filtrar Diagnóstico");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, -1, -1));

        jcmbdiagnostico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jcmbdiagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbdiagnosticoActionPerformed(evt);
            }
        });
        jPanel1.add(jcmbdiagnostico, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 260, 30));

        jLabel27.setForeground(new java.awt.Color(102, 102, 102));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Filtrar Docente:");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, -1, -1));

        jcmbdocente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jcmbdocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbdocenteActionPerformed(evt);
            }
        });
        jPanel1.add(jcmbdocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 270, 30));

        jbtnactualizar.setBackground(new java.awt.Color(58, 163, 163));
        jbtnactualizar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jbtnactualizar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnactualizar.setText("ACTUALIZAR");
        jbtnactualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnactualizar.setFocusPainted(false);
        jbtnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnactualizarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 330, 170, 40));

        jbtnabrirpdf.setBackground(new java.awt.Color(102, 102, 102));
        jbtnabrirpdf.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jbtnabrirpdf.setForeground(new java.awt.Color(255, 255, 255));
        jbtnabrirpdf.setText("Abrir PDF");
        jbtnabrirpdf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnabrirpdf.setFocusPainted(false);
        jbtnabrirpdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnabrirpdfActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnabrirpdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 270, 170, 40));

        jcmbdato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Apellidos", "Nombres", "Aula Asignada", "Celular" }));
        jcmbdato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbdatoActionPerformed(evt);
            }
        });
        jPanel1.add(jcmbdato, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 440, 170, 30));

        jbtneditar.setText("Editar datos de estudiante");
        jbtneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtneditarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtneditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 390, 170, 30));
        jPanel1.add(jtxtdato, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 490, 170, 30));

        jbtncancelar.setText("cancelar");
        jbtncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncancelarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 590, -1, -1));

        jbtncambiar.setText("cambiar");
        jbtncambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncambiarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtncambiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 590, -1, -1));

        jcmbdatoVariable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jcmbdatoVariable, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 540, 170, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1298, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlblempleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblempleadoMouseClicked
        EmpleadosAdmin empleadosvista = new EmpleadosAdmin(idAdministrador);
        empleadosvista.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jlblempleadoMouseClicked

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

    private void jlblreportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblreportesMouseClicked
        ReportesAdmin reportes = new ReportesAdmin(idAdministrador);
        reportes.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jlblreportesMouseClicked

    private void jcmbaulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbaulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbaulaActionPerformed

    private void jcmbdiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbdiagnosticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbdiagnosticoActionPerformed

    private void jcmbdocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbdocenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbdocenteActionPerformed

    private void jbtnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnactualizarActionPerformed
        adminCtrl.actualizarValores(this, jTableListaEstudiantes);
    }//GEN-LAST:event_jbtnactualizarActionPerformed

    private void jbtnabrirpdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnabrirpdfActionPerformed
        adminCtrl.abrirPDF(this);
    }//GEN-LAST:event_jbtnabrirpdfActionPerformed

    private void jbtneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtneditarActionPerformed
        jcmbaula.setVisible(true);
        jtxtdato.setVisible(true);
        jbtncambiar.setVisible(true);
        jbtncancelar.setVisible(true);
    }//GEN-LAST:event_jbtneditarActionPerformed

    private void jbtncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncancelarActionPerformed
        jcmbaula.setVisible(false);
        jtxtdato.setVisible(false);
        jbtncambiar.setVisible(false);
        jbtncancelar.setVisible(false);
    }//GEN-LAST:event_jbtncancelarActionPerformed

    private void jbtncambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncambiarActionPerformed
        String Dato=jtxtdato.getText();
        if(Dato.isBlank()){
            JOptionPane.showMessageDialog(rootPane, "Dato invalido");
        }else{
            int idEstudiante=idEstudiante();
            adminCtrl.registrarCambio(dato_final,idEstudiante);
        }
    }//GEN-LAST:event_jbtncambiarActionPerformed

    private void jcmbdatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbdatoActionPerformed
        String dato=(String) jcmbdato.getSelectedItem();
        
        switch (dato){
            case "Seleccionar":JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un dato");break;
            case "Nombres": dato_final="nombres";break;
            case "Apellidos": dato_final="apellidos";break;
            case "Aula Asignada": dato_final="aula";jcmbdatoVariable.setVisible(true); cargarAulas(jcmbdatoVariable);jtxtdato.setEditable(false);jtxtdato.setText("");break;
            case "Celular": dato_final="celular";break;
            default:return;
        }
        
    }//GEN-LAST:event_jcmbdatoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JlblEstudiantes;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTableListaEstudiantes;
    public javax.swing.JButton jbtnabrirpdf;
    public javax.swing.JButton jbtnactualizar;
    private javax.swing.JButton jbtncambiar;
    private javax.swing.JButton jbtncancelar;
    private javax.swing.JButton jbtneditar;
    public javax.swing.JComboBox<String> jcmbaula;
    private javax.swing.JComboBox<String> jcmbdato;
    private javax.swing.JComboBox<String> jcmbdatoVariable;
    public javax.swing.JComboBox<String> jcmbdiagnostico;
    public javax.swing.JComboBox<String> jcmbdocente;
    private javax.swing.JLabel jlblempleado;
    private javax.swing.JLabel jlblestudiante;
    private javax.swing.JLabel jlblmenu;
    private javax.swing.JLabel jlblnombre;
    private javax.swing.JLabel jlblreportes;
    private javax.swing.JTextField jtxtdato;
    // End of variables declaration//GEN-END:variables
}
