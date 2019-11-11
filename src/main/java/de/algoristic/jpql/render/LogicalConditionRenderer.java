package de.algoristic.jpql.render;

import de.algoristic.jpql.Condition;
import de.algoristic.jpql.conditions.LogicalCondition;

public class LogicalConditionRenderer extends ConditionRenderer {

    private LogicalCondition logicalCondition;

    public LogicalConditionRenderer(LogicalCondition logicalCondition) {
        this.logicalCondition = logicalCondition;
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        Condition leftHand = logicalCondition.getLeftHandArgument();
        Renderer leftArgRenderer = leftHand.getRenderer();
        sb.append(leftArgRenderer.render());
        Condition rightHand = logicalCondition.getRightHandArgument();
        Renderer rightArgRenderer = rightHand.getRenderer();
        sb.append(rightArgRenderer.render());
        return sb.toString();
    }

}
