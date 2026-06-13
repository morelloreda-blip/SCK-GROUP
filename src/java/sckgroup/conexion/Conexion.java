package sckgroup.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase que se encarga de la conexion a la base de datos MySQL
 * del proyecto SCK-GROUP (modulo web)
 * @author Cindy Johana Ramirez
 * @version 1.0
 */
public class Conexion {

    static String url = "jdbc:mysql://localhost:3306/sck_group_db";
    static String usuario = "root";
    static String clave = "";

    /**
     * Metodo que conecta con la base de datos
     */
    public static Connection conectar() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, clave);
        } catch (Exception e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }
        return con;
    }
}