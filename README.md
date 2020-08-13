# Ejercicio de programación
El ejercicio consiste en construir una API Rest que permita: 
##### 
- 1Dada una dirección IP, encontrar el país al que pertenece y mostrar: a. El nombre y código ISO del país b. Moneda local y su cotización actual en dólares o euros. 
- 2Ban/Blacklist de una IP: marcar la ip en una lista negra no permitiéndole consultar el la información del punto 1.  

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

### Docker
##### Pasos:
* Paso 1
    - Creación de la imagen
    ```sh
    docker build -t klinux/evaluacion-fraude:0.0.1.RELEASE . 
    ```
* Paso 2 
    - Crear contenedor con el nombre evaluacion-fraude
    ```sh
    docker run -d -p 8000:8000 --name=evaluacion-fraude klinux/evaluacion-fraude:0.0.1.RELEASE
    ```
