# 1. About

A database is an organized collection of structured information, or data, typically stored electronically in a computer system. A database is usually controlled by a database management system (DBMS). Together, the data and the DBMS, along with the applications that are associated with them, are referred to as a database system, often shortened to just database. 

## 1.1 Character Set

A character set is a set of characters. A character set can have many collations associated with it, while each collation is only associated with one character set.

## 1.2 Collations

A collation comprises the rules for comparing and sorting a particular character set. For example, a subset of a character set could consist of the letters A, B, and C. A default collation could define these as appearing in an ascending order of A, B, and C.

## 1.3 Storage Engine

A database transaction symbolizes a unit of work, performed within a database management system (or similar system) against a database, that is treated in a coherent and reliable way independent of other transactions. A transaction generally represents any change in a database.

There are two types of database engines:

- *Transactional*: A transactional database is a DBMS that provides the ACID properties for a bracketed set of database operations (begin-commit). Transactions ensure that the database is always in a consistent state, even in the event of concurrent updates and failures. **InnoDB** is a storage engine for the database management system MySQL and MariaDB.
- *Non-Transaction*: A "no transaction" database engine refers to a database system that does not support the ACID (Atomicity, Consistency, Isolation, Durability) properties of transactions, often to achieve higher performance or scalability. **MyISAM engine** was used before MySQL 5.5 and it was replaced by **InnoDB**.

## 1.4 Database Management Systems

A database management system (DBMS) is a software system for creating and managing databases. A DBMS enables end users to create, protect, read, update and delete data in a database. It also manages security, data integrity and concurrency for databases. The DBMS essentially serves as an interface between databases and users or application programs, ensuring that data is consistently organized and remains easily accessible.

### 1.4.1 **Components of a DBMS**

- Hardware
- Software
- Data
- Procedures
- Database access query language
- Users

## 1.5 Types of DBMS

- Relational Database Management Systems (RDBMS)
    - MySQL
    - Oracle
    - Microsoft SQL Server
    - MariaDB
- NoSQL DBMS
    - MongoDB
    - Cassandra
    - Couchbase


## 1.6 Data Types

- CHAR(n): CHAR is a fixed-length data type. When you define a column as CHAR(n), it reserves n bytes of storage, padding shorter strings with spaces to meet the defined length. Use Case: Ideal for storing data where entries are of consistent length, such as fixed-length codes or identifiers.
- VARCHAR(n): VARCHAR is a variable-length data type. Defining a column as VARCHAR(n) allows storage of strings up to n characters, using only as much space as needed for each entry, plus additional bytes for length information. Use Case: Suitable for storing data with variable lengths, such as names or addresses.

# 2. Relational Databases

A relational database is a type of database that stores and provides access to data points that are related to one another. Relational databases are based on the relational model, an intuitive, straightforward way of representing data in tables. In a relational database, each row in the table is a record with a unique ID called the key. The columns of the table hold attributes of the data, and each record usually has a value for each attribute, making it easy to establish the relationships among data points.

The relational model means that the logical data structures, the data tables, views, and indexes, are separate from the physical storage structures. This separation means that database administrators can manage physical data storage without affecting access to that data as a logical structure. For example, renaming a database file does not rename the tables stored within it.

A database typically requires a comprehensive database software program known as a database management system (DBMS). A DBMS serves as an interface between the database and its end users or programs, allowing users to retrieve, update, and manage how the information is organized and optimized. A DBMS also facilitates oversight and control of databases, enabling a variety of administrative operations such as performance monitoring, tuning, and backup and recovery.

> Some examples of popular database software or DBMSs include MySQL, Microsoft Access, Microsoft SQL Server, FileMaker Pro, Oracle Database, and dBASE.
>

# 3. Relational Databases

A relational database is a type of database that stores and provides access to data points that are related to one another. Relational databases are based on the relational model, an intuitive, straightforward way of representing data in tables. In a relational database, each row in the table is a record with a unique ID called the key. The columns of the table hold attributes of the data, and each record usually has a value for each attribute, making it easy to establish the relationships among data points.

The relational model means that the logical data structures, the data tables, views, and indexes, are separate from the physical storage structures. This separation means that database administrators can manage physical data storage without affecting access to that data as a logical structure. For example, renaming a database file does not rename the tables stored within it.

