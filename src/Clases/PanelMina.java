/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author GEORGE
 */
public class PanelMina extends JPanel {

    JLabel lbl0 = new JLabel("id");
    JTextField idTxt = new JTextField("AUTO");

    JLabel lbl1 = new JLabel("Nombre");
    JTextField nombreTxt = new JTextField(10);

    JLabel lbl2 = new JLabel("Nivel de Dificultad");
    JTextField nivelTxt = new JTextField(10);

    JLabel lbl3 = new JLabel("Profundidad");
    JTextField profundidadTxt = new JTextField(10);

    JLabel lbl4 = new JLabel("Inclinacion");
    JTextField inclinacionTxt = new JTextField(10);

    JButton guardarMinaBtn = new JButton("Guardar");

    public PanelMina() {
        guardarMinaBtn.addActionListener(new PanelMina.gestionaEvento());
        setLayout(new GridLayout(3, 2, 8, 8));
        setBounds(0, 0, 500, 500);
        add(lbl0);
        add(idTxt);
        add(lbl1);
        add(nombreTxt);
        add(lbl2);
        add(nivelTxt);
        add(lbl3);
        add(profundidadTxt);
        add(lbl4);
        add(inclinacionTxt);
        add(guardarMinaBtn);
    }

    private class gestionaEvento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            try {
                int idMina = Mina.ultimaIdMina() + 1;
                System.out.println(idMina);
                Mina mina = new Mina(idMina, nombreTxt.getText(), Integer.parseInt(nivelTxt.getText()), Integer.parseInt(profundidadTxt.getText()), Integer.parseInt(inclinacionTxt.getText()));
                mina.agregar();
            } catch (Exception e) {
            }
        }
    }
}
