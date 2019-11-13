# simple-jpql

## Overview

**Simple-jpql** provides a wrapper around the JPQL query-syntax defined by JPA.

```java
Table books = Table.of(Book.class);
Table authors = Table.of(Author.class);
Select.properties(books.all())
    .from(books, authors)
    .where(books.property("author").equals(authors.property("name")));
```

```java
Table books = Table.of(Book.class);
Select.all
    .from(books)
    .where(Condition.and(
        Condition.property("title").like("%Dunwich%"),
        books.property("author").like("%Lovecraft")
    ));
```

```java
Select.all.from(Books.class);
```

```java
Table books = Table.of(Book.class);
Select.properties(
        books.property("id"),
        books.property("title"))
    .from(books)
    .where(books.property("author").like("%Lovecraft"));
```
## Plans

- distinct date/time/datetime datatype
- direct use with EntityManager (to provide not only SQL-literals but queries with complex parameters)
- JOINS
- FUNCTIONS
- GROUP BY and HAVING
- ORDER BY
