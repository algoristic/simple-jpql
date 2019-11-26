package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.BasicJPQLTest;
import infrastructure.entities.Book;

@SuppressWarnings("rawtypes")
@DisplayName("Queries resembling: SELECT a.prop_1, [a.prop_n] FROM table a")
public class SelectProperties extends BasicJPQLTest {
    
    Table books = Table.of(Book.class);

    @Test
    @DisplayName("*.properties(books.property(\"title\"))*")
    void selectSinglePropertyString() {
        String qlString = Select.properties(books.property("title")).from(books).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }

    @Test
    @DisplayName("*.properties(new Property[] { books.property(\"title\") })*")
    void selectSinglePropertyStringArray() {
        String qlString = Select.properties(new Property[] {
                books.property("title")
        }).from(books).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }

    @Test
    @DisplayName("*.properties(new Property[] { books.property(\"title\"), books.property(\"author\") })*")
    void selectMultiplePropertyStringArray() {
        String qlString = Select.properties(new Property[] {
                books.property("title"), books.property("author")
        }).from(books).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }

    @Test
    @DisplayName("*.properties(books.property(\"title\"), books.property(\"author\"))*")
    void selectMultiplePropertyStringVarArgs() {
        String qlString = Select.properties(books.property("title"), books.property("author")).from(books).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }

    @Test
    @DisplayName("*.properties(books.property(\"title\"), null)*")
    void selectMultiplePropertyStringNullArgs() {
        Property dummy = null;
        assertThrows(NullPointerException.class, () -> {
            Select.properties(books.property("title"), dummy).from(books).query();
        });
    }

}
