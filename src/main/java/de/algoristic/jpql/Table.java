package de.algoristic.jpql;

public class Table {

    private String name;
    private String alias;

    Table(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    void setAlias(String alias) {
        this.alias = alias;
    }

    public Table as(String alias) {
        this.alias = alias;
        return this;
    }

    public static Table of(String name) {
        return of(name, null);
    }

    public static Table of(String name, String alias) {
        return new Table(name, alias);
    }

    public static Table of(Class<?> clazz) {
        return of(clazz, null);
    }

    public static Table of(Class<?> clazz, String alias) {
        String name = TableParser.determineTableName(clazz);
        return of(name, alias);
    }

}
