<%-- 
    Pagina JSP que muestra el mensaje de resultado
    despues de registrar un cliente
    Autor: Cindy Johana Ramirez
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultado - SCK-GROUP</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>

    <div class="contenedor">
        <h2>Resultado</h2>

        <!-- Aqui se muestra el mensaje que envia el servlet -->
        <p class="mensaje"><%= request.getAttribute("mensaje") %></p>

        <br>
        <a href="index.html">Volver al menu</a>
    </div>

</body>
</html>