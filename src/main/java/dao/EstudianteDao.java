/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.entidades.Estudiante;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author rpasc
 */
public interface EstudianteDao {

    Estudiante obtenerEstudiantePorId(int idEstudiante);

    Estudiante obtenerEstudiantePorDNI(String dni);

    List<Estudiante> obtenerListaEstudiantes(int idDocente);

    public DefaultCategoryDataset generarGraficoMatriculaPorDiagnostico();

    void registrarEstudiante(Estudiante estudiante);

    void actualizarEstudiante(Estudiante estudiante);

    public int obtenerIdPorDNI(String dni);

}
