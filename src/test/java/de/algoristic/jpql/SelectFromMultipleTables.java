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
@DisplayName("Queries resembling: SELECT * FROM <TABLE>, [<TABLES>]")
public class SelectFromMultipleTables extends BasicJPQLTest {

    Table books = Table.of(Book.class);
    Table authors = Table.of(Author.class);

    @Test
    @DisplayName("*.properties(books.property(\"title\")).from(books, authors)")
    void selectSinglePropertyFromMultipleClasses() {
        String qlString = Select.properties(books.property("title")).from(books, authors).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }

    @Test
    @DisplayName("*.from(Table.of(Author.class), null)")
    public void selectByMultipleClassesWithNull() {
        Table dummy = null;
        assertThrows(NullPointerException.class, () -> {
            Select.all.from(Table.of(Author.class), dummy).query();
        });
    }

    @Test
    @DisplayName("*.from(Table.of(Author.class), Table.of(Book.class))")
    public void selectByMultipleTables() {
        String qlString = Select.all.from(Table.of(Author.class), Table.of(Book.class)).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
    }

    @Test
    @DisplayName("*.properties(authors.all()).from(authors, Table.of(Book.class))")
    public void selectAllFromOneTableByMultiple() {
        String qlString = Select.properties(authors.all()).from(authors, Table.of(Book.class)).query();
        List ls = em.createQuery(qlString).getResultList();
        assertEquals(30, ls.size()); //cross product of author (len=3) and book (len=10)
        Object firstResult = ls.get(0);
        assertTrue(firstResult instanceof Author);
    }

}
