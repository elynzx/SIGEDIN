/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.funcionalidad;

import java.util.List;
import java.util.Map;

public interface IncidenteDao {

    public Map<String, Object> obtenerResumenIncidentes(int idEstudiante);

    List<String> obtenerActividadIncidentesRegistrados(int idDocente);

    Map<Integer, Integer> obtenerTopEstudiantesConConductasProblematicas(int idDocente);

    Map<Integer, Integer> obtenerTopEstudiantesConFichasABC(int idDocente);

    Map<String, Integer> obtenerFichasABCporAntecedente(int idEstudiante);

    Map<String, Integer> obtenerConductasProblematicasPorMes(int idEstudiante);

    Map<String, Integer> obtenerConductasPorTipo(int idEstudiante);

}
