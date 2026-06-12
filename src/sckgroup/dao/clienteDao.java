package sckgroup.dao;

import sckgroup.conexion.Conexion;
import sckgroup.modelo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase que tiene los metodos para insertar, consultar, actualizar
 * y eliminar clientes en la base de datos
 * @author Cindy Johana Ramirez
 * @version 1.0
 */
public class ClienteDao {

    /**
     * Metodo para registrar un nuevo cliente (RF5)
     */
    public void insertarCliente(Cliente cliente) {
        try {
            Connection con = Conexion.conectar();

            String sql = "INSERT INTO clientes (numero_identificacion, nombre_completo, "
                    + "telefono, ciudad, monto_credito, numero_dias, estado_credito, nombre_ruta) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNumeroIdentificacion());
            ps.setString(2, cliente.getNombreCompleto());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getCiudad());
            ps.setDouble(5, cliente.getMontoCredito());
            ps.setInt(6, cliente.getNumeroDias());
            ps.setString(7, cliente.getEstadoCredito());
            ps.setString(8, cliente.getNombreRuta());

            ps.execute();
            System.out.println("Cliente registrado correctamente.");

            con.close();
        } catch (Exception e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
    }

    /**
     * Metodo para buscar un cliente por su numero de identificacion (RF8)
     */
    public void buscarPorIdentificacion(String numeroIdentificacion) {
        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM clientes WHERE numero_identificacion = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, numeroIdentificacion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Nombre: " + rs.getString("nombre_completo"));
                System.out.println("Telefono: " + rs.getString("telefono"));
                System.out.println("Ciudad: " + rs.getString("ciudad"));
                System.out.println("Monto credito: " + rs.getDouble("monto_credito"));
                System.out.println("Estado: " + rs.getString("estado_credito"));
                System.out.println("Ruta: " + rs.getString("nombre_ruta"));
            } else {
                System.out.println("USUARIO NO ENCONTRADO");
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
    }

    /**
     * Metodo para mostrar todos los clientes de una ruta (RF3)
     */
    public void listarClientesPorRuta(String nombreRuta) {
        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM clientes WHERE nombre_ruta = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreRuta);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("nombre_completo") + " - "
                        + rs.getString("estado_credito"));
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
    }

    /**
     * Metodo para actualizar el estado del credito de un cliente (RF3)
     */
    public void actualizarEstadoCliente(String numeroIdentificacion, String nuevoEstado) {
        try {
            Connection con = Conexion.conectar();

            String sql = "UPDATE clientes SET estado_credito = ? WHERE numero_identificacion = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevoEstado);
            ps.setString(2, numeroIdentificacion);

            ps.execute();
            System.out.println("Estado actualizado correctamente.");

            con.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    /**
     * Metodo para eliminar un cliente de la base de datos (RF3)
     */
    public void eliminarCliente(String numeroIdentificacion) {
        try {
            Connection con = Conexion.conectar();

            String sql = "DELETE FROM clientes WHERE numero_identificacion = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, numeroIdentificacion);

            ps.execute();
            System.out.println("Cliente eliminado correctamente.");

            con.close();
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }
}