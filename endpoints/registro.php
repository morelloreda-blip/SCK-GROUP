<?php
// Servicio web: Registro de usuario
// Proyecto: SCK-GROUP - Sistema de Creditos Kronos Group
// Autor: Cindy Johana Ramirez
// Ficha: 3070186
//
// Este servicio recibe: nombre_usuario, clave, perfil
// Registra el usuario en la base de datos
// Devuelve un mensaje de exito o error en formato JSON

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

// Se obtienen los datos enviados en el cuerpo de la peticion (JSON)
$datos = json_decode(file_get_contents("php://input"));

// Se valida que los campos obligatorios vengan en la peticion
if (empty($datos->nombre_usuario) || empty($datos->clave) || empty($datos->perfil)) {
    http_response_code(400);
    echo json_encode(array("mensaje" => "Error: todos los campos son obligatorios (nombre_usuario, clave, perfil)."));
    exit();
}

// Se limpian los datos para evitar inyeccion SQL
$nombre_usuario = mysqli_real_escape_string($conexion, $datos->nombre_usuario);
$clave = $datos->clave;
$perfil = mysqli_real_escape_string($conexion, $datos->perfil);

// Se verifica que el nombre de usuario no exista ya en la base de datos
$consulta_verificar = "SELECT id FROM usuarios_api WHERE nombre_usuario = '$nombre_usuario'";
$resultado = mysqli_query($conexion, $consulta_verificar);

if (mysqli_num_rows($resultado) > 0) {
    // El usuario ya existe
    http_response_code(400);
    echo json_encode(array("mensaje" => "Error: el nombre de usuario ya esta registrado."));
    exit();
}

// Se encripta la clave con MD5 antes de guardarla
$clave_encriptada = md5($clave);

// Se inserta el nuevo usuario en la base de datos
$consulta_insertar = "INSERT INTO usuarios_api (nombre_usuario, clave, perfil) 
                      VALUES ('$nombre_usuario', '$clave_encriptada', '$perfil')";

$resultado_insertar = mysqli_query($conexion, $consulta_insertar);

if ($resultado_insertar) {
    // Registro exitoso
    http_response_code(201);
    echo json_encode(array(
        "mensaje" => "Usuario registrado correctamente.",
        "nombre_usuario" => $nombre_usuario,
        "perfil" => $perfil
    ));
} else {
    // Error al insertar
    http_response_code(500);
    echo json_encode(array("mensaje" => "Error al registrar el usuario."));
}

// Se cierra la conexion
mysqli_close($conexion);
?>
