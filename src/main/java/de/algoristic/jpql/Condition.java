package de.algoristic.jpql;

import de.algoristic.jpql.conditions.ConditionPreparator;
import de.algoristic.jpql.conditions.LogicalCondition;
import de.algoristic.jpql.render.Renderer;

public interface Condition {

    Renderer getRenderer();

    public static Condition and(Condition firstCondition, Condition secondCondition) {
        return LogicalCondition.AND.apply(firstCondition, secondCondition);
    }

    public static Condition or(Condition firstCondition, Condition secondCondition) {
        return LogicalCondition.OR.apply(firstCondition, secondCondition);
    }

    public static ConditionPreparator of(String property) {
        return of(Property.of(property));
    }

    public static ConditionPreparator condition(String property) {
        return of(property);
    }

    public static ConditionPreparator of(String tableAlias, String property) {
        return of(Property.of(tableAlias, property));
    }
    
    public static ConditionPreparator condition(String tableAlias, String property) {
        return of(tableAlias, property);
    }

    public static ConditionPreparator of(Class<?> table, String property) {
        return of(Property.of(table, property));
    }
    
    public static ConditionPreparator condition(Class<?> table, String property) {
        return of(table, property);
    }
    
    public static ConditionPreparator of(Property property) {
        return condition(property);
    }
    
    public static ConditionPreparator condition(Property property) {
        return new ConditionPreparator(property);
    }

}
