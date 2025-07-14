/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.entidades.Persona;
import model.entidades.Usuario;
import model.funcionalidad.ListaAulas;
import model.funcionalidad.ListaUsuarios;

/**
 *
 * @author rpasc
 */
public interface AdministradorDao {

    int obtenerIdAdministradorporPersona(int idPersona);
    ArrayList<ListaAulas> obtenerListaAulas();
    ArrayList<ListaUsuarios> obtenerListaUsuarios();
    void registrarCambio(String tipo_dato,String dato, int id);
    boolean VerificarDni(String dni);
    boolean registrarPersona(Usuario usuario);
    int obtenerIdPersona(Usuario usuario);
    boolean registrar(Usuario usuario);
    
}
