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
public class Persona {

    private static Connection datos;
    private static Conexion obj = new Conexion();

    private int cedula;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private char genero;
    private String estado;
    private String rol;
    private String usuario;
    private String clave;
    private int idMina;

    public Persona(int cedula, String nombre, String apellido, String fechaNacimiento, char genero, String estado, String rol, String usuario, String clave, int idMina) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.estado = estado;
        this.rol = rol;
        this.usuario = usuario;
        this.clave = clave;
        this.idMina = idMina;

    }

    public void agregar() {

        datos = obj.conectar();

        String consulta = "INSERT INTO persona VALUES(" + cedula + ",'" + nombre + "','" + apellido + "','" + fechaNacimiento + "','" + genero + "','" + estado + "','" + rol + "','" + usuario + "','" + clave + "'," + idMina + ")";

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

    public static ArrayList<String> consultar(int cedula) {
        ArrayList<String> columnas = new ArrayList<String>();
        datos = obj.conectar();

        String consulta = "SELECT * FROM persona WHERE cedula = " + cedula;
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            if (rs.next()) {
                columnas.add(rs.getString("nombre"));
                columnas.add(rs.getString("apellido"));
                columnas.add(rs.getString("fechaNacimiento"));
                columnas.add(rs.getString("genero"));
                columnas.add(rs.getString("estado"));
                columnas.add(rs.getString("rol"));
                columnas.add(rs.getString("usuario"));
                columnas.add(rs.getString("clave"));
                columnas.add(rs.getString("idMina"));

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

    public static ArrayList consultarPorNombre(String nombre) {
        ArrayList columnas = new ArrayList();
        datos = obj.conectar();

        String consulta = "SELECT * FROM persona WHERE nombre LIKE '%" + nombre + "%' OR apellido LIKE '%" + nombre + "%' OR cedula LIKE '%" + nombre + "%'";
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                ArrayList mina = Mina.consultar(Integer.parseInt(rs.getString("idMina")));

                columnas.add(new Object[]{rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"),
                    rs.getString("fechaNacimiento"), rs.getString("genero"), rs.getString("estado"), rs.getString("rol"),
                    rs.getString("usuario"), "***", rs.getString("idMina") + "-" + mina.get(0).toString()});

                //JOptionPane.showMessageDialog(null, "Registro encontrado", "Correcto", JOptionPane.INFORMATION_MESSAGE);
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

        String consulta = "UPDATE persona SET nombre = '" + this.nombre + "', apellido ='" + this.apellido + "', fechaNacimiento ='" + this.fechaNacimiento + "', genero ='" + this.genero + "', estado ='" + this.estado + "', rol ='" + this.rol + "', usuario ='" + this.usuario + "', clave ='" + this.clave + "', idMina =" + this.idMina + " WHERE cedula =" + cedula;

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

    public static ArrayList<String> consultarIdPersona() {
        ArrayList<String> columnas = new ArrayList<String>();

        datos = obj.conectar();

        String consulta = "SELECT cedula FROM persona ";
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                columnas.add(rs.getString("cedula"));

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

    public static ArrayList<String> consultarIdPersonaLogin(String usuario, String clave) {
        ArrayList<String> columnas = new ArrayList<String>();

        datos = obj.conectar();

        String consulta = "SELECT cedula, rol FROM persona WHERE usuario = '" + usuario + "' AND clave = '" + clave + "'";
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                columnas.add(rs.getString("cedula"));
                columnas.add(rs.getString("rol"));

            }

//                JOptionPane.showMessageDialog(null, "Registro encontrado", "Correcto", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                JOptionPane.showMessageDialog(null, "Registro no encontrado", "Incorrecto", JOptionPane.ERROR_MESSAGE);
//            }
            datos.close();

        } catch (HeadlessException | SQLException exx) {
            JOptionPane.showMessageDialog(null, "¡Error, No se pudo consultar! Login", "falló la consulta", JOptionPane.ERROR_MESSAGE);
        }
        return columnas;

    }

    public static ArrayList consultarPersonaporMina(int idMina) {
        ArrayList columnas = new ArrayList();

        datos = obj.conectar();
        String consulta;
        if (idMina == 0) {
            consulta = "SELECT * FROM persona ORDER BY persona.idMina ASC";
        } else {
            consulta = "SELECT * FROM persona WHERE idMina = " + idMina;
        }
        ResultSet rs;
        Statement st;
        try {
            st = (Statement) obj.java.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                ArrayList mina = Mina.consultar(Integer.parseInt(rs.getString("idMina")));

                columnas.add(new Object[]{rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"),
                    rs.getString("fechaNacimiento"), rs.getString("genero"), rs.getString("estado"), rs.getString("rol"),
                    rs.getString("usuario"), "***", rs.getString("idMina")+"-"+mina.get(0).toString()});
//                columnas.add(rs.getString("cedula"));
//                columnas.add(rs.getString("nombre"));
//                columnas.add(rs.getString("apellido"));
//                columnas.add(rs.getString("fechaNacimiento"));
//                columnas.add(rs.getString("genero"));
//                columnas.add(rs.getString("estado"));
//                columnas.add(rs.getString("rol"));
//                columnas.add(rs.getString("usuario"));
//                columnas.add(rs.getString("clave"));
//                columnas.add(rs.getString("idMina"));

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
}
