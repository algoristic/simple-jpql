package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import infrastructure.SimpleJPQLTest;

@DisplayName("Testing different methods to create queries like:\nSELECT * FROM <TABLE>")
public class SelectAllTest extends SimpleJPQLTest {
    
    @Test
    void selectByClassName() {
        List results = em.createQuery(Select.from("Book").execute()).getResultList();
        assertEquals(results.size(), 1);
    }
    
    @Test
    void selectByClassNameWithAlias() {
        List results = em.createQuery(Select.from("Book b").execute()).getResultList();
        assertEquals(results.size(), 1);
    }

}
