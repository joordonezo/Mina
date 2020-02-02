/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author GEORGE
 */
public class PanelPersona extends JPanel {

    JLabel lbl0 = new JLabel("Cedula");
    JTextField cedulaTxt = new JTextField(10);

    JLabel lbl1 = new JLabel("Nombre");
    JTextField nombreTxt = new JTextField(10);

    JLabel lbl2 = new JLabel("Apellido");
    JTextField apellidoTxt = new JTextField(10);

    JLabel lbl3 = new JLabel("Fecha Na");
    JDateChooser fechaTxt = new JDateChooser();

    JLabel lbl4 = new JLabel("Genero");
    JComboBox generoTxt = new JComboBox(new Object[]{"M", "F", "X"});
    //JTextField generoTxt = new JTextField(10);

    JLabel lbl5 = new JLabel("Rol");
    JComboBox rolTxt = new JComboBox(new Object[]{"Cochero", "Secretaria"});
    //JTextField rolTxt = new JTextField(10);

    JLabel lbl6 = new JLabel("Usuario");
    JTextField usuarioTxt = new JTextField(10);

    JLabel lbl7 = new JLabel("Clave");
    JPasswordField claveTxt = new JPasswordField(10);
    //JTextField claveTxt = new JTextField(10);

    JLabel lbl8 = new JLabel("Mina");
    JComboBox<String> minaTxt = new JComboBox<String>(contruirMinas());

    JButton guardarPersonaBtn = new JButton("Guardar");

    private String[] contruirMinas() {
        ArrayList<String> minas = Mina.consultarMinas();
        String[] vecMinas = new String[(minas.size() / 2) + 1];

        int i = 0;
        int a = 0, b = 1;
        for (i = 0; i < vecMinas.length - 1; i++) {

            vecMinas[i] = minas.get(a) + " - " + minas.get(b);
            a += 2;
            b += 2;
        }

        vecMinas[i] = "Seleccione";

        return vecMinas;
    }

    public PanelPersona() {
        setLayout(new GridLayout(5, 2, 8, 8));
        guardarPersonaBtn.addActionListener(new gestionaEvento());
        this.setBounds(0, 0, 100, 150);
        add(lbl0);
        add(cedulaTxt);
        add(lbl1);
        add(nombreTxt);
        add(lbl2);
        add(apellidoTxt);
        add(lbl3);
        add(fechaTxt);
        add(lbl4);
        add(generoTxt);
        add(lbl5);
        add(rolTxt);
        add(lbl6);
        add(usuarioTxt);
        add(lbl7);
        add(claveTxt);

        String[] vecMin = contruirMinas();
        add(lbl8);
        add(minaTxt);
        add(guardarPersonaBtn);

    }

    private class gestionaEvento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                char gen = generoTxt.getSelectedItem().toString().charAt(0);
                SimpleDateFormat fe = new SimpleDateFormat("YYYY-MM-dd");
                String fecha = fe.format(fechaTxt.getDate());
                int mina = Integer.parseInt(minaTxt.getSelectedItem().toString().substring(0, 1));

                Persona per = new Persona(Integer.parseInt(cedulaTxt.getText()),
                        nombreTxt.getText(), apellidoTxt.getText(),
                        fecha, gen, "Activo",
                        rolTxt.getSelectedItem().toString(), usuarioTxt.getText(), new String(claveTxt.getPassword()), mina);
                per.agregar();
            } catch (Exception e) {
            }
        }

    }
}
