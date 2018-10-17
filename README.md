# Mizuho Price Service

Take home project for Mizuho ESB role

## Features

* JMS topic for consuming and storing Price data into an in-memory cache
* REST interface to get Prices by Vendor
* REST interface to get Prices by Instrument
* Socket component for integration with Bloomberg that routes to the Price topic
* FTP component for integration with IG Group that routes to the Price topic
* Error routing to dead queues
* Scheduler for removing Prices from the cache that are older than 30 days
* Unit tests, integration tests and an end-to-end test
* UML diagrams

## Design decisions

* Camel as an EIS framework
* Active MQ as a message broker
* Domain models used for internal persistence
* Inheritance structure for modelling Instruments
* DTOs for external contracts
* JSON used for message data formats
* Guava HashBasedTable for cache to enable query by Vendor and Instrument
* Thread safe Maps for repositories

## Technologies

* Java 8
* Spring Boot
* Maven
* Camel
* Active MQ
* Guava
* JUnit
* Mockito
* AssertJ
* Awaitility
* Jackson

## Enhancements

* Persist Prices to an SQL database
* Change message format to XML and use XSD schema validation
* Idempotent consumers
* Java docs where required
* Logback and better logging
* Dockerise for use in containerised environment

## UML

Plant UML used to create diagrams (.puml)
Diagrams have been exported to .png

* Sequence diagram in `./docs`
* Class diagram in `./docs`

## Building

* `./mvnw clean install`


