# JSWDb

The aim of this project is to extract movies and TV Shows information from [IMDb](www.imdb.com) and store them in your own database for further queries. These information are extracted through web scraping, driven by a batch process.

The project follows the principles of an [Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture/) (aka Ports & Adapters) exposed by Alistair Cockburn. It is compound of the following modules (in this case, sub-projects):

* **core** (_entities, use cases and ports_)
* **data-adapter** (_adapters or implementations of the driven ports intended for persistence purposes_)
* **scraper-adapter** (_adapters or implementations for scraping purposes_)
* **main** (_The infrastructure; the root where the all the above modules gets glued and where the application gets bootstrapped_)
* **web-ui** (_The web interface from which the batch process is started and monitored_)
