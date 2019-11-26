package de.algoristic.jpql;

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

}
