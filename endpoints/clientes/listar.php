<?php
// Servicio web: Listar clientes por ruta
// Proyecto: SCK-GROUP
// Autor: Cindy Johana Ramirez
// RF3: Administrar base de datos
//
// Metodo: GET
// Parametro: ruta (en la URL) - opcional, si no se envia trae todos
// Ejemplo: listar.php?ruta=RUTA_NORTE

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET");

if ($_SERVER['REQUEST_METHOD'] !== 'GET') {
    http_response_code(405);
    echo json_encode(array("mensaje" => "Metodo no permitido. Use GET."));
    exit();
}

include_once '../../config/conexion.php';

// Si se envia el parametro ruta se filtra, si no se traen todos
if (!empty($_GET['ruta'])) {
    $ruta = mysqli_real_escape_string($conexion, $_GET['ruta']);
    $sql = "SELECT * FROM clientes WHERE nombre_ruta = '$ruta' ORDER BY nombre_completo";
} else {
    $sql = "SELECT * FROM clientes ORDER BY nombre_ruta, nombre_completo";
}

$resultado = mysqli_query($conexion, $sql);

// Se construye el array de clientes
$listaClientes = array();

while ($fila = mysqli_fetch_assoc($resultado)) {
    $listaClientes[] = array(
        "idCliente" => $fila['id_cliente'],
        "numeroIdentificacion" => $fila['numero_identificacion'],
        "nombreCompleto" => $fila['nombre_completo'],
        "telefono" => $fila['telefono'],
        "ciudad" => $fila['ciudad'],
        "montoCredito" => $fila['monto_credito'],
        "numeroDias" => $fila['numero_dias'],
        "estadoCredito" => $fila['estado_credito'],
        "nombreRuta" => $fila['nombre_ruta']
    );
}

http_response_code(200);
echo json_encode(array(
    "mensaje" => "Consulta exitosa.",
    "total" => count($listaClientes),
    "clientes" => $listaClientes
));

mysqli_close($conexion);
?>
