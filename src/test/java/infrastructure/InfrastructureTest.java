package infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import infrastructure.entities.Book;

public class InfrastructureTest extends JPAHibernateTest {

    @Test
    public void testGetObjectById_success() {
        Book book = em.find(Book.class, 1);
        assertNotNull(book);
    }

    @Test
    public void testGetAll_success() {
        List<Book> books = em.createNamedQuery("Book.getAll", Book.class).getResultList();
        assertEquals(1, books.size());
    }

    @Test
    public void testPersist_success() {
        em.getTransaction().begin();
        em.persist(new Book(10, "Unit Test Hibernate/JPA with in memory H2 Database"));
        em.getTransaction().commit();

        List<Book> books = em.createNamedQuery("Book.getAll", Book.class).getResultList();

        assertNotNull(books);
        assertEquals(2, books.size());
    }

    @Test
    public void testDelete_success() {
        Book book = em.find(Book.class, 1);

        em.getTransaction().begin();
        em.remove(book);
        em.getTransaction().commit();

        List<Book> books = em.createNamedQuery("Book.getAll", Book.class).getResultList();

        assertEquals(0, books.size());
    }

}
