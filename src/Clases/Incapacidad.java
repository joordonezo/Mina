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
public class Incapacidad {

    private static Connection datos;
    private static Conexion obj = new Conexion();

    private int idIncapacidad;
    private String fecha;
    private String tipo;
    private String fechaFin;
    private String reemplazo;
    private int cedula;

    public Incapacidad(int idIncapacidad, String fecha, String tipo, String fechaFin, String reemplazo, int cedula) {

        this.idIncapacidad = idIncapacidad;
        this.fecha = fecha;
        this.tipo = tipo;
        this.fechaFin = fechaFin;
        this.reemplazo = reemplazo;
        this.cedula = cedula;
    }

    public void agregar() {

        datos = obj.conectar();

        String consulta = "INSERT INTO incapacidad VALUES(" + idIncapacidad + ",'" + fecha + "','" + tipo + "','" + fechaFin + "','" + reemplazo + "'," + cedula + ")";

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

    public static ArrayList<String> consultar(int idIncapacidad) {
        ArrayList<String> columnas = new ArrayList<String>();
        datos = obj.conectar();

        String consulta = "SELECT * FROM incapacidad WHERE idIncapacidad = " + idIncapacidad;
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            if (rs.next()) {
                columnas.add(rs.getString("idIncapacidad"));
                columnas.add(rs.getString("fecha"));
                columnas.add(rs.getString("tipo"));
                columnas.add(rs.getString("fechaFin"));
                columnas.add(rs.getString("reemplazo"));
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

        String consulta = "UPDATE incapacidad SET fecha = '" + this.fecha + "', tipo ='" + this.tipo + "', fechaFin ='" + this.fechaFin + "', reemplazo =" + this.reemplazo + ", cedula =" + this.cedula + " WHERE idIncapacidad =" + idIncapacidad;

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

    public static ArrayList<String> consultarIdIncapacidad() {
        ArrayList<String> columnas = new ArrayList<String>();

        datos = obj.conectar();

        String consulta = "SELECT idIncapacidad FROM incapacidad ";
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                columnas.add(rs.getString("idIncapacidad"));

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

    public static int ultimoIdIncapacidad() {
        String res = "";
        datos = obj.conectar();

        String consulta = "SELECT idIncapacidad FROM incapacidad ORDER BY idIncapacidad ASC";
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                res = rs.getString("idIncapacidad");

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

    public static ArrayList consultarIncapacitadoPorMina(int idMina) {
        ArrayList columnas = new ArrayList();
        datos = obj.conectar();
        String consulta;
        System.out.println(idMina);
        if (idMina == 0) {
            consulta = "SELECT * FROM incapacidad,persona WHERE incapacidad.cedula = persona.cedula ORDER BY persona.idMina ASC";

        } else {
            consulta = "SELECT * FROM incapacidad,persona WHERE incapacidad.cedula = persona.cedula  AND persona.idMina = " + idMina;
        }
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {  
                ArrayList persona = Persona.consultar(Integer.parseInt(rs.getString("cedula")));
               

                columnas.add(new Object[]{rs.getString("idIncapacidad"),
                    rs.getString("fecha"),
                    rs.getString("tipo"),
                    rs.getString("fechaFin"),
                    rs.getString("reemplazado"),
                    rs.getString("cedula") + "-" + persona.get(0).toString() + " " + persona.get(1).toString()});

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

    public static ArrayList consultarIncapacidadPorPersona(int cedula) {
        ArrayList columnas = new ArrayList();
        datos = obj.conectar();
        String consulta;
        //System.out.println(idMina);

        consulta = "SELECT * FROM incapacidad WHERE incapacidad.cedula = " + cedula + " ORDER BY incapacidad.fechaFin ASC";

        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                ArrayList persona = Persona.consultar(Integer.parseInt(rs.getString("cedula")));
                
                columnas.add(new Object[]{rs.getString("idIncapacidad"), rs.getString("fecha"), rs.getString("tipo"), rs.getString("fechaFin"), rs.getString("reemplazado"), rs.getString("cedula") + "-" + persona.get(0).toString() + " " + persona.get(1).toString()});

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
