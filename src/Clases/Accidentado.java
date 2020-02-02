/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author GEORGE
 */
public class Accidentado {

    private static Connection datos;
    private static Conexion obj = new Conexion();

    private int idAccidentado;
    private String fecha;
    private String descripcion;
    private int cedula;

    public Accidentado(int idAccidentado, String fecha, String descripcion, int cedula) {

        this.idAccidentado = idAccidentado;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.cedula = cedula;

    }

    public void agregar() {

        datos = obj.conectar();

        String consulta = "INSERT INTO accidentado VALUES(" + idAccidentado + ",'" + fecha + "','" + descripcion + "'," + cedula + ")";

        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            st.executeUpdate(consulta);
            datos.close();
            JOptionPane.showMessageDialog(null, "Agregado correctamente");

        } catch (HeadlessException | SQLException exx) {
            JOptionPane.showMessageDialog(null, "¡Error, No se pudo agregar!", "falló la inserción", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static ArrayList<String> consultar(int idAccidentado) {
        ArrayList<String> columnas = new ArrayList<String>();
        datos = obj.conectar();

        String consulta = "SELECT * FROM persona WHERE idAccidentado = " + idAccidentado;
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            if (rs.next()) {
                columnas.add(rs.getString("fecha"));
                columnas.add(rs.getString("descricion"));
                columnas.add(rs.getString("cedula"));

                JOptionPane.showMessageDialog(null, "Registro encontrado", "Correcto", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Registro no encontrado", "Incorrecto", JOptionPane.ERROR_MESSAGE);
            }
            datos.close();

        } catch (HeadlessException | SQLException exx) {
            JOptionPane.showMessageDialog(null, "¡Error, No se pudo encontar!", "falló la consulta", JOptionPane.ERROR_MESSAGE);
        }
        return columnas;
        //insert into tabla Values(valores)
    }

    public void modificar() {
        datos = obj.conectar();

        String consulta = "UPDATE accidentado SET fecha = '" + this.fecha + "', descripcion ='" + this.descripcion + "' WHERE idAccidentado =" + idAccidentado;

        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            st.executeUpdate(consulta);
            datos.close();
            JOptionPane.showMessageDialog(null, "Actualizado correctamente");

        } catch (HeadlessException | SQLException exx) {
            JOptionPane.showMessageDialog(null, "¡Error, No se pudo actualizar!", "falló la actulización", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ArrayList<String> consultarIdAccidentado() {
        ArrayList<String> columnas = new ArrayList<String>();

        datos = obj.conectar();

        String consulta = "SELECT idAccidentado FROM accidentado ";
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                columnas.add(rs.getString("idAccidentado"));

            }

//                JOptionPane.showMessageDialog(null, "Registro encontrado", "Correcto", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                JOptionPane.showMessageDialog(null, "Registro no encontrado", "Incorrecto", JOptionPane.ERROR_MESSAGE);
//            }
            datos.close();

        } catch (HeadlessException | SQLException exx) {
            JOptionPane.showMessageDialog(null, "¡Error, No se pudo consultar!", "falló la consulta", JOptionPane.ERROR_MESSAGE);
        }
        return columnas;

    }

    public static int ultimoIdAccidente() {
        String res = "";
        datos = obj.conectar();

        String consulta = "SELECT idAccidentado FROM accidentado ORDER BY idAccidentado ASC";
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                res = rs.getString("idAccidentado");

            }

//                JOptionPane.showMessageDialog(null, "Registro encontrado", "Correcto", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                JOptionPane.showMessageDialog(null, "Registro no encontrado", "Incorrecto", JOptionPane.ERROR_MESSAGE);
//            }
            datos.close();

        } catch (HeadlessException | SQLException exx) {
            JOptionPane.showMessageDialog(null, "¡Error, No se pudo consultar!", "falló la consulta", JOptionPane.ERROR_MESSAGE);
        }
        return Integer.parseInt(res);

    }

    public static ArrayList consultarAccidentadoPorMina(int idMina) {
        ArrayList columnas = new ArrayList();
        datos = obj.conectar();
        String consulta;
        //System.out.println(idMina);
        if (idMina == 0) {
            consulta = "SELECT * FROM accidentado,persona WHERE accidentado.cedula = persona.cedula ORDER BY persona.idMina ASC";

        } else {
            consulta = "SELECT * FROM accidentado,persona WHERE accidentado.cedula = persona.cedula  AND persona.idMina = " + idMina;
        }
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                ArrayList persona = Persona.consultar(Integer.parseInt(rs.getString("cedula")));

                columnas.add(new Object[]{rs.getString("idAccidentado"), rs.getString("fecha"), rs.getString("descripcion"), rs.getString("cedula") + "-" + persona.get(0).toString() + " " + persona.get(1).toString()});

//                JOptionPane.showMessageDialog(null, "Registro encontrado", "Correcto", JOptionPane.INFORMATION_MESSAGE);
            }
//            else {
//                JOptionPane.showMessageDialog(null, "Registro no encontrado", "Incorrecto", JOptionPane.ERROR_MESSAGE);
//            }
            datos.close();

        } catch (HeadlessException | SQLException exx) {
            JOptionPane.showMessageDialog(null, "¡Error, No se pudo encontar!", "falló la consulta", JOptionPane.ERROR_MESSAGE);
        }
        return columnas;
        //insert into tabla Values(valores)
    }

    public static ArrayList consultarAccidentadoPorPersona(int cedula) {
        ArrayList columnas = new ArrayList();
        datos = obj.conectar();
        String consulta;
        //System.out.println(idMina);

        consulta = "SELECT * FROM accidentado WHERE accidentado.cedula = " + cedula + " ORDER BY accidentado.fecha ASC";

        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                ArrayList persona = Persona.consultar(Integer.parseInt(rs.getString("cedula")));

                columnas.add(new Object[]{rs.getString("idAccidentado"), rs.getString("fecha"), rs.getString("descripcion"), rs.getString("cedula") + "-" + persona.get(0).toString() + " " + persona.get(1).toString()});

//                JOptionPane.showMessageDialog(null, "Registro encontrado", "Correcto", JOptionPane.INFORMATION_MESSAGE);
            }
//            else {
//                JOptionPane.showMessageDialog(null, "Registro no encontrado", "Incorrecto", JOptionPane.ERROR_MESSAGE);
//            }
            datos.close();

        } catch (HeadlessException | SQLException exx) {
            JOptionPane.showMessageDialog(null, "¡Error, No se pudo encontar!", "falló la consulta", JOptionPane.ERROR_MESSAGE);
        }
        return columnas;
        //insert into tabla Values(valores)
    }

}
