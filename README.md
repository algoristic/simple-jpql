# simple-jpql

## Overview

**Simple-jpql** provides a wrapper around the JPQL query-syntax defined by JPA. **Simple-jpql** is designed to give you OOP-feeling when writing SQL queries, unlike "stringing" your queries together with plain JPQL, whilst providing an easier syntax than the Criteria API.

## Examples

### Example Data

Given is a very simple database with the tables:

```sql
CREATE TABLE Author (
  id INT(11) NOT NULL,
  name VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
;

CREATE TABLE Book (
  id INT(11) NOT NULL,
  title VARCHAR(255) DEFAULT NULL,
  year INT(11) DEFAULT NULL,
  author_id INT(11) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (author_id) REFERENCES Author(id)
)
;
```

**Query all from a single table**

SQL
```sql
SELECT
    *
FROM
    Books b
;
```
Java
```java
Table books = Table.of(Books.class);
Select.all.from(books);
```

**Combined specific properties, using conditions**

SQL
```sql
SELECT
    b.id,
    b.year
FROM
    Books b
WHERE
        b.title LIKE 'Berserk%'
    AND
        b.year > 1990
;
```
Java
```java
Table books = Table.of(Book.class);
Select.properties(
        books.property("id"),
        books.property("year")))
    .from(books)
    .where(Condition.and(
        books.property("title").like("Berserk%"),
        books.property("year").greaterThan(1990)
    ));
```

### Queries with Non-Primitive Datatypes

SQL
```sql
SELECT
    b
FROM
    books b
WHERE
    b.author_id = xyz
```
Java
```java
EntityManager em = ...;
Author author = ...;
Table books = Table.of(Book.class);
Query query = Select.properties(books)
    .from(books)
    .where(books.property("author").isEquals(author))
    .query(em);
```

### Planing
- FUNCTIONS (others than simple aggregate-functions)
- JOINS
- GROUP BY and HAVING
- ORDER BY
- distinct date/time/datetime datatype
