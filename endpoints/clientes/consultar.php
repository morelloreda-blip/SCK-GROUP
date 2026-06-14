<?php
// Servicio web: Consultar cliente por numero de identificacion
// Proyecto: SCK-GROUP
// Autor: Cindy Johana Ramirez
// RF8: Consultar numero de identificacion
//
// Metodo: GET
// Parametro: identificacion (en la URL)
// Ejemplo: consultar.php?identificacion=1012345678

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET");

// Solo acepta metodo GET
if ($_SERVER['REQUEST_METHOD'] !== 'GET') {
    http_response_code(405);
    echo json_encode(array("mensaje" => "Metodo no permitido. Use GET."));
    exit();
}

include_once '../../config/conexion.php';

// Se obtiene el parametro de la URL
if (empty($_GET['identificacion'])) {
    http_response_code(400);
    echo json_encode(array("mensaje" => "Error: debe enviar el parametro identificacion."));
    exit();
}

$identificacion = mysqli_real_escape_string($conexion, $_GET['identificacion']);

// Se busca el cliente en la base de datos
$sql = "SELECT * FROM clientes WHERE numero_identificacion = '$identificacion'";
$resultado = mysqli_query($conexion, $sql);

if (mysqli_num_rows($resultado) > 0) {
    // Se encontro el cliente
    $cliente = mysqli_fetch_assoc($resultado);
    http_response_code(200);
    echo json_encode(array(
        "mensaje" => "Cliente encontrado.",
        "cliente" => array(
            "idCliente" => $cliente['id_cliente'],
            "numeroIdentificacion" => $cliente['numero_identificacion'],
            "nombreCompleto" => $cliente['nombre_completo'],
            "telefono" => $cliente['telefono'],
            "ciudad" => $cliente['ciudad'],
            "montoCredito" => $cliente['monto_credito'],
            "numeroDias" => $cliente['numero_dias'],
            "estadoCredito" => $cliente['estado_credito'],
            "nombreRuta" => $cliente['nombre_ruta']
        )
    ));
} else {
    // No se encontro el cliente
    http_response_code(404);
    echo json_encode(array("mensaje" => "USUARIO NO ENCONTRADO en la base de datos."));
}

mysqli_close($conexion);
?>
