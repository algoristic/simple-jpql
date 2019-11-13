package de.algoristic.jpql;

import de.algoristic.jpql.conditions.ConditionSelector;
import de.algoristic.jpql.conditions.LogicalConditions;
import de.algoristic.jpql.render.PreProcessable;
import de.algoristic.jpql.render.Renderable;

public interface Condition extends Renderable, PreProcessable {

    String getOperator();

    public static Condition and(Condition firstCondition, Condition secondCondition) {
        return LogicalConditions.AND.apply(firstCondition, secondCondition);
    }

    public static Condition and(Condition firstCondition, Condition secondCondition, Condition... optionalConditions) {
        return LogicalConditions.AND.apply(firstCondition, secondCondition, optionalConditions);
    }

    public static Condition or(Condition firstCondition, Condition secondCondition) {
        return LogicalConditions.OR.apply(firstCondition, secondCondition);
    }

    public static Condition or(Condition firstCondition, Condition secondCondition, Condition... optionalConditions) {
        return LogicalConditions.OR.apply(firstCondition, secondCondition, optionalConditions);
    }

    public static ConditionSelector property(String property) {
        return property(Property.of(property));
    }

    public static ConditionSelector property(String tableAlias, String property) {
        return property(Property.of(tableAlias, property));
    }

    public static ConditionSelector property(Class<?> table, String property) {
        return property(Property.of(table, property));
    }

    public static ConditionSelector property(Property property) {
        return new ConditionSelector(property);
    }

}
