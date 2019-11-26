package de.algoristic.jpql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.algoristic.jpql.sql.FromClause;
import de.algoristic.jpql.sql.SelectClause;
import de.algoristic.jpql.sql.SelectCommand;

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

    public static Select properties(Property property) {
        List<Property> properties = new ArrayList<>();
        properties.add(property);
        return properties(properties);
    }

    public static Select properties(Property[] properties) {
        List<Property> propertiesList = Arrays.asList(properties);
        return properties(propertiesList);
    }

    public static Select properties(Property property, Property... properties) {
        if(properties == null) {
            return properties(property);
        } else {
            List<Property> propertiesList = Stream.concat(
                    Stream.of(property),
                    Stream.of(properties))
                .collect(Collectors.toList());
            return properties(propertiesList);
        }
    }
    
    public static Select properties(Table table) {
        return properties(table.all());
    }

    public SelectCommand from(Table table) {
        return from(table, new Table[] {});
    }

    public SelectCommand from(Table table, Table... optionalTables) {
        if (optionalTables == null) {
            return from(table);
        } else {
            List<Table> tables = Stream.concat(
                        Stream.of(table),
                        Stream.of(optionalTables))
                    .collect(Collectors.toList());
            return from(tables);
        }
    }
    
    public SelectCommand from(List<Table> tables) {
        FromClause fromClause = new FromClause(tables);
        return new SelectCommand(selectClause, fromClause);
    }

}
