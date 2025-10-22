# About
This project explores Java Database Connectivity (JDBC), Jakarta Persistence API (JPA) and Jakarta Data API. The project is structured as a Maven multi-module:

- `common-entities`: Database entity, data transfer objects (DTO classes), mapper interface to convert DTO to entity and vice versa, and helper classes.
- `jdbc-demonstrations`: Implementation details with Java Database Connectivity (JDBC).
- `jpa-demonstrations`: Implementation details with Jakarta Persistence API.
- `Jakar-data-demonstrations`: Implementation details with Jakarta Data API.

The SQL script is provided at `sql-base-construct.sql`. There are 5 tables: 

- customers
- products
- orders
- order_items
- payments

## Required Dependencies

We look at the dependencies required for all the sub-module defined in the prorject. The dependencies defined `<dependencies>` are inherited by all the sub-modules. Refer to the pom file `<dependencyManagement>` and `<properties>` to view the dependencies versions.

```
<!-- mariadb is the underlying database -->
<dependency>
	<groupId>org.mariadb.jdbc</groupId>
	<artifactId>mariadb-java-client</artifactId>
</dependency>

<!--logging-->
<dependency>
	<groupId>org.apache.logging.log4j</groupId>
	<artifactId>log4j-api</artifactId>
</dependency>

<dependency>
	<groupId>org.apache.logging.log4j</groupId>
	<artifactId>log4j-core</artifactId>
</dependency>

<dependency>
	<groupId>org.apache.logging.log4j</groupId>
	<artifactId>log4j-slf4j2-impl</artifactId>
</dependency>
```

# Using Eclipse SQL Results and Data Source Explorer to Run SQL Queries
- Download mariadb client: [maven](https://repo1.maven.org/maven2/org/mariadb/jdbc/mariadb-java-client/3.5.6/)
- Open `Data Source Explorer` (Window -> Show View -> Other... -> Data Source Explorer)
- In Data Source Explorer: Under Database Connection right click new...
- Select generic JDBC driver.
- Click on new driver definition. Under Jar list -> add jar, select the downloaded jar file. 
- Provide the database and connection details. 
- Right on the created connect and select open sql scrapbook to run sql queries. 
- The executed results are shows in `SQL Results` in window -> show view.
