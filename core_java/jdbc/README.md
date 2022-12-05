# Introduction

---

The JDBC project is to make an API call to the POSTGRESQL in the local machine. CRUD operations was performed using the **Data Access Object**. 
The **Java Database Connectivity** library is used. DBeaver workbench was used to analyze the table, create the ERD, and also execute the SQL scripts to insert
the data. 
Technologies used: Java 8, POSTGRESQL, DBeaver, Maven, Docker, Git, IntelliJ

## Design Patterns

---

There are 2 types of Design Patterns, DAO and Repository. In this application, the DAO was used. The DatabaseConnectionManager, is the Driver. This class
is in charge of connecting to the database. The Customer DAO was used to handle the CRUD operations. The results received or to be sent from the Database will be handled by the
CustomerDTO. The repository pattern is in which data is behaved as collection. The crug operations involve: contains, add, remove, etc. The 

## Implementation

---

### ER Diagram

---

![image](https://user-images.githubusercontent.com/46577410/205726731-0157f28e-b02a-4e47-ba18-4a4500a615af.png)



## Design Patterns

---

### Data Access Object Design Pattern

There are 3 main components in a DAO design pattern (4 if you include the http helper class). The first main component is the driver. This creates and handles
the connection to the database. The DAO class is the second component, this handles the Create, Read, Update, Delete (CRUD) operations. The third component is the
DTO object. This holds the data that will be received from the database, or to be inserted into the database. 

### Repository Design Pattern

The repository pattern is a very straight forward pattern in which there is the data layer, and the business/logic (Service) layer. The business layer will handle how
the data will be handled. The repository will handle the collection of objects. It is good for easy and straight forward implementation, but it is very hard
to handle requests that are highly granular in nature.

## Testing

---

The DBeaver workbench was used to set up the scripts and worked as an interface to create sql statements to see if the CRUD operations that were sent through the JDBC
app was matched with the results from the database. 


