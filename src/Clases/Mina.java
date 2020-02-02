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
public class Mina {

    private static Connection datos;
    private static Conexion obj = new Conexion();

    private int idMina;
    private String nombre;
    private int nivelDificultad;
    private double profundidad;
    private double inclinacion;

    public Mina(int idMina, String nombre, int nivelDificultad, int profundidad, int inclinacion) {
        this.idMina = idMina;
        this.nombre = nombre;
        this.nivelDificultad = nivelDificultad;
        this.profundidad = profundidad;
        this.inclinacion = inclinacion;

    }

    public void agregar() {

        datos = obj.conectar();

        String consulta = "INSERT INTO mina VALUES(" + idMina + ",'" + nombre + "'," + nivelDificultad + "," + profundidad + "," + inclinacion + ")";

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

    public static ArrayList<String> consultar(int idMina) {
        ArrayList<String> columnas = new ArrayList<String>();
        datos = obj.conectar();

        String consulta = "SELECT * FROM mina WHERE idMina = " + idMina;
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            if (rs.next()) {
                columnas.add(rs.getString("nombre"));
                columnas.add(rs.getString("nivelDificultad"));
                columnas.add(rs.getString("profundidad"));
                columnas.add(rs.getString("inclinacion"));

                //JOptionPane.showMessageDialog(null, "Registro encontrado", "Correcto", JOptionPane.INFORMATION_MESSAGE);
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

        String consulta = "UPDATE mina SET nombre = '" + this.nombre + "', nivelDificultad =" + this.nivelDificultad + ", profundidad =" + this.profundidad + ", inclinacion =" + this.inclinacion + " WHERE idMina =" + idMina;

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

    public static ArrayList<String> consultarIdMina() {
        ArrayList<String> columnas = new ArrayList<String>();

        datos = obj.conectar();

        String consulta = "SELECT idMina FROM mina ";
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                columnas.add(rs.getString("idMina"));
//                columnas.add(rs.getString("nombre"));

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

    public static ArrayList<String> consultarMinas() {
        ArrayList<String> columnas = new ArrayList<String>();

        datos = obj.conectar();

        String consulta = "SELECT idMina, nombre FROM mina ";
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                columnas.add(rs.getString("idMina"));
                columnas.add(rs.getString("nombre"));

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

    public static int ultimaIdMina() {
        String res = "0";
        datos = obj.conectar();

        String consulta = "SELECT idMina FROM mina ORDER BY idMina ASC";
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                res = rs.getString("idMina");

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
}
