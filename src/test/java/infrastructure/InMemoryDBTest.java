package infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.entities.Book;

@DisplayName("Tests providing the basic functionality of the infrastructure")
public class InfrastructureTest extends JPAHibernateTest {

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
        assertEquals(1, books.size());
    }

    @Test
    @DisplayName("Create a new object and persist it")
    public void testPersist_success() {
        em.getTransaction().begin();
        em.persist(new Book(10, "Unit Test Hibernate/JPA with in memory H2 Database"));
        em.getTransaction().commit();
        List<Book> books = em.createNamedQuery("Book.getAll", Book.class).getResultList();
        assertNotNull(books);
        assertEquals(2, books.size());
    }

    @Test
    @DisplayName("Delete one entry from the database")
    public void testDelete_success() {
        Book book = em.find(Book.class, 1);
        em.getTransaction().begin();
        em.remove(book);
        em.getTransaction().commit();
        List<Book> books = em.createNamedQuery("Book.getAll", Book.class).getResultList();
        assertEquals(0, books.size());
    }

}
