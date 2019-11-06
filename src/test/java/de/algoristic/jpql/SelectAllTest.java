package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.SimpleJPQLTest;
import infrastructure.entities.Book;

@DisplayName("Testing different methods to create queries like:\nSELECT * FROM <TABLE>")
public class SelectAllTest extends SimpleJPQLTest {
    
    @Test
    @DisplayName("Select.from(\"Book\")")
    void selectByClassName() {
        @SuppressWarnings("rawtypes") List results = em.createQuery(Select.from("Book").query()).getResultList();
        assertEquals(results.size(), 1);
    }
    
    @Test
    @DisplayName("Select.from(\"Book b\")")
    void selectByClassNameWithAlias() {
        @SuppressWarnings("rawtypes") List results = em.createQuery(Select.from("Book b").query()).getResultList();
        assertEquals(results.size(), 1);
    }
    
    @Test
    @DisplayName("Select.from(Book.class)")
    void selectByClass() {
        @SuppressWarnings("rawtypes") List results = em.createQuery(Select.from(Book.class).query()).getResultList();
        assertEquals(results.size(), 1);
    }
    
    @Test
    @DisplayName("Select.from(Book.class).commit(em)")
    void selectRawWithEm() {
        @SuppressWarnings("rawtypes") List results = Select.from(Book.class).commit(em);
        assertEquals(results.size(), 1);
    }
    
    @Test
    @DisplayName("Select.from(\"Book\").commit(em, Book.class)")
    void selectTypedWithEm() {
        List<Book> results = Select.from("Book").commit(em, Book.class);
        assertEquals(results.size(), 1);
    }

}
