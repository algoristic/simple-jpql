package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.Query;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.BasicJPQLTest;
import infrastructure.entities.Author;
import infrastructure.entities.Book;

@DisplayName("SELECT ...")
public class FunctionTest extends BasicJPQLTest {

    Table authors = Table.of(Author.class);
    Table books = Table.of(Book.class);

    @Test
    @DisplayName("... MIN(year) ...")
    void minTest() {
        Query authorQuery = Select.all.from(authors).where(authors.property("name").like("%Miura")).query(em);
        Author miura = (Author) authorQuery.getSingleResult();
        Query query = Select.properties(Function.min(books.property("year"))).from(books).where(books.property("author").isEquals(miura)).query(em);
        Object result = query.getSingleResult();
        assertEquals(1990, result);
    }

    @Test
    @DisplayName("... MAX(year) ...")
    void maxTest() {
        Query query = Select.properties(Function.max(books.property("year"))).from(books).query(em);
        Object result = query.getSingleResult();
        assertEquals(2004, result);
    }

    @Test
    @DisplayName("... AVG(id) ...")
    void avgTest() {
        Query query = Select.properties(Function.avg(books.property("id"))).from(books).query(em);
        Object result = query.getSingleResult();
        assertEquals(5.5, result);
    }

    @Test
    @DisplayName("... SUM(id) ...")
    void sumTest() {
        Query query = Select.properties(Function.sum(books.property("id"))).from(books).query(em);
        Object result = query.getSingleResult();
        assertEquals(55L, result);
    }

    @Test
    @DisplayName("... COUNT(*) ...")
    void countTest() {
        Query query = Select.properties(Function.count(authors)).from(authors).query(em);
        Object count = query.getSingleResult();
        assertEquals(3L, count);
    }

}
