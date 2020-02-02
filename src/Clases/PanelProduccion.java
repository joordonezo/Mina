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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author GEORGE
 */
public class PanelProduccion extends JPanel {

    private int cedula;
    private int idMina;

    JLabel lbl0 = new JLabel("id Producción");
    JTextField idProducciónTxt = new JTextField("AUTO");

    JLabel lbl1 = new JLabel("Fecha");
    JDateChooser fechaTxt = new JDateChooser();

    JLabel lbl2 = new JLabel("Cantidad");
    JTextField cantidadTxt = new JTextField(10);

    JButton guardarProduccion = new JButton("Guardar");

    public PanelProduccion(int cedula, int idMina) {
        guardarProduccion.addActionListener(new PanelProduccion.gestionaEvento());
        setLayout(new GridLayout(2, 2, 8, 8));
        setBounds(0, 0, 500, 500);
        add(lbl0);
        add(idProducciónTxt);
        add(lbl1);
        add(fechaTxt);
        add(lbl2);
        add(cantidadTxt);
        add(guardarProduccion);
        this.cedula = cedula;
        this.idMina = idMina;
    }

    private class gestionaEvento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                SimpleDateFormat fe = new SimpleDateFormat("YYYY-MM-dd");
                String fecha = fe.format(fechaTxt.getDate());
                Produccion produccion = new Produccion(Produccion.ultimaIdProduccion() + 1, fecha, Integer.parseInt(cantidadTxt.getText()), cedula, idMina);
                produccion.agregar();
            } catch (Exception e) {
            }
        }
    }
}
