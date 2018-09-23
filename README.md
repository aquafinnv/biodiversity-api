# Biodiversity API [![Build Status](https://travis-ci.org/g00glen00b/biodiversity-api.svg?branch=master)](https://travis-ci.org/g00glen00b/biodiversity-api) [![License](https://img.shields.io/github/license/g00glen00b/biodiversity-api.svg)](https://github.com/g00glen00b/biodiversity-api/blob/master/LICENSE)

## What is this?
The biodiversity API is a small webservice built with Spring boot, which will import [GBIF datasets](http://gbif.org)
and allow spatial queries to see if there is any of the imported species nearby.

## Installation

To use the application, you need to have [Docker](https://docker.com) installed. Additionally to that, you have to download GBIF datasets from [gbif.org](https://gbif.org). Sign up for an account and download the datasets using the **Darwin Core Archive** format.

> **Note**: Downloading these datasets might take a while and can be quite large, so be prepared.

After downloading these datasets, unzip them and place the folder inside a folder called **datasets/**.

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

## Development

For development, it's recommended to have either [Maven 3](https://maven.apache.org) installed, or you need to use
the local Maven wrapper (`./mvnw`). After that, you can build the Docker image using the following command:

```
mvn package dockerfile:build
```

If you want to run the Java application directly, rather than using the Docker image, you'll have to configure the following environment variables manually:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `API_GBIF_LOCATION_PATTERN`

For convenience, you can also use the following command to only run the database as a Docker container:

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
