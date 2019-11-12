package de.algoristic.jpql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import infrastructure.BasicJPQLTest;
import infrastructure.entities.Book;

@SuppressWarnings("rawtypes")
public class SelectWithConditions extends BasicJPQLTest {

    static Logger logger = LogManager.getLogger(SelectWithConditions.class);

    @Test
    void selectSpecificId() {
        String qlString = Select.properties("title").from(Book.class).where(Condition.of("id").equals(2)).query();
        List resultList = em.createQuery(qlString).getResultList();
        logger.info(qlString);
        assertEquals(1, resultList.size());
        assertTrue(resultList.get(0) instanceof String);
    }
    
    @Test
    void selectIdRange() {
        String qlString = Select.all.from(Book.class).where(Condition.and(Condition.of("id").greaterThan(2), Condition.of("id").lessThan(5))).query();
        logger.info(qlString);
        List resultList = em.createQuery(qlString).getResultList();
        assertEquals(2, resultList.size());
        assertTrue(resultList.get(0) instanceof Book);
    }

}
