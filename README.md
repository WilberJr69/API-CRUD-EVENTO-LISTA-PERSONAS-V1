# API-CRUD-EVENTO-LISTA-PERSONAS-V1

Anécdota por la cuál comencé a diseñar esta api:

Era época de octubre, quise organizar un evento para halloween el 31 de octubre y sacar ganancias vendiendo entradas. Idea la cuál no se llevé a cabo por motivos de espacio para realizarlo. Me quedé con el mal sabor, y bueno, ya en mi computadora me dije: ¿Cómo funciona una pagina que permite publicar, gestionar eventos? Ya sea para la venta de entradas y más.

Entonces, partí de lo más básico, y propuse unas condiciones también básicas para mi versión 1 de esta Api:

- Un evento puede contener varias listas (como una lista VIP, General, etc), y en cada lista del evento, se tiene que definir a la cantidad de personas que pueden haber.
- Asimismo, una persona puede inscribirse a varios eventos, ya que por lo general a lo largo del tiempo las personas suelen ir a diferentes eventos que se dan en diferentes fechas

Ahora, condiciones técnicas a abarcar:

- Java version: 22
- SpringBoot version: 3.3.4
- Gestor de dependencias: Gradle
- SGBD: Mysql

Dependencias para este proyecto:

- Spring Data JPA
- Loombok (Para simplificar Getter y Setter)
- Spring Web (Para RestFul y ApacheTomcat)
- MySQL Driver
- PostMan: para probar los request

Condiciones técnicas a restirngir:

- No usar DTOs, solo con el manejo de Entitys. Lo que limitará a la estructura de algunas salidas de datos al trabajar con JSON en los request.
- No usar JWT, SpringSecurity o OAuth. No se implementará algún tipo de seguridad en la aplicación ni en la Api.
- No usar MapStruct
- No realización de pruebas ni configuraciones de documentación.

Ahora toda la información adicional puedes encontrarla en mi page de Notion para que puedas visuaizar todos mis avances sobre esta api y futuras versiones con implementaciones, donde encontrarás, el json del postman de los request:
https://burly-haircut-0b8.notion.site/Versiones-Api-Backend-para-Eventos-con-Spring-1274a97b2168802f8808f714874d0891
