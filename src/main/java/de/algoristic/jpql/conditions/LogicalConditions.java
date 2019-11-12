package de.algoristic.jpql.conditions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.algoristic.jpql.Condition;

public enum LogicalConditions {

    AND("AND"),
    OR("OR");

    private String operator;

    private LogicalConditions(String operator) {
        this.operator = operator;
    }

    public Condition apply(Condition firstCondition, Condition secondCondition) {
        return new LogicalCondition(operator, firstCondition, secondCondition);
    }
    
    public Condition apply(Condition firstCondition, Condition secondCondition, Condition... optionalConditions) {
        if(optionalConditions == null) {
            return this.apply(firstCondition, secondCondition);
        } else {
            List<Condition> conditions = Stream.concat(
                        Stream.of(firstCondition, secondCondition),
                        Stream.of(optionalConditions))
                    .collect(Collectors.toList());
            return new LogicalCondition(operator, conditions);
        }
    }

}
