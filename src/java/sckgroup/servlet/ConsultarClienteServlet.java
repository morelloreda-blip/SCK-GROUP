package sckgroup.servlet;

import sckgroup.conexion.Conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que busca un cliente por su numero de identificacion.
 * RF8: Consultar numero de identificacion
 * @author Cindy Johana Ramirez
 * @version 1.0
 */
@WebServlet(name = "ConsultarClienteServlet", urlPatterns = {"/ConsultarCliente"})
public class ConsultarClienteServlet extends HttpServlet {

    /**
     * Metodo GET: recibe el numero de identificacion desde la URL
     * y consulta la informacion del cliente
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el dato enviado desde el formulario (metodo GET)
        String identificacion = request.getParameter("identificacion");

        String nombre = "";
        String telefono = "";
        String ciudad = "";
        String estado = "";
        String ruta = "";
        double monto = 0;
        boolean encontrado = false;

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM clientes WHERE numero_identificacion = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, identificacion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nombre = rs.getString("nombre_completo");
                telefono = rs.getString("telefono");
                ciudad = rs.getString("ciudad");
                monto = rs.getDouble("monto_credito");
                estado = rs.getString("estado_credito");
                ruta = rs.getString("nombre_ruta");
                encontrado = true;
            }

            con.close();

        } catch (Exception e) {
            request.setAttribute("mensaje", "Error en la consulta: " + e.getMessage());
            request.getRequestDispatcher("resultado.jsp").forward(request, response);
            return;
        }

        // Enviar los datos a la pagina JSP para mostrarlos
        request.setAttribute("encontrado", encontrado);
        request.setAttribute("identificacion", identificacion);
        request.setAttribute("nombre", nombre);
        request.setAttribute("telefono", telefono);
        request.setAttribute("ciudad", ciudad);
        request.setAttribute("monto", monto);
        request.setAttribute("estado", estado);
        request.setAttribute("ruta", ruta);

        request.getRequestDispatcher("consultaResultado.jsp").forward(request, response);
    }
}