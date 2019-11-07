package infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.entities.Book;

@DisplayName("Tests providing the basic functionality of the In-Memory DB")
public class InMemoryDBTest extends BasicJPQLTest {

    @Test
    @DisplayName("Query single object by ID")
    public void getObjectByID() {
        Book book = em.find(Book.class, 1);
        assertNotNull(book);
    }

    @Test
    @DisplayName("Query all entries from one table")
    public void getAllFromSingleTable() {
        List<Book> books = em.createNamedQuery("Book.getAll", Book.class).getResultList();
        assertEquals(10, books.size());
    }
    
    @Test
    @DisplayName("Create a new object and persist it")
    public void persistNewEntry() {
        em.getTransaction().begin();
        em.persist(new Book(11, "Unit Test Hibernate/JPA with in memory H2 Database", "Marco Leweke"));
        em.getTransaction().commit();
        List<Book> books = em.createNamedQuery("Book.getAll", Book.class).getResultList();
        assertNotNull(books);
        assertEquals(11, books.size());
    }

    @Test
    @DisplayName("Delete one entry from the database")
    public void deleteEntry() {
        Book book = em.find(Book.class, 1);
        em.getTransaction().begin();
        em.remove(book);
        em.getTransaction().commit();
        List<Book> books = em.createNamedQuery("Book.getAll", Book.class).getResultList();
        assertEquals(9, books.size());
    }

}
