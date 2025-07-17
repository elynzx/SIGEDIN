package dao.funcionalidad;

import configuration.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.entidades.Reporte;

public class ReporteImp implements ReporteDao {

    private static ReporteImp instancia;
    private Connection conn;

    public ReporteImp() {
        conn = Conexion.estableceConexion();
    }

    public static ReporteImp obtenerInstancia() {
        if (instancia == null) {
            instancia = new ReporteImp();
        }
        return instancia;
    }

    @Override
    public void registrarReporte(Reporte reporte) {
        String sql = "INSERT INTO reporte (id_tipo_reporte, criterio_filtro, id_estudiante, id_aula, generado_por) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reporte.getTipoReporte().getId());
            ps.setString(2, reporte.getCriterioFiltro());
            ps.setInt(3, reporte.getEstudiante().getId());
            ps.setInt(4, reporte.getAula().getId());
            ps.setInt(5, reporte.getUsuario().getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar seguimiento: " + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> obtenerDatosPersonalesBasicos(int idEstudiante) {
        Map<String, Object> datos = new HashMap<>();

        String sqlDatosEstudiante = "SELECT CONCAT(p.nombres, ' ', p.apellidos) AS nombre_estudiante, "
                + "nf.nombre AS nivel_funcional, a.nombre AS nombre_aula, "
                + "CONCAT(pd.nombres, ' ', pd.apellidos) AS nombre_docente "
                + "FROM estudiante e "
                + "JOIN persona p ON e.id_persona = p.id_persona "
                + "LEFT JOIN nivel_funcional nf ON e.id_nivel_funcional = nf.id_nivel "
                + "LEFT JOIN matricula m ON m.id_estudiante = e.id_estudiante AND m.estado = 'activo' "
                + "LEFT JOIN aula a ON m.id_aula = a.id_aula "
                + "LEFT JOIN docente d ON a.id_docente = d.id_docente "
                + "LEFT JOIN persona pd ON d.id_persona = pd.id_persona "
                + "WHERE e.id_estudiante = ?";

        String sqlDiagnosticos = "SELECT d.nombre FROM estudiante_diagnostico ed "
                + "JOIN diagnostico d ON ed.id_diagnostico = d.id_diagnostico WHERE ed.id_estudiante = ?";

        try (PreparedStatement ps = conn.prepareStatement(sqlDatosEstudiante)) {
            ps.setInt(1, idEstudiante);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                datos.put("nombreEstudiante", rs.getString("nombre_estudiante"));
                datos.put("nivelFuncional", rs.getString("nivel_funcional"));
                datos.put("nombreAula", rs.getString("nombre_aula"));
                datos.put("nombreDocente", rs.getString("nombre_docente"));
            }
        } catch (SQLException e) {
            System.out.println("Error info personal: " + e.getMessage());
        }

        try (PreparedStatement ps = conn.prepareStatement(sqlDiagnosticos)) {
            ps.setInt(1, idEstudiante);
            ResultSet rs = ps.executeQuery();
            List<String> listaDiagnosticos = new ArrayList<>();
            while (rs.next()) {
                listaDiagnosticos.add(rs.getString("nombre"));
            }
            datos.put("diagnosticos", listaDiagnosticos);
        } catch (SQLException e) {
            System.out.println("Error diagnósticos: " + e.getMessage());
        }

        return datos;
    }

    @Override
    public Map<String, Object> obtenerDatosConductas(int idEstudiante) {
        Map<String, Object> datos = new HashMap<>();
        List<Map<String, String>> conductas = new ArrayList<>();

        String sqlConductas = "SELECT cp.fecha, tc.nombre AS tipo_conducta, fc.nombre AS funcion, "
                + "cp.descripcion, cp.gravedad "
                + "FROM conducta_problematica cp "
                + "JOIN tipo_conducta tc ON cp.id_tipo_conducta = tc.id_tipo_conducta "
                + "JOIN funcion_comportamiento fc ON cp.id_funcion_comportamiento = fc.id_funcion "
                + "WHERE cp.id_estudiante = ? "
                + "ORDER BY cp.fecha DESC";

        String sqlDatosEstudiante = "SELECT CONCAT(p.nombres, ' ', p.apellidos) AS nombre_estudiante, "
                + "nf.nombre AS nivel_funcional, a.nombre AS nombre_aula, "
                + "CONCAT(pd.nombres, ' ', pd.apellidos) AS nombre_docente "
                + "FROM estudiante e "
                + "JOIN persona p ON e.id_persona = p.id_persona "
                + "LEFT JOIN nivel_funcional nf ON e.id_nivel_funcional = nf.id_nivel "
                + "LEFT JOIN matricula m ON m.id_estudiante = e.id_estudiante AND m.estado = 'activo' "
                + "LEFT JOIN aula a ON m.id_aula = a.id_aula "
                + "LEFT JOIN docente d ON a.id_docente = d.id_docente "
                + "LEFT JOIN persona pd ON d.id_persona = pd.id_persona "
                + "WHERE e.id_estudiante = ?";

        String sqlDiagnosticos = "SELECT d.nombre FROM estudiante_diagnostico ed "
                + "JOIN diagnostico d ON ed.id_diagnostico = d.id_diagnostico "
                + "WHERE ed.id_estudiante = ?";

        try {

            PreparedStatement psConductas = conn.prepareStatement(sqlConductas);
            psConductas.setInt(1, idEstudiante);
            ResultSet rsConductas = psConductas.executeQuery();

            while (rsConductas.next()) {
                Map<String, String> fila = new HashMap<>();

                fila.put("fecha", rsConductas.getDate("fecha").toString());
                fila.put("tipo", rsConductas.getString("tipo_conducta"));
                fila.put("funcion", rsConductas.getString("funcion"));
                fila.put("descripcion", rsConductas.getString("descripcion"));
                fila.put("gravedad", String.valueOf(rsConductas.getInt("gravedad")));
                conductas.add(fila);
            }
            datos.put("conductas", conductas);

            PreparedStatement psDatos = conn.prepareStatement(sqlDatosEstudiante);
            psDatos.setInt(1, idEstudiante);
            ResultSet rsDatos = psDatos.executeQuery();
            if (rsDatos.next()) {
                datos.put("nombreEstudiante", rsDatos.getString("nombre_estudiante"));
                datos.put("nivelFuncional", rsDatos.getString("nivel_funcional"));
                datos.put("nombreAula", rsDatos.getString("nombre_aula"));
                datos.put("nombreDocente", rsDatos.getString("nombre_docente"));
            }

            PreparedStatement psDiag = conn.prepareStatement(sqlDiagnosticos);
            psDiag.setInt(1, idEstudiante);
            ResultSet rsDiag = psDiag.executeQuery();
            List<String> listaDiagnosticos = new ArrayList<>();
            while (rsDiag.next()) {
                listaDiagnosticos.add(rsDiag.getString("nombre"));
            }
            datos.put("diagnosticos", listaDiagnosticos);

        } catch (SQLException e) {
            System.out.println("Error al obtener datos conductas: " + e.getMessage());
        }

        return datos;
    }

