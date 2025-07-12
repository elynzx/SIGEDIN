/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.funcionalidad;

import configuration.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.funcionalidad.catalogo.Antecedente;
import model.funcionalidad.catalogo.CategoriaConducta;
import model.funcionalidad.catalogo.EstrategiaIntervencion;
import model.funcionalidad.catalogo.FuncionComportamiento;
import model.funcionalidad.catalogo.TipoConducta;

public class CatalogoImp implements CatalogoDao {

    private static CatalogoImp instancia;
    private Connection conn;

    public CatalogoImp() {
        conn = Conexion.estableceConexion();
    }

    public static CatalogoImp obtenerInstancia() {
        if (instancia == null) {
            instancia = new CatalogoImp();
        }
        return instancia;
    }


    @Override
    public List<TipoConducta> obtenerTipoConductas() {
        List<TipoConducta> listaTipoConductas = new ArrayList<>();

        String sql = "SELECT id_tipo_conducta, nombre FROM tipo_conducta";

        try (PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                TipoConducta tipoConducta = new TipoConducta(
                        rs.getInt("id_tipo_conducta"),
                        rs.getString("nombre")
                );
                listaTipoConductas.add(tipoConducta);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener tipos de conducta: : " + e.getMessage());
        }
        return listaTipoConductas;
    }


    @Override
    public List<FuncionComportamiento> obtenerFuncionComportamientos() {
        List<FuncionComportamiento> listaFuncionComportamientos = new ArrayList<>();

        String sql = "SELECT id_funcion, nombre FROM funcion_comportamiento";

        try (PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                FuncionComportamiento funcionComportamiento = new FuncionComportamiento(
                        rs.getInt("id_funcion"),
                        rs.getString("nombre")
                );
                listaFuncionComportamientos.add(funcionComportamiento);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de categorias: : " + e.getMessage());
        }
        return listaFuncionComportamientos;
    }


    @Override
    public List<Antecedente> obtenerAntecedentes() {
        List<Antecedente> listaAntecedentes = new ArrayList<>();

        String sql = "SELECT id_antecedente, descripcion FROM antecedente";

        try (PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Antecedente antecedente = new Antecedente(
                        rs.getInt("id_antecedente"),
                        rs.getString("descripcion")
                );
                listaAntecedentes.add(antecedente);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de categorias: : " + e.getMessage());
        }
        return listaAntecedentes;
    }

    @Override
    public List<CategoriaConducta> ObtenerCategoriaConductas() {
        List<CategoriaConducta> listaCategorias = new ArrayList<>();

        String sql = "SELECT id_categoria, nombre FROM categoria_conducta";

        try (PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                CategoriaConducta categoria = new CategoriaConducta(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre")
                );
                listaCategorias.add(categoria);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de categorias: : " + e.getMessage());
        }
        return listaCategorias;

    }

    @Override
    public List<EstrategiaIntervencion> obtenerEstrategiasIntervencion() {
        List<EstrategiaIntervencion> listaEstrategias = new ArrayList<>();
        String sql = "SELECT id_estrategia, nombre FROM estrategia_intervencion";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                listaEstrategias.add(new EstrategiaIntervencion(rs.getInt("id_estrategia"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener estrategias de intervención: " + e.getMessage());
        }
        return listaEstrategias;
    }

}
