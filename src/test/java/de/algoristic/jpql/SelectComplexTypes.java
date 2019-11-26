package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.Query;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.BasicJPQLTest;
import infrastructure.entities.Author;
import infrastructure.entities.Book;

@SuppressWarnings("rawtypes")
@DisplayName("Queries resembling: SELECT <ANY> FROM <TABLE>, [<TABLES>] WHERE <PROPERTY_1 = PROPERTY_2>")
public class SelectComplexTypes extends BasicJPQLTest {
    
    Table authors = Table.of(Author.class);
    Table books = Table.of(Book.class);
    
    @Test
    @DisplayName("*.where(books.property(\"author\").isEquals(author))")
    void selectWithPropertyAsReference() {
        Author author = em.find(Author.class, 2);
        Query query = Select.all.from(books).where(books.property("author").isEquals(author)).query(em);
        List resultList = query.getResultList();
        assertEquals(5, resultList.size());
    }
    
    @Test
    @DisplayName("*.where(Condition.and(books.property(\"author\").isEquals(authors), authors.property(\"name\").like(\"%Miura\")))")
    void selectWithTableAsReference() {
        Query query = em.createQuery(Select.properties(books)
                .from(authors, books)
                .where(Condition.and(
                        books.property("author").isEquals(authors),
                        authors.property("name").like("%Miura")))
                .query());
        List resultList = query.getResultList();
        assertEquals(5, resultList.size());
    }

}
