package sckgroup.dao;

import sckgroup.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase que tiene los metodos para registrar y consultar los abonos
 * que hacen los clientes a su credito (RF9)
 * @author Cindy Johana Ramirez
 * @version 1.0
 */
public class AbonoDao {

    /**
     * Metodo para registrar un abono de un cliente
     */
    public void registrarAbono(String numeroIdentificacion, double valorAbono,
                                String fecha, String realizoAbono) {
        try {
            Connection con = Conexion.conectar();

            String sql = "INSERT INTO abonos (numero_identificacion, valor_abono, "
                    + "fecha_abono, realizo_abono) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, numeroIdentificacion);
            ps.setDouble(2, valorAbono);
            ps.setString(3, fecha);
            ps.setString(4, realizoAbono);

            ps.execute();
            System.out.println("Abono registrado correctamente.");

            con.close();
        } catch (Exception e) {
            System.out.println("Error al registrar abono: " + e.getMessage());
        }
    }

    /**
     * Metodo para mostrar el historial de abonos de un cliente
     */
    public void consultarHistorial(String numeroIdentificacion) {
        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM abonos WHERE numero_identificacion = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, numeroIdentificacion);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Fecha: " + rs.getString("fecha_abono")
                        + " | Abono: " + rs.getString("realizo_abono")
                        + " | Valor: " + rs.getDouble("valor_abono"));
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error al consultar historial: " + e.getMessage());
        }
    }

    /**
     * Metodo para eliminar un abono por su id
     */
    public void eliminarAbono(int idAbono) {
        try {
            Connection con = Conexion.conectar();

            String sql = "DELETE FROM abonos WHERE id_abono = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAbono);

            ps.execute();
            System.out.println("Abono eliminado correctamente.");

            con.close();
        } catch (Exception e) {
            System.out.println("Error al eliminar abono: " + e.getMessage());
        }
    }
}