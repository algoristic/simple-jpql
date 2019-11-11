package de.algoristic.jpql.sql;

import java.util.Arrays;
import java.util.Iterator;

import de.algoristic.jpql.Condition;
import de.algoristic.jpql.render.Renderer;
import de.algoristic.jpql.render.WhereClauseRenderer;

public class WhereClause implements OperationalClause<Condition> {

    public static WhereClause noConditions = new WhereClause(null);

    private Condition condition;

    WhereClause(Condition condition) {
        this.condition = condition;
    }

    Condition getCondition() {
        return condition;
    }

    boolean hasConditions() {
        return (condition != null);
    }

    @Override
    public Iterator<Condition> iterator() {
        return Arrays.asList(condition).iterator();
    }

    @Override
    public Renderer getRenderer() {
        return new WhereClauseRenderer(this);
    }

}
