# About

This consists of Database entity, data transfer objects (DTO classes), mapper interface to convert DTO to entity and vice versa, and helper classes.

## Required Dependencies

We look at the dependencies required for this module.

```
<!-- the generated entity classes contain annotation present in this module (JPA 3.2) -->
<dependency>
	<groupId>jakarta.persistence</groupId>
	<artifactId>jakarta.persistence-api</artifactId>
</dependency>

<!-- this dependency is required to generated mapper implementation (mapping DTO to Entity and Entity to DTO) -->
<dependency>
	<groupId>org.mapstruct</groupId>
	<artifactId>mapstruct</artifactId>
</dependency>
```

## Maven Plugin Configuration

We look at some additional Maven plugin configuration required for this module.

- `hibernate-processor` plugin generates the metamodel classes.
- `mapstruct-processor` plugin generates the map struct implementation class.
- `build-helper-maven-plugin` plugin adds the specified folder as source during Maven compilation.

```
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-compiler-plugin</artifactId>
	<configuration>
		<annotationProcessorPaths>
			<!--hibernate jpa metamodel generator, it generates the implemenation classes as '<Entity>_' -->
			<path>
				<groupId>org.hibernate.orm</groupId>
				<artifactId>hibernate-processor</artifactId>
				<version>${hibernate-core.version}</version>
			</path>
			<!--map struct implementation generator module-->
			<path>
				<groupId>org.mapstruct</groupId>
				<artifactId>mapstruct-processor</artifactId>
				<version>${org.mapstruct.version}</version>
			</path>
		</annotationProcessorPaths>
	</configuration>
</plugin>
<plugin>
	<!-- Adds the specified path as a source folder so that both Maven and Eclipse recognize it as part of the project sources -->
	<groupId>org.codehaus.mojo</groupId>
	<artifactId>build-helper-maven-plugin</artifactId>
	<version>3.6.1</version>
	<executions>
		<execution>
			<id>add-source</id>
			<phase>generate-sources</phase>
			<goals>
				<goal>add-source</goal>
			</goals>
			<configuration>
				<sources>
					<source>target/generated-sources/annotations/</source>
				</sources>
			</configuration>
		</execution>
	</executions>
</plugin>
```

# Generating Java Entity Class

In this section we explore how Java entity class can be auto generated from an existing table. Two possible way are using the `hibernate-core` dependency and other is adding the `hibernate-tools` plugin. 

## Using `hibernate-core` dependency

In this method we only need to add the `hibernate-core` dependency and *do not need to make any plugin configuration changes*.

- Add the following dependency in the pom file:

```
<properties>
	<hibernate-core.version>7.1.3.Final</hibernate-core.version>
</properties>
....
<dependency>
	<groupId>org.hibernate.orm</groupId>
	<artifactId>hibernate-core</artifactId>
	<version>${hibernate-core.version}</version>
</dependency>
```

- The dependency looks for a `hibernate.properties` or `META-INF/persistent.xml` file.
- Create a `hibernate.properties` file with the following content. **In the file the password is parameterized**.

```
hibernate.connection.driver_class=<database-driver>
hibernate.connection.url=<database-url>
hibernate.connection.username=<database-url>
hibernate.connection.password=${DB_PASS}
```

After adding the relevant configuration, you can choose any one approach (1 or 2) to generate the entity classes.

1. If Maven is installed and accessable from the CLI invoke the following command to generate the entity classes from the database tables:
	- `mvn org.hibernate.tool:hibernate-tools-maven:7.1.3.Final:hbm2java -DDB_PASS=<database-password>`.
	- The generated files are located at `target/generated-sources`.

2. To execute the Maven command directly from Eclipse: 
	- Right click on project (`common-entities`) -> Run As -> \[Run configurations ... or Maven build...\]
	- On the left side menu ensure the configuration is under **Maven Build**.
	- On the right side menu populate the **Goal** Field with: `org.hibernate.tool:hibernate-tools-maven:7.1.3.Final:hbm2java -DDB_PASS=<database-password>`.

	
## With Hibernate Tools Maven Plugin

In this method we do not need to add dependency (for generating the entities itself we do not require the `hibernate-core` dependency) but *require the `hibernate-tools-maven` plugin*.

- Add the following plugin in the pom file:

```
<properties>
	<hibernate-core.version>7.1.3.Final</hibernate-core.version>
</properties>
....
<build>
	<plugins>
		<plugin>
			<groupId>org.hibernate.tool</groupId>
			<artifactId>hibernate-tools-maven</artifactId>
			<version>${hibernate-core.version}</version>
			<dependencies>
				<dependency>
					<groupId>org.hibernate.tool</groupId>
					<artifactId>hibernate-tools-orm</artifactId>
					<version>${hibernate-core.version}</version>
				</dependency>
			</dependencies>
			<executions>
				<execution>
					<id>entity-generation</id>
					<phase>generate-sources</phase>
					<goals>
						<goal>hbm2java</goal>
					</goals>
					<configuration>
						<outputDirectory>${project.basedir}/src/main/java/</outputDirectory>
						<packageName>com.learn.model</packageName>
						<ejb3>true</ejb3>
					</configuration>
				</execution>
			</executions>
		</plugin>
	</plugins>
</build>
```

- The `<outputDirectory>` can be used to ensure the output directory to a specific path. Removing the `<outputDirectory>` tag would result in the entity files being generated in `target/generated-sources` path.
- The `<packageName>` ensures it is created in the relevant package and folder. Both `<packageName>` and `<outputDirectory>` are optional, removing them would result in the entity files being generated at `target/generated-sources` path.

After adding the relevant configuration, you can choose any one approach (1 or 2 or 3) to generate the entity classes.

1. If Maven is installed and accessable from the CLI invoke the following command to generate the entity classes from the database tables:
	- `mvn clean generate-sources -DDB_PASS=<database-password>`

2. To execute the Maven command directly from Eclipse: 
	- Right click on project (`common-entities`) -> Run As -> \[Run configurations ... or Maven build...\]
	- On the left side menu ensure the configuration is under **Maven Build**.
	- On the right side menu populate the **Goal** Field with: `clean generate-sources -DDB_PASS=<database-password>`.

3. Allow Eclipse to automatically generate during Maven build phases.
	- The plugin is binded to `generate-sources` phase of the Maven `default` build lifecycle.
	- `Build Automatically` should be enabled. (Navigate: `Project` -> `Build Automatically`)
	- NOTE: Since the entity generation is dependent on the `hibernate.properties` files to connect with the database, the database information in the file should be valid. In our case since the database password is passed as a argument, Eclipse does not generate the entity classes. Changing the database password argument to the actual value would result in the entity classes being generated automcatically. 

# Reference
- Using the hibernate tool with hibernate-core dependency [hibernate tools](https://hibernate.org/tools/)
- List of Hibernate Tools [list](https://github.com/hibernate/hibernate-tools).
- Tutorial to setup Hibernate Maven Plugin Tool [Hibernate Tools Maven Plugin](https://github.com/hibernate/hibernate-tools/blob/main/maven/docs/5-minute-tutorial.md)
- All available Hibernate maven tools properties [properties](https://github.com/hibernate/hibernate-tools/blob/main//maven/README.md)