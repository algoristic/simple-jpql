package de.algoristic.jpql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.algoristic.jpql.parse.FromClauseParser;
import de.algoristic.jpql.parse.PropertyParser;
import de.algoristic.jpql.parse.QualifierParser;
import de.algoristic.jpql.parse.SelectClauseParser;
import de.algoristic.jpql.parse.TableParser;
import de.algoristic.jpql.sql.FromClause;
import de.algoristic.jpql.sql.SelectClause;
import de.algoristic.jpql.sql.SelectCommand;
import de.algoristic.jpql.util.RandomStringProvider;

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

    public FromClause getFromClause() {
        return fromClause;
    }

    public SelectClause getSelectClause() {
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
        List<Property> propertiesList = Stream.concat(
                    Stream.of(property),
                    Stream.of(properties))
                .collect(Collectors.toList());
        return properties(propertiesList);
    }

    public static Select properties(String properties) {
        QualifierParser<SelectClause> selectClauseParser = new SelectClauseParser();
        SelectClause selectClause = selectClauseParser.parse(properties);
        return new Select(selectClause);
    }

    public static Select properties(String property, String... optionalProperties) {
        if (optionalProperties == null) {
            return properties(property);
        }
        String[] properties = Stream.concat(
                    Stream.of(property),
                    Stream.of(optionalProperties))
                .toArray(String[]::new);
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

    public SelectCommand from(String tableName) {
        RandomStringProvider randomStringProvider = new RandomStringProvider();
        QualifierParser<FromClause> nameParser = new FromClauseParser(randomStringProvider);
        FromClause fromClause = nameParser.parse(tableName);
        return new SelectCommand(selectClause, fromClause);
    }

    public SelectCommand from(String table, String... optionalTables) {
        if(optionalTables == null) {
            return from(table);
        } else {
            String tables = Stream.concat(
                        Stream.of(table),
                        Stream.of(optionalTables))
                    .collect(Collectors.joining(", "));
            return from(tables);
        }
    }

    public <T> SelectCommand from(Class<T> clazz) {
        return from(clazz, new Class[] {});
    }

    public <T> SelectCommand from(Class<T> clazz, Class<?>... optionalClasses) {
        if (optionalClasses == null) {
            return from(clazz);
        } else {
            String tables = Stream.concat(
                        Stream.of(clazz),
                        Stream.of(optionalClasses))
                    .map(TableParser::determineTableName)
                    .collect(Collectors.joining(", "));
            return from(tables);
        }
    }

    public SelectCommand from(Table table) {
        FromClause fromClause = new FromClause(table);
        return new SelectCommand(selectClause, fromClause);
    }

    public SelectCommand from(Table table, Table... optionalTables) {
        if (optionalTables == null) {
            return from(table);
        } else {
            List<Table> tables = Stream.concat(
                        Stream.of(table),
                        Stream.of(optionalTables))
                    .collect(Collectors.toList());
            FromClause fromClause = new FromClause(tables);
            return new SelectCommand(selectClause, fromClause);
        }
    }

}
