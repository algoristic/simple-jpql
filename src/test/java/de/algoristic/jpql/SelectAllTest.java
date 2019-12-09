package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.BasicJPQLTest;
import infrastructure.entities.Author;
import infrastructure.entities.Book;

@SuppressWarnings("rawtypes")
@DisplayName("SELECT * ...")
public class SelectAllTest extends BasicJPQLTest {

    @Test
    @DisplayName("... FROM books")
    void selectAllFromSingleTable() {
        String qlString = Select.all.from(Table.of(Book.class)).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(results.size(), 10);
    }

    @Test
    @DisplayName("... FROM books, authors")
    void selectAllFromMultipleTables() {
        String qlString = Select.all.from(Table.of(Book.class), Table.of(Author.class)).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(results.size(), 30);
    }

}
