package controller.secretaria;
import dao.SecretariaImp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.Secretaria.VistaDashboardMatricula;
import view.Secretaria.VistaEstudiantes;
import view.Secretaria.Matricula;
import view.Secretaria.ReportesMatricula;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import model.funcionalidad.ListaAulas;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import model.entidades.Estudiante;
import model.entidades.Persona;


public class SecretariaCtrl {
    private VistaDashboardMatricula dashboard;
    private VistaEstudiantes estudiantes;
    private Matricula matricula;
    private ReportesMatricula reportes;
    private SecretariaImp dao;
    private Persona persona;
    int ad,vdt,adt;
    
    


    private void registrar() {
        String nombreAlumno=matricula.getNombresAlumno();
        String apellidoAlumno=matricula.getApellidosAlumno();
        String dniAlumno=matricula.getDniAlumno();
        String generoAlumno=matricula.getDniAlumno();
        Date fechaNacimientoAlumno=matricula.getJDatenacimientoAlumno();
        String tipoAlergia=matricula.getJtxtalergia();
        boolean confirmacionAlergia;
        if(tipoAlergia == null || tipoAlergia.isEmpty()){
            confirmacionAlergia = false;
        }else{
            confirmacionAlergia = true;
        }
        String tipoMedicamento=matricula.getjtxtmedicinas();
        boolean confirmacionMedicamentos;
        if(tipoMedicamento == null || tipoMedicamento.isEmpty()){
            confirmacionMedicamentos = false;
        }else{
            confirmacionMedicamentos = true;
        }
        String nombreApoderado=matricula.getJtxtnombreApoderado();
        String apellidoApoderado=matricula.getJtxtapellidoApoderado();
        String dniApoderado=matricula.getJtxtdniApoderado();
        String parentesco=matricula.getJcmbparentesco();
        String celular=matricula.getJtxtcelular();
        String correo=matricula.getJtxtcorreo();
        String direccion=matricula.getJTextAreadireccion();
        String estado=matricula.getJcmbestado();
        String fecha=matricula.getJtxtfecha();
        String diagnostico=matricula.getJListdiagnostico();
        String nivelFuncional=matricula.getJcmbnivelFuncional();
        String aulaAsignada=matricula.getJcmbaulaAsignada();
        String docenteCargo=matricula.getJtxtdocenteCargo();
        String observaciones=matricula.getJTextAreaobservaciones();
        
        Estudiante estudiante = new Estudiante(
            0, // idEstudiante (temporal)
            confirmacionAlergia, // alergias (temporal)
            tipoAlergia, // tipoAlergia
            confirmacionMedicamentos, // tomaMedicamentos
            tipoMedicamento, // medicamentos
            null, // nivelFuncional (falta implementar)
            null, // apoderado (falta implementar)
            "", // observaciones
            0, // id (persona)
            nombreAlumno,
            apellidoAlumno,
            dniAlumno,
            celular,
            correo,
            direccion,
            fechaNacimientoAlumno,
            generoAlumno
        );
        
    }
    
    public SecretariaCtrl(VistaDashboardMatricula dashboard) {
        this.dashboard = dashboard;
        this.dao = new SecretariaImp();
        cargarTablaAulas();

        dashboard.getJlblmatricula().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new Matricula().setVisible(true);
                dashboard.dispose();
            }
        });

        dashboard.getJlblestudiantes().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new VistaEstudiantes().setVisible(true);
                dashboard.dispose();
            }
        });

        dashboard.getJlblreportes().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ReportesMatricula().setVisible(true);
                dashboard.dispose();
            }
        });
    }
    
    public SecretariaCtrl(VistaEstudiantes estudiantes){
        this.estudiantes = estudiantes;
        
        estudiantes.getJlblmatricula().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new Matricula().setVisible(true);
                estudiantes.dispose();
            }
        });
        
        estudiantes.getJlblinicio().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new VistaDashboardMatricula().setVisible(true);
                estudiantes.dispose();
            }
        });
        
        estudiantes.getJlblreportes().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ReportesMatricula().setVisible(true);
                estudiantes.dispose();
            }
        });
    }
    
        public SecretariaCtrl(Matricula matricula){
            this.matricula=matricula;

            this.matricula.jbtnregistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar(); 
            }
            });


            matricula.getJlblreportes().addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    new ReportesMatricula().setVisible(true);
                    matricula.dispose();
                }
            });

            matricula.getJlblestudiantes().addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    new VistaEstudiantes().setVisible(true);
                    matricula.dispose();
                }
            });

            matricula.getJlblinicio().addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    new VistaDashboardMatricula().setVisible(true);
                    matricula.dispose();
                }
            });
        }
    
    public SecretariaCtrl(ReportesMatricula reportes){
        this.reportes = reportes;
        
                
        reportes.getJlblinicio().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new VistaDashboardMatricula().setVisible(true);
                reportes.dispose();
            }
        });
        
        reportes.getJlblestudiantes().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new VistaEstudiantes().setVisible(true);
                reportes.dispose();
            }
        });
        
        reportes.getJlblmatricula().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new Matricula().setVisible(true);
                reportes.dispose();
            }
        });
    }
    
    private void cargarTablaAulas() {
        DefaultTableModel modelo = (DefaultTableModel) dashboard.getTbAlumnosDashboard().getModel();
        modelo.setRowCount(0);
//        dashboard.getJlblnombre().setText(SeguridadSesion.getUsuarioActual());
        String num = Integer.toString(dao.numeroEstudiantes());
        dashboard.getJlbltotal().setText(num);
        num = Integer.toString(dao.numeroMatriculas());
        dashboard.getJlblmatriculas().setText(num);
        num = Integer.toString(dao.obtenerVacantesDisponibles());
        dashboard.getJlblvacantes().setText(num);
        ad=dao.obtenerAulasDisponibles();
        adt=dao.obtenerAulasDisponiblesTotales();
        dashboard.getJlblasistentes().setText(ad+"/"+adt);
        
        List<ListaAulas> aulas = dao.obtenerListaAulas();
        for (ListaAulas a : aulas) {
            modelo.addRow(new Object[]{
                a.getNombre(),
                a.getNivelFuncional(),
                a.getDiagnostico(),
                a.getDniDocente(),
                a.getVacantesTotales(),
                a.getVacantesDisponibles()
            });
        }
    }
    
}
