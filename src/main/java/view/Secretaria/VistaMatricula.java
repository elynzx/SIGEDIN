package view.Secretaria;

import controller.secretaria.MatriculaCtrl;
import dao.ApoderadoImp;
import dao.AulaImp;
import dao.EstudianteImp;
import dao.funcionalidad.CatalogoImp;
import dao.funcionalidad.MatriculaImp;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionListener;
import model.entidades.Apoderado;
import model.entidades.Aula;
import model.entidades.Estudiante;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.NivelFuncional;
import model.funcionalidad.Matricula;

public class VistaMatricula extends javax.swing.JPanel {

    private final MatriculaCtrl matriculaCtrl;
    private Estudiante estudianteActual;

    public VistaMatricula(int idSecretaria) {
        this.matriculaCtrl = new MatriculaCtrl(
                EstudianteImp.obtenerInstancia(),
                ApoderadoImp.obtenerInstancia(),
                CatalogoImp.obtenerInstancia(),
                AulaImp.obtenerInstancia(),
                MatriculaImp.obtenerInstancia()
        );

        initComponents();

        rbAlergiaSi.addActionListener(e -> txtTipoAlergia.setEnabled(true));
        rbAlergiaNo.addActionListener(e -> {
            txtTipoAlergia.setText("");
            txtTipoAlergia.setEnabled(false);
        });

        rbMedicamentoSi.addActionListener(e -> txtMedicamentos.setEnabled(true));
        rbMedicamentoNo.addActionListener(e -> {
            txtMedicamentos.setText("");
            txtMedicamentos.setEnabled(false);
        });

        initFechaMatricula();
        initDiagnosticos();
        initNivelesFuncionales();
        obtenerDiagnosticoSeleccionados();

        activarEventos();
    }

    private void silenciarEventos() {
        for (ActionListener al : cbNivelFuncional.getActionListeners()) {
            cbNivelFuncional.removeActionListener(al);
        }
        for (ActionListener al : cbAula.getActionListeners()) {
            cbAula.removeActionListener(al);
        }
        for (ListSelectionListener lsl : jListDiagnostico.getListSelectionListeners()) {
            jListDiagnostico.removeListSelectionListener(lsl);
        }
    }

