package dao;

import model.entidades.Aula;

public interface DocenteDao {

    int obtenerIdDocenteporPersona(int idPersona);

    Aula obtenerDatosAula(int idDocente);

}
