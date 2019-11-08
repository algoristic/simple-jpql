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
@DisplayName("Queries resembling:\nSELECT a.prop_1, a.prop_2, ... FROM table a")
public class SelectProperties extends BasicJPQLTest {

    static Logger logger = LogManager.getLogger(SelectProperties.class);

    @Test
    @DisplayName("Select.properties(\"title\").from(Book.class)")
    void selectSinglePropertyString() {
        String qlString = Select.properties("title").from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }

    @Test
    @DisplayName("Select.properties(\"b.title\").from(Table.of(\"Book b\"))")
    void selectSinglePropertyWithAliasString() {
        String qlString = Select.properties("b.title").from(Table.of("Book b")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }

    @Test
    @DisplayName("Select.properties(new String[] { \"title\" }).from(Book.class)")
    void selectSinglePropertyStringArray() {
        String qlString = Select.properties(new String[] { "title" }).from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }

    @Test
    @DisplayName("Select.properties(\"title, author\").from(Book.class)")
    void selectMultiplePropertyString() {
        String qlString = Select.properties("title, author").from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }

    @Test
    @DisplayName("Select.properties(\"b.title, b.author\").from(Table.of(\"Book\", \"b\"))")
    void selectMultiplePropertyWithAliasString() {
        String qlString = Select.properties("b.title, b.author").from(Table.of("Book", "b")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }

    @Test
    @DisplayName("Select.properties(new String[] { \"title\", \"author\" }).from(Book.class)")
    void selectMultiplePropertyStringArray() {
        String qlString = Select.properties(new String[] { "title", "author" }).from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }

    @Test
    @DisplayName("Select.properties(\"title\", \"author\").from(Book.class)")
    void selectMultiplePropertyStringVarArgs() {
        String qlString = Select.properties("title", "author").from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }
    
    @Test
    @DisplayName("Select.properties(\"title\", null).from(Book.class)")
    void selectMultiplePropertyStringNullArgs() {
        String qlString = Select.properties("title", null).from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }
    
    @Test
    void selectSinglePropertyOfString() {
        String qlString = Select.properties(Property.of("title")).from("Book").query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }
    
    @Test
    void selectSinglePropertyOfClassAndName() {
        String qlString = Select.properties(Property.of(Book.class, "title")).from("Book").query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }
    
    @Test
    void selectSinglePropertyOfAliasAndName() {
        String qlString = Select.properties(Property.of("b", "title")).from(Table.of("Book b")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }
    
    @Test
    void selectSinglePropertyOfTableAndName() {
        String qlString = Select.properties(Property.of(Table.of(Book.class), "title")).from("Book").query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }
    
    @Test
    @DisplayName("Select.properties(Property.of(\"id\"), Property.of(\"b\", \"title\"), Property.of(Book.class, \"author\")).from(Table.of(\"Book\", \"b\"))")
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
    @DisplayName("Select.properties(propertiesList).from(Table.of(\"Book\", \"b\"))")
    void selectMiltiplePropertiesList() {
        List<Property> propertiesList = Arrays.asList(Property.of("id"),
                Property.of("b", "title"),
                Property.of(Book.class, "author"));
        String qlString = Select.properties(propertiesList).from(Table.of("Book", "b")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }

}
