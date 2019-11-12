package de.algoristic.jpql.conditions;

import de.algoristic.jpql.Condition;
import de.algoristic.jpql.Property;
import de.algoristic.jpql.render.values.SQLDisplayWrapper;

public enum PropertyConditions {

    EQUALS("="),
    LESS_THAN("<"),
    LESS_EQUALS("<="),
    GREATER_THEN(">"),
    GREATER_EQUALS(">=");

    private String operator;

    private PropertyConditions(String operator) {
        this.operator = operator;
    }

    public Condition apply(Property property, SQLDisplayWrapper wrapper) {
        return new PropertyCondition(operator, property, wrapper);
    }

}
