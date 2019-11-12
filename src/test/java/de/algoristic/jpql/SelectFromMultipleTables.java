package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.BasicJPQLTest;
import infrastructure.entities.Author;
import infrastructure.entities.Book;

@SuppressWarnings("rawtypes")
@DisplayName("Queries resembling: SELECT * FROM <TABLE>, [<TABLES>]")
public class SelectFromMultipleTables extends BasicJPQLTest {
    
    @Test
    @DisplayName("*.from(\"Author, Book\")")
    public void selectByMultipleTableString() {
        String qlString = Select.all.from("Author, Book").query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("*.from(\"Author\", \"Book\")")
    public void selectByMultipleTableStrings() {
        String qlString = Select.all.from("Author", "Book").query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("*.from(\"Author\", null)")
    public void selectByMultipleTableStringsWithNull() {
        String qlString = Select.all.from("Author", null).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(3, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("*.from(\"Author a, Book b\")")
    public void selectByMultipleTableStringWithAliases () {
        String qlString = Select.all.from("Author a, Book b").query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("*.from(\"Author a, Book\")")
    public void selectByMultipleTableStringMixed () {
        String qlString = Select.all.from("Author a, Book").query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("*.from(Author.class, Book.class)")
    public void selectByMultipleClasses() {
        String qlString = Select.all.from(Author.class, Book.class).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("*.properties(Property.of(Book.class, \"title\")).from(Author.class, Book.class)")
    void selectSinglePropertyFromMultipleClasses() {
        String qlString = Select.properties(Property.of(Book.class, "title")).from(Author.class, Book.class).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("*.from(Author.class, null)")
    public void selectByMultipleClassesWithNull() {
        String qlString = Select.all.from(Author.class, null).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(3, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("*.from(Table.of(Author.class), Table.of(Book.class))")
    public void selectByMultipleTables() {
        String qlString = Select.all.from(Table.of(Author.class), Table.of(Book.class)).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("*.from(Table.of(Author.class), null)")
    public void selectByMultipleTablesWithNull() {
        String qlString = Select.all.from(Table.of(Author.class), null).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(3, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("*.from(Table.of(Author.class, \"a\"), Table.of(Book.class, \"b\"))")
    public void selectByMultipleTablesWithAliases() {
        String qlString = Select.all.from(Table.of(Author.class, "a"), Table.of(Book.class, "b")).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("*.from(Table.of(Author.class, \"a\"), Table.of(Book.class))")
    public void selectByMultipleTablesMixed() {
        String qlString = Select.all.from(Table.of(Author.class, "a"), Table.of(Book.class)).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }
    
    @Test
    @DisplayName("*.from(Table.of(Author.class), Table.of(Book.class).as(\"b\"))")
    public void selectByMultipleTablesMixedAlternative() {
        String qlString = Select.all.from(Table.of(Author.class), Table.of(Book.class).as("b")).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }

}
