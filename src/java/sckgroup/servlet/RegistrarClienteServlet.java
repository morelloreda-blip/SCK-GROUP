package sckgroup.servlet;

import sckgroup.conexion.Conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que recibe los datos del formulario de registro de cliente
 * y los guarda en la base de datos.
 * RF5: Registro clientes nuevos
 * @author Cindy Johana Ramirez
 * @version 1.0
 */
@WebServlet(name = "RegistrarClienteServlet", urlPatterns = {"/RegistrarCliente"})
public class RegistrarClienteServlet extends HttpServlet {

    /**
     * Metodo POST: recibe los datos del formulario y los guarda
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los datos enviados desde el formulario HTML
        String identificacion = request.getParameter("identificacion");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String ciudad = request.getParameter("ciudad");
        String monto = request.getParameter("monto");
        String dias = request.getParameter("dias");
        String ruta = request.getParameter("ruta");

        String mensaje;

        try {
            Connection con = Conexion.conectar();

            String sql = "INSERT INTO clientes (numero_identificacion, nombre_completo, "
                    + "telefono, ciudad, monto_credito, numero_dias, estado_credito, nombre_ruta) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, identificacion);
            ps.setString(2, nombre);
            ps.setString(3, telefono);
            ps.setString(4, ciudad);
            ps.setDouble(5, Double.parseDouble(monto));
            ps.setInt(6, Integer.parseInt(dias));
            ps.setString(7, "PENDIENTE");
            ps.setString(8, ruta);

            ps.execute();
            con.close();

            mensaje = "Cliente registrado correctamente.";

        } catch (Exception e) {
            mensaje = "Error al registrar el cliente: " + e.getMessage();
        }

        // Enviar el mensaje a la pagina de resultado
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("resultado.jsp").forward(request, response);
    }

    /**
     * Metodo GET: si alguien entra directo por la URL, lo manda al formulario
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("registrar.html");
    }
}