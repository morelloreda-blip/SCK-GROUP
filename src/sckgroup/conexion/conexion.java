package sckgroup.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que se encarga de la conexion a la base de datos MySQL
 * del proyecto SCK-GROUP
 * @author Cindy Johana Ramirez
 * @version 1.0
 */
public class Conexion {

    // Datos para conectarse a la base de datos
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
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }
        return con;
    }
}
