package de.algoristic.jpql.conditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.algoristic.jpql.Condition;
import de.algoristic.jpql.Property;
import de.algoristic.jpql.render.values.BooleanWrapper;
import de.algoristic.jpql.render.values.DualValueWrapper;
import de.algoristic.jpql.render.values.ListWrapper;
import de.algoristic.jpql.render.values.NullWrapper;
import de.algoristic.jpql.render.values.NumberWrapper;
import de.algoristic.jpql.render.values.SQLDisplayWrapper;
import de.algoristic.jpql.render.values.StringWrapper;

public class ConditionSelector {

    private Property affectedProperty;

    public ConditionSelector(Property property) {
        affectedProperty = property;
    }

    public Condition equals(Number value) {
        return numberCondition(PropertyConditions.EQUALS, value);
    }

    public Condition equals(String value) {
        return stringCondition(PropertyConditions.EQUALS, value);
    }

    public Condition equals(Boolean value) {
        return applyWrapper(PropertyConditions.EQUALS, new BooleanWrapper(value));
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

    public Condition like(String value) {
        return stringCondition(PropertyConditions.LIKE, value);
    }

    public Condition between(String firstValue, String secondValue) {
        SQLDisplayWrapper firstValueWrapper = new StringWrapper(firstValue);
        SQLDisplayWrapper secondValueWrapper = new StringWrapper(secondValue);
        return applyDualWrapper(firstValueWrapper, secondValueWrapper);
    }

    public Condition between(Number firstValue, Number secondValue) {
        SQLDisplayWrapper firstValueWrapper = NumberWrapper.getNumberWrapper(firstValue);
        SQLDisplayWrapper secondValueWrapper = NumberWrapper.getNumberWrapper(secondValue);
        return applyDualWrapper(firstValueWrapper, secondValueWrapper);
    }

    public Condition isNull() {
        return applyWrapper(PropertyConditions.IS_NULL, new NullWrapper());
    }
    
    public Condition in(Object... values) {
        if(values == null) {
            return in(new ArrayList<>());
        } else {
            List<Object> list = Stream.of(values).collect(Collectors.toList());
            return in(list);
        }
    }

    public Condition in(List<Object> list) {
        SQLDisplayWrapper listWrapper = ListWrapper.getListWrapper(list);
        return applyListWrapper(listWrapper);
    }

    private Condition numberCondition(PropertyConditions condition, Number value) {
        return applyWrapper(condition, NumberWrapper.getNumberWrapper(value));
    }

    private Condition stringCondition(PropertyConditions condition, String value) {
        return applyWrapper(condition, new StringWrapper(value));
    }
    
    private Condition applyListWrapper(SQLDisplayWrapper listWrapper) {
        return applyWrapper(PropertyConditions.IN, listWrapper);
    }

    private Condition applyDualWrapper(SQLDisplayWrapper firstValue, SQLDisplayWrapper secondValue) {
        SQLDisplayWrapper dualWrapper = new DualValueWrapper<SQLDisplayWrapper>(firstValue, secondValue);
        return applyWrapper(PropertyConditions.BETWEEN, dualWrapper);
    }

    private Condition applyWrapper(PropertyConditions condition, SQLDisplayWrapper wrapper) {
        return condition.apply(affectedProperty, wrapper);
    }

}
