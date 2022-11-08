#Introduction

---

The JDBC project is to make an API call to the POSTGRESQL in the local machine. CRUD operations was performed using the **Data Access Object**. 
The **Java Database Connectivity** library is used. DBeaver workbench was used to analyze the table, create the ERD, and also execute the SQL scripts to insert
the data. 
Technologies used: Java 8, POSTGRESQL, DBeaver, Maven, Docker, Git, IntelliJ

##Design Patterns

---

There are 2 types of Design Patterns, DAO and Repository. In this application, the DAO was used. The DatabaseConnectionManager, is the Driver. This class
is in charge of connecting to the database. The Customer DAO was used to handle the CRUD operations. The results received or to be sent from the Database will be handled by the
CustomerDTO. The repository pattern is in which data is behaved as collection. The crug operations involve: contains, add, remove, etc. The 
