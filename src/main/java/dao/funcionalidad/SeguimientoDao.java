/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.funcionalidad;

import java.util.List;
import java.util.Map;


public interface SeguimientoDao {
    
    
    boolean guardarSeguimiento(int idEstudiante, int idCategoria, String descripcion, int frecuencia, String observaciones);
    List<Map<String, Object>> obtenerPromedioFrecuencia(int idEstudiante);
    
}
