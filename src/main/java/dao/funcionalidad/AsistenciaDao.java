/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.funcionalidad;

import java.util.List;
import java.sql.Date;
import model.funcionalidad.Asistencia;

public interface AsistenciaDao {

    List<Asistencia> obtenerAsistenciasDelDia(int idDocente, Date fecha);

    List<Asistencia> obtenerEstudiantesSinAsistencia(int idDocente);

    void registrarAsistencias(List<Asistencia> lista);

    void modificarAsistencias(List<Asistencia> lista);
}
