package de.algoristic.jpql.sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import de.algoristic.jpql.Table;
import de.algoristic.jpql.render.FromClauseRenderer;
import de.algoristic.jpql.render.Renderer;

public class FromClause implements OperationalClause<Table> {

    private List<Table> tables;

    public FromClause(Table table) {
        this(new ArrayList<>());
        tables.add(table);
    }

    public FromClause(List<Table> tables) {
        this.tables = tables;
    }

    List<Table> getTables() {
        return tables;
    }

    @Override
    public Iterator<Table> iterator() {
        return tables.iterator();
    }

    @Override
    public Renderer getRenderer() {
        return new FromClauseRenderer(this);
    }

    @Override
    public Stream<Table> stream() {
        return tables.stream();
    }

}
