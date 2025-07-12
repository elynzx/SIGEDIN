/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.entidades.Estudiante;

/**
 *
 * @author rpasc
 */
public interface EstudianteDao {

    Estudiante obtenerEstudiantePorId(int idEstudiante);

    List<Estudiante> obtenerListaEstudiantes(int idDocente);

}
