<?php
// Servicio web: Registrar abono de un cliente
// Proyecto: SCK-GROUP
// Autor: Cindy Johana Ramirez
// RF9: Gestionar abonos
//
// Metodo: POST
// Recibe: numeroIdentificacion, valorAbono, fechaAbono, realizoAbono (SI/NO), observacion

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

// Validacion de campos obligatorios
if (empty($datos->numeroIdentificacion) || empty($datos->fechaAbono) || empty($datos->realizoAbono)) {
    http_response_code(400);
    echo json_encode(array("mensaje" => "Error: los campos numeroIdentificacion, fechaAbono y realizoAbono son obligatorios."));
    exit();
}

$numeroIdentificacion = mysqli_real_escape_string($conexion, $datos->numeroIdentificacion);
$valorAbono = $datos->valorAbono ?? 0;
$fechaAbono = mysqli_real_escape_string($conexion, $datos->fechaAbono);
$realizoAbono = mysqli_real_escape_string($conexion, $datos->realizoAbono);
$observacion = mysqli_real_escape_string($conexion, $datos->observacion ?? '');

// Se inserta el abono en la base de datos
$sql = "INSERT INTO abonos (numero_identificacion, valor_abono, fecha_abono, realizo_abono, observacion) 
        VALUES ('$numeroIdentificacion', $valorAbono, '$fechaAbono', '$realizoAbono', '$observacion')";

$resultado = mysqli_query($conexion, $sql);

if ($resultado) {
    // Si el cliente no abono se envia alerta
    if ($realizoAbono === 'NO') {
        http_response_code(201);
        echo json_encode(array(
            "mensaje" => "Abono registrado. ALERTA: el cliente no realizo el pago del dia.",
            "numeroIdentificacion" => $numeroIdentificacion,
            "realizoAbono" => "NO"
        ));
    } else {
        http_response_code(201);
        echo json_encode(array(
            "mensaje" => "Abono registrado correctamente.",
            "numeroIdentificacion" => $numeroIdentificacion,
            "valorAbono" => $valorAbono,
            "fechaAbono" => $fechaAbono
        ));
    }
} else {
    http_response_code(500);
    echo json_encode(array("mensaje" => "Error al registrar el abono."));
}

mysqli_close($conexion);
?>
