package dao;
import java.util.ArrayList;
import model.funcionalidad.ListaAulas;


public interface SecretariaDao {
    
    int obtenerIdSecretariaporPersona (int idPersona);
    ArrayList<ListaAulas> obtenerListaAulas();
    
}
