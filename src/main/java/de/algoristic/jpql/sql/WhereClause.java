package de.algoristic.jpql.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

import de.algoristic.jpql.Condition;
import de.algoristic.jpql.render.Renderer;
import de.algoristic.jpql.render.WhereClauseRenderer;

public class WhereClause implements OptionalClause<Condition> {

    public static WhereClause noConditions = new WhereClause(null);

    private Condition condition;

    WhereClause(Condition condition) {
        this.condition = condition;
    }

    @Override
    public Iterator<Condition> iterator() {
        return Optional.ofNullable(condition).map(Arrays::asList).orElseGet(ArrayList::new).iterator();
    }

    @Override
    public Renderer getRenderer() {
        return new WhereClauseRenderer(this);
    }

    @Override
    public Stream<Condition> stream() {
        return Optional.ofNullable(condition).map(Stream::of).orElseGet(Stream::empty);
    }

    @Override
    public boolean containsStatements() {
        return condition != null;
    }

}
