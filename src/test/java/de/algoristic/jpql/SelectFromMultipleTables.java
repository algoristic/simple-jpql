package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.BasicJPQLTest;
import infrastructure.entities.Author;
import infrastructure.entities.Book;

@SuppressWarnings("rawtypes")
@DisplayName("Create queries resembling:\nSELECT * FROM <TABLE>, [<TABLES>]")
public class SelectFromMultipleTables extends BasicJPQLTest {
    
    @Test
    @DisplayName("Select.from(\"Author, Book\")")
    public void selectByChainedExpression() {
        String qlString = Select.from("Author, Book").query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("Select.from(\"Author a, Book b\")")
    public void selectByChainedExpressionWithAliases () {
        String qlString = Select.from("Author a, Book b").query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("Select.from(\"Author a, Book\")")
    public void selectByChainedExpressionWithMixedAliases () {
        String qlString = Select.from("Author a, Book").query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("Select.from(Author.class, Book.class)")
    public void selectByChainedExpressionWithClasses() {
        String qlString = Select.from(Author.class, Book.class).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("Select.from(Table.of(Author.class), Table.of(Book.class))")
    public void selectByChainedExpressionWithTables() {
        String qlString = Select.from(Table.of(Author.class), Table.of(Book.class)).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("Select.from(Table.of(Author.class, \"a\"), Table.of(Book.class, \"b\"))")
    public void selectByExpressionTablesWithAliases() {
        String qlString = Select.from(Table.of(Author.class, "a"), Table.of(Book.class, "b")).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("Select.from(Table.of(Author.class, \"a\"), Table.of(Book.class))")
    public void selectByExpressionTablesWithAliasesMixed() {
        String qlString = Select.from(Table.of(Author.class, "a"), Table.of(Book.class)).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("Select.from(Table.of(Author.class), Table.of(Book.class).as(\"b\"))")
    public void selectByExpressionTablesWithAliasesMixedAlternative() {
        String qlString = Select.from(Table.of(Author.class), Table.of(Book.class).as("b")).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }

}
