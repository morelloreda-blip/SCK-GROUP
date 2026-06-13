// Componente principal de la aplicacion SCK-GROUP
// Se encarga de manejar que pantalla se muestra al usuario
// Autor: Cindy Johana Ramirez
// RF1: Acceso al sistema

import { useState } from 'react';
import Menu from './componentes/Menu';
import FormularioRegistro from './componentes/FormularioRegistro';
import FormularioConsulta from './componentes/FormularioConsulta';
import ListaClientes from './componentes/ListaClientes';

function App() {

  // El state "pantalla" decide que componente se muestra
  // Puede ser: 'menu', 'registro', 'consulta', 'lista'
  const [pantalla, setPantalla] = useState('menu');

  // Funcion para cambiar de pantalla
  function cambiarPantalla(nuevaPantalla) {
    setPantalla(nuevaPantalla);
  }

  // Segun el valor de "pantalla" se muestra el componente correspondiente
  return (
    <div>
      {pantalla === 'menu' && (
        <Menu onCambiarPantalla={cambiarPantalla} />
      )}

      {pantalla === 'registro' && (
        <FormularioRegistro onCambiarPantalla={cambiarPantalla} />
      )}

      {pantalla === 'consulta' && (
        <FormularioConsulta onCambiarPantalla={cambiarPantalla} />
      )}

      {pantalla === 'lista' && (
        <ListaClientes onCambiarPantalla={cambiarPantalla} />
      )}
    </div>
  );
}

export default App;
