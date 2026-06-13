<?php
// Servicio web: Inicio de sesion (Login)
// Proyecto: SCK-GROUP - Sistema de Creditos Kronos Group
// Autor: Cindy Johana Ramirez
// Ficha: 3070186
//
// Este servicio recibe: nombre_usuario y clave
// Verifica si el usuario existe y la clave es correcta
// Si es correcto devuelve: "Autenticacion satisfactoria"
// Si es incorrecto devuelve: "Error en la autenticacion"

// Se permite recibir peticiones desde cualquier origen (CORS)
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");

// Solo se acepta el metodo POST
if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    http_response_code(405);
    echo json_encode(array("mensaje" => "Metodo no permitido. Use POST."));
    exit();
}

// Se incluye el archivo de conexion a la base de datos
include_once '../config/conexion.php';

// Se obtienen los datos enviados en formato JSON
$datos = json_decode(file_get_contents("php://input"));

// Se valida que los campos obligatorios vengan en la peticion
if (empty($datos->nombre_usuario) || empty($datos->clave)) {
    http_response_code(400);
    echo json_encode(array("mensaje" => "Error: debe ingresar el nombre de usuario y la clave."));
    exit();
}

// Se limpian los datos para evitar inyeccion SQL
$nombre_usuario = mysqli_real_escape_string($conexion, $datos->nombre_usuario);
$clave = $datos->clave;

// Se encripta la clave recibida para compararla con la guardada en la base de datos
$clave_encriptada = md5($clave);

// Se busca el usuario con ese nombre y esa clave en la base de datos
$consulta = "SELECT id, nombre_usuario, perfil 
             FROM usuarios_api 
             WHERE nombre_usuario = '$nombre_usuario' 
             AND clave = '$clave_encriptada'";

$resultado = mysqli_query($conexion, $consulta);

if (mysqli_num_rows($resultado) > 0) {
    // Se encontro el usuario, la autenticacion fue exitosa
    $usuario = mysqli_fetch_assoc($resultado);

    http_response_code(200);
    echo json_encode(array(
        "mensaje" => "Autenticacion satisfactoria.",
        "nombre_usuario" => $usuario['nombre_usuario'],
        "perfil" => $usuario['perfil']
    ));
} else {
    // No se encontro el usuario o la clave es incorrecta
    http_response_code(401);
    echo json_encode(array(
        "mensaje" => "Error en la autenticacion. Usuario o clave incorrectos."
    ));
}

// Se cierra la conexion
mysqli_close($conexion);
?>
