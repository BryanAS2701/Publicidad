# BIENVENIDO AL GESTOR DE PUBLICIDADES 🏷️ 
## 📌 Introducción
Esta API permite la administración de anuncios y promociones, permitiendo operaciones CRUD sobre las publicidades.  
### 📂 Contenido del proyecto
* **Controller** → Manejo de las solicitudes HTTP y lógica de enrutamiento.
* **Model** → Definición de las entidades y modelos de datos.
* **Repository** → Interfaz de acceso a la base de datos mediante JPA/Hibernate.
* **Service** → Implementación de la lógica de negocio.  
* **utils** → Funciones auxiliares y utilidades generales.
## 🚀 Tecnologías utilizadas
- **Spring Boot**  
- **Git & GitHub**  
- **Docker & Contenedores**  
- **Base de Datos (PostgreSQL)**
## 🌐 Endpoints
### 📌 Obtener todas las publicidades  
```bash
curl -X GET http://localhost:8080/advertisements -H "Content-Type: application/json"
```
### ➕ Crear una nueva publicidad 
```bash
curl -X POST http://localhost:8080/advertisements \
     -H "Content-Type: application/json" \
     -d '{
          "title": "PROMO 1",
          "redirectUrl": "https://img.freepik.com/fotos-premium/silueta-hombre-plano-fondo-luna_273003-4451.jpg",
          "active": true,
          "imageUrl": {
          "HORIZONTAL": {
          "SMALL": "https://plus.unsplash.com/premium_photo-1673264933056-8f2f9c87742f?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8aW0lQzMlQTFnZW5lcyUyMGJvbml0YXN8ZW58MHx8MHx8fDA%3D",
          "LARGE": "https://console.cloudinary.com/pm/c-8a55de1fa56d6b51ce0177c07b23c5/media-explorer?assetId=398dc9ee3a782d89acce102de96f1584"
          },
          "VERTICAL": {
          "SMALL": "https://console.cloudinary.com/pm/c-8a55de1fa56d6b51ce0177c07b23c5/media-explorer?assetId=43ab4d555986a67e19179ba4efa3ec17",
          "LARGE": "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pixelcut.ai%2Fes%2Fexpandir-imagen-con-ia&psig=AOvVaw3kyN42n5YcdBjm1KTs6VkG&ust=1740864302812000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCLDqoP6j54sDFQAAAAAdAAAAABAE"
        } }
         }'
```
### 📝 Actualizar una publicidad existente
```bash
curl -X PATCH http://localhost:8080/advertisements/1 \
     -H "Content-Type: application/json" \
     -d '{
          "title": "Promo Actualizada"
          "imageUrl": {
          "HORIZONTAL": {
          "SMALL": "https://plus.unsplash.com/premium_photo-1673264933056-8f2f9c87742f?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8aW0lQzMlQTFnZW5lcyUyMGJvbml0YXN8ZW58MHx8MHx8fDA%3D"
        }}
         }'
```
### 🗑️ Eliminar una publicidad
```bash
curl -X DELETE http://localhost:8080/advertisements/1
```
### 📌 Consultar una publicidad mediante el id
```bash
curl -X GET http://localhost:8080/advertisements/consultar/1
```

