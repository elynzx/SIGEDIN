package controller.secretaria;

import dao.EstudianteDao;
import model.entidades.Estudiante;

import java.util.List;

public class EstudianteMatriculaCtrl {

    private final EstudianteDao estudianteDao;

    public EstudianteMatriculaCtrl(EstudianteDao estudianteDao) {
        this.estudianteDao = estudianteDao;
    }

    public List<Estudiante> obtenerEstudiantesParaVistaSecretaria() {
        return estudianteDao.listarEstudiantesParaVistaMatricula();
    }
}
