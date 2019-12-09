package de.algoristic.jpql.sql;

public enum AggregateFunctions implements SQLFunction {

    MIN("MIN"),
    MAX("MAX"),
    AVG("AVG"),
    SUM("SUM"),
    COUNT("COUNT");

    private String name;

    private AggregateFunctions(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
