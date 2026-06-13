// Punto de entrada de la aplicacion React
// Proyecto: SCK-GROUP - Sistema de Creditos Kronos Group
// Autor: Cindy Johana Ramirez
// Ficha: 3070186

import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './estilos.css';

// Se renderiza el componente principal App dentro del div con id="root"
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
