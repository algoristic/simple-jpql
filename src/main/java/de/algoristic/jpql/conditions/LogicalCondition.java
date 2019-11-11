package de.algoristic.jpql.conditions;

import de.algoristic.jpql.Condition;
import de.algoristic.jpql.render.LogicalConditionRenderer;
import de.algoristic.jpql.render.Renderer;

public enum LogicalCondition implements Condition {

    AND("AND"),
    OR("OR");

    private String operator;

    private Condition leftHandArgument;
    private Condition rightHandArgument;

    private LogicalCondition(String operator) {
        this.operator = operator;
    }

    public Condition getLeftHandArgument() {
        return leftHandArgument;
    }

    public Condition getRightHandArgument() {
        return rightHandArgument;
    }
    
    @Override
    public Renderer getRenderer() {
        return new LogicalConditionRenderer(this);
    }

    String getOperator() {
        return operator;
    }

    public Condition apply(Condition firstCondition, Condition secondCondition) {
        this.leftHandArgument = firstCondition;
        this.rightHandArgument = secondCondition;
        return this;
    }

}
