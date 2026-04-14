# Configuración de Base de Datos Local y Ejecución (PostgreSQL + Spring Boot)

Este documento describe los pasos para configurar el entorno de base de datos necesario y levantar la aplicación en Windows.

## 1. Requisito Inicial: Instalar PostgreSQL
Descargue e instale PostgreSQL para Windows desde la [web oficial](https://www.postgresql.org/download/windows/).
* Durante la instalación, defina una contraseña para el usuario administrador (`postgres`) y asegúrese de recordarla.

## 2. Configuración mediante Terminal
Puede usar **PowerShell** (recomendado) o el **Símbolo del Sistema (CMD)**.

### Paso A: Navegar a la carpeta de PostgreSQL
Abra su terminal y diríjase a la carpeta `bin`.

> **¡ATENCIÓN!** Sustituya `<VERSION>` por el número de versión que se instaló (ejemplo: 16, 17 o 18).

**En PowerShell o CMD:**
```cmd
cd "C:\Program Files\PostgreSQL\<VERSION>\bin"
```

### Paso B: Crear Usuario y Base de Datos
1. Conéctese como administrador (use la contraseña que se definió al instalar):
    * **PowerShell:** `.\psql -U postgres`
    * **CMD:** `psql -U postgres`

2. Una vez dentro de la consola de postgres (se verá el prompt `postgres=#`), pegue los siguientes comandos:

```sql
-- Crear el usuario para la aplicación
CREATE ROLE crud_user WITH LOGIN PASSWORD 'crud_pass';

-- Crear la base de datos asignando al dueño
CREATE DATABASE crud_db OWNER crud_user;

-- Conectarse a la nueva base para asignar permisos de esquema
\c crud_db

-- Dar permisos totales al usuario sobre el esquema público
GRANT ALL ON SCHEMA public TO crud_user;

-- Salir de PostgreSQL
\q
```

## 3. Ejecutar el Proyecto
Una vez configurada la base de datos y clonado este repositorio, siga estos pasos para levantar la aplicación:

1. Abra una nueva terminal y navegue hasta la carpeta raíz del proyecto (donde se encuentra el archivo `gradlew`).
   ```cmd
   cd "ruta\a\la\carpeta\del\proyecto"
   ```
2. Ejecute el comando de Spring Boot para compilar e iniciar la aplicación:
    * **PowerShell:** `.\gradlew bootRun`
    * **CMD:** `gradlew bootRun`

*(Nota: Al ejecutar este comando, Hibernate detectará la base de datos vacía y creará automáticamente las tablas necesarias antes de abrir la aplicación).*

## 4. Verificación de Registros (Opcional)
Una vez que el programa esté corriendo y se guaden datos, se pueden verificar de la siguiente forma:

Desde la misma carpeta `bin` de PostgreSQL en la terminal:
```cmd
.\psql -U crud_user -d crud_db
-- (Introduzca la contraseña: crud_pass)
SELECT * FROM productos;
```