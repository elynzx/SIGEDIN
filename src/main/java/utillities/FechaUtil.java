/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utillities;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author rpasc
 */
public class FechaUtil {


    public static void mostrarDiaHora(JLabel lbFecha) {
        SimpleDateFormat s = new SimpleDateFormat("EEEE dd 'de' MMMM 'del' YYYY HH:mm:ss");
        Timer t = new Timer(1000, e -> {
            Date d = new Date();
            String fechaHora = s.format(d).toUpperCase();
            lbFecha.setText(fechaHora);
        });
        t.start();
    }

    public static void mostrarDia(JLabel lbDia) {
        String fecha = new SimpleDateFormat("EEEE dd 'de' MMMM 'del' YYYY").format(new Date()).toUpperCase();
        lbDia.setText(fecha);
    }

}


//    public void mostrarFechaCompleta(JLabel lbFecha) {
//        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        Timer t = new Timer(1000, e -> {
//            Date d = new Date();
//            String fechaHora = s.format(d);
//            lbFecha.setText(fechaHora);
//        });
//        t.start();
//    }
//
//    public void mostrarFechaHora(JLabel lbFecha, JLabel lbHora) {
//        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
//        SimpleDateFormat h = new SimpleDateFormat("HH:mm:ss");
//        Timer t = new Timer(1000, e -> {
//            Date d = new Date();
//            lbFecha.setText(f.format(d));
//            lbHora.setText(h.format(d));
//
//        });
//        t.start();
//    }
//
//    public void mostrarFecha(JLabel lbFecha) {
//        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
//        Timer t = new Timer(1000, e -> {
//            Date d = new Date();
//            lbFecha.setText(f.format(d));
//        });
//        t.start();
//    }