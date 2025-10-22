# About
This module explores Jakarta Persistence API (JPA) with Hibernate. The underlying JPA provider is Hibernate. The database properties are defined at `src/main/resources/META-INF/persistence.xml`. The database password is not hardcoded in the file therefore it should be passed during application startup.

## About Project classes

We look at the primary classes involved in this module.

- `DBOperationsJPA.java` is an abstract class defining CRUD operations for all the 5 tables.
- `JPAHibernateExample.java` extends the `DBOperationsJPA` class. This class defines a simple JPA operations using the JPA defined connection interfaces.
- `JPAHibernateWithHikariExample` extends the `DBOperationsJPA` class. This class demonstrates JPA connection pooling with `Hikari` library.
- `JPAAppMain.java` is the modules main class defining the CRUD operations.

## Required Dependencies

We look at the dependencies required for this module.

```
<dependency>
	<groupId>com.example.javadbexplorer</groupId>
	<artifactId>common-entities</artifactId>
	<version>1</version>
</dependency>

<!-- contains the jakarta.persistence module -->
<dependency>
	<groupId>jakarta.persistence</groupId>
	<artifactId>jakarta.persistence-api</artifactId>
</dependency>

<!--object relation mapping provider (ORM), provides the JPA implementation-->
<dependency>
	<groupId>org.hibernate.orm</groupId>
	<artifactId>hibernate-core</artifactId>
</dependency>

<!-- required for connection pooling -->
<dependency>
	<groupId>com.zaxxer</groupId>
	<artifactId>HikariCP</artifactId>
</dependency>
```

# Starting the Application with Eclipse

Follow the steps to start the application.

- Right click on project (`jpa-demonstration`) -> Run As -> Run configurations.
- On the left side menu ensure the configuration is under **Java Application**.
- On the right side menu navigate to `Arguments` tab
- Populate the `VM Arguments` Field with: `-DDB_PASS=<database-password>`.

- Both the `org.hibernate.Session` API and `jakarta.persistence.EntityManager` API represent a context for dealing with persistent data. This concept is called a persistence context. Persistent data has a state in relation to both a persistence context and the underlying database.
- `persist()`: Inserts the entity object into database. Reusing (detached entity) the same object would result in `EntityExistsException`.
- `merge()`: Can save be used to insert new or detached entity.
- The EntityManager is the interface that lets us interact with the persistence context. Whenever we use the EntityManager, we are actually interacting with the persistence context.
