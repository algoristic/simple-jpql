package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.BasicJPQLTest;
import infrastructure.entities.Author;
import infrastructure.entities.Book;

@SuppressWarnings("rawtypes")
@DisplayName("Queries resembling: SELECT * FROM <TABLE> WHERE <CONDITION>, [<CONDITIONS>]")
public class SelectWithConditions extends BasicJPQLTest {

    static Logger logger = LogManager.getLogger(SelectWithConditions.class);

    @Test
    @DisplayName("*.where(Condition.of(\"id\").equals(2))")
    void selectSpecificId() {
        Table books = Table.of(Book.class);
        String qlString = Select.properties(books.property("title")).from(books).where(Condition.property("id").equals(2)).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(1, resultList.size());
        assertTrue(resultList.get(0) instanceof String);
    }
    
    @Test
    @DisplayName("*.where(Condition.and(Condition.of(\"id\").greaterThan(2), Condition.of(\"id\").lessThan(5)))")
    void selectIdRange() {
        Table books = Table.of(Book.class);
        String qlString = Select.all.from(books).where(Condition.and(Condition.property("id").greaterThan(2), Condition.property("id").lessThan(5))).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(2, resultList.size());
        assertTrue(resultList.get(0) instanceof Book);
    }
    
    @Test
    @DisplayName("*.where(Condition.of(\"author\").equals(\"Frank Schätzing\"))")
    void selectByTitle() {
        Table books = Table.of(Book.class);
        String qlString = Select.properties(books.property("title")).from(books).where(Condition.property("author").equals("Frank Schätzing")).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(1, resultList.size());
        String result = (String) resultList.get(0);
        assertEquals("The Swarm", result);
    }
    
    @Test
    @DisplayName("*.where(Condition.of(\"author\").like(\"%Lovecraft\"))")
    void selectByLikeOperator() {
        String qlString = Select.all.from(Book.class).where(Condition.property("author").like("%Lovecraft")).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(4, resultList.size());
    }
    
    @Test
    @DisplayName("*.where(Condition.of(\"author\").isNull())")
    void selectWithIsNull() {
        Table books = Table.of(Book.class);
        String qlString = Select.properties(books.property("title")).from(books).where(Condition.property("author").isNull()).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(1, resultList.size());
        assertEquals(resultList.get(0), "Berserk Vol. 4");
    }
    
    @Test
    @DisplayName("*.where(Condition.of(\"id\").in(1, 2, 3))")
    void selectWithListOfIDs() {
        String qlString = Select.all.from(Book.class).where(Condition.property("id").in(1, 2, 3)).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(3, resultList.size());
    }
    
    @Test
    @DisplayName("*.where(Condition.of(\"id\").between(3, 5))")
    void selectWithBetweenValues() {
        String qlString = Select.all.from(Book.class).where(Condition.property("id").between(3, 5)).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(3, resultList.size());
    }
    
    @Test
    @DisplayName("*.where(Condition.or(Condition.of(\"id\").equals(1), Condition.of(\"id\").between(2, 3), Condition.of(\"id\").in(4, 5, 6)))")
    void selectWithChainedOr() {
        String qlString = Select.all.from(Book.class).where(Condition.or(Condition.property("id").equals(1), Condition.property("id").between(2, 3), Condition.property("id").in(4, 5, 6))).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(6, resultList.size());
    }
    
    @Test
    @DisplayName(".where(Condition.property(books.property(\"author\")).like(\"%Lovecraft\"))")
    void test() {
        Table books = Table.of(Book.class);
        String qlString = Select.all.from(books).where(Condition.property(books.property("author")).like("%Lovecraft")).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(4, resultList.size());
    }
    
    @Test
    @DisplayName(".properties(books.all()).from(books, authors).where(books.property(\"author\").equals(authors.property(\"name\")))")
    void selectAllFromOneTableByNaturalJoin() {
        Table books = Table.of(Book.class);
        Table authors = Table.of(Author.class);
        String qlString = Select.properties(books.all()).from(books, authors).where(books.property("author").equals(authors.property("name"))).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(9, resultList.size()); //9 because one author=null
        Object firstResult = resultList.get(0);
        assertTrue(firstResult instanceof Book);
    }

}
