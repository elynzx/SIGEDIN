/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.google.common.base.Preconditions;
import model.entidades.Estudiante;
import dao.EstudianteDao;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class EstudianteCtrl {

    private final EstudianteDao estudianteDao;

    public EstudianteCtrl(EstudianteDao estudianteDao) {
        this.estudianteDao = estudianteDao;

    }

    public Estudiante obtenerEstudiantePorId(int idEstudiante) {
        Preconditions.checkArgument(idEstudiante > 0, "ID de estudiante inválido");
        return estudianteDao.obtenerEstudiantePorId(idEstudiante);
    }

    public List<Estudiante> obtenerListaEstudiantes(int idDocente) {
        Preconditions.checkArgument(idDocente > 0, "ID de docente inválido");
        return estudianteDao.obtenerListaEstudiantes(idDocente);

    }

    public void cargarListaTablaEstudiantes(int idDocente, JTable tableListaEstudiantes) {
        Preconditions.checkArgument(idDocente > 0, "ID de docente inválido");

        DefaultTableModel modelo = (DefaultTableModel) tableListaEstudiantes.getModel();
        modelo.setRowCount(0);

        try {
            List<Estudiante> listaEstudiantes = estudianteDao.obtenerListaEstudiantes(idDocente);

            if (CollectionUtils.isEmpty(listaEstudiantes)) {
                System.out.println("No hay estudiantes asignados al docente");
                return;
            }

            for (Estudiante estudiante : listaEstudiantes) {
                modelo.addRow(new Object[]{
                    estudiante.getIdEstudiante(),
                    StringUtils.capitalize(estudiante.getApellidos()),
                    StringUtils.capitalize(estudiante.getNombres())
                });
            }
        } catch (Exception e) {
            System.out.println("Error al cargar estudiantes: " + e.getMessage());
        }
    }
}
