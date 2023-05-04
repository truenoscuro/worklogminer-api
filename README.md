# Proyecto Base Spring Boot

## Versiones:

- **JDK:** openjdk-17.0.2
- **Spring Boot:** 3.0.2
- **Estilos:** intellij-java-google-style.xml
- **Inspecciones:** intellij-3digits-inspections.xml

## Descripción

Proyecto de API REST pensado para utilizarse junto al **Proyecto Base
Angular** (https://gitlab.3digits.es/3digits/proyecto-base-angular). El proyecto tiene Swagger
instalado y cuenta con diferentes CRUDs de entidades genéricas.

## Antes de empezar

### Instalación del proyecto

* Ejecutar 'Application'.

### Cache de traducciones

* La cache funciona mediante 3 anotaciones principalmente
    * @Cacheable  : Guarda la traducción en cache
    * @CachePut   : Actualiza el registro en la cache
    * @CacheEvict : Elimina el registro en la cache
* Información a tener en cuenta:
    * La key de las traducciones es el id de lenguaje y el id del label
    * El nombre de la cache es translations

### application.yaml

Archivo de propiedades de la aplicación. Desde aquí podemos definir los datos de la conexión a base
de datos y el SECRET que utilizará la aplicación para firmar los tokens JWT.
**IMPORTANTE: Cambiar SECRET del JWT para que sea seguro, puesto que el que está ahora mismo en la
base es dummy.**

## Seguridad

### Autenticación

Los controladores de los CRUDs están protegidos por Spring Security con autenticación por JWT Token.
Para acceder a las rutas protegidas se necesita que las peticiones tengan un token de JWT generado
por la aplicación en el header **Authorization**. Para inciar sesión y generar un token se tiene que
realizar una petición POST al endpoint /api/authentication/authenticate con las credenciales del
usuario en el body, el cual nos devolverá el token.

### Spring Security

La configuración de Spring Security se encuentra en **
es.tresdigits.base.configuration.WebSecurityConfiguration**. Desde esta clase podemos controlar que
rutas protegeremos. Actualmente, todas las rutas están protegidas salvo aquellas referentes a la
autenticación, Swagger y listar etiquetas.

Además, desde la configuración de Spring Security podemos configurar los filtros por los que van a
tener que pasar las peticiones para que la aplicación las acepta, entre los que se encuentra el **
JwtAuthenticationFilter**, encargado de decodificar el token del header "Authorization" y comprobar
si las credenciales son correctas.

### JwtTokenUtil

Es la clase que emplea la aplicación para generar, decodificar y validar tokens en la aplicación. El
método que se utiliza para validar los tokens en el **JwtAuthenticationFilter** es _validate()_.
Este método comprueba si el token ha expirado y el token ha sido generado por la propia aplicación.

## Estructura de un CRUD

Un CRUD, por lo general, está formado por un RestController, un Service, una Entidad, un DTO, un
Mapper y un Repository. Algunas de estas clases pueden estar o no dependiendo de los requisitos del
CRUD.

- ### Controller:

Son las clases que van anotadas con **@RestController** y en las que tendremos los endpoints que se
expondrán al frontal. Todos los métodos devuelven una ResponseEntity, que contendrá una CommonRs que
encapsulará los datos que queramos devolver. Las requests que tengan body tendrán que tener el
formato de una CommonRQ, conteniendo el campo _data_ los datos enviados por el frontal. Para formar
las respuestas se tiene que utilizar la clase **CommonUtils**. Más información
en: https://3digits.atlassian.net/wiki/spaces/DES/pages/92083280/Creaci+n+de+controladores

- ### Service:

Son las clases que van anotadas con **@Service** y que contienen la lógica de negocio. Contaremos
con una interfaz llamada _IEntidadService_ en la que declarán los métodos, y la clase de _
EntidadService_, que implementará la interfaz y desarrollará la lógica de los métodos. Para realizar
las operaciones a base de datos empleará el _Repository_ de la entidad. Este tipo de clases realiza
mapeos de entidad a DTO mediante el _Mapper_  y devuelve DTOs.

- ### Repository:

Son las clases anotadas con **@Repository** y que se encargan de realizar operaciones sobre la base
de datos. Extienden de JpaRepository. Cuentan con muchos métodos autogenerados, pero siempre puedes
crear más métodos usando la convención de JPA de los nombre de los métodos para que te genere él
nuevos tipos de consultas o crear las tuyas desde cero con **@Query**. Este tipo de clases devuelven
instancias de _Entity_.

- ### Entity:

Son clases anotadas con **@Entity** y que representan una tabla de la base de datos. Para más
información sobre como crear este tipo de clases consulta el siguiente
link: https://3digits.atlassian.net/wiki/spaces/DES/pages/92083330/Creaci+n+de+entidades

- ### DTO:

Son clases POJO que sirven para mapear una entidad y enviar y recibir información del frontal.
Contiene los campos de una entity que se van a envir al frontal. **Los campos son los mismos,
eliminando el prefijo del la tabla de los nombres de los campos. Ej: exaName -> name.**

Para las relaciones @ManyToOne, un DTO puede contar con un campo para la relación que sea otro DTO
anidado y un campo que sea el ID de la relación. Ej: User está relacionado con Language por una
relación @ManyToOne. UserDto tendrá dos campos para representar el Language: LanguageDto languageDto
y Integer codLang. Esta regla se aplica a todos los DTOs de entidades que tengan este tipo de
relaciones. A la hora de pasar de _Entity_ a DTO, lo que dictaminará como se tratan las relaciones
será el _Mapper_

- ### Mapper:

Son las clases encargadas de realizar el mapeo de _Entity_ a DTO y viceversa. Los mappers del
proyecto emplean **Mapstruct**. A modo de ejemplo, está el _UserMapper_, que contiene métodos para
mapear DTOs sin relaciones y con relaciones. **Al crear un Mapper, lo añadiremos a la clase
MapperBuilder, siguiendo el ejemplo del resto de Mappers**.

## Paginación, Ordenación y Filtrado

El proyecto soporta estas acciones. Se realizan en la capa de negocio y se aplican al realizar la
consulta a la base de datos.

### Integrar paginación, ordenación y filtrado en un CRUD

#### En el Controller:

El endpoint que reciba el TableContextDto desde el front deberá ser un POST. Además, recibirá una
PaginatedResponse del _Service_ y, al formar la respuesta, le pasará al CommonUtils.processResponse
el totalRows de la PaginatedResponse.

#### En el Repository:

Modificaremos el _Repository_ para que exitienda de JpaSpecificationExecutor. De esta manera, el _
Repository_ tendrá soporte para las Specification.

#### En el DTO:

Añadiremos el método estático de _getRelatedFields_. Lo que hace este método es devolver un Map<
String, String> asociando los nombres de los campos del DTO a los nombres de los campos de la
entidad. Utilizaremos este Map en el _Service_ a la hora de realizar el filtrado y la ordenación.

#### En el Service:

Instanciermos un GenericSpecificationsBuilder que sera el que utilicemos para generar el
Specification que necesita el _Repository_ para realizar el filtrado. El método devolverá una
PaginatedResponse encapsulando los datos que queremos enviar al front.

### Clases relacionadas

#### TableContextDto

Es el DTO que recibimos del front con la página a obtener, filtros que aplicar, orden, número de
resultados a obtener, etc. A partir de este objeto, devolveremos una respuesta paginada.

#### PaginatedResponse

Es el objeto en el que encapsulamos la respuesta de la consulta aplicando paginación, ordenación y
filtrado. Contiene los datos a devolver y el número total de registros (no confundir con el número
de registros de la página).

#### es.tresdigits.base.common.model.Direction

Es el enum que asocia la Direction que recibimos del front (1, -1) con la dirección en la que se va
a ordenar la respuesta (ASC, DESC).

## Fechas

El Proyecto Base Angular trata las fechas en formato EpochMilli. Para tratar esto contamos con
Serializers y Deserializers de LocalDateTime a EpochMilli y viceversa. De esta manera, cuando
envíemos un LocalDateTime al frontal, llegará en formato EpochMilli; y cuando recibamos una fecha en
formato EpochMilli, se parseará a LocalDateTime.

Esta acción la realiza el **Jackson**. Para settear un Serializer y/o un Deserialezer en un DTO
haremos uso de las anotaciones _@JsonSerialize_ y _@JsonDeserialize_ respectivamente. En **
ExampleDto** tenemos un ejemplo de como utilizar estas anotaciones.