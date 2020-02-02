/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author HRL56
 */
public class Conexion {

    public Connection java = null;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java = DriverManager.getConnection("jdbc:mysql://localhost/minadb", "root", "");
            //JOptionPane.showMessageDialog(null, "Correcto", "Conexion exitosa!", JOptionPane.INFORMATION_MESSAGE);

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "¡Error, Falló la conexión!", "Conexion fallo", JOptionPane.ERROR_MESSAGE);

        }
        return java;

    }

    // funcion para 
}
