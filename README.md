# JSWDb

The aim of this project is to extract movies and TV Shows information from [IMDb](www.imdb.com) and store them in your own database for further queries. These information are extracted through web scraping, driven by a batch process.

The project follows the principles of an [Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture/) (aka Ports & Adapters) exposed by Alistair Cockburn. It is compound of the following modules (in this case, sub-projects):

* [**core**](https://github.com/jersonsw/jswdb/tree/master/core) (_entities, use cases and ports_)
* [**data-adapter**](https://github.com/jersonsw/jswdb/tree/master/data-adapter) (_adapters or implementations of the driven ports intended for persistence purposes_)
* [**scraping-adapter**](https://github.com/jersonsw/jswdb/tree/master/scraping-adapter) (_adapters or implementations for scraping purposes_)
* [**main**](https://github.com/jersonsw/jswdb/tree/master/main) (_The infrastructure; the root where all the above modules gets glued and where the application gets bootstrapped_)
* [**web-ui**](https://github.com/jersonsw/jswdb/tree/master/web-ui) (_The web interface from which the batch process gets triggered and can be monitored_)
![Hexagonal Architecture](https://github.com/jersonsw/jswdb/blob/master/JSWDb.png?raw=true)

The following are the technologies I've used for building this project:

* [**Java**](https://docs.oracle.com/en/java/): the language used in the whole project at the backend.
* [**Spring Boot**](https://spring.io/projects/spring-boot): the framework used for providing infrastructure and autoconfiguration. Also, several dependencies of it are used for exposing http and websocket endpoints, for batch processing, security, validation, etc...It is used at the **main** module.
* [**Spring Batch**](https://spring.io/projects/spring-batch): It is used in the main module for batch processing.
* [**PostgreSQL**](https://www.postgresql.org/): Database used for storing the data extracted from the IMDb website. The driver for this database is included as dependency in the infrastructure module (**main**).
* [**Jsoup**](https://jsoup.org/): A Java-based library for scraping and parsing HTML from a URL, file, or string.
* [**ReactJS**](https://es.reactjs.org/): Javascript library for building UIs.

The below image is a screenshot of the UI in which you can see the progress of the scraping activity:

![Job Execution](https://github.com/jersonsw/jswdb/blob/master/JSWDb%20UI.png?raw=true)
