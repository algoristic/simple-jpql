package de.algoristic.jpql;

import org.junit.jupiter.api.Test;

import infrastructure.entities.Book;

public class SelectWithConditions {
    
    @Test
    void firstTestWithCondition() {
        String qlString = Select
                             .all
                          .from(
                             Book.class
                          ).where(
                             Condition.of("id").lessThan(4)
                          ).query();
    }

}
