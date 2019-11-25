package de.algoristic.jpql.conditions;

import de.algoristic.jpql.Condition;
import de.algoristic.jpql.Property;
import de.algoristic.jpql.render.wrapper.SQLDisplayWrapper;

public enum PropertyConditions {

    EQUALS("="),
    LESS_THAN("<"),
    LESS_EQUALS("<="),
    GREATER_THEN(">"),
    GREATER_EQUALS(">="),
    LIKE("LIKE"),
    BETWEEN("BETWEEN"),
    IS_NULL("IS"),
    IN("IN");

    private String operator;

    private PropertyConditions(String operator) {
        this.operator = operator;
    }

    public Condition apply(Property property, SQLDisplayWrapper wrapper) {
        return new PropertyCondition(operator, property, wrapper);
    }

}
