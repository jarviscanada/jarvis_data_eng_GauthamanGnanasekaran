# Linux Cluster Monitoring Agent
___
## Introduction
This project is a building block to eventually manage a cluster of 10 server that runs the CentOS 7. Ideally the servers
are connected to a switch which enables them to communicate. This project will include scripts that are written in bash
and PSQL, which will show the hardware usage and specifications of the local machine. This is saved to a PostgresSQL
container that is created from the docker hub. The machine uses CRON to update the data to the server on each minute.

### Architecture and Design
![img.png](img.png)





### Database and tables
The database created in PostGresSQL