A database typically requires a comprehensive database software program known as a database management system (DBMS). A DBMS serves as an interface between the database and its end users or programs, allowing users to retrieve, update, and manage how the information is organized and optimized. A DBMS also facilitates oversight and control of databases, enabling a variety of administrative operations such as performance monitoring, tuning, and backup and recovery.

> Some examples of popular database software or DBMSs include MySQL, Microsoft Access, Microsoft SQL Server, FileMaker Pro, Oracle Database, and dBASE.
>

## 3.1 Data Definition Language

- CREATE

```sql
CREATE TABLE table_name (
    column_1 datatype,
    column_2 datatype,
    ...,
    column_n datatype
);
```

- ALTER

```sql
ALTER TABLE table_name ADD column_name datatype;
```

- DROP

```sql
ALTER TABLE table_name DROP COLUMN column_name;
DROP TABLE table_name; 
```

- RENAME

```sql
ALTER TABLE table_name RENAME COLUMN old_column_name TO new_column_name;
```

- TRUNCATE (The result would be a table with only column names and no values.)

```sql
TRUNCATE TABLE table_name;
```

- COMMENT

```sql
CREATE TABLE actor (
 actor_id String(32767), --unique identifier
 first_name String(32767),
 last_name String(32767),
 last_update String(32767)--datetime of last update
);
```

## 3.2 Data Manipulation Language

- INSERT

```sql
INSERT INTO friends (name, birthday) VALUES (‘Billy Joel’, ‘05-09-1949’)
```

- UPDATE

```sql
UPDATE friends
SET birthday = '09-16-1992'
WHERE name = 'Nick Jonas' AND birthday = '08-15-1992';
```

- DELETE

```sql
DELETE FROM friends
WHERE name = 'Theresa Guidice' AND birthday = '05-18-1972';
```

- MERGE (MERGE statement in SQL is used to perform insert, update, and delete operations on a target table based on the results of JOIN with a source table.)

```sql
/* Selecting the Target and the Source */
MERGE PRODUCT_LIST AS TARGET
    USING UPDATE_LIST AS SOURCE 

    /* 1. Performing the UPDATE operation */

    /* If the P_ID is same, 
       check for change in P_NAME or P_PRICE */
    ON (TARGET.P_ID = SOURCE.P_ID)
    WHEN MATCHED 
         AND TARGET.P_NAME <> SOURCE.P_NAME 
         OR TARGET.P_PRICE <> SOURCE.P_PRICE

    /* Update the records in TARGET */
    THEN UPDATE 
         SET TARGET.P_NAME = SOURCE.P_NAME,
         TARGET.P_PRICE = SOURCE.P_PRICE
     
    /* 2. Performing the INSERT operation */

    /* When no records are matched with TARGET table 
       Then insert the records in the target table */
    WHEN NOT MATCHED BY TARGET 
    THEN INSERT (P_ID, P_NAME, P_PRICE)          
         VALUES (SOURCE.P_ID, SOURCE.P_NAME, SOURCE.P_PRICE)

    /* 3. Performing the DELETE operation */

    /* When no records are matched with SOURCE table 
       Then delete the records from the target table */
    WHEN NOT MATCHED BY SOURCE 
    THEN DELETE

/* END OF MERGE */
```

- CALL: Runs a stored procedure that returns a void either in the current scope or optionally on a specified cube. A stored procedure is a prepared SQL code that you can save, so the code can be reused over and over again.

```sql
--create a procedure
DELIMITER //
Create procedure InsertData (
   IN name VARCHAR(30),
   IN sal INT,
   IN loc VARCHAR(45))
      BEGIN
         INSERT INTO Emp(Name, Salary, Location) VALUES (name, sal, loc);
      END //
DELIMITER ;

CALL InsertData ('Raju', 35000, 'Bangalore');
CALL InsertData ('Raman', 45000, 'Vishakhapatnam'); 
CALL InsertData ('Rahman', 55000, 'Hyderabad');
```

- EXPLAIN

```sql
EXPLAIN SELECT * FROM user WHERE name = 'van 2';
```

