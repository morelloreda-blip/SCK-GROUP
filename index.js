/* Estilos globales del proyecto SCK-GROUP */
/* Autor: Cindy Johana Ramirez */

body {
  font-family: Arial, sans-serif;
  background-color: #f0f4f8;
  margin: 0;
  padding: 20px;
}

/* Contenedor principal de cada pagina */
.contenedor {
  background-color: #ffffff;
  max-width: 550px;
  margin: 0 auto;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

/* Contenedor mas ancho para la lista de clientes */
.contenedor-ancho {
  background-color: #ffffff;
  max-width: 800px;
  margin: 0 auto;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

/* Titulos principales */
h1 {
  color: #1B4F72;
  text-align: center;
}

h2 {
  color: #1F618D;
}

/* Campos del formulario */
label {
  font-weight: bold;
  display: block;
  margin-top: 12px;
}

input {
  width: 100%;
  padding: 8px;
  margin-top: 4px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

/* Botones */
.boton {
  background-color: #1B4F72;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 15px;
  margin-top: 10px;
  width: 100%;
}

.boton:hover {
  background-color: #154360;
}

.boton-secundario {
  background-color: #aab7c4;
  color: white;
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
}

.boton-eliminar {
  background-color: #c0392b;
  color: white;
  padding: 5px 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 13px;
}

.boton-eliminar:hover {
  background-color: #a93226;
}

/* Enlace volver */
.enlace-volver {
  color: #1B4F72;
  cursor: pointer;
  text-decoration: underline;
  display: block;
  margin-top: 15px;
  text-align: center;
}

/* Mensajes de exito y error */
.mensaje-exito {
  color: #1E8449;
  font-weight: bold;
  font-size: 16px;
  text-align: center;
  margin: 10px 0;
}

.mensaje-error {
  color: #c0392b;
  font-weight: bold;
  font-size: 16px;
  text-align: center;
  margin: 10px 0;
}

/* Tabla de clientes */
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 15px;
  font-size: 13px;
}

th {
  background-color: #1B4F72;
  color: white;
  padding: 8px;
  text-align: left;
}

td {
  border: 1px solid #ccc;
  padding: 7px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

/* Datos del cliente en resultado de consulta */
.dato-cliente {
  margin: 8px 0;
  font-size: 15px;
}

.dato-cliente span {
  font-weight: bold;
}
