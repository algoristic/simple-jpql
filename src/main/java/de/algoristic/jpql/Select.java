package de.algoristic.jpql;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Select {

    private SelectClause selectClause;
    private FromClause fromClause;

    public Select(FromClause fromClause) {
        this.selectClause = new SelectClause(this);
        this.fromClause = fromClause;
    }

    public String query() {
        StatementPreparator.prepareQuery(this);
        StringBuilder sb = new StringBuilder();
        sb.append(selectClause.render());
        sb.append(fromClause.render());
        String qlString = sb.toString();
        return qlString;
    }

    FromClause getFromClause() {
        return fromClause;
    }

    public static Select from(String tableName) {
        RandomStringProvider randomStringProvider = new RandomStringProvider();
        QualifierParser<FromClause> nameParser = new FromClauseParser(randomStringProvider);
        FromClause table = nameParser.parse(tableName);
        return new Select(table);
    }

    public static <T> Select from(Class<T> clazz) {
        return from(clazz, new Class[] {});
    }

    public static <T> Select from(Class<T> clazz, Class<?>... optionalClasses) {
        StringBuilder tables = new StringBuilder();
        String firstTableName = TableParser.determineTableName(clazz);
        tables.append(firstTableName);
        if (optionalClasses != null) {
            for(Class<?> optionalClass: optionalClasses) {
                String tableName = TableParser.determineTableName(optionalClass);
                tables.append(", ").append(tableName);
            }
        }
        return from(tables.toString());
    }
    
    public static Select from(Table table, Table... optionalTables) {
        if(optionalTables == null) {
            return from(table);
        } else {
            List<Table> tables = Stream.of(optionalTables).collect(Collectors.toList());
            tables.add(table);
            FromClause fromClause = new NonJoiningTableSource(tables);
            return new Select(fromClause);
        }
    }

    public static Select from(Table table) {
        FromClause fromClause = new NonJoiningTableSource(table);
        return new Select(fromClause);
    }

}
