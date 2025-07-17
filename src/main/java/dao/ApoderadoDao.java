package dao;

import model.entidades.Apoderado;

public interface ApoderadoDao {

    Apoderado obtenerApoderadoPorDNI(String dni);

    boolean registrarApoderado(Apoderado apoderado);

    Apoderado obtenerApoderadoPorId(int idApoderado);
}
