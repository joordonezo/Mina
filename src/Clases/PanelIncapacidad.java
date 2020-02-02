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
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author GEORGE
 */
public class PanelIncapacidad extends JPanel {

    private int cedula;
    private boolean bandera = true;

    JLabel lbl0 = new JLabel("id Incapacidad");
    JTextField idIncapacidadTxt = new JTextField("AUTO");

    JLabel lbl1 = new JLabel("Fecha Inicio");
    JDateChooser fechaIniTxt = new JDateChooser();

    JLabel lbl2 = new JLabel("Tipo");
    JComboBox tipoTxt = new JComboBox(new Object[]{"Tipo 1", "Tipo 2"});

    JTextField fechaFinT = new JTextField(10);

    JCheckBox porDiasCheck = new JCheckBox("Dias");

    JLabel lbl3 = new JLabel("Fecha Fin");
    JDateChooser fechaFinTxt = new JDateChooser();

    JLabel lbl4 = new JLabel("Reemplazo");
    JComboBox reemplazoTxt = new JComboBox(new Object[]{"SI", "NO"});

    JButton guardarIncapacidad = new JButton("Guardar");

    public PanelIncapacidad(int cedula) {
        guardarIncapacidad.addActionListener(new PanelIncapacidad.gestionaEvento());
        porDiasCheck.addActionListener(new PanelIncapacidad.gestionaEvento1());

        setLayout(new GridLayout(3, 2, 3, 3));
        setBounds(0, 0, 500, 500);
        add(lbl0);
        add(idIncapacidadTxt);
        add(lbl1);
        add(fechaIniTxt);
        add(lbl2);
        add(tipoTxt);
        add(porDiasCheck);
        add(lbl3);
        add(fechaFinTxt);
        add(fechaFinT);
        add(lbl4);
        add(reemplazoTxt);
        add(guardarIncapacidad);

        fechaFinT.setVisible(false);
        this.cedula = cedula;
    }

    public static String sumarDiasAFecha(String fecha, int dias) {
        if (dias == 0) {
            return fecha;
        }

        String[] f = fecha.split("-");
        Calendar calendar = Calendar.getInstance();
        //calendar.setTime(new Date(Integer.parseInt(f[0]), Integer.parseInt(f[1]), Integer.parseInt(f[2])));
        calendar.set(Integer.parseInt(f[0]), Integer.parseInt(f[1]) - 1, Integer.parseInt(f[2]));

        calendar.add(Calendar.DAY_OF_MONTH, dias);
        SimpleDateFormat fe = new SimpleDateFormat("YYYY-MM-dd");
        return fe.format(calendar.getTime());

    }

    private class gestionaEvento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {

                SimpleDateFormat fe = new SimpleDateFormat("YYYY-MM-dd");
                String fecha = fe.format(fechaIniTxt.getDate());
                String fechaFin;

                if (bandera) {
                    fechaFin = fe.format(fechaFinTxt.getDate());
                } else {

                    fechaFin = sumarDiasAFecha(fecha, Integer.parseInt(fechaFinT.getText()));
                }

           

                Incapacidad incapacidad = new Incapacidad(Incapacidad.ultimoIdIncapacidad() + 1, fecha, tipoTxt.getSelectedItem().toString(), fechaFin, reemplazoTxt.getSelectedItem().toString(), cedula);
                incapacidad.agregar();

            } catch (Exception e) {
            }

        }

    }

    private class gestionaEvento1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {

                if (porDiasCheck.isSelected()) {
                    lbl3.setVisible(false);
                    //lbl5.setVisible(true);
                    fechaFinT.setVisible(true);
                    fechaFinTxt.setVisible(false);
                    bandera = false;
                } else {
                    // lbl5.setVisible(false);

                    fechaFinT.setVisible(false);
                    fechaFinTxt.setVisible(true);
                    lbl3.setVisible(true);
                    bandera = true;

                }
            } catch (Exception e) {
            }
        }
    }
}
