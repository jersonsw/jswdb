# JSWDb

The aim of this project is to extract movies and tv shows information from [IMDb](www.imdb.com) and store them in your own database for further queries. These information are extracted through web scraping, driven by a batch process.

The project follows the principles of an Hexagonal Architecture (aka Ports & Adapters) exposed by Alistair Cockburn. It is compound of the following modules (in this case, sub-projects):

* core (contains the Entities, Use Cases and Ports)
* data-adapter (contains the adapters or implementations of the driven ports intended for persistence)
