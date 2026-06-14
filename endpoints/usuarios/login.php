<?php
// Servicio web: Inicio de sesion de usuarios del sistema
// Proyecto: SCK-GROUP
// Autor: Cindy Johana Ramirez
// RF1: Iniciar sesion
//
// Metodo: POST
// Recibe: nombre_usuario, clave
// Devuelve: mensaje de autenticacion satisfactoria o error

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    http_response_code(405);
    echo json_encode(array("mensaje" => "Metodo no permitido. Use POST."));
    exit();
}

include_once '../../config/conexion.php';

$datos = json_decode(file_get_contents("php://input"));

if (empty($datos->nombre_usuario) || empty($datos->clave)) {
    http_response_code(400);
    echo json_encode(array("mensaje" => "Error: debe ingresar el nombre de usuario y la clave."));
    exit();
}

$nombre_usuario = mysqli_real_escape_string($conexion, $datos->nombre_usuario);
$clave_encriptada = md5($datos->clave);

$sql = "SELECT id, nombre_usuario, perfil FROM usuarios_api 
        WHERE nombre_usuario = '$nombre_usuario' AND clave = '$clave_encriptada'";

$resultado = mysqli_query($conexion, $sql);

if (mysqli_num_rows($resultado) > 0) {
    $usuario = mysqli_fetch_assoc($resultado);
    http_response_code(200);
    echo json_encode(array(
        "mensaje" => "Autenticacion satisfactoria.",
        "nombre_usuario" => $usuario['nombre_usuario'],
        "perfil" => $usuario['perfil']
    ));
} else {
    http_response_code(401);
    echo json_encode(array(
        "mensaje" => "Error en la autenticacion. Usuario o clave incorrectos."
    ));
}

mysqli_close($conexion);
?>
