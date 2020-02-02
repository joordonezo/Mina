/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.toedter.calendar.*;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author GEORGE
 */
public class PanelAccidentado extends JPanel {

    private int cedula;

    JLabel lbl0 = new JLabel("id Accidente");
    JTextField idAccidenteTxt = new JTextField("AUTO");

    JLabel lbl1 = new JLabel("Fecha");
    JDateChooser fechaTxt = new JDateChooser();
    //JFormattedTextField fechaTxt = new JFormattedTextField();

    JButton guardarAccidente = new JButton("Guardar");

    JLabel lbl2 = new JLabel("Descripción");
    JTextArea desripcionTxt = new JTextArea(10, 20);

    public PanelAccidentado(int cedula) {
        guardarAccidente.addActionListener(new PanelAccidentado.gestionaEvento());
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 2, 8, 8));

        //fechaTxt.setValue(new Date(20201224));
        setBounds(0, 0, 500, 500);
        panel1.add(lbl0);
        panel1.add(idAccidenteTxt);
        panel1.add(lbl1);
        panel1.add(fechaTxt);
        panel1.add(guardarAccidente);

        add(lbl2);
        add(desripcionTxt);
        add(panel1);

        this.cedula = cedula;
    }

    private class gestionaEvento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            try {

                SimpleDateFormat fe = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = fe.format(fechaTxt.getDate());
                Accidentado accidente = new Accidentado(Accidentado.ultimoIdAccidente() + 1, fecha, desripcionTxt.getText(), cedula);
                accidente.agregar();
                int opc = JOptionPane.showConfirmDialog(null, "¿Desea agegar una incapacidad?", "Seleccione", WIDTH);
                if (opc == 0) {
                    PanelIncapacidad incapacitado = new PanelIncapacidad(cedula);
                    JOptionPane.showMessageDialog(null, incapacitado);
                }
            } catch (Exception e) {
            }
        }
    }
}
