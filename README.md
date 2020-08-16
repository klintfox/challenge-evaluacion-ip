# Ejercicio de programación
El ejercicio consiste en construir una API Rest que permita: 
##### 
- 1 Dada una dirección IP, encontrar el país al que pertenece y mostrar: 
    * a. El nombre y código ISO del país 
    * b. Moneda local y su cotización actual en dólares o euros. 
- 2 Ban/Blacklist de una IP: marcar la ip en una lista negra no permitiéndole consultar la información del punto 1.  

##### Observaciones
- Tener en cuenta que el punto 1 puede recibir fluctuaciones agresivas de tráfico.

##### Consideraciones:
- Se solicita una solución con un diseño OOP.
- Preferentemente en Java, Kotlin o Groovy.
- Incluir tests que aseguren el correcto funcionamiento de la API. Idealmente de caja blanca (unitarios) y caja negra (end to end / funcionales).
- Es deseable que la aplicación pueda correr, ser construida y ejecutada dentro de un contenedor Docker (incluir un Dockerfile e instrucciones para ejecutarlo).
- La aplicación deberá hacer un uso racional de las APIs, evitando hacer llamadas innecesarias.
- La aplicación no deberá perder su estado ante un shutdown.
- Además de funcionamiento, prestar atención al estilo y calidad del código fuente.

## Solución 1
### Docker Compose
* Paso 1
    - Clonar el proyecto y ubicarse en la misma ruta donde se ubica el archivo "docker-compose.yml"
* Paso 2
    - Ejecutar la siguiente instrucción
    ```sh
    docker-compose up
    ```
* Paso 3
    - Para realizar una prueba utilizar el siguiente enlace (se puede utiliaza las ip Pruebas al final de la documentación para comprobar las respuestas)
    ```sh
    localhost:8000/evaluate-fraud/186.84.91.60
    ```
## Solución 2
### Docker
##### Servicio Evaluacion Ip:
* Paso 1
    - Ubicarse en la carpeta del proyecto "evaluacion-ip"
* Paso 2
    - Creación de la imagen
    ```sh
    docker build -t kfroman/evaluacion-ip:0.0.1.RELEASE .
    ```
* Paso 3 
    - Creación del contenedor
    ```sh
    docker run -d -p 8001:8001 --name=evaluacion-ip kfroman/evaluacion-ip:0.0.1.RELEASE
    ```
* Paso 4
    - Para verificar el servicio usar el siguiente enlace
    ```sh
    localhost:8001/evaluate-ip/186.84.91.63
    ```
##### Servicio Evaluacion Fraude:
* Paso 1
    - Ubicarse en la carpeta del proyecto "evaluacion-fraude"
* Paso 1
    - Creación de la imagen
    ```sh
    docker build -t kfroman/evaluacion-fraude:0.0.1.RELEASE .
    ```
* Paso 2 
    - Creación del contenedor
    ```sh
    docker run -d -p 8000:8000 --env WS_BLACK_LIST=http://evaluacion-ip --name=evaluacion-fraude --link evaluacion-ip kfroman/evaluacion-fraude:0.0.1.RELEASE
    ```
* Paso 3
    - Para verificar el servicio usar el siguiente enlace (el cambio se muestra en euros)
    ```sh
    localhost:8000/evaluate-fraud/186.84.91.60
    ```

## Pruebas

### Lista de Ips que retorna la información solicitada y la equivalencia en moneda local de 1 euro
- 186.84.91.60 (Colombia)
- 1.1.1.1 (Australia)
- 190.5.7.12 (Argentina)

### Lista de IP's en la Blacklist para pruebas del servicio "Evaluacion Fraude"
- 186.84.91.59
- 186.84.91.61
- 186.84.91.62
- 186.84.91.63
- 186.84.91.64
- 186.84.91.65
- 186.84.91.66
- 186.84.91.67
- 186.84.91.68
- 186.84.91.69
- 186.84.91.70
- 186.84.91.71
- 186.84.91.72
- 186.84.91.73
- 186.84.91.74
- 186.84.91.75