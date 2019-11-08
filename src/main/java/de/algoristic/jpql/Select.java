package de.algoristic.jpql;

import java.util.ArrayList;
import java.util.Arrays;
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

    SelectClause getSelectClause() {
        return selectClause;
    }

    public static Select properties(List<Property> properties) {
        SelectClause selectClause = new SelectClause(properties);
        return new Select(selectClause);
    }

    public static Select properties(Property[] properties) {
        List<Property> propertiesList = Arrays.asList(properties);
        return properties(propertiesList);
    }

    public static Select properties(Property property, Property... properties) {
        List<Property> propertiesList = Stream.concat(Stream.of(property), Stream.of(properties)).collect(Collectors.toList());
        return properties(propertiesList);
    }

    public static Select properties(String properties) {
        QualifierParser<SelectClause> selectClauseParser = new SelectClauseParser();
        SelectClause selectClause = selectClauseParser.parse(properties);
        return new Select(selectClause);
    }

    public static Select properties(String property, String... optionalProperties) {
        int count = 1;
        boolean hasOptionalProperties = false;
        if (optionalProperties != null) {
            hasOptionalProperties = true;
            count += optionalProperties.length;
        }
        String[] properties = new String[count];
        properties[0] = property;
        if (hasOptionalProperties) {
            for (int i = 1; i <= optionalProperties.length; i++) {
                properties[i] = optionalProperties[i - 1];
            }
        }
        return properties(properties);
    }

    public static Select properties(String[] properties) {
        QualifierParser<Property> propertyParser = new PropertyParser();
        List<Property> propertiesList = new ArrayList<>();
        for (String propertyString : properties) {
            Property property = propertyParser.parse(propertyString);
            propertiesList.add(property);
        }
        SelectClause selectClause = new SelectClause(propertiesList);
        return new Select(selectClause);
    }

    public ExecutableSelect from(String tableName) {
        RandomStringProvider randomStringProvider = new RandomStringProvider();
        QualifierParser<FromClause> nameParser = new FromClauseParser(randomStringProvider);
        FromClause fromClause = nameParser.parse(tableName);
        return new ExecutableSelect(selectClause, fromClause);
    }
    
    public ExecutableSelect from(String table, String... optionalTables) {
        StringBuilder tables = new StringBuilder();
        tables.append(table);
        if(optionalTables != null) {
            for(String tableName: optionalTables) {
                tables.append(", ").append(tableName);
            }
        }
        return from(tables.toString());
    }

    public <T> ExecutableSelect from(Class<T> clazz) {
        return from(clazz, new Class[] {});
    }

    public <T> ExecutableSelect from(Class<T> clazz, Class<?>... optionalClasses) {
        StringBuilder tables = new StringBuilder();
        String firstTableName = TableParser.determineTableName(clazz);
        tables.append(firstTableName);
        if (optionalClasses != null) {
            for (Class<?> optionalClass : optionalClasses) {
                String tableName = TableParser.determineTableName(optionalClass);
                tables.append(", ").append(tableName);
            }
        }
        return from(tables.toString());
    }

    public ExecutableSelect from(Table table, Table... optionalTables) {
        if (optionalTables == null) {
            return from(table);
        } else {
            List<Table> tables = Stream.concat(Stream.of(table), Stream.of(optionalTables)).collect(Collectors.toList());
            FromClause fromClause = new NonJoiningTableSource(tables);
            return new ExecutableSelect(selectClause, fromClause);
        }
    }

    public ExecutableSelect from(Table table) {
        FromClause fromClause = new NonJoiningTableSource(table);
        return new ExecutableSelect(selectClause, fromClause);
    }

}
