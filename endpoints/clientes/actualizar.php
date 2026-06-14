<?php
// Servicio web: Actualizar estado del credito de un cliente
// Proyecto: SCK-GROUP
// Autor: Cindy Johana Ramirez
// RF3: Administrar base de datos
//
// Metodo: PUT
// Recibe: numeroIdentificacion, estadoCredito (AL_DIA, DEUDOR_MOROSO, PENDIENTE)

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: PUT");

if ($_SERVER['REQUEST_METHOD'] !== 'PUT') {
    http_response_code(405);
    echo json_encode(array("mensaje" => "Metodo no permitido. Use PUT."));
    exit();
}

include_once '../../config/conexion.php';

$datos = json_decode(file_get_contents("php://input"));

// Validacion de campos obligatorios
if (empty($datos->numeroIdentificacion) || empty($datos->estadoCredito)) {
    http_response_code(400);
    echo json_encode(array("mensaje" => "Error: los campos numeroIdentificacion y estadoCredito son obligatorios."));
    exit();
}

// Validacion: el estado debe ser uno de los permitidos
$estadosPermitidos = array('AL_DIA', 'DEUDOR_MOROSO', 'PENDIENTE');
if (!in_array($datos->estadoCredito, $estadosPermitidos)) {
    http_response_code(400);
    echo json_encode(array("mensaje" => "Error: el estado debe ser AL_DIA, DEUDOR_MOROSO o PENDIENTE."));
    exit();
}

$numeroIdentificacion = mysqli_real_escape_string($conexion, $datos->numeroIdentificacion);
$estadoCredito = mysqli_real_escape_string($conexion, $datos->estadoCredito);

// Se actualiza el estado del cliente
$sql = "UPDATE clientes SET estado_credito = '$estadoCredito' 
        WHERE numero_identificacion = '$numeroIdentificacion'";

$resultado = mysqli_query($conexion, $sql);

if (mysqli_affected_rows($conexion) > 0) {
    http_response_code(200);
    echo json_encode(array(
        "mensaje" => "Estado del cliente actualizado correctamente.",
        "numeroIdentificacion" => $numeroIdentificacion,
        "nuevoEstado" => $estadoCredito
    ));
} else {
    http_response_code(404);
    echo json_encode(array("mensaje" => "No se encontro el cliente o el estado ya era el mismo."));
}

mysqli_close($conexion);
?>
