<%-- 
    Pagina JSP que muestra la informacion del cliente
    consultado por numero de identificacion
    Autor: Cindy Johana Ramirez
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta - SCK-GROUP</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>

    <div class="contenedor">
        <h2>Resultado de la consulta</h2>

        <%-- Se valida si el cliente fue encontrado o no --%>
        <% if ((Boolean) request.getAttribute("encontrado")) { %>

            <p><b>Numero de identificacion:</b> <%= request.getAttribute("identificacion") %></p>
            <p><b>Nombre:</b> <%= request.getAttribute("nombre") %></p>
            <p><b>Telefono:</b> <%= request.getAttribute("telefono") %></p>
            <p><b>Ciudad:</b> <%= request.getAttribute("ciudad") %></p>
            <p><b>Monto del credito:</b> $<%= request.getAttribute("monto") %></p>
            <p><b>Estado del credito:</b> <%= request.getAttribute("estado") %></p>
            <p><b>Ruta:</b> <%= request.getAttribute("ruta") %></p>

        <% } else { %>

            <p class="mensaje">USUARIO NO ENCONTRADO en la base de datos.</p>

        <% } %>

        <br>
        <a href="index.html">Volver al menu</a>
    </div>

</body>
</html>