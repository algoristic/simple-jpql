//package de.algoristic.jpql;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//@DisplayName("Queries resembling:\nSELECT a.prop_1, a.prop_2, ... FROM table a")
//public class SelectProperties {
//    
//    static Logger logger = LogManager.getLogger(SelectProperties.class);
//    
//    @Test
//    void first() {
//        String qlString = Select.properties("title, author").from("Book").query();
//        logger.info(qlString);
//    }
//    
//}
