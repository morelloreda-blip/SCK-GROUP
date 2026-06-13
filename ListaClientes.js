// Componente TarjetaCliente
// Muestra una fila de la tabla con los datos de un cliente
// Se reutiliza por cada cliente en la lista
// Autor: Cindy Johana Ramirez
// RF3: Administrar base de datos

function TarjetaCliente({ cliente, onEliminar }) {
  return (
    <tr>
      <td>{cliente.numeroIdentificacion}</td>
      <td>{cliente.nombreCompleto}</td>
      <td>{cliente.ciudad}</td>
      <td>${cliente.montoCredito}</td>
      <td>{cliente.estadoCredito}</td>
      <td>{cliente.nombreRuta}</td>
      <td>
        {/* Boton eliminar con evento onClick */}
        <button
          className="boton-eliminar"
          onClick={() => onEliminar(cliente.numeroIdentificacion)}
        >
          Eliminar
        </button>
      </td>
    </tr>
  );
}

export default TarjetaCliente;
