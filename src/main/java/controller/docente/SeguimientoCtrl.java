package controller.docente;

import com.google.common.base.Preconditions;
import dao.funcionalidad.CatalogoDao;
import dao.funcionalidad.SeguimientoDao;
import java.util.List;
import java.util.Map;
import model.funcionalidad.catalogo.CategoriaConducta;

public class SeguimientoCtrl {

    private final SeguimientoDao seguimientoDao;
    private final CatalogoDao catalogoDao;

    public SeguimientoCtrl(SeguimientoDao seguimientoDao, CatalogoDao catalogoDao) {
        this.seguimientoDao = seguimientoDao;
        this.catalogoDao = catalogoDao;
    }

    public List<CategoriaConducta> obtenerCategorias() {
        return catalogoDao.ObtenerCategoriaConductas();
    }

    public List<Map<String, Object>> obtenerPromediosSeguimiento(int idEstudiante) {
        Preconditions.checkArgument(idEstudiante > 0, "ID de estudiante inválido");
        return seguimientoDao.obtenerPromedioFrecuencia(idEstudiante);
    }

    public String registrarSeguimiento(int idEstudiante, int idCategoria, String descripcion, int frecuencia, String observaciones) {
        if (idEstudiante <= 0) {
            return "ID del estudiante no válido.";
        }
        if (idCategoria <= 0) {
            return "Debe seleccionar una categoria.";
        }

        if (descripcion == null || descripcion.isBlank()) {
            return "Debe ingresar una descripción de la conducta analizada.";
        }
        if (frecuencia <= 0) {
            return "Debes seleccionar la frecuencia de la conducta.";
        }
        if (observaciones == null || observaciones.isBlank()) {
            return "Debe ingresar más las observaciones de la conducta.";
        }
        boolean guardado = seguimientoDao.guardarSeguimiento(idEstudiante, idCategoria, descripcion, frecuencia, observaciones);
        return guardado ? "Seguimiento conductual guardado correctamente." : "Error al guardar el seguimiento conductual.";
    }

}
