# SCK-GROUP - Frontend React JS
## GA7-220501096-AA4-EV03

**Autora:** Cindy Johana Ramirez  
**Ficha:** 3070186  

---

## Descripcion

Modulo frontend del proyecto SCK-GROUP (Sistema de Creditos Kronos Group) desarrollado con React JS. Permite gestionar clientes con credito de dinero a traves de una interfaz web moderna basada en componentes.

---

## Estructura del proyecto

```
SCK_GROUP_REACT/
├── public/
│   └── index.html
└── src/
    ├── index.js                    (punto de entrada)
    ├── App.js                      (componente principal - maneja navegacion)
    ├── estilos.css                 (estilos globales)
    └── componentes/
        ├── Menu.js                 (RF1 - menu principal)
        ├── FormularioRegistro.js   (RF5 - registrar cliente)
        ├── FormularioConsulta.js   (RF8 - consultar cliente)
        ├── ResultadoConsulta.js    (RF8 - mostrar resultado)
        ├── ListaClientes.js        (RF3 - listar y eliminar)
        ├── TarjetaCliente.js       (RF3 - fila de la tabla)
        └── Mensaje.js              (componente reutilizable)
```

---

## Componentes y requerimientos

| Componente | Requerimiento | Descripcion |
|-----------|---------------|-------------|
| App | RF1 | Componente raiz, maneja la navegacion |
| Menu | RF1 | Menu principal con opciones |
| FormularioRegistro | RF5 | Formulario para registrar clientes |
| FormularioConsulta | RF8 | Buscar cliente por identificacion |
| ResultadoConsulta | RF8 | Muestra datos del cliente encontrado |
| ListaClientes | RF3 | Tabla con todos los clientes |
| TarjetaCliente | RF3 | Fila reutilizable de la tabla |
| Mensaje | RF5,RF8,RF9 | Mensajes de exito o error |

---

## Como ejecutar

1. Tener instalado Node.js (version 16 o superior)
2. En la carpeta del proyecto ejecutar:
```
npm install
npm start
```
3. Abrir el navegador en: http://localhost:3000

---

## Enlace del repositorio

https://github.com/morelloreda-blip/SCK-GROUP
