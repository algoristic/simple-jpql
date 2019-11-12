package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.BasicJPQLTest;
import infrastructure.entities.Book;

@SuppressWarnings("rawtypes")
@DisplayName("Queries resembling: SELECT a.prop_1, a.prop_2, ... FROM table a")
public class SelectProperties extends BasicJPQLTest {

    static Logger logger = LogManager.getLogger(SelectProperties.class);

    @Test
    @DisplayName("*.properties(\"title\")*")
    void selectSinglePropertyString() {
        String qlString = Select.properties("title").from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }

    @Test
    @DisplayName("*.properties(\"b.title\")*")
    void selectSinglePropertyWithAliasString() {
        String qlString = Select.properties("b.title").from(Table.of("Book b")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }

    @Test
    @DisplayName("*.properties(new String[] { \"title\" })*")
    void selectSinglePropertyStringArray() {
        String qlString = Select.properties(new String[] { "title" }).from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }

    @Test
    @DisplayName("*.properties(\"title, author\")*")
    void selectMultiplePropertyString() {
        String qlString = Select.properties("title, author").from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }

    @Test
    @DisplayName("*.properties(\"b.title, b.author\")*")
    void selectMultiplePropertyWithAliasString() {
        String qlString = Select.properties("b.title, b.author").from(Table.of("Book", "b")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }

    @Test
    @DisplayName("*.properties(new String[] { \"title\", \"author\" })*")
    void selectMultiplePropertyStringArray() {
        String qlString = Select.properties(new String[] { "title", "author" }).from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }

    @Test
    @DisplayName("*.properties(\"title\", \"author\")*")
    void selectMultiplePropertyStringVarArgs() {
        String qlString = Select.properties("title", "author").from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }
    
    @Test
    @DisplayName("*.properties(\"title\", null)*")
    void selectMultiplePropertyStringNullArgs() {
        String qlString = Select.properties("title", null).from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }
    
    @Test
    @DisplayName("*.properties(Property.of(\"title\"))*")
    void selectSinglePropertyOfString() {
        String qlString = Select.properties(Property.of("title")).from("Book").query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }
    
    @Test
    @DisplayName("*.properties(Property.of(Book.class, \"title\"))*")
    void selectSinglePropertyOfClassAndName() {
        String qlString = Select.properties(Property.of(Book.class, "title")).from("Book").query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }
    
    @Test
    @DisplayName("*.properties(Property.of(\"b\", \"title\"))*")
    void selectSinglePropertyOfAliasAndName() {
        String qlString = Select.properties(Property.of("b", "title")).from(Table.of("Book b")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }
    
    @Test
    @DisplayName("*.properties(Property.of(Table.of(Book.class), \"title\"))*")
    void selectSinglePropertyOfTableAndName() {
        String qlString = Select.properties(Property.of(Table.of(Book.class), "title")).from("Book").query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }
    
    @Test
    @DisplayName("*.properties(Property.of(\"id\"), Property.of(\"b\", \"title\"), Property.of(Book.class, \"author\"))*")
    void selectMiltipleProperties() {
        String qlString = Select.properties(
                Property.of("id"),
                Property.of("b", "title"),
                Property.of(Book.class, "author"))
            .from(Table.of("Book", "b")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }
    
    @Test
    @DisplayName("*.properties(propertiesList)*")
    void selectMiltiplePropertiesList() {
        List<Property> propertiesList = Arrays.asList(Property.of("id"),
                Property.of("b", "title"),
                Property.of(Book.class, "author"));
        String qlString = Select.properties(propertiesList).from(Table.of("Book", "b")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }

}
