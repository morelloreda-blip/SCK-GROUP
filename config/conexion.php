<?php
// Archivo de conexion a la base de datos MySQL
// Proyecto: SCK-GROUP - Sistema de Creditos Kronos Group
// Autor: Cindy Johana Ramirez
// Ficha: 3070186

// Datos de configuracion de la base de datos
$host = "localhost";
$usuario = "root";
$clave = "";
$base_datos = "sck_group_db";

// Se intenta conectar a la base de datos con mysqli
$conexion = mysqli_connect($host, $usuario, $clave, $base_datos);

// Si la conexion falla se muestra un error y se detiene el script
if (!$conexion) {
    http_response_code(500);
    echo json_encode(array("mensaje" => "Error al conectar con la base de datos."));
    die();
}

// Se configura el charset para evitar problemas con caracteres especiales
mysqli_set_charset($conexion, "utf8");
?>
