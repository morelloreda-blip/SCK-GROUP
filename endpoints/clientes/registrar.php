<?php
// Servicio web: Registrar nuevo cliente
// Proyecto: SCK-GROUP
// Autor: Cindy Johana Ramirez
// RF5: Registro clientes nuevos
//
// Metodo: POST
// Recibe: numeroIdentificacion, nombreCompleto, telefono, ciudad,
//         montoCredito, numeroDias, nombreRuta

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");

// Solo acepta metodo POST
if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    http_response_code(405);
    echo json_encode(array("mensaje" => "Metodo no permitido. Use POST."));
    exit();
}

include_once '../../config/conexion.php';

// Se obtienen los datos del cuerpo de la peticion en formato JSON
$datos = json_decode(file_get_contents("php://input"));

// Validacion de campos obligatorios
if (empty($datos->numeroIdentificacion) || empty($datos->nombreCompleto) || empty($datos->montoCredito) || empty($datos->numeroDias)) {
    http_response_code(400);
    echo json_encode(array("mensaje" => "Error: los campos numeroIdentificacion, nombreCompleto, montoCredito y numeroDias son obligatorios."));
    exit();
}

// Validacion: el monto debe ser mayor a cero
if ($datos->montoCredito <= 0) {
    http_response_code(400);
    echo json_encode(array("mensaje" => "Error: el monto del credito debe ser mayor a cero."));
    exit();
}

// Validacion: los dias deben ser mayor a cero
if ($datos->numeroDias <= 0) {
    http_response_code(400);
    echo json_encode(array("mensaje" => "Error: el numero de dias debe ser mayor a cero."));
    exit();
}

// Se limpian los datos para evitar inyeccion SQL
$numeroIdentificacion = mysqli_real_escape_string($conexion, $datos->numeroIdentificacion);
$nombreCompleto = mysqli_real_escape_string($conexion, $datos->nombreCompleto);
$telefono = mysqli_real_escape_string($conexion, $datos->telefono ?? '');
$ciudad = mysqli_real_escape_string($conexion, $datos->ciudad ?? '');
$montoCredito = $datos->montoCredito;
$numeroDias = $datos->numeroDias;
$nombreRuta = mysqli_real_escape_string($conexion, $datos->nombreRuta ?? '');

// Se verifica que el numero de identificacion no exista ya
$consulta_verificar = "SELECT id_cliente FROM clientes WHERE numero_identificacion = '$numeroIdentificacion'";
$resultado = mysqli_query($conexion, $consulta_verificar);

if (mysqli_num_rows($resultado) > 0) {
    http_response_code(400);
    echo json_encode(array("mensaje" => "Error: el numero de identificacion ya esta registrado."));
    exit();
}

// Se inserta el nuevo cliente
$sql = "INSERT INTO clientes (numero_identificacion, nombre_completo, telefono, ciudad, 
        monto_credito, numero_dias, estado_credito, nombre_ruta) 
        VALUES ('$numeroIdentificacion', '$nombreCompleto', '$telefono', '$ciudad',
        $montoCredito, $numeroDias, 'PENDIENTE', '$nombreRuta')";

$resultado = mysqli_query($conexion, $sql);

if ($resultado) {
    http_response_code(201);
    echo json_encode(array(
        "mensaje" => "Cliente registrado correctamente.",
        "numeroIdentificacion" => $numeroIdentificacion,
        "nombreCompleto" => $nombreCompleto,
        "estadoCredito" => "PENDIENTE"
    ));
} else {
    http_response_code(500);
    echo json_encode(array("mensaje" => "Error al registrar el cliente."));
}

mysqli_close($conexion);
?>