- [LOCK](https://docs.oracle.com/cd/B13789_01/appdev.101/b10795/adfns_sq.htm#1024837)

```sql
LOCK TABLE emp IN ROW SHARE MODE NOWAIT;
```

## 3.3 Data Control Language

- GRANT

```sql
GRANT SELECT, INSERT, DELETE ON mydb TO 'user1'@'localhost';
```

- REVOKE

```sql
REVOKE INSERT ON mydb FROM 'user1'@'localhost';
```

## 3.4 Transaction Control Language

- COMMIT (BEGIN: Starts a transaction. It marks the beginning of a series of operations that should be treated as one unit. COMMIT: Used to save all the changes you made during a transaction permanently.)

```sql
BEGIN;
-- SQL statements
COMMIT;
```

- ROLLBACK (ROLLBACK: Used to undo changes you made during a transaction if something went wrong.)

```sql
BEGIN;
-- SQL statements
ROLLBACK;
```

- SAVEPOINT (SAVEPOINT: It lets you set a point inside a transaction to which you can roll back if needed.)

```sql
BEGIN;
-- SQL statements
SAVEPOINT my_savepoint;
-- More SQL statements
ROLLBACK TO my_savepoint;
```

- [SET TRANSACTION](https://docs.oracle.com/cd/B13789_01/appdev.101/b10807/13_elems047.htm) (You use the SET TRANSACTION statement to begin a read-only or read-write transaction, establish an isolation level, or assign your current transaction to a specified rollback segment. Read-only transactions are useful for running multiple queries while other users update the same tables.)

```sql
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

--example
DECLARE
   daily_sales   REAL;
   weekly_sales  REAL;
   monthly_sales REAL;
BEGIN
   COMMIT;  -- ends previous transaction
   SET TRANSACTION READ ONLY NAME 'Calculate sales figures';
   SELECT SUM(amt) INTO daily_sales FROM sales
      WHERE dte = SYSDATE;
   SELECT SUM(amt) INTO weekly_sales FROM sales
      WHERE dte > SYSDATE - 7;
   SELECT SUM(amt) INTO monthly_sales FROM sales
      WHERE dte > SYSDATE - 30;
   COMMIT;  -- ends read-only transaction
END;
```

# 4. Types of Keys

- Super key
- Alternate key
- Foreign key
- Surrogate key
- Nature key
- Simple key
- Composite key
- Compound key
- Intelligent key
- Primary key
- Candidate key

## 4.1 Primary key

A primary key is a column or columns in a database table with values that uniquely identify each row or record. For example, an employee ID column could be a primary key in a table of employee information. 

- A primary key is a special type of a unique key.

```sql
ALTER TABLE table_name PRIMARY KEY (candidate_key);
```

## 4.2 Candidate Key

A Candidate Key is a set of one or more fields/columns that can identify a record uniquely in a table. There can be multiple Candidate Keys in one table. Each Candidate Key can work as a Primary Key.

> When we are deciding which column should be primary key the columns that can be primary key are refereed to as candidate keys.
**Candidate key should exhibit uniqueness across time.**
> 

## 4.3 Super Key

A super key is a set of one or more than one key that can be used to identify a record uniquely in a table. Super Key can contain multiple attributes that might not be able to identify tuples in a table independently, but when grouped with certain keys, they can identify tuples uniquely. 

> A super is something that consists all the attributes of a candidate key plus potentially some additional attributes.
**Practically not useful only in theory.**
> 

## 4.4 Alternate Key

An Alternate key is a key that can work as a primary key. It is a candidate key that is currently not a primary key. I**t is also called a secondary key**. 

```sql
ALTER TABLE table_name ADD UNIQUE (another_candidate_key_that_is_not_primary_key);
```

## 4.5 Foreign Key

A foreign key is an attribute that is a Primary key in its parent table but is included as an attribute in another host table. The relation that is being referenced is called the referenced relation, and the corresponding attribute is called the referenced attribute. The relation that refers to the referenced relation is called a referencing relation, and the corresponding attribute is called a referencing attribute. The referenced attribute of the referenced relation should be the primary key to it. 

```sql
ALTER TABLE child_table_name ADD FOREIGN KEY (column_on_child_table) REFERENCES parent_table_name (primary_key);
```

## 4.6 Surrogate Key

A surrogate key on a table is a column with a unique identifier for each row. The key isn't generated from the table data. A surrogate key is a single column that has been artificially added, typically during ETL, and which also contains values that are unique across the table. Because surrogate keys are made up of technical values, they are sometimes also known as synthetic keys.

> Surrogate key stands in for something else. For example we have have state codes as the primary key and the codes can be identified in the real world but lets say we have a state id (a random generated value but unique) as surrogate key. This surrogate key (state id) is not identified in the real world but is understood in the context the database or the application. 
Surrogate key have real world meaning within the context of our database.
> 

> The idea of using a surrogate key not to uniquely identify a record but also to ensure that uniqueness is maintained. In the case of stat id, we cannot have different state id referring to same state code.
> 

## 4.7 Natural Key (Business key)

A natural key (also known as a business key or domain key) is a type of unique key in a database formed of attributes that exist and are used in the external world outside the database (i.e., in the business domain or domain of discourse).

> Referring back to surrogate key example, we say that the state code is the natural key while the state id is the surrogate key.
Example of natural key: your email, bank of mail customer id, insurgence number, ISBN for physical books (e-books have not ISBN) etc. Surrogate key are not shared in the real world.
> 

## 4.8 Composite Key

A composite key in SQL combines two or more columns to uniquely identify each record in a table. Database designers use composite keys when a single column cannot ensure uniqueness. The combination of multiple columns provides a unique identifier for each row, enhancing data integrity and retrieval efficiency. 

```sql
ALTER TABLE table_name PRIMARY KEY (candidate_key_1, candidate_key_2);
```

> A key is called composite if it contains more than one attribute.
> 

## 4.9 Simple Key

A simple key is just **a key using only one attribute in the table**. With reference to a composite key, if a key contains only one attribute, it is called a simple key. 

## 4.10 Compound Key

A compound key is similar to a composite key in that two or more fields are needed to create a unique value. However, a compound key is created when two or more primary keys from different tables are present as foreign keys within an entity. The foreign keys are used together to uniquely identify each record.

Compound keys are always made up of two or more primary keys from other tables. In their own tables, both of these keys uniquely identify data but in the table using the compound key, they are both needed to uniquely identify data.

> Consider a table Customers with primary key {customer id} and another table Accounts with primary key {account id}. Another table called Customer_Account has a primary key {customer id, account id} where both the columns are a foreign key to their respective table reference. The primary key is called a compound key.
> 

## 4.11 Intelligent Key

An intelligent key is a natural key with multiple parts stuck together where each of the part have its own meaning. 

# 5. Databases Indexes

Types of database indexes:

- Structure
    - Clustered Index
    - Non-Clustered Index
- Storage
    - Rowstore Index
    - Columnstore Index
- Function
    - Unique Index
    - Filter Index

A heap is a table without a clustered index. Without a cluster index, the writes are fast but reads are slow. 

Page: The smallest unit of data storage in a database. It stores anything: data, metadata, indexes, etc. 

Full Table Scan: SQL scans the entire table, page by page, row by row, searching for data.

## 5.1 Clustered Index

Determines the order in which data is physically stored in the table. A clustered index is most useful when we’re searching in a range. **Only one clustered index can exist per table (Since data is physically sorted and stored once).**

> Clustering indexing is a database indexing technique that is used to physically arrange the data in a table based on the values of the clustered index key. This means that the rows in the table are stored on disk in the same order as the clustered index key.
>

```sql
CREATE CLUSTERED INDEX index_name ON table_name (column1, column2,..);
```

## 5.2 Non-Clustered Index

This index does not store data in the order of the index. **Instead, it provides a list of virtual pointers or references to the location where the data is actually stored**. A non-clustered index won’t reorganize or change anything on the data page. 

> The non-cluster index B-Tree does not have the data within its structure as opposed to a cluster index.
Multiple non-cluster index can exist.
>

```sql
CREATE NONCLUSTERED INDEX index_name ON table_name (column1, column2,..);
-- default is non clustered index
CREATE INDEX index_name on table_name (column1); -- will create a non clustered index
-- on composite index
CREATE NONCLUSTERED INDEX index_name ON table_name (column1 DESC, column2 ASC);
-- example
-- create index as
CREATE INDEX ix_index ON mytable (col1, col2 DESC);
-- use in query as 
SELECT  * FROM    mytable ORDER BY col1, col2 DESC

```

> With composite index the order is important. The order provided in the index creation should be used in the query as well to fully utilize the query optimizer. 
**Leftmost Prefix Rule:** Index works only if your query filters start from the first column in the index and follows its order.
> 

## 5.3 Rowstore Index

A row-oriented database organizes data by rows, with each row containing information about a single entity or record. This is the default. We have the whole record in the data page, and each data page can have multiple records. 

```sql
CREATE NONCLUSTERED INDEX index_name ON table_name (column_name, ..);-- by default its rowstore

CREATE CLUSTERED INDEX index_name ON table_name (column_name,..);
```

## 5.4 Columnstore Index

A columnar database stores data grouped by columns rather than by rows, optimizing performance for analytical queries. Each column contains data of the same type, allowing for efficient compression. And because a query needs to access only relevant columns, the design enhances data retrieval speed.

```sql
CREATE NONCLUSTERED COLUMNSTORE INDEX index_name ON table_name (column_name);

CREATE CLUSTERED COLUMNSTORE INDEX index_name ON table_name; 
```

### 5.4.1 Process of Building Columnstore

The table starts as a normal rowstore heap table.

- Row Group: The data is split. (to allow parallel processing)
- Column Segment: For each row group, split the data by column
- Compression: compress common data types
- Store: store as LOB (large object page)

> When saving to the disk the column store can be saved as either clustered columnstore or non-clustered columnstore (in either, B-Tree structure is not used). 
Clustered columnstore: The row based table is completeley replaced with the new columnstore structure. 
Non-Clustered columnstore: It is an additional structure and does not replace the original table. Therefore, the rowstore heap table is preserved while a new structure for the columnstore is also maintained. With non-clustered columnstore only one column can be selected during the index creation. 
You cannot have multiple columns stores. It can either be a clustered or non-clustered. Contrasting it to rowstore we can have one clustered index and multiple non-clustered indexes (in sql server this limitation).
> 

> Storage Efficiency:
For a table with 60K records
- Clustered Columnstore: table size: 1.9MB, index space: 0MB
- Clustered Rowstore: table size: 9.6MB, index space: 0.055MB
- Heap table (no index): table size: 9.6MB, index space: 0.008MB
> 

## 5.5 Unique Index

Ensures no duplicate values exist in a specific column. 

```sql
CREATE UNIQUE INDEX index_name ON table_name (column_name);-- creates a unique index.

--default is non-unique index
CREATE INDEX index_name ON table_name (column_name);-- this allows duplicates.
--syntax
CREATE [UNIQUE] [CLUSTERED | NONCLUSTERED] [COLUMNSTORE] INDEX index_name on table_name (column1,colum2,..);
```

> It is important to understand that there exists no significant difference between a **primary key or unique key constraint and a unique index**. To implement the concept of primary and unique key constraints, the database manager uses a combination of a unique index and the NOT NULL constraint. Therefore, unique indexes do not enforce primary key constraints by themselves because they allow null values.
> 

## 5.6 Filtered Index

An index that includes only rows meeting the specific condition. Such as, We have a material table and the web application only requests the active material data. In this case, create an index for the only active material rows is very reasonable than creating an index for whole rows of the table.

> SQL server allows filtered index only on non-clustered index.
- you cannot create a filtered index on clustered index. 
- you cannot create a filtered index on a columnstore index.
> 

```sql
CREATE [UNIQUE] [NONCLUSTERED] INDEX index_name on table_name (column1,colum2,..) WHERE [condition];
```

## 5.7 Data Structures Used in Index

### 5.7.1 B-Tree (Balance Tree)

B-trees have a hierarchical structure consisting of a root node, internal nodes (also known as index nodes), and leaf nodes. Each node in a B-Tree contains a sorted array of keys and pointers to child nodes.

B-Trees are:

- Self balancing
- Ordered
- Disk Friendly

In a B+ tree, all data values are stored only in the leaf nodes, which can further improve performance for certain use cases like range queries.

## 5.8 Types of Indexes: Comparison

| Cluster Index | Non-Cluster Index | Rowstore Index | Columnstore Index | Unique Index | Filtered Index |
| --- | --- | --- | --- | --- | --- |
| Def: Physically sort and store rows | Def: Separate structure with pointer to the data | Def: Organize and store data row by row | Def: Organize and store data column by column | Def: no duplicate values exist in a specific column  |  |
| One Index per table | Multiple indexes per table | One Index per table
Multiple indexes per table | Only one index type i.e. cluster columnstore or nonclustered columnstore | If data in each column is unique, both unique clustered index and multiple unique nonclustered indexes can be created |  |
| Read performance: fast | Read performance: slow | Read performance: balanced | Read performance: fast | Read performance: fast than reading from a non-unique index |  |
| Write performance: slow (due to potential data reordering) | Write performance: fast (physical data order is unaffected) | Write performance: balanced | Write performance: slow | Write performance: slow than writing to a non-unique index |  |
| Storage efficiency: efficient | Storage efficiency: requires additional space | Storage efficiency: less efficient in storage | Storage efficient: highly efficient with compression  |  |  |
| Use case:
- Unique columns
- No frequent column updates (generally primary key are better candidates for this since they are unique and not modified after creation)
- Improve range query performance  | Use case:
- Columns frequently used in search conditions and joins 
- Exact match queries  | Use case:
- OLTP (online transaction processing): commerce, banking, financial systems, order processing.
- high frequency transaction applications
- quick access to complete record | Use case:
- OLAP (online analytical processing): dataware house, business intelligence, reporting, analytics. 
- big data analytics
- scanning of large documents
- fast aggregation |  | Uses case: 
- Target optimization
- less storage in index |
|  |  | I/O : higher. retrieves all columns | I/O : lower. retrieves specificl columns |  |  |
| When to use: for primary keys, if not then for date columns | When to use: For non-primary columns. Foreign keys, joins, filters.  |  | When to use: For analytical purpose. reduce size of large table | When to use: Enforce uniqueness. Improve query speed.  | When to use: Target subset of data. reduce size of index |

## 5.9 Index Management

The commands are in reference to SQL Server

- **Monitor Index Usage**: ‘sys’ - system tables that store table, view, index, etc metadata. ‘sys.indexes’ tables contain metadata for indexes. The object_id on the ‘sys.indexes’ tables can be joined with ‘sys.tables’ object_id to get the names of the table.
    - Dynamic management views (DMVs) and dynamic management functions (DMFs) return server state information that can be used to monitor the health of a server instance, diagnose problems, and tune performance. Table name: ‘sys.dm_db_index_usage_stats’. The query displays the read, update etc usage by the query optimizer this way we can understand when an index was last used and for what operation.
    
    ```sql
    SELECT
    	tbl.name AS TableName,
    	ide.name AS IndexName,
    	ide.type_desc AS IndexType,
    	ide.is_primary_key AS IsPrimayKey,
    	idx.is_unique AS IsUnique,
    	idx.is_disabled AS IsDisabled,
    	s.user_seeks AS UserSeeks,
    	s.user_scans AS UserScans,
    	s.user_lookups AS UserLookups,
    	s.user_updates AS UserUpdates,
    	COALESCE(s.last_user_seek,	s.last_user_scan) LastUpdate
    FROM sys.indexes idx
    JOIN sys.tables tbl
    ON idx.object_id = tbl.object_id
    LEFT JOIN sys.dm_db_index_usage_stats s
    ON s.object_id = idx.object_id
    AND s.index_id = idx.index_id
    ORDER BY tbl.name, idx.name
    ```
    
- **Monitor Missing Indexes**: Table: ‘sys.dm_db_missing_index_details’. After running a query check this table to see the recommendations by the SQL server.
- **Monitor Duplicate Indexes**: The query displays the index by each table and shows the count of index that have same column.

```sql
SELECT
	tbl.name AS TableName,
	col.name AS IndexColumn,
	idx.name AS indexName,
	idx.type_desc AS IndexType,
	COUNT(*) OVER (PARTITION BY tbl.name, col.name) COlumnCOunt
FROM sys.indexes idx
JOIN sys.tables tbl ON idx.object_id = tbl.object_id 
JOIN sys.index_columns ic ON idx.object_id = ic.object_id AND idx.index_id = ic.index_id
JOIN sys.columns col ON ic.object_id = col.object_id AND ic.column_id = col.colum_id
ORDER BY ColumnCount DESC
```

- **Updating statistics**: SQL Server statistics are one of the key inputs for the query optimizer during generating a query plan. Statistics are used by the optimizer to estimate how many rows will return from a query so it calculates the cost of a query plan using this estimation.
    - Behind the scenes the database engine creates the statistics which is basically a metadata about the table such as number of rows, distribution of data, patterns etc.
    - When executing a query the database engine creates an ‘execution plan’. The statistics are used in the execution plan.
    - When to do:
        - weekly automated jobs.
        - After major database migration.
    
    ```sql
    --to view the statistics
    SELECT
    	SCHEME_NAME (t.schem_id) AS SchemaName,
    	t.name AS TableName,
    	s.name AS StatisticName,
    	sp.last_updated As LastUpdated,
    	DATEDIFF(day, sp.last_updated, GETDATE()) AS LastUpdateDay,
    	sp.rows AS 'ROWS',
    	sp.modification_counter AS ModificationsSinceLastUpdate
    FROM sys.stats AS s
    JOIN sys.tables t
    ON s.object_id = t.object_id
    CRROSS APPLY sys.dm_db_stats_properties(s.object_id,s.stats_id) AS sp
    ORDER BY
    sp.modification_counter DESC;
    
    -- update statistics by statistic name
    UPDATE STATISTICS table_name statistics_name
    
    -- update statistics on whole table
    UPDATE STATISTICS table_name
    
    -- update statistics on whole database
    EXECUTE database_name
    ```
    
- **Monitor Fragmentation**: In SQL Server, fragmentation occurs when data in your database is not stored contiguously, resulting in disorganized pages and inefficient query executions.
    - Internal Fragmentation: happens when data pages in SQL Server have unnecessary data storage space or capacity.
    - External Fragmentation: occurs when the logical order of index pages varies from their physical organization in the data file. When you insert or update a record or data row in an already full table or index, SQL Server performs a page split and creates a new data page for storing the additional data. It moves some part of the information from the existing page to the new page.
    - **Fragmentation Methods:**
        - Reorganize:
            - Defragment leaf nodes to keep them sorted
            - ‘Light’ Operation
        - Rebuild:
            - Recreate Index from scratch
            - ‘Heavy’ Operation
    - When to defragment:
        - < 10%: no action
        - 10% to 30%: Reorganize
        - >30%: Rebuild

```sql
-- to check the degree of fragmentation
SELECT * FROM sys.dm_db_index_physical_stats (DB_ID(),NULL,NULL,NULL,'LIMITED');

SELECT 
	tbl.name AS TableName,
	idx.name AS IndexName,
	s.avg_fragmentation_in_percent,
	s.page_count
FROM sys.dm_db_index_physical_stats(DB_ID(),NULL,NULL,NULL,'LIMITED') AS a
INNER JOIN sys.tables tbl
ON s.object_id = tbl.object_id
INNER JOINN sys.indexes AS ids
ON idx.object_id = s.object_id
AND idx.index_id = s.index_id
ORDER BY s.avg_gragmentation_in_percent DESC;

-- fragmentation Reorganize
ALTER INDEX index_name ON table_name REORGANIZE

-- fragment rebuild
ALTER INDEX index_name ON table_name REBUILD
```

# 6. Combining Tables in SQL

## 6.1 Why use SQL JOIN

- Recombine data (Big Picture)
- Data Entrenchment (Extra Info)
- Check Existence (Filtering)

## 6.2 INNER JOIN

- Returns only matching tables from both tables

```sql
SELECT * FROM table_a INNER JOIN table_b ON A.key = B.key;-- default is inner join
-- order does not matter. below query will give same result
SELECT * FROM table_b INNER JOIN table_a ON A.key = B.key;
```

## 6.3 LEFT JOIN

- Returns all rows from the left table and only matching from the right
- The left table is the primary source of data. The right table is additional, and only adds extra information.

```sql
-- order matters
-- table_a is left
-- table_b is right
SELECT * FROM table_a LEFT JOIN table_b ON A.key = B.key;
```

## 6.4 RIGHT JOIN

- Return all rows from the right table and only matching from the left.

```sql
-- order matters
-- table_a is left
-- table_b is right
SELECT * FROM table_a RIGHT JOIN table_b ON A.key = B.key;

-- same can be achieved with LEFT JOIN
-- order matters
-- table_a is right
-- table_b is left
SELECT * FROM table_b LEFT JOIN table_a ON A.key = B.key;
```

## 6.5 FULL JOIN

- Return all rows from both tables.

```sql
--order of tables does not matter
SELECT * FROM table_a FULL JOIN table_b ON A.key = B.key;
```

## 6.6 LEFT ANTI JOIN

- Return rows from left table that has n**o matches** on the right
- The left table is primary source and the right table acts as filter.

```sql
-- the order of the table is important
SELECT * FROM table_a LEFT JOIN table_b ON A.key = B.key WHERE B.key IS NULL;
```

## 6.7 RIGHT ANTI JOIN

- Return rows from the right table that has no matches on the left.

```sql
-- the order of the table is important
SELECT * FROM table_a RIGHT JOIN table_b ON A.key = B.key WHERE A.key IS NULL;

-- can be done similarly with left join
-- the order of the table is important
SELECT * FROM table_b LEFT JOIN table_a ON A.key = B.key WHERE A.key IS NULL;
```

## 6.8 FULL ANTI JOIN

- Return only the rows that dont match in either tables.

```sql
-- the order of the tables does not matter
SELECT * FROM table_a FULL JOIN table_b ON A.key = B.key WHERE A.key IS NULL OR B.key IS NULL;
```

## 6.9 CROSS JOIN

- Combine every row from the left **with** every row from the right. All possible combinations: Cartesian join.
- Example: if we have two tables called products and colors then we can see the combination of both tables.
When combining table with columns we use JOIN and when combining with rows we use SET operators.

```sql
-- order does not matter
SELECT * FROM A CROSS JOIN B
```

# 7. MongoDB

MongoDB is a cross platform, document oriented database that provides, high performance, high-availability, and easy scalability. MongoDB works on the concept of collection and document.

**Database:** Database is a physical container for collection. Each database gets its own set of files on the file system. A Single MongoDB server typically has multiple databases.

**Collection:** Collection is a group of MongoDB documents. It is the equivalent of an RDBMS table. Collections do not enforce a schema. Documents within a collection can have different fields. A record in MongoDB is a document, documents are similar to JSON objects. 

**Document:** A document is a set of key-value pair. Documents have dynamic schema. *Dynamic schema means that documents in the same collections do not need to have the same set of fields or structure, and common fields in a collections documents may hold different types of data.* 

> **_id** is a 12 bytes hexadecimal number which assures the
> 

## 7.1 Create Database

In MongoDB, databases hold one or more collections of documents.

> **Run commands in *mongosh***
> 

If a database does not exist, MongoDB creates the database when you first store data for that database.

```jsx
use employeeDb
db.employeeDepartment.insertOne( { x : 1} )
```

The above commands once executed will create a database 'employeeDb' and a collection 'employeeDepartment'.

## 7.2 Drop a Database

```jsx
use employeeDb
db.dropDatabase()
```

## 7.3 Create a Collections

MongoDB stores documents in collections. Collections are analogous to tables in relational databases.

If a collection does not exist, MongoDB creates the collection when you first store data for that collection. 

```jsx
db.myNewCollection2.insertOne( { x: 1 } )
db.myNewCollection3.createIndex( { y: 1 } )
```

## 7.4 Explicit Creation

```jsx
db.createCollection(name,options)
```

Can be used to explicitly create a collection

## 7.5 Show Collections

```jsx
show collections
(or)
db.getCollectionInfos()
```

## 7.6 Drop a Collection

After selecting the database, assuming 'myCollection' is the collection to delete

```jsx
db.myCollection.drop()
```