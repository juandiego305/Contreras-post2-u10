# Productos Service - Calidad Mejorada
![CI](https://github.com/juandiego305/Contreras-post2-u10/actions/workflows/ci.yml/badge.svg)

## Descripción
Microservicio de gestión de productos sometido a inspección de código. Se configuró un Quality Gate ("Estándar Universidad") y se integró un pipeline de GitHub Actions para automatizar las pruebas unitarias en cada push.

*(Nota: El análisis de SonarQube se ejecuta localmente mediante Docker, por lo cual el paso en GitHub Actions se encuentra comentado para evitar errores de conexión al `localhost`).*

## Ejecutar las pruebas
```bash
# Solo pruebas unitarias
mvn test

# Pruebas + reporte JaCoCo + SonarQube Local
mvn clean verify sonar:sonar -Dsonar.token=TU_TOKEN
