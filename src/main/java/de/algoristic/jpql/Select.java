package de.algoristic.jpql;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Select {
    
    public static Select all = new Select();

    protected SelectClause selectClause;
    protected FromClause fromClause;
    
    private Select() {
        this.selectClause = new SelectClause(this);
    }
    
    protected Select(SelectClause selectClause) {
        this.selectClause = selectClause;
        this.selectClause.setParentOperation(this);
    }
    
    FromClause getFromClause() {
        return fromClause;
    }

    public ExecutableSelect from(String tableName) {
        RandomStringProvider randomStringProvider = new RandomStringProvider();
        QualifierParser<FromClause> nameParser = new FromClauseParser(randomStringProvider);
        FromClause fromClause = nameParser.parse(tableName);
        return new ExecutableSelect(selectClause, fromClause);
    }

    public <T> ExecutableSelect from(Class<T> clazz) {
        return from(clazz, new Class[] {});
    }

    public <T> ExecutableSelect from(Class<T> clazz, Class<?>... optionalClasses) {
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
    
    public ExecutableSelect from(Table table, Table... optionalTables) {
        if(optionalTables == null) {
            return from(table);
        } else {
            List<Table> tables = Stream.of(optionalTables).collect(Collectors.toList());
            tables.add(table);
            FromClause fromClause = new NonJoiningTableSource(tables);
            return new ExecutableSelect(selectClause, fromClause);
        }
    }

    public ExecutableSelect from(Table table) {
        FromClause fromClause = new NonJoiningTableSource(table);
        return new ExecutableSelect(selectClause, fromClause);
    }

}
