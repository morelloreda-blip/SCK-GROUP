<?php
// Servicio web: Consultar historial de abonos de un cliente
// Proyecto: SCK-GROUP
// Autor: Cindy Johana Ramirez
// RF9: Gestionar abonos
//
// Metodo: GET
// Parametro: identificacion (en la URL)
// Ejemplo: historial.php?identificacion=1012345678

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET");

if ($_SERVER['REQUEST_METHOD'] !== 'GET') {
    http_response_code(405);
    echo json_encode(array("mensaje" => "Metodo no permitido. Use GET."));
    exit();
}

include_once '../../config/conexion.php';

if (empty($_GET['identificacion'])) {
    http_response_code(400);
    echo json_encode(array("mensaje" => "Error: debe enviar el parametro identificacion."));
    exit();
}

$identificacion = mysqli_real_escape_string($conexion, $_GET['identificacion']);

// Se obtiene el historial de abonos ordenado por fecha descendente
$sql = "SELECT * FROM abonos WHERE numero_identificacion = '$identificacion' ORDER BY fecha_abono DESC";
$resultado = mysqli_query($conexion, $sql);

$historial = array();
$totalAbonado = 0;

while ($fila = mysqli_fetch_assoc($resultado)) {
    $historial[] = array(
        "idAbono" => $fila['id_abono'],
        "fechaAbono" => $fila['fecha_abono'],
        "valorAbono" => $fila['valor_abono'],
        "realizoAbono" => $fila['realizo_abono'],
        "observacion" => $fila['observacion']
    );
    // Se calcula el total abonado
    if ($fila['realizo_abono'] === 'SI') {
        $totalAbonado += $fila['valor_abono'];
    }
}

http_response_code(200);
echo json_encode(array(
    "mensaje" => "Historial obtenido correctamente.",
    "numeroIdentificacion" => $identificacion,
    "totalAbonado" => $totalAbonado,
    "totalRegistros" => count($historial),
    "historial" => $historial
));

mysqli_close($conexion);
?>
