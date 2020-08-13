# evaluacion-fraude
El ejercicio consiste en construir una API Rest que permita: 
    - 1. Dada una dirección IP, encontrar el país al que pertenece y mostrar: a. El nombre y código ISO del país b. Moneda local y su cotización actual en dólares o euros. 
    - 2. Ban/Blacklist de una IP: marcar la ip en una lista negra no permitiéndole consultar el la información del punto 1.

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
