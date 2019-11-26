package de.algoristic.jpql.sql;

public class ComplexParameter {

    private String name;
    private Object value;

    public ComplexParameter(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    String getName() {
        return name;
    }

    Object getValue() {
        return value;
    }

}
