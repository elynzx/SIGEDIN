/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utillities;

import java.awt.Font;
import java.io.InputStream;

/**
 *
 * @author rpasc
 */
public class FuenteUtil {

    public static Font cargarFuente(String nombreArchivo, float tamaño) {
        try {
            InputStream is = FuenteUtil.class.getResourceAsStream("/resources/fonts/" + nombreArchivo);
            Font fuente = Font.createFont(Font.TRUETYPE_FONT, is);
            return fuente.deriveFont(tamaño);
        } catch (Exception e) {
            System.out.println("Error al cargar fuente personalizada: " + e.getMessage());
            return new Font("SansSerif", Font.PLAIN, (int) tamaño); // Fuente por defecto en caso de error
        }
    }


    public static final Font TITULO = cargarFuente("Poppins-Bold.ttf", 20f);
    public static final Font DETALLE = cargarFuente("Poppins-Regular.ttf", 16f);
    public static final Font SUBTITULO = cargarFuente("Poppins-Regular.ttf", 16f);
    public static final Font CUERPO = cargarFuente("Poppins-Regular.ttf", 14f);
}
