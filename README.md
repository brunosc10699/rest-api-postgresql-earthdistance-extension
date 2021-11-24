# Get Distance - REST API

First of all, I would like to thank [André L. Gomes](https://github.com/anddrelugomes), IT Expert at DIO ([Digital Innovation One](https://lnkd.in/e2f-aeNX)), for his classes. It has contributed a lot to my growth in software development.

*If anyone else would like to take free lessons from him and other IT experts at DIO, hit this link: https://lnkd.in/e2f-aeNX*

This repository contains a simple REST API that uses PostgreSQL's "earthdistance" module to measure the distance between cities in Km. PostgreSQL calculates the distance itself.

## About PostgreSQL "earthdistance" module

> F.13. earthdistance
>
> F.13.1. Cube-Based Earth Distances
> F.13.2. Point-Based Earth Distances
>
> The earthdistance module provides two different approaches to calculating great circle distances on the surface of the Earth. The one described first depends on the cube module. The second one is based on the built-in point data type, using longitude and latitude for the coordinates.
>
> In this module, the Earth is assumed to be perfectly spherical. (If that's too inaccurate for you, you might want to look at the PostGIS project.)
>
> The cube module must be installed before earthdistance can be installed (although you can use the CASCADE option of CREATE EXTENSION to install both in one command).
>


Click this link to view the full documentation: https://www.postgresql.org/docs/14/earthdistance.html

## Requirements to run this API

PostgreSQL 10+
Java 11
Spring Boot 2.5.6
SQL Data Script

## Preparing the environment

### Create the database

psql -U postgres
create database geo;
\q

If you want to choose a different name for the database, remember to update the settings in the application-dev.properties file and also in the command in the next step.

### Import the cities.sql script

save the sql script on your computer: https://github.com/chinnonsantos/sql-paises-estados-cidades/tree/master/PostgreSQL/cities.sql
psql -U postgres geo -f cities.sql

### Activate PostgreSQL extensions

psql -U postgres
CREATE EXTENSION cube;
CREATE EXTENSION earthdistance;

update your postgres user password in the application-dev.properties file if you need to.

## Running the application

The endpoint receives two numeric parameters: "from" and "to". Both of them are city id.

URN (Uniform Resource Name)
/api/v1/cities

Query Params
/distance?from=**[city1 id]**&to=**[city2 id]**

Run the main application file and make a request from your browser or Postman at:
http://localhost:8080/api/v1/cities/distance?from=1&to=2

