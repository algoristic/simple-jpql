package de.algoristic.jpql.conditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import de.algoristic.jpql.Condition;
import de.algoristic.jpql.render.LogicalConditionRenderer;
import de.algoristic.jpql.render.Renderer;
import de.algoristic.jpql.sql.SharedQueryInformation;

public class LogicalCondition implements Condition {

    private String operator;
    private List<Condition> conditions;

    private LogicalCondition() {
        this.conditions = new ArrayList<>();
    }

    public LogicalCondition(String operator, Condition leftHandArgument, Condition rightHandArgument) {
        this();
        this.operator = operator;
        conditions.add(leftHandArgument);
        conditions.add(rightHandArgument);
    }

    protected LogicalCondition(String operator, List<Condition> conditions) {
        this.operator = operator;
        this.conditions = conditions;
    }

    @Override
    public Renderer getRenderer() {
        return new LogicalConditionRenderer(this);
    }

    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public void preProcess(SharedQueryInformation queryInfo) {
        conditions.forEach(condition -> condition.preProcess(queryInfo));
    }

    public Stream<Condition> getConditions() {
        return conditions.stream();
    }

}
