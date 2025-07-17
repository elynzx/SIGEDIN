package dao.funcionalidad;

import java.util.List;
import model.funcionalidad.catalogo.Antecedente;
import model.funcionalidad.catalogo.CategoriaConducta;
import model.funcionalidad.catalogo.Diagnostico;
import model.funcionalidad.catalogo.EstrategiaIntervencion;
import model.funcionalidad.catalogo.FuncionComportamiento;
import model.funcionalidad.catalogo.NivelFuncional;
import model.funcionalidad.catalogo.TipoConducta;


public interface CatalogoDao {

    List<TipoConducta> obtenerTipoConductas();

    List<FuncionComportamiento> obtenerFuncionComportamientos();

    List<Antecedente> obtenerAntecedentes();

    List<CategoriaConducta> ObtenerCategoriaConductas();

    List<EstrategiaIntervencion> obtenerEstrategiasIntervencion();

    List<Diagnostico> obtenerDiagnosticos();

    List<NivelFuncional> obtenerNivelesFuncionales();

}
