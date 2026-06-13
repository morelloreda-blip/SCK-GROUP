// Componente ResultadoConsulta
// Muestra la informacion del cliente encontrado
// Si no existe, muestra un mensaje de error
// Autor: Cindy Johana Ramirez
// RF8: Consultar numero de identificacion

function ResultadoConsulta({ cliente }) {

  // Si el cliente existe se muestran sus datos
  if (cliente) {
    return (
      <div>
        <p className="dato-cliente"><span>Identificacion:</span> {cliente.numeroIdentificacion}</p>
        <p className="dato-cliente"><span>Nombre:</span> {cliente.nombreCompleto}</p>
        <p className="dato-cliente"><span>Telefono:</span> {cliente.telefono}</p>
        <p className="dato-cliente"><span>Ciudad:</span> {cliente.ciudad}</p>
        <p className="dato-cliente"><span>Monto del credito:</span> ${cliente.montoCredito}</p>
        <p className="dato-cliente"><span>Estado:</span> {cliente.estadoCredito}</p>
        <p className="dato-cliente"><span>Ruta:</span> {cliente.nombreRuta}</p>
      </div>
    );
  }

  // Si no existe se muestra el mensaje de usuario no encontrado
  return (
    <p className="mensaje-error">USUARIO NO ENCONTRADO en la base de datos.</p>
  );
}

export default ResultadoConsulta;
