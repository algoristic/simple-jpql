package de.algoristic.jpql;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Select {

    private static Logger logger = LogManager.getLogger(Select.class);

    private Table table;

    public Select(Table table) {
        super();
        this.table = table;
    }

    public String query() {
        String alias = table.getAlias();
        String name = table.getName();
        String qlString = "SELECT " + alias + " FROM " + name + " " + alias;
        logger.info(qlString);
        return qlString;
    }
    
    @SuppressWarnings("rawtypes")
    public List commit(EntityManager em) {
        return em.createQuery(this.query()).getResultList();
    }
    
    public <T> List<T> commit(EntityManager em, Class<T> resultType) {
        return em.createQuery(this.query(), resultType).getResultList();
    }

    public static Select from(String tableName) {
        RandomStringProvider randomStringProvider = new RandomStringProvider();
        QualifierParser<Table> nameParser = new TableQualifierParser(randomStringProvider);
        Table table = nameParser.parse(tableName);
        return new Select(table);
    }

    public static <T> Select from(Class<T> clazz) {
        String className = clazz.getSimpleName();
        return from(className);
    }

}
