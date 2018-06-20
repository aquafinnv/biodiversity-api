# Biodiversity API [![Build Status](https://travis-ci.org/g00glen00b/biodiversity-api.svg?branch=master)](https://travis-ci.org/g00glen00b/biodiversity-api)

## What is this?
The biodiversity API is a small webservice built with Spring boot, which will import [GBIF datasets](http://gbif.org)
and allow spatial queries to see if there is any of the imported species nearby.


## Installation
To install the application, you need to have either [Maven 3](https://maven.apache.org) installed, or you need to use
the local Maven wrapper (`./mvnw`). Additionally to that, it's recommended to use [Docker](https://www.docker.com/) to
run the application.

Since the application uses GBIF datasets from [gbif.org](http://gbif.org), you need to download some datasets first.
Sign up for an account and download the datasets using the **Darwin Core Archive** format. Downloading these datasets
might take a while, so be prepared. When downloaded, place them inside a folder called **datasets/**.

To build the application, you can use the following command:

```
mvn package dockerfile:build
```

This command will build the Java application into a JAR file, and create a Docker image.

Additionally to that, you also have to create a file called `.env` and add the following properties:

```
DATABASE_USER=<username for your database>
DATABASE_PASSWORD=<password for your database>
```

For example:

```
DATABASE_USER=admin
DATABASE_PASSWORD=secret
```

## Running the application
To run the application, you can use Docker compose:
```
docker-compose up
```

Once the application runs, it will start to import the datasets. This can take a few minutes, as it can load about
~1500 occurrences every second.

If you use a separate volume for your database container, you can disable running the batch the next time you start by
setting the `SPRING_BATCH_JOB_ENABLED=false` environment property within `docker-compose.yml`.


If you want to run the Java application directly, you can configure the `SPRING_DATASOURCE_URL`,
`SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD` and `API_GBIF_LOCATION_PATTERN` environment variables
locally.

You can also run Docker compose to run only the database:

```
docker-compose -f docker-compose-db.yml
```

## Continuous integration
The project is built on [Travis CI](https://travis-ci.org/g00glen00b/biodiversity-api), which will run the tests and push the generated documentation onto the gh-pages branch.


## API
When the application is up and running, the API documentation will be available at
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html). Additionally to that, you can also find
the documentation at [gh-pages](http://g00glen00b.github.io/biodiversity-api).

The documentation is automatically generated using the following command:

```
mvn test swagger2markup:convertSwagger2markup asciidoctor:process-asciidoc
```