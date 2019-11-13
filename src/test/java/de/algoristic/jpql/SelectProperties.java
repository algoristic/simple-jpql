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
@DisplayName("Queries resembling: SELECT a.prop_1, [a.prop_n] FROM table a")
public class SelectProperties extends BasicJPQLTest {

    static Logger logger = LogManager.getLogger(SelectProperties.class);

    @Test
    @DisplayName("*.properties(books.property(\"title\"))*")
    void selectSinglePropertyString() {
        Table books = Table.of(Book.class);
        String qlString = Select.properties(books.property("title")).from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }

    @Test
    @DisplayName("*.properties(Property.of(\"b.title\"))*")
    void selectSinglePropertyWithAliasString() {
        String qlString = Select.properties(Property.of("b.title")).from(Table.of("Book b")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }

    @Test
    @DisplayName("*.properties(new Property[] { Property.of(\"title\") })*")
    void selectSinglePropertyStringArray() {
        String qlString = Select.properties(new Property[] { Property.of("title") }).from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
        Object firstResult = results.get(0);
        assertTrue(firstResult instanceof String);
    }

    @Test
    @DisplayName("*.properties(new Property[] { Property.of(\"title\"), Property.of(\"author\") })*")
    void selectMultiplePropertyStringArray() {
        String qlString = Select.properties(new Property[] { Property.of("title"), Property.of("author") }).from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }

    @Test
    @DisplayName("*.properties(Property.of(\"title\"), Property.of(\"author\"))*")
    void selectMultiplePropertyStringVarArgs() {
        String qlString = Select.properties(Property.of("title"), Property.of("author")).from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }
    
    @Test
    @DisplayName("*.properties(Property.of(\"title\"), null)*")
    void selectMultiplePropertyStringNullArgs() {
        String qlString = Select.properties(Property.of("title"), null).from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(10, results.size());
    }
    
    @Test
    @DisplayName("*.properties(Property.of(Book.class, \"title\"))*")
    void selectSinglePropertyOfClassAndName() {
        String qlString = Select.properties(Property.of(Book.class, "title")).from(Book.class).query();
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
        String qlString = Select.properties(Property.of(Table.of(Book.class), "title")).from(Book.class).query();
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
