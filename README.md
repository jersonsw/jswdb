# JSWDb

This project is meant for extracting movies and TV Shows information from [IMDb](www.imdb.com) and to store the obtained data into our own database for further queries. These information are extracted through web scraping, driven by a batch process.

The project follows the principles of an [Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture/) (aka Ports & Adapters) exposed by Alistair Cockburn. It's compound of the following modules:

* **core**: _Domain + Ports + Usecases_.
* **data-adapter**: _Adapters intended for persistence purposes_.
* **scraping-adapter**: _Adapters meant for scraping purposes_.
* **main**: _The infrastructure; the root where all the above modules gets glued and the application gets bootstrapped_.
* **web-ui**: _The web interface from which the batch process gets triggered and can be monitored_.
![Hexagonal Architecture](https://github.com/jersonsw/jswdb/blob/master/JSWDb.png?raw=true)

The following are the technologies I used for building this project:

* [**Java**](https://docs.oracle.com/en/java/): the language used in the whole project at the backend.
* [**Spring Boot**](https://spring.io/projects/spring-boot): the framework used for providing infrastructure and autoconfiguration. Also, several dependencies of it are used for exposing http and websocket endpoints, for batch processing, security, validation, etc...It is used at the **main** module.
* [**Spring Batch**](https://spring.io/projects/spring-batch): It is used in the main module for batch processing.
* [**PostgreSQL**](https://www.postgresql.org/): Database used for storing the data extracted from the IMDb website. The driver for this database is included as dependency in the infrastructure module (**main**).
* [**Jsoup**](https://jsoup.org/): A Java-based library for scraping and parsing HTML from a URL, file, or string.
* [**ReactJS**](https://es.reactjs.org/): Javascript library for building UIs.

The project was built with Maven following a multimodule-based approach, having a parent POM for dependencies management and a child POM for each module.

The below image is a screenshot of the UI in which you can see the progress of the scraping activity:

![Job Execution](https://github.com/jersonsw/jswdb/blob/master/JSWDb%20UI.png?raw=true)
