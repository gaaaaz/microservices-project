# Microservices-project
Proyecto de Sistema de Ventas Online aplicando Arquitectura de Microservicios 

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

Mira **Ejecución** para conocer como ejecutar el proyecto.


### Pre-requisitos 📋

* Java 11
* Maven 
* Spring Tool Suite 4 o cualquier IDE de tu agrado
* Git
* Repositorio en Github - Para subir el proyecto y usarlo junto a Spring Cloud para los archivos de configuración

## Ejecución 📦

_Para ejecutar el proyecto comienza ejecutando Spring Cloud (service-config) y Eureka (service-registry), seguido de API Gateway (service-gateway) y Admin Server (service-admin)
Finalmente, solo ejecuta los microservicios en cualquier orden._

## Construido con 🛠️

* Spring Web
* JPA 
* H2 Database - Base de datos en memoria 
* Spring Cloud - Uso de los archivos de configuración de cada proyecto en el repositorio (Carpeta /config-data)
* Eureka Server / Client - Registro de los servicios existentes en un único lugar
* Feign - Comunicación entre microservicios (uso de métodos de un microservicio en otro ms)
* Hystrix - Solución alternativa ante una falla de un microservicio o los microservicios de los que depende (Fallback)
* Actuator / Admin Server - Monitorizar la salud, env, peticiones de nuestros microservicios
* Sleuth - Otorgar una id única a cada petición realizada a los microservicios para hacer seguimiento

## Curso seguido 📄

* [Curso Microservicios con Spring Boot y Spring Cloud](https://www.youtube.com/playlist?list=PLxy6jHplP3Hi_W8iuYSbAeeMfaTZt49PW) - Digital Lab Academy