    private void activarEventos() {
        cbNivelFuncional.addActionListener(e -> actualizarAulas());

        cbAula.addActionListener(e -> actualizarDocente());

        jListDiagnostico.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                List<Diagnostico> seleccionados = jListDiagnostico.getSelectedValuesList();
                String texto = seleccionados.stream()
                        .map(Diagnostico::getNombre)
                        .collect(Collectors.joining(", "));
                txtDiagnosticosSeleccionados.setText(texto);
                actualizarAulas();
            }
        });
    }

    private void cargarDatosEstudiante(Estudiante estudiante) {
        silenciarEventos();

        this.estudianteActual = estudiante;

        txtNombres.setText(estudiante.getNombres());
        txtApellidos.setText(estudiante.getApellidos());
        txtDni.setText(estudiante.getDni());
        txtFechaNacimiento.setDate(estudiante.getFechaNacimiento());
        cbGeneroEstudiante.setSelectedItem(estudiante.getGenero());
        rbAlergiaSi.setSelected(estudiante.isAlergias());
        rbAlergiaNo.setSelected(!estudiante.isAlergias());
        txtTipoAlergia.setText(estudiante.getTipoAlergia());
        txtTipoAlergia.setEnabled(estudiante.isAlergias());

        rbMedicamentoSi.setSelected(estudiante.isTomaMedicamentos());
        rbMedicamentoNo.setSelected(!estudiante.isTomaMedicamentos());
        txtMedicamentos.setText(estudiante.getMedicamentos());
        txtMedicamentos.setEnabled(estudiante.isTomaMedicamentos());

        txtObservaciones.setText(estudiante.getObservaciones());

        jListDiagnostico.setSelectedIndices(obtenerIndicesDiagnostico(estudiante.getDiagnosticos()));
        String textoDiagnosticos = estudiante.getDiagnosticos().stream()
                .map(Diagnostico::getNombre)
                .collect(Collectors.joining(", "));
        txtDiagnosticosSeleccionados.setText(textoDiagnosticos);

        initNivelesFuncionales();
        cbNivelFuncional.setSelectedItem(estudiante.getNivelFuncional());

        if (estudiante.getApoderado() != null) {
            txtDniApoderado.setText(estudiante.getApoderado().getDni());
            txtNombresApoderado.setText(estudiante.getApoderado().getNombres());
            txtApellidosApoderado.setText(estudiante.getApoderado().getApellidos());
            txtCorreoApoderado.setText(estudiante.getApoderado().getCorreo());
            txtDireccionApoderado.setText(estudiante.getApoderado().getDireccion());
            txtFechaNacimientoApoderado.setDate(estudiante.getApoderado().getFechaNacimiento());
            txtCelularApoderado.setText(estudiante.getApoderado().getCelular());
            cbParentesco.setSelectedItem(estudiante.getApoderado().getParentesco());
        }

        Matricula matricula = matriculaCtrl.obtenerMatriculaPorEstudiante(estudiante.getIdEstudiante());
        if (matricula != null) {
            txtIdMatricula.setText(String.valueOf(matricula.getId()));
            txtFechaMatricula.setText(matricula.getFechaMatricula().toString());
            cbEstadoActual.setSelectedItem(matricula.getEstado());
            cbNivelFuncional.setSelectedItem(matricula.getAula().getNivelFuncional());
            txtDocente.setText(matricula.getAula().getDocente().getNombreCompleto());

            int nivelId = estudiante.getNivelFuncional().getId();
            List<Aula> aulas = matriculaCtrl.obtenerTodasLasAulasPorNivel(nivelId);

            if (!aulas.contains(matricula.getAula())) {
                aulas.add(matricula.getAula());
            }

            initAulas(aulas);
            cbAula.setSelectedItem(matricula.getAula());
        }

        activarEventos();
    }

    private void initDiagnosticos() {
        DefaultListModel<Diagnostico> modelo = new DefaultListModel<>();
        List<Diagnostico> lista = matriculaCtrl.obtenerDiagnosticos();
        for (Diagnostico diag : lista) {
            modelo.addElement(diag);
        }
        jListDiagnostico.setModel(modelo);
    }

    private void initNivelesFuncionales() {
        cbNivelFuncional.removeAllItems();
        List<NivelFuncional> niveles = matriculaCtrl.obtenerNivelesFuncionales();
        for (NivelFuncional nf : niveles) {
            cbNivelFuncional.addItem(nf);
        }
    }

    private void initAulas(List<Aula> aulas) {
        cbAula.removeAllItems();
        for (Aula aula : aulas) {
            cbAula.addItem(aula);
        }
    }

    private void actualizarAulas() {
        NivelFuncional nivel = (NivelFuncional) cbNivelFuncional.getSelectedItem();
        List<Diagnostico> seleccionados = jListDiagnostico.getSelectedValuesList();

        if (nivel != null && !seleccionados.isEmpty()) {
            int nivelId = nivel.getId();
            List<Integer> diagnosticoIds = seleccionados.stream()
                    .map(Diagnostico::getId)
                    .collect(Collectors.toList());

            List<Aula> aulasFiltradas = matriculaCtrl.filtrarAulas(nivelId, diagnosticoIds);

            if (aulasFiltradas.isEmpty()) {
                if (cbAula.getItemCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No hay aulas disponibles.");
                }
                cbAula.removeAllItems();
                txtDocente.setText("");
            } else {
                initAulas(aulasFiltradas);
            }
        }
    }

    private void actualizarDocente() {
        Aula aulaSeleccionada = (Aula) cbAula.getSelectedItem();
        if (aulaSeleccionada != null && aulaSeleccionada.getDocente() != null) {
            txtDocente.setText(aulaSeleccionada.getDocente().getNombreCompleto());
        } else {
            txtDocente.setText("");
        }
    }

    private int[] obtenerIndicesDiagnostico(List<Diagnostico> seleccionados) {
        ListModel<Diagnostico> modelo = jListDiagnostico.getModel();
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < modelo.getSize(); i++) {
            Diagnostico actual = modelo.getElementAt(i);
            for (Diagnostico seleccionado : seleccionados) {
                if (actual.getId() == seleccionado.getId()) {
                    indices.add(i);
                    break;
                }
            }
        }

        return indices.stream().mapToInt(Integer::intValue).toArray();
    }

    private void obtenerDiagnosticoSeleccionados() {
        jListDiagnostico.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                List<Diagnostico> seleccionados = jListDiagnostico.getSelectedValuesList();
                String texto = seleccionados.stream()
                        .map(Diagnostico::getNombre)
                        .collect(Collectors.joining(", "));
                txtDiagnosticosSeleccionados.setText(texto);

                actualizarAulas();
            }
        });
    }

    private void initFechaMatricula() {
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txtFechaMatricula.setText(hoy.format(formato));
    }

    private Apoderado construirApoderado() {
        Apoderado apoderado = new Apoderado();
        apoderado.setDni(txtDniApoderado.getText().trim());
        apoderado.setNombres(txtNombresApoderado.getText().trim());
        apoderado.setApellidos(txtApellidosApoderado.getText().trim());
        apoderado.setCorreo(txtCorreoApoderado.getText().trim());
        apoderado.setDireccion(txtDireccionApoderado.getText().trim());
        java.util.Date fechaUtil = txtFechaNacimientoApoderado.getDate();
        if (fechaUtil != null) {
            apoderado.setFechaNacimiento(new java.sql.Date(fechaUtil.getTime()));
        }
        apoderado.setCelular(txtCelularApoderado.getText().trim());
        apoderado.setParentesco(cbParentesco.getSelectedItem().toString());
        return apoderado;
    }

    private Matricula construirMatricula(Estudiante estudiante) {
        Matricula matricula = new Matricula();
        matricula.setEstudiante(estudiante);
        matricula.setAula((Aula) cbAula.getSelectedItem());

        String fechaTexto = txtFechaMatricula.getText().trim();
        if (fechaTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una fecha de matrícula.");
            return null;
        }

        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(fechaTexto, formato);
            matricula.setFechaMatricula(java.sql.Date.valueOf(fecha));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Fecha de matrícula inválida.");
            return null;
        }

        matricula.setEstado(cbEstadoActual.getSelectedItem().toString());
        return matricula;
    }

    private Estudiante construirEstudiante() {
        Estudiante estudiante = new Estudiante();
        estudiante.setDni(txtDni.getText().trim());
        estudiante.setNombres(txtNombres.getText().trim());
        estudiante.setApellidos(txtApellidos.getText().trim());

        java.util.Date fechaUtil = txtFechaNacimientoApoderado.getDate();
        if (fechaUtil != null) {
            estudiante.setFechaNacimiento(new java.sql.Date(fechaUtil.getTime()));
        }

        estudiante.setGenero(cbGeneroEstudiante.getSelectedItem().toString());
        estudiante.setAlergias(rbAlergiaSi.isSelected());
        estudiante.setTipoAlergia(txtTipoAlergia.getText().trim());
        estudiante.setTomaMedicamentos(rbMedicamentoSi.isSelected());
        estudiante.setMedicamentos(txtMedicamentos.getText().trim());
        estudiante.setObservaciones(txtObservaciones.getText().trim());
        estudiante.setNivelFuncional((NivelFuncional) cbNivelFuncional.getSelectedItem());

        List<Diagnostico> diagnosticos = jListDiagnostico.getSelectedValuesList();
        estudiante.setDiagnosticos(diagnosticos);

        return estudiante;
    }

    private void limpiarCampos() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDni.setText("");
        txtFechaNacimiento.setDate(null);
        cbGeneroEstudiante.setSelectedIndex(0);
        rbAlergiaSi.setSelected(false);
        rbAlergiaNo.setSelected(false);
        txtTipoAlergia.setText("");
        txtTipoAlergia.setEnabled(false);
        rbMedicamentoSi.setSelected(false);
        rbMedicamentoNo.setSelected(false);
        txtMedicamentos.setText("");
        txtMedicamentos.setEnabled(false);
        txtObservaciones.setText("");
        cbNivelFuncional.setSelectedIndex(0);
        jListDiagnostico.clearSelection();
        txtDiagnosticosSeleccionados.setText("");

        txtDniApoderado.setText("");
        txtNombresApoderado.setText("");
        txtApellidosApoderado.setText("");
        txtCorreoApoderado.setText("");
        txtDireccionApoderado.setText("");
        txtFechaNacimientoApoderado.setDate(null);
        txtCelularApoderado.setText("");
        cbParentesco.setSelectedIndex(0);

        initFechaMatricula(); // vuelve a poner la fecha actual
        cbEstadoActual.setSelectedIndex(0);
        cbAula.removeAllItems();
        txtDocente.setText("");

        estudianteActual = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jpDashboardDocente = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnDescFichaMatricula = new javax.swing.JLabel();
        textBuscarEstudiante = new javax.swing.JTextField();
        btnBuscarDni = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        panelDatos20 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        panelDatos21 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        panelDatos23 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        panelDatos22 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        cbGeneroEstudiante = new javax.swing.JComboBox<>();
        panelDatos24 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtFechaNacimiento = new com.toedter.calendar.JDateChooser();
        panelDatos32 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        rbMedicamentoSi = new javax.swing.JRadioButton();
        rbMedicamentoNo = new javax.swing.JRadioButton();
        txtMedicamentos = new javax.swing.JTextField();
        panelDatos34 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        rbAlergiaSi = new javax.swing.JRadioButton();
        rbAlergiaNo = new javax.swing.JRadioButton();
        txtTipoAlergia = new javax.swing.JTextField();
        lbNivel3 = new javax.swing.JLabel();
        btnRegistrarMatricula = new javax.swing.JButton();
        btnAnularMatricula = new javax.swing.JButton();
        btnModificarMatricula = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        txtIdMatricula = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        panelDatos36 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        txtNombresApoderado = new javax.swing.JTextField();
        panelDatos37 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        txtApellidosApoderado = new javax.swing.JTextField();
        panelDatos38 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        txtDniApoderado = new javax.swing.JTextField();
        panelDatos40 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        cbParentesco = new javax.swing.JComboBox<>();
        panelDatos41 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        txtFechaNacimientoApoderado = new com.toedter.calendar.JDateChooser();
        panelDatos44 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        txtCelularApoderado = new javax.swing.JTextField();
        panelDatos46 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDireccionApoderado = new javax.swing.JTextArea();
        panelDatos45 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        txtCorreoApoderado = new javax.swing.JTextField();
        lbNivel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        panelDatos50 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        cbNivelFuncional = new javax.swing.JComboBox<>();
        panelDatos53 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        panelDatos54 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        txtDocente = new javax.swing.JTextField();
        panelDatos55 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        cbAula = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListDiagnostico = new javax.swing.JList<>();
        jLabel47 = new javax.swing.JLabel();
        lbNivel7 = new javax.swing.JLabel();
        txtDiagnosticosSeleccionados = new javax.swing.JTextField();
        panelDatos57 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        txtFechaMatricula = new javax.swing.JTextField();
        panelDatos56 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        cbEstadoActual = new javax.swing.JComboBox<>();
        txtIdMatricula1 = new javax.swing.JLabel();

        jpDashboardDocente.setBackground(new java.awt.Color(255, 255, 255));
        jpDashboardDocente.setMinimumSize(new java.awt.Dimension(1250, 734));
        jpDashboardDocente.setPreferredSize(new java.awt.Dimension(1250, 734));
        jpDashboardDocente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));
        jpDashboardDocente.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 1160, 10));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Info_fill-1_1.png"))); // NOI18N
        jLabel6.setText("Registra observaciones so frecuencia diaria.");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jpDashboardDocente.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 290, -1));

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(45, 94, 152));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Registro de Matrícula");
        jpDashboardDocente.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 330, 30));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Buscar DNI:");
        jpDashboardDocente.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 80, 40));

        btnDescFichaMatricula.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        btnDescFichaMatricula.setForeground(new java.awt.Color(23, 64, 112));
        btnDescFichaMatricula.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnDescFichaMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Import-2.png"))); // NOI18N
        btnDescFichaMatricula.setText("Descargar Ficha de Matricula");
        btnDescFichaMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDescFichaMatricula.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnDescFichaMatricula.setIconTextGap(8);
        btnDescFichaMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDescFichaMatriculaMouseClicked(evt);
            }
        });
        jpDashboardDocente.add(btnDescFichaMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 120, 220, 30));

        textBuscarEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        textBuscarEstudiante.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        textBuscarEstudiante.setForeground(new java.awt.Color(102, 102, 102));
        textBuscarEstudiante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jpDashboardDocente.add(textBuscarEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 180, 40));

        btnBuscarDni.setBackground(new java.awt.Color(16, 58, 108));
        btnBuscarDni.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        btnBuscarDni.setForeground(new java.awt.Color(98, 66, 26));
        btnBuscarDni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Search_alt-2.png"))); // NOI18N
        btnBuscarDni.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(66, 128, 191)));
        btnBuscarDni.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDniActionPerformed(evt);
            }
        });
        jpDashboardDocente.add(btnBuscarDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 40, 40, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(234, 234, 234)));
        jPanel2.setForeground(new java.awt.Color(235, 235, 235));
        jPanel2.setPreferredSize(new java.awt.Dimension(310, 480));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDatos20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 102, 102));
        jLabel27.setText("Nombres:");

        txtNombres.setBackground(new java.awt.Color(255, 255, 255));
        txtNombres.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtNombres.setForeground(new java.awt.Color(23, 64, 112));
        txtNombres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        javax.swing.GroupLayout panelDatos20Layout = new javax.swing.GroupLayout(panelDatos20);
        panelDatos20.setLayout(panelDatos20Layout);
        panelDatos20Layout.setHorizontalGroup(
            panelDatos20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos20Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(panelDatos20Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelDatos20Layout.setVerticalGroup(
            panelDatos20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos20Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(panelDatos20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 300, 50));

        panelDatos21.setBackground(new java.awt.Color(255, 255, 255));

        jLabel28.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText("Apellidos:");

        txtApellidos.setBackground(new java.awt.Color(255, 255, 255));
        txtApellidos.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtApellidos.setForeground(new java.awt.Color(23, 64, 112));
        txtApellidos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        javax.swing.GroupLayout panelDatos21Layout = new javax.swing.GroupLayout(panelDatos21);
        panelDatos21.setLayout(panelDatos21Layout);
        panelDatos21Layout.setHorizontalGroup(
            panelDatos21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos21Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatos21Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(0, 247, Short.MAX_VALUE))
                    .addComponent(txtApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
        );
        panelDatos21Layout.setVerticalGroup(
            panelDatos21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos21Layout.createSequentialGroup()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(panelDatos21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 300, -1));

        panelDatos23.setBackground(new java.awt.Color(255, 255, 255));

        jLabel32.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setText("DNI o C.E.:");

        txtDni.setBackground(new java.awt.Color(255, 255, 255));
        txtDni.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtDni.setForeground(new java.awt.Color(23, 64, 112));
        txtDni.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        javax.swing.GroupLayout panelDatos23Layout = new javax.swing.GroupLayout(panelDatos23);
        panelDatos23.setLayout(panelDatos23Layout);
        panelDatos23Layout.setHorizontalGroup(
            panelDatos23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos23Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatos23Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtDni, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
        );
        panelDatos23Layout.setVerticalGroup(
            panelDatos23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos23Layout.createSequentialGroup()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jPanel2.add(panelDatos23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 130, -1));

        panelDatos22.setBackground(new java.awt.Color(255, 255, 255));

        jLabel29.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("Género:");

        cbGeneroEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        cbGeneroEstudiante.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        cbGeneroEstudiante.setForeground(new java.awt.Color(23, 64, 112));
        cbGeneroEstudiante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "masculino", "femenino" }));
        cbGeneroEstudiante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        javax.swing.GroupLayout panelDatos22Layout = new javax.swing.GroupLayout(panelDatos22);
        panelDatos22.setLayout(panelDatos22Layout);
        panelDatos22Layout.setHorizontalGroup(
            panelDatos22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos22Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbGeneroEstudiante, 0, 300, Short.MAX_VALUE)
                    .addGroup(panelDatos22Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelDatos22Layout.setVerticalGroup(
            panelDatos22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbGeneroEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelDatos22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 300, -1));

        panelDatos24.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 102));
        jLabel33.setText("Fecha de Nacimiento:");

        txtFechaNacimiento.setForeground(new java.awt.Color(23, 64, 112));
        txtFechaNacimiento.setOpaque(false);

        javax.swing.GroupLayout panelDatos24Layout = new javax.swing.GroupLayout(panelDatos24);
        panelDatos24.setLayout(panelDatos24Layout);
        panelDatos24Layout.setHorizontalGroup(
            panelDatos24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
            .addGroup(panelDatos24Layout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDatos24Layout.setVerticalGroup(
            panelDatos24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(panelDatos24, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 140, -1));

        panelDatos32.setBackground(new java.awt.Color(255, 255, 255));

        jLabel34.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setText("Medicinas:");

        rbMedicamentoSi.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbMedicamentoSi);
        rbMedicamentoSi.setForeground(new java.awt.Color(102, 102, 102));
        rbMedicamentoSi.setText("Sí");
        rbMedicamentoSi.setOpaque(true);

        rbMedicamentoNo.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbMedicamentoNo);
        rbMedicamentoNo.setForeground(new java.awt.Color(102, 102, 102));
        rbMedicamentoNo.setSelected(true);
        rbMedicamentoNo.setText("No");
        rbMedicamentoNo.setOpaque(true);

        txtMedicamentos.setBackground(new java.awt.Color(255, 255, 255));
        txtMedicamentos.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtMedicamentos.setForeground(new java.awt.Color(23, 64, 112));
        txtMedicamentos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        txtMedicamentos.setEnabled(false);

        javax.swing.GroupLayout panelDatos32Layout = new javax.swing.GroupLayout(panelDatos32);
        panelDatos32.setLayout(panelDatos32Layout);
        panelDatos32Layout.setHorizontalGroup(
            panelDatos32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos32Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMedicamentos, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(panelDatos32Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbMedicamentoSi)
                        .addGap(26, 26, 26)
                        .addComponent(rbMedicamentoNo)
                        .addContainerGap())))
        );
        panelDatos32Layout.setVerticalGroup(
            panelDatos32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos32Layout.createSequentialGroup()
                .addGroup(panelDatos32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbMedicamentoSi)
                    .addComponent(rbMedicamentoNo))
                .addGap(6, 6, 6)
                .addComponent(txtMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel2.add(panelDatos32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 300, 60));

        panelDatos34.setBackground(new java.awt.Color(255, 255, 255));

        jLabel48.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(102, 102, 102));
        jLabel48.setText("Alergias");

        rbAlergiaSi.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rbAlergiaSi);
        rbAlergiaSi.setForeground(new java.awt.Color(102, 102, 102));
        rbAlergiaSi.setText("Sí");
        rbAlergiaSi.setOpaque(true);

        rbAlergiaNo.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rbAlergiaNo);
        rbAlergiaNo.setForeground(new java.awt.Color(102, 102, 102));
        rbAlergiaNo.setSelected(true);
        rbAlergiaNo.setText("No");
        rbAlergiaNo.setOpaque(true);

        txtTipoAlergia.setBackground(new java.awt.Color(255, 255, 255));
        txtTipoAlergia.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtTipoAlergia.setForeground(new java.awt.Color(23, 64, 112));
        txtTipoAlergia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        txtTipoAlergia.setEnabled(false);

        javax.swing.GroupLayout panelDatos34Layout = new javax.swing.GroupLayout(panelDatos34);
        panelDatos34.setLayout(panelDatos34Layout);
        panelDatos34Layout.setHorizontalGroup(
            panelDatos34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos34Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTipoAlergia, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(panelDatos34Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbAlergiaSi)
                        .addGap(26, 26, 26)
                        .addComponent(rbAlergiaNo)
                        .addContainerGap())))
        );
        panelDatos34Layout.setVerticalGroup(
            panelDatos34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos34Layout.createSequentialGroup()
                .addGroup(panelDatos34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbAlergiaSi)
                    .addComponent(rbAlergiaNo))
                .addGap(6, 6, 6)
                .addComponent(txtTipoAlergia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel2.add(panelDatos34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 300, -1));

        lbNivel3.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel3.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel3.setText("1. Datos del estudiante");
        lbNivel3.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel2.add(lbNivel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 200, 30));

        jpDashboardDocente.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 360, 490));

        btnRegistrarMatricula.setBackground(new java.awt.Color(16, 58, 108));
        btnRegistrarMatricula.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnRegistrarMatricula.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Save_fill-2.png"))); // NOI18N
        btnRegistrarMatricula.setText("REGISTRAR");
        btnRegistrarMatricula.setBorder(null);
        btnRegistrarMatricula.setBorderPainted(false);
        btnRegistrarMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarMatricula.setFocusPainted(false);
        btnRegistrarMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarMatriculaActionPerformed(evt);
            }
        });
        jpDashboardDocente.add(btnRegistrarMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, 135, 40));

        btnAnularMatricula.setBackground(new java.awt.Color(51, 51, 51));
        btnAnularMatricula.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnAnularMatricula.setForeground(new java.awt.Color(255, 255, 255));
        btnAnularMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_1.png"))); // NOI18N
        btnAnularMatricula.setText("ANULAR");
        btnAnularMatricula.setBorder(null);
        btnAnularMatricula.setBorderPainted(false);
        btnAnularMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnularMatricula.setFocusPainted(false);
        btnAnularMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularMatriculaActionPerformed(evt);
            }
        });
        jpDashboardDocente.add(btnAnularMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 40, 135, 40));

        btnModificarMatricula.setBackground(new java.awt.Color(221, 168, 83));
        btnModificarMatricula.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnModificarMatricula.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Edit_fill-1.png"))); // NOI18N
        btnModificarMatricula.setText("MODIFICAR");
        btnModificarMatricula.setBorder(null);
        btnModificarMatricula.setBorderPainted(false);
        btnModificarMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarMatricula.setFocusPainted(false);
        btnModificarMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarMatriculaActionPerformed(evt);
            }
        });
        jpDashboardDocente.add(btnModificarMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 135, 40));

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(23, 64, 112));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("MATRICULA:");
        jLabel31.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jpDashboardDocente.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 70, 40));

        txtIdMatricula.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        txtIdMatricula.setForeground(new java.awt.Color(23, 64, 112));
        txtIdMatricula.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtIdMatricula.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jpDashboardDocente.add(txtIdMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 140, 40));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(234, 234, 234)));
        jPanel5.setForeground(new java.awt.Color(235, 235, 235));
        jPanel5.setPreferredSize(new java.awt.Dimension(310, 480));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDatos36.setBackground(new java.awt.Color(255, 255, 255));

        jLabel37.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 102, 102));
        jLabel37.setText("Nombres:");

        txtNombresApoderado.setBackground(new java.awt.Color(255, 255, 255));
        txtNombresApoderado.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtNombresApoderado.setForeground(new java.awt.Color(23, 64, 112));
        txtNombresApoderado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        javax.swing.GroupLayout panelDatos36Layout = new javax.swing.GroupLayout(panelDatos36);
        panelDatos36.setLayout(panelDatos36Layout);
        panelDatos36Layout.setHorizontalGroup(
            panelDatos36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos36Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombresApoderado, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(panelDatos36Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelDatos36Layout.setVerticalGroup(
            panelDatos36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos36Layout.createSequentialGroup()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombresApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.add(panelDatos36, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 300, -1));

        panelDatos37.setBackground(new java.awt.Color(255, 255, 255));

        jLabel40.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(102, 102, 102));
        jLabel40.setText("Apellidos:");

        txtApellidosApoderado.setBackground(new java.awt.Color(255, 255, 255));
        txtApellidosApoderado.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtApellidosApoderado.setForeground(new java.awt.Color(23, 64, 112));
        txtApellidosApoderado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        javax.swing.GroupLayout panelDatos37Layout = new javax.swing.GroupLayout(panelDatos37);
        panelDatos37.setLayout(panelDatos37Layout);
        panelDatos37Layout.setHorizontalGroup(
            panelDatos37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos37Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApellidosApoderado, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(panelDatos37Layout.createSequentialGroup()
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelDatos37Layout.setVerticalGroup(
            panelDatos37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos37Layout.createSequentialGroup()
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtApellidosApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.add(panelDatos37, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 300, -1));

        panelDatos38.setBackground(new java.awt.Color(255, 255, 255));

        jLabel49.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(102, 102, 102));
        jLabel49.setText("DNI o C.E.:");

        txtDniApoderado.setBackground(new java.awt.Color(255, 255, 255));
        txtDniApoderado.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtDniApoderado.setForeground(new java.awt.Color(23, 64, 112));
        txtDniApoderado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        javax.swing.GroupLayout panelDatos38Layout = new javax.swing.GroupLayout(panelDatos38);
        panelDatos38.setLayout(panelDatos38Layout);
        panelDatos38Layout.setHorizontalGroup(
            panelDatos38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos38Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDniApoderado, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addGroup(panelDatos38Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addGap(0, 72, Short.MAX_VALUE))))
        );
        panelDatos38Layout.setVerticalGroup(
            panelDatos38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos38Layout.createSequentialGroup()
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(txtDniApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.add(panelDatos38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 130, -1));

        panelDatos40.setBackground(new java.awt.Color(255, 255, 255));

        jLabel51.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(102, 102, 102));
        jLabel51.setText("Parentesco");

        cbParentesco.setBackground(new java.awt.Color(255, 255, 255));
        cbParentesco.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        cbParentesco.setForeground(new java.awt.Color(23, 64, 112));
        cbParentesco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "madre", "padre", "tía", "tío", "hermano/a", "abuelo/a", "tutor legal" }));
        cbParentesco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        javax.swing.GroupLayout panelDatos40Layout = new javax.swing.GroupLayout(panelDatos40);
        panelDatos40.setLayout(panelDatos40Layout);
        panelDatos40Layout.setHorizontalGroup(
            panelDatos40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos40Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbParentesco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelDatos40Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addContainerGap(77, Short.MAX_VALUE))))
        );
        panelDatos40Layout.setVerticalGroup(
            panelDatos40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.add(panelDatos40, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 140, -1));

        panelDatos41.setBackground(new java.awt.Color(255, 255, 255));

        jLabel52.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(102, 102, 102));
        jLabel52.setText("Fecha de Nacimiento:");

        txtFechaNacimientoApoderado.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaNacimientoApoderado.setForeground(new java.awt.Color(23, 64, 112));

        javax.swing.GroupLayout panelDatos41Layout = new javax.swing.GroupLayout(panelDatos41);
        panelDatos41.setLayout(panelDatos41Layout);
        panelDatos41Layout.setHorizontalGroup(
            panelDatos41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtFechaNacimientoApoderado, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
            .addGroup(panelDatos41Layout.createSequentialGroup()
                .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDatos41Layout.setVerticalGroup(
            panelDatos41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaNacimientoApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(panelDatos41, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 140, -1));

        panelDatos44.setBackground(new java.awt.Color(255, 255, 255));

        jLabel55.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(102, 102, 102));
        jLabel55.setText("Celular:");

        txtCelularApoderado.setBackground(new java.awt.Color(255, 255, 255));
        txtCelularApoderado.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtCelularApoderado.setForeground(new java.awt.Color(23, 64, 112));
        txtCelularApoderado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        javax.swing.GroupLayout panelDatos44Layout = new javax.swing.GroupLayout(panelDatos44);
        panelDatos44.setLayout(panelDatos44Layout);
        panelDatos44Layout.setHorizontalGroup(
            panelDatos44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos44Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCelularApoderado, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addGroup(panelDatos44Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelDatos44Layout.setVerticalGroup(
            panelDatos44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos44Layout.createSequentialGroup()
                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCelularApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.add(panelDatos44, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 130, -1));

        panelDatos46.setBackground(new java.awt.Color(255, 255, 255));

        jLabel57.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(102, 102, 102));
        jLabel57.setText("Dirección:");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDireccionApoderado.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccionApoderado.setColumns(20);
        txtDireccionApoderado.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtDireccionApoderado.setForeground(new java.awt.Color(23, 64, 112));
        txtDireccionApoderado.setLineWrap(true);
        txtDireccionApoderado.setRows(5);
        txtDireccionApoderado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(219, 234, 251)));
        txtDireccionApoderado.setCaretColor(new java.awt.Color(51, 51, 51));
        txtDireccionApoderado.setHighlighter(null);
        txtDireccionApoderado.setMinimumSize(new java.awt.Dimension(235, 18));
        jScrollPane2.setViewportView(txtDireccionApoderado);

        javax.swing.GroupLayout panelDatos46Layout = new javax.swing.GroupLayout(panelDatos46);
        panelDatos46.setLayout(panelDatos46Layout);
        panelDatos46Layout.setHorizontalGroup(
            panelDatos46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos46Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(panelDatos46Layout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelDatos46Layout.setVerticalGroup(
            panelDatos46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos46Layout.createSequentialGroup()
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jPanel5.add(panelDatos46, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 300, 110));

        panelDatos45.setBackground(new java.awt.Color(255, 255, 255));

        jLabel56.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(102, 102, 102));
        jLabel56.setText("Correo:");

        txtCorreoApoderado.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreoApoderado.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtCorreoApoderado.setForeground(new java.awt.Color(23, 64, 112));
        txtCorreoApoderado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        javax.swing.GroupLayout panelDatos45Layout = new javax.swing.GroupLayout(panelDatos45);
        panelDatos45.setLayout(panelDatos45Layout);
        panelDatos45Layout.setHorizontalGroup(
            panelDatos45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos45Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCorreoApoderado, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(panelDatos45Layout.createSequentialGroup()
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelDatos45Layout.setVerticalGroup(
            panelDatos45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos45Layout.createSequentialGroup()
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreoApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.add(panelDatos45, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 300, -1));

        lbNivel5.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel5.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel5.setText("2. Datos del apoderado");
        lbNivel5.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel5.add(lbNivel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 200, 30));

        jpDashboardDocente.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 360, 490));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(234, 234, 234)));
        jPanel6.setForeground(new java.awt.Color(235, 235, 235));
        jPanel6.setPreferredSize(new java.awt.Dimension(310, 480));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDatos50.setBackground(new java.awt.Color(255, 255, 255));

        jLabel61.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(102, 102, 102));
        jLabel61.setText("Nivel Funcional:");

        cbNivelFuncional.setBackground(new java.awt.Color(255, 255, 255));
        cbNivelFuncional.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        cbNivelFuncional.setForeground(new java.awt.Color(23, 64, 112));
        cbNivelFuncional.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        javax.swing.GroupLayout panelDatos50Layout = new javax.swing.GroupLayout(panelDatos50);
        panelDatos50.setLayout(panelDatos50Layout);
        panelDatos50Layout.setHorizontalGroup(
            panelDatos50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos50Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbNivelFuncional, 0, 300, Short.MAX_VALUE)
                    .addGroup(panelDatos50Layout.createSequentialGroup()
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelDatos50Layout.setVerticalGroup(
            panelDatos50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos50Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbNivelFuncional, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.add(panelDatos50, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 300, -1));

        panelDatos53.setBackground(new java.awt.Color(255, 255, 255));

        jLabel64.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(102, 102, 102));
        jLabel64.setText("Observaciones sobre el estudiante:");

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtObservaciones.setBackground(new java.awt.Color(255, 255, 255));
        txtObservaciones.setColumns(20);
        txtObservaciones.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtObservaciones.setForeground(new java.awt.Color(23, 64, 112));
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setRows(5);
        txtObservaciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(219, 234, 251)));
        txtObservaciones.setCaretColor(new java.awt.Color(51, 51, 51));
        txtObservaciones.setHighlighter(null);
        txtObservaciones.setMinimumSize(new java.awt.Dimension(235, 18));
        jScrollPane4.setViewportView(txtObservaciones);

        javax.swing.GroupLayout panelDatos53Layout = new javax.swing.GroupLayout(panelDatos53);
        panelDatos53.setLayout(panelDatos53Layout);
        panelDatos53Layout.setHorizontalGroup(
            panelDatos53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos53Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(panelDatos53Layout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelDatos53Layout.setVerticalGroup(
            panelDatos53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos53Layout.createSequentialGroup()
                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jPanel6.add(panelDatos53, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 300, 80));

        panelDatos54.setBackground(new java.awt.Color(255, 255, 255));

        jLabel65.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(102, 102, 102));
        jLabel65.setText("Docente a cargo:");

        txtDocente.setEditable(false);
        txtDocente.setBackground(new java.awt.Color(255, 255, 255));
        txtDocente.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtDocente.setForeground(new java.awt.Color(102, 102, 102));
        txtDocente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        txtDocente.setFocusable(false);

        javax.swing.GroupLayout panelDatos54Layout = new javax.swing.GroupLayout(panelDatos54);
        panelDatos54.setLayout(panelDatos54Layout);
        panelDatos54Layout.setHorizontalGroup(
            panelDatos54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtDocente, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
            .addGroup(panelDatos54Layout.createSequentialGroup()
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelDatos54Layout.setVerticalGroup(
            panelDatos54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos54Layout.createSequentialGroup()
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.add(panelDatos54, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, 140, -1));

        panelDatos55.setBackground(new java.awt.Color(255, 255, 255));

        jLabel66.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(102, 102, 102));
        jLabel66.setText("Aula asignada:");

        cbAula.setBackground(new java.awt.Color(255, 255, 255));
        cbAula.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        cbAula.setForeground(new java.awt.Color(23, 64, 112));
        cbAula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        javax.swing.GroupLayout panelDatos55Layout = new javax.swing.GroupLayout(panelDatos55);
        panelDatos55.setLayout(panelDatos55Layout);
        panelDatos55Layout.setHorizontalGroup(
            panelDatos55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos55Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbAula, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelDatos55Layout.createSequentialGroup()
                        .addComponent(jLabel66)
                        .addGap(0, 57, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelDatos55Layout.setVerticalGroup(
            panelDatos55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos55Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbAula, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.add(panelDatos55, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        jListDiagnostico.setBackground(new java.awt.Color(255, 255, 255));
        jListDiagnostico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(219, 234, 251)));
        jListDiagnostico.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jListDiagnostico.setForeground(new java.awt.Color(23, 64, 112));
        jScrollPane5.setViewportView(jListDiagnostico);

        jPanel6.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 300, 90));

        jLabel47.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(102, 102, 102));
        jLabel47.setText("Diagnóstico:");
        jPanel6.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, 16));

        lbNivel7.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbNivel7.setForeground(new java.awt.Color(45, 94, 152));
        lbNivel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNivel7.setText("3. Información Educativa");
        lbNivel7.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel6.add(lbNivel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 200, 30));

        txtDiagnosticosSeleccionados.setEditable(false);
        txtDiagnosticosSeleccionados.setBackground(new java.awt.Color(255, 255, 255));
        txtDiagnosticosSeleccionados.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtDiagnosticosSeleccionados.setForeground(new java.awt.Color(102, 102, 102));
        txtDiagnosticosSeleccionados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        txtDiagnosticosSeleccionados.setFocusable(false);
        jPanel6.add(txtDiagnosticosSeleccionados, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 300, 25));

        panelDatos57.setBackground(new java.awt.Color(255, 255, 255));

        jLabel68.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(102, 102, 102));
        jLabel68.setText("Fecha:");

        txtFechaMatricula.setEditable(false);
        txtFechaMatricula.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaMatricula.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtFechaMatricula.setForeground(new java.awt.Color(102, 102, 102));
        txtFechaMatricula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        txtFechaMatricula.setFocusable(false);

        javax.swing.GroupLayout panelDatos57Layout = new javax.swing.GroupLayout(panelDatos57);
        panelDatos57.setLayout(panelDatos57Layout);
        panelDatos57Layout.setHorizontalGroup(
            panelDatos57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos57Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addGroup(panelDatos57Layout.createSequentialGroup()
                        .addComponent(jLabel68)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelDatos57Layout.setVerticalGroup(
            panelDatos57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos57Layout.createSequentialGroup()
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.add(panelDatos57, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        panelDatos56.setBackground(new java.awt.Color(255, 255, 255));

        jLabel67.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(102, 102, 102));
        jLabel67.setText("Estado actual:");

        cbEstadoActual.setBackground(new java.awt.Color(255, 255, 255));
        cbEstadoActual.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        cbEstadoActual.setForeground(new java.awt.Color(23, 64, 112));
        cbEstadoActual.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "activo", "inactivo", "retirado", "graduado" }));
        cbEstadoActual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        javax.swing.GroupLayout panelDatos56Layout = new javax.swing.GroupLayout(panelDatos56);
        panelDatos56.setLayout(panelDatos56Layout);
        panelDatos56Layout.setHorizontalGroup(
            panelDatos56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos56Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDatos56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbEstadoActual, 0, 150, Short.MAX_VALUE)
                    .addGroup(panelDatos56Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addContainerGap())))
        );
        panelDatos56Layout.setVerticalGroup(
            panelDatos56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos56Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEstadoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.add(panelDatos56, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        jpDashboardDocente.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 160, 360, 490));

        txtIdMatricula1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        txtIdMatricula1.setForeground(new java.awt.Color(23, 64, 112));
        txtIdMatricula1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtIdMatricula1.setText("2025 -");
        txtIdMatricula1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jpDashboardDocente.add(txtIdMatricula1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 80, 40));

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

    private void btnDescFichaMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDescFichaMatriculaMouseClicked

    }//GEN-LAST:event_btnDescFichaMatriculaMouseClicked

    private void btnBuscarDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDniActionPerformed

        String dni = textBuscarEstudiante.getText().trim();
        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un DNI válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            limpiarCampos();
            return;
        }

        Estudiante estudiante = matriculaCtrl.buscarEstudiantePorDNI(dni);
        if (estudiante != null) {
            cargarDatosEstudiante(estudiante);
        } else {
            JOptionPane.showMessageDialog(this, "Estudiante no encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        }
    }//GEN-LAST:event_btnBuscarDniActionPerformed

    private void btnRegistrarMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarMatriculaActionPerformed

        try {
            Apoderado apoderado = construirApoderado();
            Estudiante estudiante = construirEstudiante();
            estudiante.setApoderado(apoderado);

            Apoderado existenteApoderado = matriculaCtrl.buscarApoderadoPorDNI(apoderado.getDni());
            if (existenteApoderado == null) {
                matriculaCtrl.registrarApoderado(apoderado);
            } else {
                apoderado = existenteApoderado;
            }

            Estudiante existenteEstudiante = matriculaCtrl.buscarEstudiantePorDNI(estudiante.getDni());
            if (existenteEstudiante == null) {
                matriculaCtrl.registrarEstudiante(estudiante);
                int idEstudiante = matriculaCtrl.obtenerIdEstudiantePorDNI(estudiante.getDni());
                estudiante.setIdEstudiante(idEstudiante);
            } else {
                estudiante = existenteEstudiante;
            }

            Matricula matricula = construirMatricula(estudiante);

            matriculaCtrl.registrarMatricula(matricula);

            JOptionPane.showMessageDialog(this, "Matrícula registrada exitosamente.");
            limpiarCampos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar matrícula: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnRegistrarMatriculaActionPerformed

    private void btnAnularMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularMatriculaActionPerformed
        if (estudianteActual == null) {
            JOptionPane.showMessageDialog(this, "Primero debes buscar un estudiante.");
            return;
        }
        Matricula matricula = matriculaCtrl.obtenerMatriculaPorEstudiante(estudianteActual.getIdEstudiante());
        if (matricula != null) {
            matriculaCtrl.cambiarEstadoMatricula(matricula.getId(), "inactivo");
            JOptionPane.showMessageDialog(this, "Matrícula desactivada correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró matrícula para este estudiante.");
        }
    }//GEN-LAST:event_btnAnularMatriculaActionPerformed

    private void btnModificarMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarMatriculaActionPerformed

        if (estudianteActual == null) {
            JOptionPane.showMessageDialog(this, "Primero debes buscar un estudiante.");
            return;
        }

        Apoderado apoderado = construirApoderado();
        apoderado.setIdApoderado(estudianteActual.getApoderado().getIdApoderado());
        apoderado.setId(estudianteActual.getApoderado().getId());

        Estudiante estudiante = construirEstudiante();
        estudiante.setIdEstudiante(estudianteActual.getIdEstudiante());
        estudiante.setId(estudianteActual.getId());
        estudiante.setApoderado(apoderado);

        Matricula matricula = construirMatricula(estudiante);
        if (matricula == null) {
            return;
        }

        Matricula matriculaExistente = matriculaCtrl.obtenerMatriculaPorEstudiante(estudiante.getIdEstudiante());
        if (matriculaExistente == null) {
            JOptionPane.showMessageDialog(this, "No se encontró matrícula existente para modificar.");
            return;
        }
        matricula.setId(matriculaExistente.getId());

        matriculaCtrl.actualizarApoderado(apoderado);
        matriculaCtrl.actualizarEstudiante(estudiante);
        matriculaCtrl.actualizarMatricula(matricula);

        JOptionPane.showMessageDialog(this, "Matrícula modificada correctamente.");
        limpiarCampos();

    }//GEN-LAST:event_btnModificarMatriculaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnularMatricula;
    private javax.swing.JButton btnBuscarDni;
    private javax.swing.JLabel btnDescFichaMatricula;
    private javax.swing.JButton btnModificarMatricula;
    private javax.swing.JButton btnRegistrarMatricula;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<Aula> cbAula;
    private javax.swing.JComboBox<String> cbEstadoActual;
    private javax.swing.JComboBox<String> cbGeneroEstudiante;
    private javax.swing.JComboBox<NivelFuncional> cbNivelFuncional;
    private javax.swing.JComboBox<String> cbParentesco;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JList<Diagnostico> jListDiagnostico;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel jpDashboardDocente;
    private javax.swing.JLabel lbNivel3;
    private javax.swing.JLabel lbNivel5;
    private javax.swing.JLabel lbNivel7;
    private javax.swing.JPanel panelDatos20;
    private javax.swing.JPanel panelDatos21;
    private javax.swing.JPanel panelDatos22;
    private javax.swing.JPanel panelDatos23;
    private javax.swing.JPanel panelDatos24;
    private javax.swing.JPanel panelDatos32;
    private javax.swing.JPanel panelDatos34;
    private javax.swing.JPanel panelDatos36;
    private javax.swing.JPanel panelDatos37;
    private javax.swing.JPanel panelDatos38;
    private javax.swing.JPanel panelDatos40;
    private javax.swing.JPanel panelDatos41;
    private javax.swing.JPanel panelDatos44;
    private javax.swing.JPanel panelDatos45;
    private javax.swing.JPanel panelDatos46;
    private javax.swing.JPanel panelDatos50;
    private javax.swing.JPanel panelDatos53;
    private javax.swing.JPanel panelDatos54;
    private javax.swing.JPanel panelDatos55;
    private javax.swing.JPanel panelDatos56;
    private javax.swing.JPanel panelDatos57;
    private javax.swing.JRadioButton rbAlergiaNo;
    private javax.swing.JRadioButton rbAlergiaSi;
    private javax.swing.JRadioButton rbMedicamentoNo;
    private javax.swing.JRadioButton rbMedicamentoSi;
    private javax.swing.JTextField textBuscarEstudiante;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtApellidosApoderado;
    private javax.swing.JTextField txtCelularApoderado;
    private javax.swing.JTextField txtCorreoApoderado;
    private javax.swing.JTextField txtDiagnosticosSeleccionados;
    private javax.swing.JTextArea txtDireccionApoderado;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtDniApoderado;
    private javax.swing.JTextField txtDocente;
    private javax.swing.JTextField txtFechaMatricula;
    private com.toedter.calendar.JDateChooser txtFechaNacimiento;
    private com.toedter.calendar.JDateChooser txtFechaNacimientoApoderado;
    private javax.swing.JLabel txtIdMatricula;
    private javax.swing.JLabel txtIdMatricula1;
    private javax.swing.JTextField txtMedicamentos;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNombresApoderado;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtTipoAlergia;
    // End of variables declaration//GEN-END:variables
}
