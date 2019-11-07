package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.BasicJPQLTest;
import infrastructure.entities.Book;

@SuppressWarnings("rawtypes")
@DisplayName("Queries resembling:\nSELECT * FROM <TABLE>")
public class SelectAllTest extends BasicJPQLTest {

    @Test
    @DisplayName("Select.from(\"Book\")")
    void selectByClassName() {
        String qlString = Select.all.from("Book").query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(results.size(), 10);
    }

    @Test
    @DisplayName("Select.from(\"Book b\")")
    void selectByClassNameWithAlias() {
        String qlString = Select.all.from("Book b").query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(results.size(), 10);
    }

    @Test
    @DisplayName("Select.from(Book.class)")
    void selectByClass() {
        String qlString = Select.all.from(Book.class).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(results.size(), 10);
    }

    @Test
    @DisplayName("Select.from(Table.of(\"Book\", \"b\"))")
    void selectByTableWithAlias() {
        String qlString = Select.all.from(Table.of("Book", "b")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(results.size(), 10);
    }
    
    @Test
    @DisplayName("Select.from(Table.of(Book.class, \"b\"))")
    void selectByClassWithAlias() {
        String qlString = Select.all.from(Table.of(Book.class, "b")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(results.size(), 10);
    }

    @Test
    @DisplayName("Select.from(Table.of(\"Book\").as(\"b\"))")
    void selectByTableWithAliasAlternative() {
        String qlString = Select.all.from(Table.of("Book").as("b")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(results.size(), 10);
    }

    @Test
    @DisplayName("Select.from(Table.of(Book.class).as(\"b\"))")
    void selectByClassWithAliasAlternative() {
        String qlString = Select.all.from(Table.of(Book.class).as("b")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(results.size(), 10);
    }
    
    @Test
    @DisplayName("Select.from(Table.of(\"Book\"))")
    void selectByTableWithoutAlias() {
        String qlString = Select.all.from(Table.of("Book")).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(results.size(), 10);
    }
    
    @Test
    @DisplayName("Select.from(Table.of(Book.class))")
    void selectByClassWithoutAlias() {
        String qlString = Select.all.from(Table.of(Book.class)).query();
        List results = em.createQuery(qlString).getResultList();
        assertEquals(results.size(), 10);
    }

}
