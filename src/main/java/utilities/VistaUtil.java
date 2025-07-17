/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class VistaUtil {

    public static void cambiarVistaPanel(JPanel contenedorMenu, Component vista) {
        contenedorMenu.removeAll();
        contenedorMenu.add(vista);
        SwingUtilities.updateComponentTreeUI(contenedorMenu);
    }

    public static void resaltarOpcionMenu(JPanel opcionMenu, JPanel... otrasOpciones) {
        Color azul = new Color(23,64,112);
        opcionMenu.setBackground(azul);
        for (JPanel opcion : otrasOpciones) {
            opcion.setBackground(Color.white);
        }
    }

    public static void mostrarVista(JFrame vista) {
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public static boolean modalConfirmarSalir(JFrame vista) {
        Object[] opciones = {"Salir", "Cancelar"};
        int opcion = JOptionPane.showOptionDialog(
                vista,
                "¿Deseas cerrar sesión?",
                "Confirmación",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[1]
        );
        return opcion == 0;
    }

}
