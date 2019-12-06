package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.Query;

import org.junit.jupiter.api.Test;

import infrastructure.BasicJPQLTest;
import infrastructure.entities.Author;

public class FunctionTest extends BasicJPQLTest {

    Table authors = Table.of(Author.class);

    @Test
    void countTest() {
        Query query = Select.properties(Function.count(authors.all())).from(authors).query(em);
        List result = query.getResultList();
        Object count = result.get(0);
        assertEquals(3L, count);
    }

}
