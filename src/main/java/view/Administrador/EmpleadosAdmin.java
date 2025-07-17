/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Administrador;

import controller.AdministradorCtrl;
import dao.AdministradorImp;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.funcionalidad.ListaUsuarios;

/**
 *
 * @author HAYDEE
 */
public class EmpleadosAdmin extends javax.swing.JFrame {

    private int idAdministrador;
    private final AdministradorCtrl adminCtrl;   
   
    
    public EmpleadosAdmin(int idAdministrador) {
        this.idAdministrador=idAdministrador;
        this.adminCtrl= new AdministradorCtrl(
        AdministradorImp.obtenerInstancia()
        );
        initComponents();
        cargarTablaUsuarios();
        jtxtDato.setEditable(false);
        jbmcdato.setEnabled(false);
        jbtnconfirmar.setVisible(false);
        jbtncancelar.setVisible(false);
        

    }
    
    public JTable getjTableusuarios() {
        return jTableusuarios;
    }

    public void setjTableusuarios(JTable jTableusuarios) {
        this.jTableusuarios = jTableusuarios;
    }
    

    private DefaultTableModel cargarTablaUsuarios() {
        DefaultTableModel modeloUsuarios = new DefaultTableModel(
            new String[]{
                "ID Persona", 
                "ID Usuario", 
                "Nombres", 
                "Apellidos", 
                "Nombre de Usuario", 
                "Rol", 
                "Estado"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        getjTableusuarios().setModel(modeloUsuarios);

        List<ListaUsuarios> usuarios = adminCtrl.cargarTablaUsuarios();

        for (ListaUsuarios u : usuarios) {
            modeloUsuarios.addRow(new Object[]{
                u.getPersona().getId(),
                u.getId(),
                u.getPersona().getNombres(),
                u.getPersona().getApellidos(),
                u.getNombre_usuario(),
                u.getRol(),
                u.getEstado()
            });
        }   

       return modeloUsuarios;
    }
    
    private void actualizarDato(){
            int filaSeleccionada = jTableusuarios.getSelectedRow();
            Object Id = jTableusuarios.getValueAt(filaSeleccionada, 0);
            String tipo_dato=(String) jbmcdato.getSelectedItem();
            int id = Integer.parseInt(Id.toString());
            String dato=jtxtDato.getText();
            adminCtrl.cambiarDato(tipo_dato,dato, id);
            cargarTablaUsuarios();
            jtxtDato.setEditable(false);
            jtxtDato.setText(" ");
            jbmcdato.setEnabled(false);
            jbtnconfirmar.setVisible(false);
            jbtncancelar.setVisible(false);
            
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jblbempleados = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jlblnombre = new javax.swing.JLabel();
        jlblmenu = new javax.swing.JLabel();
        jlblreportes = new javax.swing.JLabel();
        jlblestudiante = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jlblempleado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableusuarios = new javax.swing.JTable();
        jbtnagregar = new javax.swing.JButton();
        jbtneliminar = new javax.swing.JButton();
        jbtneditar = new javax.swing.JButton();
        jbtnVerContraseña = new javax.swing.JButton();
        jbmcdato = new javax.swing.JComboBox<>();
        jtxtDato = new javax.swing.JTextField();
        jbtnconfirmar = new javax.swing.JButton();
        jbtncancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblbempleados.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jblbempleados.setText("Lista de empleados");
        jPanel1.add(jblbempleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 98, 320, -1));

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

        jlblmenu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblmenu.setText("Menu");
        jlblmenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblmenuMouseClicked(evt);
            }
        });
        jPanel2.add(jlblmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        jlblreportes.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblreportes.setText("Reportes");
        jPanel2.add(jlblreportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, -1));

        jlblestudiante.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblestudiante.setText("Estudiantes");
        jPanel2.add(jlblestudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblempleado.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblempleado.setText("Empleados");
        jPanel3.add(jlblempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 140, 80));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 80));

        jTableusuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableusuarios);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 730, 390));

        jbtnagregar.setBackground(new java.awt.Color(153, 255, 102));
        jbtnagregar.setText("Agregar Empleado");
        jbtnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnagregarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnagregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 210, 190, 30));

        jbtneliminar.setBackground(new java.awt.Color(255, 51, 51));
        jbtneliminar.setText("Eliminar Empleado");
        jPanel1.add(jbtneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 260, 190, 30));

        jbtneditar.setBackground(new java.awt.Color(255, 255, 153));
        jbtneditar.setText("Editar Dato de Empleado");
        jbtneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtneditarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtneditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 310, 190, 30));

        jbtnVerContraseña.setBackground(new java.awt.Color(153, 255, 255));
        jbtnVerContraseña.setText("Ver Contraseña");
        jPanel1.add(jbtnVerContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 490, 190, 30));

        jbmcdato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombres", "Apellidos", "Nombre de Usuario", "Rol", "Estado" }));
        jPanel1.add(jbmcdato, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 360, 190, 30));
        jPanel1.add(jtxtDato, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 402, 190, 30));

        jbtnconfirmar.setBackground(new java.awt.Color(102, 255, 102));
        jbtnconfirmar.setText("Confirmar");
        jbtnconfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnconfirmarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnconfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 450, 90, -1));

        jbtncancelar.setBackground(new java.awt.Color(255, 51, 51));
        jbtncancelar.setText("Cancelar");
        jbtncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncancelarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 450, 90, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnconfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnconfirmarActionPerformed
        if(jtxtDato.getText().isEmpty() || jtxtDato.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "El dato esta vacio");
        }else{
            actualizarDato();
        }
        
    }//GEN-LAST:event_jbtnconfirmarActionPerformed

    private void jbtneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtneditarActionPerformed
        int filaSeleccionada = jTableusuarios.getSelectedRow();
        if (filaSeleccionada != -1) {
        jbtncancelar.setVisible(true);
        jbtnconfirmar.setVisible(true);
        jbmcdato.setEnabled(true);
        jtxtDato.setEditable(true);
        }else{
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un empleado");
        }
    }//GEN-LAST:event_jbtneditarActionPerformed

    private void jbtncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncancelarActionPerformed
        jbtncancelar.setVisible(false);
        jbtnconfirmar.setVisible(false);
        jtxtDato.setEditable(false);
        jbmcdato.setEnabled(false);
    }//GEN-LAST:event_jbtncancelarActionPerformed

    private void jbtnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnagregarActionPerformed
        String rol="empleado";
        RegistrarAdministrador registrar = new RegistrarAdministrador(rol,idAdministrador);
        registrar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jbtnagregarActionPerformed

    private void jlblmenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuMouseClicked
        MenuAdminView menu = new MenuAdminView(idAdministrador);
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jlblmenuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableusuarios;
    private javax.swing.JLabel jblbempleados;
    private javax.swing.JComboBox<String> jbmcdato;
    private javax.swing.JButton jbtnVerContraseña;
    private javax.swing.JButton jbtnagregar;
    private javax.swing.JButton jbtncancelar;
    private javax.swing.JButton jbtnconfirmar;
    private javax.swing.JButton jbtneditar;
    private javax.swing.JButton jbtneliminar;
    private javax.swing.JLabel jlblempleado;
    private javax.swing.JLabel jlblestudiante;
    private javax.swing.JLabel jlblmenu;
    private javax.swing.JLabel jlblnombre;
    private javax.swing.JLabel jlblreportes;
    private javax.swing.JTextField jtxtDato;
    // End of variables declaration//GEN-END:variables
}
