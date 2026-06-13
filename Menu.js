// Componente FormularioConsulta
// Permite buscar un cliente por su numero de identificacion
// Autor: Cindy Johana Ramirez
// RF8: Consultar numero de identificacion

import { useState } from 'react';
import ResultadoConsulta from './ResultadoConsulta';

// Datos de prueba para simular la base de datos mientras no hay backend conectado
const clientesPrueba = [
  {
    numeroIdentificacion: '1012345678',
    nombreCompleto: 'Maria Fernanda Lopez',
    telefono: '3001234567',
    ciudad: 'Bogota',
    montoCredito: 500000,
    estadoCredito: 'AL_DIA',
    nombreRuta: 'RUTA_NORTE'
  },
  {
    numeroIdentificacion: '9876543210',
    nombreCompleto: 'Carlos Andres Perez',
    telefono: '3109876543',
    ciudad: 'Bogota',
    montoCredito: 1000000,
    estadoCredito: 'AL_DIA',
    nombreRuta: 'RUTA_NORTE'
  }
];

function FormularioConsulta({ onCambiarPantalla }) {

  // State para el campo de busqueda
  const [identificacion, setIdentificacion] = useState('');

  // State para guardar el resultado de la busqueda
  // null = no se ha buscado todavia
  // objeto = cliente encontrado
  // 'no_encontrado' = no existe
  const [resultado, setResultado] = useState(null);
  const [buscado, setBuscado] = useState(false);

  // Funcion que busca el cliente cuando se envia el formulario
  function handleSubmit(evento) {
    evento.preventDefault();

    if (identificacion.trim() === '') {
      return;
    }

    // Buscar en los datos de prueba
    // En produccion aqui se llamaria al API del backend
    const clienteEncontrado = clientesPrueba.find(
      c => c.numeroIdentificacion === identificacion.trim()
    );

    setBuscado(true);
    setResultado(clienteEncontrado || null);
  }

  return (
    <div className="contenedor">
      <h2>Consultar Cliente</h2>
      <p>RF8 - Consultar numero de identificacion</p>

      {/* Formulario de busqueda con evento onSubmit */}
      <form onSubmit={handleSubmit}>
        <label>Numero de identificacion:</label>
        <input
          type="text"
          value={identificacion}
          onChange={(e) => setIdentificacion(e.target.value)}
        />
        <button type="submit" className="boton" style={{ marginTop: '10px' }}>
          Buscar
        </button>
      </form>

      {/* Solo se muestra el resultado si ya se hizo una busqueda */}
      {buscado && (
        <div style={{ marginTop: '20px' }}>
          <h3>Resultado:</h3>
          <ResultadoConsulta cliente={resultado} />
        </div>
      )}

      <span className="enlace-volver" onClick={() => onCambiarPantalla('menu')}>
        Volver al menu
      </span>
    </div>
  );
}

export default FormularioConsulta;
