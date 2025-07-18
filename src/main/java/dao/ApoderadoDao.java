package dao;

import model.entidades.Apoderado;

public interface ApoderadoDao {

    Apoderado obtenerApoderadoPorDNI(String dni);

    Apoderado obtenerApoderadoPorId(int idApoderado);

    void registrarApoderado(Apoderado apoderado);

    void actualizarApoderado(Apoderado apoderado);

}
