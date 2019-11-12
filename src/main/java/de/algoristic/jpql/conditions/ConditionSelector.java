package de.algoristic.jpql.conditions;

import de.algoristic.jpql.Condition;
import de.algoristic.jpql.Property;
import de.algoristic.jpql.render.values.NumberWrapper;
import de.algoristic.jpql.render.values.SQLDisplayWrapper;

public class ConditionSelector {

    private Property affectedProperty;

    public ConditionSelector(Property property) {
        affectedProperty = property;
    }

    public Condition equals(Number value) {
        return numberCondition(PropertyConditions.EQUALS, value);
    }

    //TODO: date conditions // string conditions
    public Condition lessThan(Number value) {
        return numberCondition(PropertyConditions.LESS_THAN, value);
    }

    public Condition lessEquals(Number value) {
        return numberCondition(PropertyConditions.LESS_EQUALS, value);
    }

    public Condition greaterThan(Number value) {
        return numberCondition(PropertyConditions.GREATER_THEN, value);
    }

    public Condition greaterEquals(Number value) {
        return numberCondition(PropertyConditions.GREATER_EQUALS, value);
    }

    private Condition numberCondition(PropertyConditions condition, Number value) {
        return applyRenderer(condition, NumberWrapper.getNumberWrapper(value));
    }

    private Condition applyRenderer(PropertyConditions condition, SQLDisplayWrapper wrapper) {
        return condition.apply(affectedProperty, wrapper);
    }

}
