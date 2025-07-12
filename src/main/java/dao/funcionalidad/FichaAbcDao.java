/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.funcionalidad;

import java.util.List;
import model.funcionalidad.FichaAbc;

/**
 *
 * @author rpasc
 */
public interface FichaAbcDao {

    boolean guardarFichaAbc(int idEstudiante, int idAntecedente, String comportamiento, String consecuencia, int gravedad);

    List<FichaAbc> obtenerFichasPorEstudiante(int idEstudiante);
    
    

}
