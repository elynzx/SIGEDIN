package view;

import controller.LoginCtrl;
import javax.swing.JFrame;

public class Login extends javax.swing.JFrame {

    private final LoginCtrl loginCtrl;

    public Login(LoginCtrl usuarioCtrl) {
        this.loginCtrl = usuarioCtrl;
        setUndecorated(true);
        initComponents();
    }

    private void abrirCorreoSoporte() {
        String email = "contacto@sistemainclusivo.com";
        String asunto = "Ayuda para iniciar sesión";
        String cuerpo = "Hola, tengo problemas para acceder al sistema. Mi usuario es: ";

        String uriString = String.format("mailto:%s?subject=%s&body=%s",
                email,
                uriEncode(asunto),
                uriEncode(cuerpo));

        try {
            java.awt.Desktop.getDesktop().mail(new java.net.URI(uriString));
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se pudo abrir el cliente de correo.");
        }
    }

    private String uriEncode(String text) {
        return text.replace(" ", "%20").replace("\n", "%0A");
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textPassword = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbContacto = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnMinimizarLogin = new javax.swing.JLabel();
        btnCerrarLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 740));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 740));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Usuario");

        textUsername.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        textUsername.setText("epascual");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Contraseña");

        textPassword.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        textPassword.setText("1234");

        btnIngresar.setBackground(new java.awt.Color(45, 94, 152));
        btnIngresar.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("ENTRAR");
        btnIngresar.setBorder(null);
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("¿No puede iniciar sesión?");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Contáctate con el ");

        lbContacto.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lbContacto.setForeground(new java.awt.Color(45, 94, 152));
        lbContacto.setText(" Área de Soporte");
        lbContacto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbContacto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbContactoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lbContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textPassword)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textUsername))
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbContacto))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 32)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Inicia Sesión");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jLabel8)
                .addGap(28, 28, 28)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(185, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 790));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 740));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo01.png"))); // NOI18N

        btnMinimizarLogin.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMinimizarLogin.setForeground(new java.awt.Color(45, 94, 152));
        btnMinimizarLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnMinimizarLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/line.png"))); // NOI18N
        btnMinimizarLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimizarLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizarLoginMouseClicked(evt);
            }
        });

        btnCerrarLogin.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnCerrarLogin.setForeground(new java.awt.Color(45, 94, 152));
        btnCerrarLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCerrarLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        btnCerrarLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrarLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarLoginMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(488, Short.MAX_VALUE)
                .addComponent(btnMinimizarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMinimizarLogin)
                    .addComponent(btnCerrarLogin))
                .addGap(81, 81, 81)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(243, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 590, 790));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed

        String username = textUsername.getText();
        String password = new String(textPassword.getPassword());

        loginCtrl.iniciarSesion(username, password, this);

    }//GEN-LAST:event_btnIngresarActionPerformed

    private void lbContactoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbContactoMouseClicked

        abrirCorreoSoporte();


    }//GEN-LAST:event_lbContactoMouseClicked

    private void btnMinimizarLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarLoginMouseClicked

        setState(JFrame.ICONIFIED);

    }//GEN-LAST:event_btnMinimizarLoginMouseClicked

    private void btnCerrarLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarLoginMouseClicked

        System.exit(0);

    }//GEN-LAST:event_btnCerrarLoginMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCerrarLogin;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel btnMinimizarLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbContacto;
    private javax.swing.JPasswordField textPassword;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables
}
