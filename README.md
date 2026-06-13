# SCK-GROUP - Servicio Web (API REST en PHP)
## GA7-220501096-AA5-EV01

**Autora:** Cindy Johana Ramirez  
**Ficha:** 3070186  

---

## Descripcion

Servicio web (API REST) del proyecto SCK-GROUP desarrollado en PHP con conexion a MySQL. Permite registrar usuarios e iniciar sesion con autenticacion.

---

## Estructura del proyecto

```
SCK_GROUP_API/
├── config/
│   ├── conexion.php              (conexion a MySQL)
│   └── crear_tabla_usuarios.sql  (script SQL)
└── endpoints/
    ├── registro.php              (POST - registrar usuario)
    └── login.php                 (POST - iniciar sesion)
```

---

## Endpoints del servicio

### 1. Registro de usuario
- **URL:** `POST /endpoints/registro.php`
- **Cuerpo (JSON):**
```json
{
  "nombre_usuario": "cobrador1",
  "clave": "mi_clave",
  "perfil": "COBRADOR"
}
```
- **Respuesta exitosa (201):**
```json
{
  "mensaje": "Usuario registrado correctamente.",
  "nombre_usuario": "cobrador1",
  "perfil": "COBRADOR"
}
```
- **Respuesta error (400):**
```json
{
  "mensaje": "Error: el nombre de usuario ya esta registrado."
}
```

---

### 2. Inicio de sesion (Login)
- **URL:** `POST /endpoints/login.php`
- **Cuerpo (JSON):**
```json
{
  "nombre_usuario": "admin",
  "clave": "admin123"
}
```
- **Respuesta exitosa (200):**
```json
{
  "mensaje": "Autenticacion satisfactoria.",
  "nombre_usuario": "admin",
  "perfil": "ADMINISTRADOR"
}
```
- **Respuesta error (401):**
```json
{
  "mensaje": "Error en la autenticacion. Usuario o clave incorrectos."
}
```

---

## Como ejecutar

1. Tener XAMPP instalado con Apache y MySQL corriendo
2. Copiar la carpeta `SCK_GROUP_API` dentro de `C:\xampp\htdocs\`
3. Ejecutar el script SQL `crear_tabla_usuarios.sql` en phpMyAdmin
4. Probar los endpoints desde un navegador o con Postman:
   - Registro: `http://localhost/SCK_GROUP_API/endpoints/registro.php`
   - Login: `http://localhost/SCK_GROUP_API/endpoints/login.php`

---

## Enlace del repositorio

https://github.com/morelloreda-blip/SCK-GROUP
