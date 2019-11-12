package de.algoristic.jpql.render;

import java.util.stream.Collectors;

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
        sb.append("(");
        String operator = logicalCondition.getOperator();
        String subConditions = logicalCondition.getConditions()
                    .map(Condition::getRenderer)
                    .map(Renderer::render)
                .collect(Collectors.joining(" " + operator + " "));
        sb.append(subConditions);
        sb.append(")").append(" ");
        return sb.toString();
    }

}
