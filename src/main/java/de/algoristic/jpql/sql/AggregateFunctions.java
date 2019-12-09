package de.algoristic.jpql.sql;

public enum CommonFunctions implements SQLFunction {

    COUNT("COUNT");

    private String name;

    private CommonFunctions(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
