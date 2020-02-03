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
public class Produccion {

    private static Connection datos;
    private static Conexion obj = new Conexion();

    private int idProduccion;
    private String fecha;
    private int cantidad;
    private int cedula;
    private int idMina;

    public Produccion(int idProduccion, String fecha, int cantidad, int cedula, int idMina) {

        this.idProduccion = idProduccion;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.cedula = cedula;
        this.idMina = idMina;
    }

    public void agregar() {

        datos = obj.conectar();

        String consulta = "INSERT INTO produccion VALUES(" + idProduccion + ",'" + fecha + "'," + cantidad + "," + cedula + "," + idMina + ")";

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

    public static ArrayList<String> consultar(int idProduccion) {
        ArrayList<String> columnas = new ArrayList<String>();
        datos = obj.conectar();

        String consulta = "SELECT * FROM produccion WHERE idProduccion = " + idProduccion;
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            if (rs.next()) {
                columnas.add(rs.getString("fecha"));
                columnas.add(rs.getString("cantidad"));
                columnas.add(rs.getString("cedula"));
                columnas.add(rs.getString("idMina"));

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

    public static ArrayList consultarPorMina(int idMina) {
        ArrayList columnas = new ArrayList();
        datos = obj.conectar();
        String consulta;
        System.out.println(idMina);
        if (idMina == 0) {
            consulta = "SELECT * FROM produccion ORDER BY produccion.idMina ASC";

        } else {
            consulta = "SELECT * FROM produccion WHERE produccion.idMina = " + idMina;
        }
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                ArrayList mina = Mina.consultar(Integer.parseInt(rs.getString("idMina")));
                ArrayList persona = Persona.consultar(Integer.parseInt(rs.getString("cedula")));

                columnas.add(new Object[]{rs.getString("fecha"), rs.getString("cantidad"), rs.getString("cedula") + "-" + persona.get(0).toString() + " " + persona.get(1).toString(), rs.getString("idMina") + "-" + mina.get(0).toString()});
//                columnas.add(rs.getString("fecha"));
//                columnas.add(rs.getString("cantidad"));
//                columnas.add(rs.getString("cedula"));
//                columnas.add(rs.getString("idMina"));

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

    public static ArrayList consultarPorCedula(int cedula) {
        ArrayList columnas = new ArrayList();
        datos = obj.conectar();
        String consulta = "SELECT * FROM produccion WHERE produccion.cedula = " + cedula + " ORDER BY produccion.fecha ASC";

        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                ArrayList mina = Mina.consultar(Integer.parseInt(rs.getString("idMina")));
                ArrayList persona = Persona.consultar(Integer.parseInt(rs.getString("cedula")));

                columnas.add(new Object[]{rs.getString("fecha"), rs.getString("cantidad"), rs.getString("cedula") + "-" + persona.get(0).toString() + " " + persona.get(1).toString(), rs.getString("idMina") + "-" + mina.get(0).toString()});
//                columnas.add(rs.getString("fecha"));
//                columnas.add(rs.getString("cantidad"));
//                columnas.add(rs.getString("cedula"));
//                columnas.add(rs.getString("idMina"));

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

    public void modificar() {
        datos = obj.conectar();

        String consulta = "UPDATE produccion SET fecha = '" + this.fecha + "', cantidad =" + this.cantidad + ", cedula =" + this.cedula + ", idMina =" + this.idMina + " WHERE idProduccion =" + idProduccion;

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

    public static ArrayList<String> consultarIdProduccion() {
        ArrayList<String> columnas = new ArrayList<String>();

        datos = obj.conectar();

        String consulta = "SELECT idProduccion FROM produccion ";
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                columnas.add(rs.getString("idProduccion"));

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

    public static int ultimaIdProduccion() {
        String res = "0";
        datos = obj.conectar();

        String consulta = "SELECT idProduccion FROM produccion ORDER BY idProduccion ASC";
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                res = rs.getString("idProduccion");

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
