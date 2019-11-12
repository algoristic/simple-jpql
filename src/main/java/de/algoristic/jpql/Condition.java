package de.algoristic.jpql;

import de.algoristic.jpql.conditions.ConditionSelector;
import de.algoristic.jpql.conditions.LogicalConditions;
import de.algoristic.jpql.render.Renderable;
import de.algoristic.jpql.sql.FromClause;

public interface Condition extends Renderable {

    String getOperator();

    void completeReferences(FromClause fromClause);

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

    public static ConditionSelector of(String property) {
        return of(Property.of(property));
    }

    public static ConditionSelector condition(String property) {
        return of(property);
    }

    public static ConditionSelector of(String tableAlias, String property) {
        return of(Property.of(tableAlias, property));
    }

    public static ConditionSelector condition(String tableAlias, String property) {
        return of(tableAlias, property);
    }

    public static ConditionSelector of(Class<?> table, String property) {
        return of(Property.of(table, property));
    }

    public static ConditionSelector condition(Class<?> table, String property) {
        return of(table, property);
    }

    public static ConditionSelector of(Property property) {
        return condition(property);
    }

    public static ConditionSelector condition(Property property) {
        return new ConditionSelector(property);
    }

}
