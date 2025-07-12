/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.funcionalidad;

import java.util.List;
import model.funcionalidad.ConductaProblematica;

/**
 *
 * @author rpasc
 */
public interface ConductaProblematicaDao {

    boolean guardarConductaProblematica(int idEstudiante, int idConducta, int idFuncionComportamiento, int gravedad, String descripcion);

    List<ConductaProblematica> obtenerConductasPorEstudiante(int idEstudiante);

}
