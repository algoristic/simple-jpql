package de.algoristic.jpql.sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @Override
    public Iterator<Table> iterator() {
        return tables.iterator();
    }

    @Override
    public Renderer getRenderer() {
        return new FromClauseRenderer(this);
    }

}