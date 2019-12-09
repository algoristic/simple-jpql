package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.BasicJPQLTest;
import infrastructure.entities.Author;
import infrastructure.entities.Book;

@SuppressWarnings("rawtypes")
@DisplayName("SELECT <PROPERTIES> ...")
public class SelectFromMultipleTables extends BasicJPQLTest {

    Table books = Table.of(Book.class);
    Table authors = Table.of(Author.class);

    @Test
    @DisplayName("... FROM books, authors")
    void selectSinglePropertyFromMultipleClasses() {
        String qlString = Select.properties(books.property("title")).from(books, authors).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }

    @Test
    @DisplayName("... FROM authors, NULL")
    public void selectByMultipleClassesWithNull() {
        Table dummy = null;
        assertThrows(NullPointerException.class, () -> {
            Select.all.from(Table.of(Author.class), dummy).query();
        });
    }

    @Test
    @DisplayName("... FROM authors, books")
    public void selectByMultipleTables() {
        String qlString = Select.all.from(Table.of(Author.class), Table.of(Book.class)).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }

    @Test
    @DisplayName("... FROM authors, books")
    public void selectAllFromOneTableByMultiple() {
        String qlString = Select.properties(authors.all()).from(authors, Table.of(Book.class)).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
        Object firstResult = ls.get(0);
        assertTrue(firstResult instanceof Author);
    }

}
