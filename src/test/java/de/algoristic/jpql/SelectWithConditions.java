package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.BasicJPQLTest;
import infrastructure.entities.Book;

@SuppressWarnings("rawtypes")
@DisplayName("Queries resembling: SELECT * FROM <TABLE> WHERE <CONDITION>, [<CONDITIONS>]")
public class SelectWithConditions extends BasicJPQLTest {

    Table books = Table.of(Book.class);

    @Test
    @DisplayName("*.where(Condition.of(\"id\").equals(2))")
    void selectSpecificId() {
        String qlString = Select.properties(books.property("title")).from(books).where(books.property("id").isEquals(2)).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(1, resultList.size());
        assertTrue(resultList.get(0) instanceof String);
    }

    @Test
    @DisplayName("*.where(Condition.and(Condition.of(\"id\").greaterThan(2), Condition.of(\"id\").lessThan(5)))")
    void selectIdRange() {
        String qlString = Select.all.from(books).where(Condition.and(books.property("id").greaterThan(2), books.property("id").lessThan(5))).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(2, resultList.size());
        assertTrue(resultList.get(0) instanceof Book);
    }

    @Test
    @DisplayName("*.where(Condition.of(\"author\").isNull())")
    void selectWithIsNull() {
        String qlString = Select.properties(books.property("title")).from(books).where(books.property("year").isNull()).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(1, resultList.size());
        assertEquals(resultList.get(0), "Berserk Vol. 4");
    }

    @Test
    @DisplayName("*.where(Condition.of(\"id\").in(1, 2, 3))")
    void selectWithListOfIDs() {
        String qlString = Select.all.from(books).where(books.property("id").in(1, 2, 3)).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(3, resultList.size());
    }

    @Test
    @DisplayName("*.where(Condition.of(\"id\").between(3, 5))")
    void selectWithBetweenValues() {
        String qlString = Select.all.from(books).where(books.property("id").between(3, 5)).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(3, resultList.size());
    }

    @Test
    @DisplayName("*.where(Condition.or(Condition.of(\"id\").equals(1), Condition.of(\"id\").between(2, 3), Condition.of(\"id\").in(4, 5, 6)))")
    void selectWithChainedOr() {
        String qlString = Select.all.from(books).where(Condition.or(books.property("id").isEquals(1), books.property("id").between(2, 3), books.property("id").in(4, 5, 6))).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(6, resultList.size());
    }

    @Test
    @DisplayName(".where(Condition.property(books.property(\"author\")).like(\"%Lovecraft\"))")
    void test() {
        String qlString = Select.all.from(books).where(books.property("title").like("Berserk%")).query();
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(5, resultList.size());
    }

}
