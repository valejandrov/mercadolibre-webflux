# Examen Mercadolibre

## Objetivo

Desarrollar una aplicación que detecte si una secuencia de ADN corresponde a un mutante con las siguientes consideraciones:


1. Crear una función que reciba como parámetro la secuencia mediante un array de Strings. La firma de la función es isMutant(dna).

2. Crear una API Rest, hostear esa API en un cloud computing libre (Google App Engine, Amazon AWS, etc), crear el servicio “/mutant/” en donde se pueda detectar si un humano es mutante enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el siguiente formato:
    ```
    POST → /mutant/
    {“
    dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
    }
    ```

En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un
403-Forbidden

3. Anexar una base de datos, la cual guarde los ADN’s verificados con la API. Solo 1 registro por ADN. Exponer un servicio extra “/stats” que devuelva un Json con las estadísticas de las verificaciones de ADN: {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}

**Observaciones**
-Tener en cuenta que la API puede recibir fluctuaciones agresivas de tráfico (Entre 100 y 1 millón de peticiones por segundo).
-Test-Automáticos, Code coverage > 80%


## Instrucciones de uso

### API Rest deployada en Google Cloud Platform

URL: https://gcp-spring-281323.ue.r.appspot.com

**Servicios**

- Detección de mutante
    Request:
        ```
        POST: 
        ```
    Response:
        ```
        200 OK          --> Es mutante
        403 FORBIDDEN   --> Es humano
        ```
        
- Estadística
    Request:
        ```
        GET: 
        ```
    Response:
        ```
        {
            count_mutant_dna: 40,
            count_human_dna: 100,
            ratio: 0.4
        }
        ```    