    @Override
    public Map<String, Object> obtenerDatosSeguimientoConductual(int idEstudiante) {
        Map<String, Object> datos = new HashMap<>();
        List<Map<String, String>> seguimientos = new ArrayList<>();

        String sql = "SELECT sc.fecha, cc.nombre AS categoria, sc.descripcion_conducta, "
                + "sc.frecuencia, sc.observaciones "
                + "FROM seguimiento_conductual sc "
                + "JOIN categoria_conducta cc ON sc.id_categoria = cc.id_categoria "
                + "WHERE sc.id_estudiante = ? ORDER BY sc.fecha DESC";

        Map<String, Object> datosEstudiante = obtenerDatosPersonalesBasicos(idEstudiante);
        datos.putAll(datosEstudiante);

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEstudiante);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, String> fila = new HashMap<>();
                fila.put("fecha", rs.getDate("fecha").toString());
                fila.put("categoria", rs.getString("categoria"));
                fila.put("descripcion", rs.getString("descripcion_conducta"));
                fila.put("frecuencia", String.valueOf(rs.getInt("frecuencia")));
                fila.put("observaciones", rs.getString("observaciones"));
                seguimientos.add(fila);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener seguimiento: " + e.getMessage());
        }

        datos.put("seguimientos", seguimientos);
        return datos;
    }

    @Override
    public Map<String, Object> obtenerDatosPlanActual(int idEstudiante) {
        Map<String, Object> datos = new HashMap<>();
        List<Map<String, String>> listaPlanes = new ArrayList<>();

        String sql = "SELECT pi.fecha_inicio, tc.nombre AS tipo_conducta, fc.nombre AS funcion, "
                + "pi.objetivo, ei.nombre AS estrategia, pi.aplicado_antes, pi.observaciones "
                + "FROM plan_intervencion pi "
                + "JOIN tipo_conducta tc ON pi.id_tipo_conducta = tc.id_tipo_conducta "
                + "JOIN funcion_comportamiento fc ON pi.id_funcion_comportamiento = fc.id_funcion "
                + "JOIN estrategia_intervencion ei ON pi.id_estrategia = ei.id_estrategia "
                + "WHERE pi.id_estudiante = ? ORDER BY pi.fecha_inicio DESC";

        Map<String, Object> datosEstudiante = obtenerDatosPersonalesBasicos(idEstudiante);
        datos.putAll(datosEstudiante);

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEstudiante);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, String> plan = new HashMap<>();
                plan.put("fechaInicio", rs.getDate("fecha_inicio").toString());
                plan.put("tipoConducta", rs.getString("tipo_conducta"));
                plan.put("funcion", rs.getString("funcion"));
                plan.put("objetivo", rs.getString("objetivo"));
                plan.put("estrategia", rs.getString("estrategia"));
                plan.put("aplicadoAntes", rs.getBoolean("aplicado_antes") ? "Sí" : "No");
                plan.put("observaciones", rs.getString("observaciones"));
                listaPlanes.add(plan);
            }

            datos.put("plan", listaPlanes);

        } catch (SQLException e) {
            System.out.println("Error plan actual: " + e.getMessage());
        }

        return datos;
    }

    @Override
    public Map<String, Object> obtenerDatosAsistencia(int idEstudiante) {
        Map<String, Object> datos = new HashMap<>();
        List<LocalDate> fechasAsistidas = new ArrayList<>();

        // 1) Datos personales: nombre, diagnóstico, aula…
        Map<String, Object> basicos = obtenerDatosPersonalesBasicos(idEstudiante);
        datos.putAll(basicos);

        // 2) Consultar asistencias del mes actual desde la base
        String sql = """
        SELECT fecha
        FROM asistencia
        WHERE id_estudiante = ?
          AND MONTH(fecha) = MONTH(CURDATE())
          AND YEAR(fecha) = YEAR(CURDATE())
          AND DAYOFWEEK(fecha) NOT IN (1,7)
    """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEstudiante);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fechasAsistidas.add(rs.getDate("fecha").toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println("Error obtención asistencia: " + e.getMessage());
        }

        datos.put("fechasAsistidas", fechasAsistidas);
        return datos;
    }

}
