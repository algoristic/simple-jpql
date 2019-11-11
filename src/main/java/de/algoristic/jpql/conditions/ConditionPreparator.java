package de.algoristic.jpql.conditions;

import de.algoristic.jpql.Condition;
import de.algoristic.jpql.Property;

public class ConditionPreparator {

    private Property affectedProperty;

    public ConditionPreparator(Property property) {
        affectedProperty = property;
    }
    
    public Condition lessThan(Number value) {
        return null;
    }

}
