/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.funcionalidad;

import java.util.List;
import model.funcionalidad.catalogo.Antecedente;
import model.funcionalidad.catalogo.CategoriaConducta;
import model.funcionalidad.catalogo.EstrategiaIntervencion;
import model.funcionalidad.catalogo.FuncionComportamiento;
import model.funcionalidad.catalogo.TipoConducta;

/**
 *
 * @author rpasc
 */
public interface CatalogoDao {

    List<TipoConducta> obtenerTipoConductas();

    List<FuncionComportamiento> obtenerFuncionComportamientos();

    List<Antecedente> obtenerAntecedentes();

    List<CategoriaConducta> ObtenerCategoriaConductas();
    
    List<EstrategiaIntervencion> obtenerEstrategiasIntervencion();

}
