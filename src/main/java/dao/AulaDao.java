package dao;

import java.util.List;
import model.entidades.Aula;

public interface AulaDao {

    public int contarVacantesDisponibles();

    public int contarAulasConVacantes();

    public List<Aula> obtenerTodasLasAulas();

    List<Aula> filtrarAulasPorNivelYDiagnostico(int idNivelFuncional, List<Integer> idsDiagnosticos);

    public List<Aula> obtenerAulasPorNivelSinFiltrar(int idNivelFuncional);
}
