package de.algoristic.jpql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Select {
    
    private static Logger logger = LogManager.getLogger(Select.class);

    private Table table;

    public Select(Table table) {
        super();
        this.table = table;
    }

    public String execute() {
        String alias = table.getAlias();
        String name = table.getName();
        String qlString = "SELECT " + alias + " FROM " + name + " " + alias;
        logger.info(qlString);
        return qlString;
    }
    
    public static Select from(String tableName) {
        RandomStringProvider randomStringProvider = new RandomStringProvider();
        QualifierParser<Table> nameParser = new TableQualifierParser(randomStringProvider);
        Table table = nameParser.parse(tableName);
        return new Select(table);
    }
    
    

}
